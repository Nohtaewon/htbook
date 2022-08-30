package com.ht.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/goodsDetail")
	public void goodsGetInfoGET(int bookId, Criteria cri, Model model) throws JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		// 카테고리 리스트 데이터
		model.addAttribute("cateList", mapper.writeValueAsString(bookService.cateList()));
		
		// 목록 페이지 조건 정보
		model.addAttribute("cri", cri);
		
		// 조회 페이지 정보
		model.addAttribute("goodsInfo", bookService.goodsGetDetail(bookId));
	}
	
}
