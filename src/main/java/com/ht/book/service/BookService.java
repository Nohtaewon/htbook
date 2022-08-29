package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateVO;

public interface BookService {
	
	// 상품 등록
	public void bookEnroll(BookVO book);
	
	// 카테고리 리스트
	public List<CateVO> cateList();
}
