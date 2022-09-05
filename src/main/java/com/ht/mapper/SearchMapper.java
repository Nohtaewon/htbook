package com.ht.mapper;

import java.util.List;

import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateFilterDTO;
import com.ht.book.domain.CateVO;
import com.ht.common.Criteria;

public interface SearchMapper {

	// 상품 검색
	public List<BookVO> getGoodsList(Criteria cri);
	
	// 상품 총 갯수
	public int goodsGetTotal(Criteria cri);
	
	// 작가 id 리스트 요청
	public String[] getAuthorIdList(String keyword);
	
	// 국내 카테고리 테스트
	public List<CateVO> getCateCode1();
	
	// 국외 카테고리 테스트
	public List<CateVO> getCateCode2();
	
	// 검색 대상 카테고리 리스트
	public String[] getCateList(Criteria cri);
	
	// 카테고리 정보(+검색대상 갯수)
	public CateFilterDTO getCateInfo(Criteria cri);
}
