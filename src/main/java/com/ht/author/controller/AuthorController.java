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
}
