package com.ht.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.book.service.SearchService;

import lombok.extern.log4j.Log4j;



@Log4j
@Controller
public class MainController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public void mainPageGet(Model model) {
		log.info("메인 페이지 진입");
		
		model.addAttribute("cate1", searchService.getCateCode1());
		model.addAttribute("cate2", searchService.getCateCode2());
	}
}
