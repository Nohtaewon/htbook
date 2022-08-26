package com.ht.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.book.domain.BookVO;
import com.ht.book.service.BookService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.ht.config.RootConfig.class})
@Log4j
public class BookServiceTests {

	@Autowired
	private BookService service;
	
	// 상품 등록
	@Test
	public void bookEnrollTest() throws Exception{
		BookVO book = new BookVO();
		
		
		book.setBookName("service 테스트");
		book.setAuthorId(123);
		book.setPubleYear("2022-08-26");
		book.setPublisher("출판사");
		book.setCateCode("0231");
		book.setBookPrice(20000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("책 소개");
		book.setBookContents("책 목차");
		
		service.bookEnroll(book);
	}
}