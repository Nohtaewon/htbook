package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateFilterDTO;
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
	
	// 검색결과 카테고리 필터 정보
	public List<CateFilterDTO> getCateInfoList(Criteria cri);

	// 상품 정보
	public BookVO getGoodsInfo(int bookId);

}
