package com.ht.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.cart.domain.CartDTO;
import com.ht.cart.service.CartService;
import com.ht.member.domain.MemberVO;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	/*
	 * 1: 등록 성공
	 * 2: 등록된 데이터 존재
	 * 5: 로그인 필요
	 */
	@PostMapping("/cart/add")
	@ResponseBody // 데이터반환
	public String addCartPOST(CartDTO cart, HttpServletRequest request) {
		//로그인 체크
		HttpSession session = request.getSession();
		
		MemberVO loginId = (MemberVO)session.getAttribute("member");
		
		if(loginId == null) {
			return "5"; // 로그인이 안되었을 경우 5
		}
		
		// 카트 등록
		
		int result = cartService.addCart(cart);
		
		return result + ""; // int -> String으로 변환하기 위해 빈 문자열 연산
		
		
	}
}
