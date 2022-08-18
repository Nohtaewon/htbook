package com.ht.author.controller;


import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ht.author.domain.AuthorVO;
import com.ht.author.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Controller
@RequestMapping("/author/*")

public class AuthorController {
	
	@Setter(onMethod_= {@Autowired})
	private AuthorService authorService;
	
	// 작가 등록 페이지 접속
	@GetMapping("/authorEnroll")
	public void authorEnrollGet() throws Exception{
	}
	
	// 작가 관리 페이지 접속
	@GetMapping("/authorManage")
	public void authorManageGet() throws Exception{
	}
	
	// 작가 등록
	@PostMapping("/authorEnroll")
	public String authorEnrollPost(AuthorVO author, RedirectAttributes rttr) throws Exception{
		authorService.authorEnroll(author); // 작가 등록 쿼리 수행
		// 작가 등록에 성공여부를 체크하기 위해 데이터를 일회성으로 사용할 수 있도록 addFlashAttribute 사용
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());
		return "redirect:/author/authorManage";
	}
}
