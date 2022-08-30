package com.ht.cart.service;

import com.ht.cart.domain.CartDTO;

public interface CartService {

	//장바구니 추가
	public int addCart(CartDTO cart);
	
}
