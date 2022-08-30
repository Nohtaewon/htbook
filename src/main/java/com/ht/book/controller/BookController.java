package com.ht.book.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ht.book.domain.BookVO;
import com.ht.book.service.BookService;
import com.ht.common.Criteria;
import com.ht.common.PageDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book")
@Log4j
public class BookController {

	@Autowired
	private BookService bookService;
	
	// 상품 등록 페이지 접속
	@GetMapping("/goodsEnroll")
	public void goodsEnrollGet(Model model) throws Exception{
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
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		}else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		// 페이지 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
	}
	
	// 상품 조회 페이지
	@GetMapping({"/goodsDetail", "/goodsModify"})
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException{
		
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
		int result = bookService.goodsDelete(bookId);
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/book/goodsManage";
	}
	
	
	// 첨부파일업로드
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public void uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		log.info("uploadAjaxActionPOST");
		String uploadFolder = "c:\\storage";
		
		// SimpleDateForamt은 Date 클래스를 통해 얻은 오늘의 날짜를 지정된 형식의 문자열 데이터로 생성하기 위해서 사용을 합니다. 
		// SimpleDateFormat에 대해서 간략히 설명을 하면 날짜 데이터를 지정된 문자열 형식으로 변환하거나 날짜 문자열 데이터를 날짜 데이터로 변환할 수 있게 해주는 클래스입니다.
		// 날짜 폴더 경로
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘의 날짜 데이터
		Date date = new Date();
		// 오늘의 날짜 데이터 값을 가지고 있는 date 변수를 "yyyy-MM-dd" 형식의 문자열로 변환을 해주기 위해서 SimpleDateFormat의 format 메서드를 호출
		String str = sdf.format(date);
		String datePath = str.replace("-", File.separator);
		
		// 폴더 생성
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			// 파일 이름 
			String uploadFileName = multipartFile.getOriginalFilename();			
			
			// uuid 적용 파일 이름 
			String uuid = UUID.randomUUID().toString();
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			// 파일 위치, 파일 이름을 합친 File 객체 
			File saveFile = new File(uploadPath, uploadFileName);
			
			// 파일 저장 
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

	}
	
}
