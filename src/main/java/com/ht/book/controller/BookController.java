package com.ht.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ht.book.domain.BookVO;
import com.ht.book.service.BookService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book")
@Log4j
public class BookController {

	@Autowired
	private BookService bookService;
	
	// 상품 등록 페이지 접속
	@GetMapping("/goodsEnroll")
	public void goodsEnrollGet() throws Exception{
		log.info("상품 등록 페이지 접속");
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
	public void goodsManageGet() {
		
	}
}
