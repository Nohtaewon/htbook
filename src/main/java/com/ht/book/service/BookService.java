package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.AttachImageVO;
import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateVO;
import com.ht.common.Criteria;
import com.ht.order.domain.OrderDTO;

public interface BookService {
	
	// 상품 등록
	public void bookEnroll(BookVO book);
	
	// 카테고리 리스트
	public List<CateVO> cateList();
	
	// 상품 리스트
	public List<BookVO> goodsGetList(Criteria cri);
	
	// 상품 총 개수
	public int goodsGetTotal(Criteria cri);
	
	// 상품 조회 페이지
	public BookVO goodsGetDetail(int bookId);
	
	// 상품 수정
	public int goodsModify(BookVO vo);
	
	// 상품 정보 삭제
	public int goodsDelete(int bookId);
	
	// 지정 상품 이미지 정보 얻기
	public List<AttachImageVO> getAttachInfo(int bookId);
	
	
}
