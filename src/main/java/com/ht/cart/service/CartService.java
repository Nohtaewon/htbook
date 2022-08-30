package com.ht.cart.service;

import java.util.List;

import com.ht.cart.domain.CartDTO;

public interface CartService {

	// 장바구니 추가
	public int addCart(CartDTO cart);
	
	// 장바구니 정보 리스트
	public List<CartDTO> getCartList(String member_id);
	
	// 장바구니 수량 수정
	public int modifyCount(CartDTO cart);
	
	//장바구니 삭제
	public int deleteCart(int cart_id);
	
}
