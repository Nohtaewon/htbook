package com.ht.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.BookVO;
import com.ht.mapper.BookMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	// 상품 등록
	@Override
	public void bookEnroll(BookVO book) {
		log.info("bookEnroll.........");
		
		bookMapper.bookEnroll(book);
		
	}

}