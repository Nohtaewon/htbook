package com.ht.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateVO;
import com.ht.common.Criteria;
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

	@Override
	public List<CateVO> cateList() {
		log.info("cateList.........");
		return bookMapper.cateList();
	}

	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		
		return bookMapper.goodsGetList(cri);
	}

	@Override
	public int goodsGetTotal(Criteria cri) {
		
		return bookMapper.goodsGetTotal(cri);
	}

}
