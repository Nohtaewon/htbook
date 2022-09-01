package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.common.Criteria;

public interface SearchService {

	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
}
