package com.ht.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ht.book.domain.BookVO;
import com.ht.book.service.SearchService;
import com.ht.common.Criteria;
import com.ht.common.PageDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	// 상품 검색 
	@GetMapping("/search")
	public String searchGoodsGET(Criteria cri, Model model) {
		
		log.info("cri : " + cri);
		
		List<BookVO> list = searchService.getGoodsList(cri);
		log.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			log.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");
			
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, searchService.goodsGetTotal(cri)));
		
		String[] typeArr = cri.getType().split("");
		
		for(String s : typeArr) {
			if(s.equals("T") || s.equals("A")) {
				model.addAttribute("filter_info", searchService.getCateInfoList(cri));		
			}
		}
		
		return "search";
		
	}
}
