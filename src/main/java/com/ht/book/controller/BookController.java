package com.ht.book.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ht.book.domain.AttachImageVO;
import com.ht.book.domain.BookVO;
import com.ht.book.service.BookService;
import com.ht.common.Criteria;
import com.ht.common.PageDTO;
import com.ht.member.domain.MemberVO;
import com.ht.member.service.MemberService;
import com.ht.order.domain.OrderCancelDTO;
import com.ht.order.domain.OrderDTO;
import com.ht.order.service.OrderService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/book")
@Log4j
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	// 관리자 메인 페이지 이동
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception {
		log.info("관리자 페이지 이동");
	}

	// 이미지 파일 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) {
		log.info("deleteFile" + fileName);

		File file = null;

		try {
			// 썸네일 파일 삭제
			file = new File("c:\\storage\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();

			// 원본 파일 삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			log.info("originFileName : " + originFileName);

			file = new File(originFileName);
			file.delete();
		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);

		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// 이미지 업로드
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName) {
		File file = new File("c:\\storage\\" + fileName);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();

			header.add("Content-type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 상품 등록 페이지 접속
	@GetMapping("/goodsEnroll")
	public void goodsEnrollGet(Model model) throws Exception {
		log.info("상품 등록 페이지 접속");

		ObjectMapper objm = new ObjectMapper();

		List list = bookService.cateList();

		// 자바 객체를 JSON형식의 데이터로 변환
		String cateList = objm.writeValueAsString(list);

		model.addAttribute("cateList", cateList);

		log.info("변경 전 ......" + list);
		log.info("변경 후 ......" + cateList);
	}

	// 상품 등록
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {

		log.info("goodsEnroll....." + book);
		bookService.bookEnroll(book);
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		return "redirect:/book/goodsManage";
	}

	// 상품 관리
	@GetMapping("/goodsManage")
	public void goodsManageGet(Criteria cri, Model model) throws Exception {
		// 상품 리스트
		List list = bookService.goodsGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}

		// 페이지 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
	}

	// 상품 조회 페이지
	@GetMapping({ "/goodsDetail", "/goodsModify" })
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		// 카테고리 리스트 데이터
		model.addAttribute("cateList", mapper.writeValueAsString(bookService.cateList()));

		// 목록 페이지 조건 정보
		model.addAttribute("cri", cri);

		// 조회 페이지 정보
		model.addAttribute("goodsInfo", bookService.goodsGetDetail(bookId));
	}

	// 상품 정보 수정
	@PostMapping("/goodsModify")
	public String goodsModifyPOST(BookVO vo, RedirectAttributes rttr) {
		int result = bookService.goodsModify(vo);
		rttr.addFlashAttribute("modify_result", result);
		return "redirect:/book/goodsManage";
	}

	// 상품 정보 삭제
	@PostMapping("/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes rttr) {

		List<AttachImageVO> fileList = bookService.getAttachInfo(bookId);

		if (fileList != null) {

			List<Path> pathList = new ArrayList();

			fileList.forEach(vo -> {

				// 원본 이미지
				Path path = Paths.get("C:\\storage", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);

				// 섬네일 이미지
				path = Paths.get("C:\\storage", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);

			});

			pathList.forEach(path -> {
				path.toFile().delete();
			});

		}

		int result = bookService.goodsDelete(bookId);
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/book/goodsManage";
	}

	// 첨부파일업로드
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {

		log.info("uploadAjaxActionPOST");

		// 이미지 파일 체크
		for (MultipartFile multipartFile : uploadFile) {
			// 전달받은 파일
			File checkfile = new File(multipartFile.getOriginalFilename());
			// MIME TYPE을 저장할 String 타입의 type 변수를 선언, 초기화
			String type = null;
			// Files의 probeContetype() 메서드를 호출하여 반환하는 MIME TYPE 데이터를 type 변수에 대입
			try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE:" + type);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!type.startsWith("image")) {
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}

		}

		String uploadFolder = "c:\\storage";

		// SimpleDateForamt은 Date 클래스를 통해 얻은 오늘의 날짜를 지정된 형식의 문자열 데이터로 생성하기 위해서 사용을 합니다.
		// SimpleDateFormat에 대해서 간략히 설명을 하면 날짜 데이터를 지정된 문자열 형식으로 변환하거나 날짜 문자열 데이터를 날짜
		// 데이터로 변환할 수 있게 해주는 클래스입니다.
		// 날짜 폴더 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘의 날짜 데이터
		Date date = new Date();
		// 오늘의 날짜 데이터 값을 가지고 있는 date 변수를 "yyyy-MM-dd" 형식의 문자열로 변환을 해주기 위해서
		// SimpleDateFormat의 format 메서드를 호출
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);

		// 폴더 생성
		File uploadPath = new File(uploadFolder, datePath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		// 이미지 정보 담는 객체
		List<AttachImageVO> list = new ArrayList<AttachImageVO>();

		for (MultipartFile multipartFile : uploadFile) {

			// 이미지 정보 객체
			AttachImageVO vo = new AttachImageVO();

			// 파일 이름
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);

			// uuid 적용 파일 이름
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);

			uploadFileName = uuid + "_" + uploadFileName;

			// 파일 위치, 파일 이름을 합친 File 객체
			File saveFile = new File(uploadPath, uploadFileName);

			// 파일 저장
			try {
				multipartFile.transferTo(saveFile);

				/*
				 * 방법 1 // 썸네일 이미지 파일의 File객체가 필요 썸네일 생성 File thumbnailFile = new
				 * File(uploadPath, "s_" + uploadFileName); // 원본 이미지 파일을 read() 메서드를 호출하여
				 * BufferedImage 타입으로 변경 // 참조 변수를 선언하여 해당 변수에 대입 BufferedImage bo_image =
				 * ImageIO.read(saveFile); // 비율 double ratio = 3; // 넓이 높이 int width = (int)
				 * (bo_image.getWidth() / ratio); int height = (int) (bo_image.getHeight() /
				 * ratio); // 생성자를 사용하여 썸네일 이미지인 BufferedImage 객체를 생성, 참조 변수에 대입(크기를 지정하여 흰색 도화지
				 * 만듬) BufferedImage bt_image = new BufferedImage(width, height,
				 * BufferedImage.TYPE_3BYTE_BGR); // BufferedImage 객체에서 createGraphics() 메서드 호출을
				 * 총해 Graphics2D 객체 생성 참조 변수 대입 // 도화지에 그림을 그릴 수 있도록 하는 과정 Graphics2D graphic =
				 * bt_image.createGraphics(); // drawImage 메서드를 이용하여 원본이미지를 썸네일에 지정한 크기로 변경(도화지에
				 * 이미지를 그리는 과정) graphic.drawImage(bo_image, 0, 0,width,height, null); // 파일로 저장
				 * ImageIO.write(bt_image, "jpg", thumbnailFile);
				 */

				// 방법2(라이브러리 사용)
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);

				BufferedImage bo_image = ImageIO.read(saveFile);

				// 비율
				double ratio = 3;
				// 넓이 높이
				int width = (int) (bo_image.getWidth() / ratio);
				int height = (int) (bo_image.getHeight() / ratio);

				Thumbnails.of(saveFile).size(160, 160).toFile(thumbnailFile);

			} catch (Exception e) {
				e.printStackTrace();
			}

			list.add(vo);
		} // for

		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);

		return result;

	}
	
	// 주문 현황 페이지
	@GetMapping("/orderList")
	public String orderListGET(Criteria cri, Model model) {
		List<OrderDTO> list = orderService.getOrderList(cri);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			model.addAttribute("pageMaker", new PageDTO(cri, orderService.getOrderTotal(cri)));
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		return "/book/orderList";
	}
	
	// 주문삭제
		@PostMapping("/orderCancle")
		public String orderCanclePOST(OrderCancelDTO dto,HttpServletRequest request) {
			orderService.orderCancle(dto);
			MemberVO member = new MemberVO();
			member.setMember_id(dto.getMember_id());
			
			HttpSession session = request.getSession();
			
			try {
				MemberVO memberLogin = memberService.memberLogin(member);
				memberLogin.setMember_pw("");
				session.setAttribute("member", memberLogin);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return "redirect:/book/orderList?keyword=" + dto.getKeyword() + "&amount=" + dto.getAmount() + "&pageNum=" + dto.getPageNum();
		}

}
