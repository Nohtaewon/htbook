package com.ht.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;



@Log4j
@Controller
public class BookController {
	
	
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public void mainPageGet() {
		log.info("메인 페이지 진입");
		
		
	}
}
