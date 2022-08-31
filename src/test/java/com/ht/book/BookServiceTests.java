package com.ht.book;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.book.domain.AttachImageVO;
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
		
		// 상품 정보
		book.setBookName("service 테스트");
		book.setAuthorId(27);
		book.setPubleYear("2022-08-26");
		book.setPublisher("출판사");
		book.setCateCode("202001");
		book.setBookPrice(20000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("책 소개");
		book.setBookContents("책 목차");
		
		
		// 이미지 정보
		List<AttachImageVO> imageList = new ArrayList<AttachImageVO>(); 
		
		AttachImageVO image1 = new AttachImageVO();
		AttachImageVO image2 = new AttachImageVO();
		
		image1.setFileName("test Image 1");
		image1.setUploadPath("test image 1");
		image1.setUuid("test1111");
		
		image2.setFileName("test Image 2");
		image2.setUploadPath("test image 2");
		image2.setUuid("test2222");
		
		imageList.add(image1);
		imageList.add(image2);
		
		book.setImageList(imageList); 
		
		// bookEnroll() 메서드 호출
		service.bookEnroll(book);
		
		System.out.println("등록된 VO : " + book);
	}
}
