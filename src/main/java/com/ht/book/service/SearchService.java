package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateVO;
import com.ht.common.Criteria;

public interface SearchService {

	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 국내 카테고리 리스트
	public List<CateVO> getCateCode1();
	
	// 국외 카테고리 테스트
	public List<CateVO> getCateCode2();
}
