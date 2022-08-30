package com.ht.cart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.cart.domain.CartDTO;
import com.ht.cart.service.CartService;
import com.ht.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CartServiceTests {
	@Autowired
	private CartService service;
	
	//등록 테스트
	@Test
	public void addCartTest() {
		//given
			String member_id = "admin";
			int bookId = 1;
			int count = 5;
			
			CartDTO dto = new CartDTO();
			dto.setMember_id(member_id);
			dto.setBookId(bookId);
			dto.setBook_count(count);
			
			int result = service.addCart(dto);
			
			System.out.println(" result : " + result);
			// 테스트 확인사항
			
			/*
			 * DB에 등록된 데이터 값 : 2 반환
			 * DB에 등록되지 않은 값 : 1반환 and 데이터 등록
			 * SQL 에러 (존재하지 않는 member_id값) : 0반환
			 * */
			
		
	}
}
