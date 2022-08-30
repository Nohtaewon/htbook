package com.ht.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.cart.domain.CartDTO;
import com.ht.mapper.CartMapper;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;
	
	// 장바구니 추가
	@Override
	public int addCart(CartDTO cart) {
		// 장바구니 데이터 체크
		CartDTO checkCart = cartMapper.checkCart(cart);
		if(checkCart != null) {
			return 2; // 존재할 경우 2를 반환
		}
		try {			
			return cartMapper.addCart(cart);		
		} catch (Exception e) {		
			return 0;
		}
	}
	
	// 장바구니 리스트
	@Override
	public List<CartDTO> getCartList(String member_id) {
		List<CartDTO> cart = cartMapper.getCart(member_id);
		
		for(CartDTO dto : cart) {
			dto.initSaleTotal();
			
			//이미지 정보 얻는 코드 추가 
		}
		
		return cart;
	}

	@Override
	public int modifyCount(CartDTO cart) {
		return cartMapper.modifyCount(cart);
	}

	@Override
	public int deleteCart(int cart_id) {	
		
		return cartMapper.deleteCart(cart_id);
	}

}
