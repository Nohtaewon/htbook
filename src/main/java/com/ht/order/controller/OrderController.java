package com.ht.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ht.order.domain.OrderPageDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class OrderController {
	@GetMapping("/order/{member_id}")
	public void orderPageGET(@PathVariable("member_id")String member_id, OrderPageDTO opd, Model model ) {
		log.info("member_id : " + member_id);
		log.info("orders : "+ opd.getOrders() );
	}
}
