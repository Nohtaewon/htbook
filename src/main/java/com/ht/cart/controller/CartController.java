package com.ht.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.cart.domain.CartDTO;
import com.ht.cart.service.CartService;
import com.ht.member.domain.MemberVO;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	// 아이디체크 & 장바구니 등록
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
	
	// 카트 리스트
	@GetMapping("/cart/{member_id}")
	public String cartPageGET(@PathVariable("member_id") String member_id, Model model) {
		
		model.addAttribute("cartInfo", cartService.getCartList(member_id));
		
		return "/cart";
	}
	
	// 장바구니 수량 수정
	@PostMapping("/cart/update")
	public String updateCartPOST(CartDTO cart) {
		cartService.modifyCount(cart);	
		return "redirect:/cart/" + cart.getMember_id();
	}
	
	// 장바구니 수량 삭제
	@PostMapping("/cart/delete")
	public String deleteCartPOST(CartDTO cart) {
		cartService.deleteCart(cart.getCart_id());		
		return "redirect:/cart/" + cart.getMember_id();
	}
}
