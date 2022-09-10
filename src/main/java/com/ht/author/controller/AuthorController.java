package com.ht.author.controller;

import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ht.author.domain.AuthorVO;
import com.ht.author.service.AuthorService;
import com.ht.common.Criteria;
import com.ht.common.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/author")
@Log4j
@AllArgsConstructor
public class AuthorController {
	

	private AuthorService authorService;
	
	// 작가 등록 페이지 접속
	@GetMapping("/authorEnroll")
	public void authorEnrollGet() throws Exception{
		log.info("작가 등록 페이지 접속");
	}
	
	// 작가 관리 페이지 접속
	@GetMapping("/authorManage")
	public void authorManageGet(Criteria cri, Model model) throws Exception{
		log.info("작가 관리 페이지 접속");
		// 작가 목록 출력 데이터
		List<AuthorVO> list = authorService.authorGetList(cri);
		
		// 작가 존재 유무
		if(!list.isEmpty()) {
			model.addAttribute("list", list);	// 작가가 존재할 경우			
		}else {
			model.addAttribute("listCheck", "empty");	// 작가가 존재하지 않을 경우
		}
		
		// 페이지 이동 인터페이스 데이터
		int total = authorService.authorGetTotal(cri);
		
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	// 작가 등록
	@PostMapping("/authorEnroll")
	public String authorEnrollPost(AuthorVO author, RedirectAttributes rttr) throws Exception{
		authorService.authorEnroll(author); // 작가 등록 쿼리 수행
		// 작가 등록에 성공여부를 체크하기 위해 데이터를 일회성으로 사용할 수 있도록 addFlashAttribute 사용
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());
		return "redirect:/author/authorManage";
	}
	
	// 작가 상세페이지
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {
		// 작가 관리 페이지 정보
		model.addAttribute("cri", cri);
		
		// 선택 작가 정보
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	}
	
	// 작가 정보 수정
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/author/authorManage";
	}
	
	// 작가 검색 팝업창
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception{
		log.info("authorPopGET...........");
		
		cri.setAmount(5);
		// 게시물 목록 출력 데이터
		List<AuthorVO> list = authorService.authorGetList(cri);
		
		// 작가 존재 유무
		if(!list.isEmpty()) {
			model.addAttribute("list", list);	// 작가가 존재할 경우			
		}else {
			model.addAttribute("listCheck", "empty");	// 작가가 존재하지 않을 경우
		}
		
		// 페이지 이동 인터페이스 데이터
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));
		
	}
	
	// 작가 정보 삭제
	// 우리가 삭제하고자 하는 데이터는 작가 테이블(author)의 데이터입니다. 
	// 문제는 외래 키 조건으로 인해 작가 테이블을 참조(reference) 하고 있는  상품 테이블(book)이 있다는 점입니다. 
	// 참조되지 않고 있는 행을 지운다면 문제가 없지만 만약 참조되고 있는 행을 지우려고 시도를 한다면 '무결성 제약 조건을 위반' 
	// 한다는 경고와 함께 SQLIntegrityConstraintViolationException 예외가 발생합니다
	// 따라서 try catch 문을 사용하여 참조되지 않는 행을 지울땐 삭제를 수행하고 '작가 목록' 페이지로 1을 전성하도록 하고, 
	// 예외가 발생한 상황에는 '작가 목록' 페이지로 2를 전송하도록 작성하였습니다. 
	
	@PostMapping("/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes rttr) {
		
		int result = 0;		
		try {		
			result = authorService.authorDelete(authorId);			
		} catch (Exception e) {			
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);		
			return "redirect:/author/authorManage";			
		}
			
		rttr.addFlashAttribute("delete_result", result);	
		return "redirect:/author/authorManage";
		
	}
	
}
