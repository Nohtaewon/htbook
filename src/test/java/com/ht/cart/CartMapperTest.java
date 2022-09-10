package com.ht.cart; 

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.cart.domain.CartDTO;
import com.ht.config.RootConfig;
import com.ht.mapper.CartMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class CartMapperTest {
	
	@Autowired
	private CartMapper mapper;
	
//	@Test
//	public void addCart() {
//		String member_id = "admin";
//		int bookId = 1;
//		int count = 2;
//		
//		CartDTO cart = new CartDTO();
//		cart.setMember_id(member_id);
//		cart.setBookId(bookId);
//		cart.setBook_count(count);
//		
//		int result = 0;
//		result = mapper.addCart(cart);
//		System.out.println("결과 : " + result);	
//	}
	
//	// 카트 삭제
//	@Test
//	public void deleteCartTest() {
//		int cart_id = 1;
//		
//		mapper.deleteCart(cart_id);
//	}
	// 카트목록
	@Test
	public void getCartTest() {
		String member_id = "admin";
		
		List<CartDTO> list = mapper.getCart(member_id);
		for(CartDTO cart : list) {
			
			System.out.println(cart);
			cart.initSaleTotal();
			System.out.println("init cart : " + cart);
		}
	}
	
	// 카트 확인
	@Test
	public void checkCartTest() {
		String member_id ="admin";
		int bookId = 1;
		
		CartDTO cart = new CartDTO();
		cart.setMember_id(member_id);
		cart.setBookId(bookId);
		
		CartDTO resultCart = mapper.checkCart(cart);
		System.out.println("결과 : " + resultCart);
	}
	
}
