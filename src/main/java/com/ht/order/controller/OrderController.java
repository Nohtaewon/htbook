package com.ht.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ht.member.service.MemberService;
import com.ht.order.domain.OrderPageDTO;
import com.ht.order.service.OrderService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/order/{member_id}")
	public String orderPageGET(@PathVariable("member_id")String member_id, OrderPageDTO opd, Model model ) {
		log.info("member_id : " + member_id);
		log.info("orders : "+ opd.getOrders() );
		
		model.addAttribute("orderList", orderService.getGoodInfo(opd.getOrders()));
		model.addAttribute("memberInfo", memberService.getMemberInfo(member_id));
		
		return "/order";
	}
}
