package com.ht.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		log.info("bookEnroll.........");
		
		bookMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		// 여러개 이미지 처리 
		/*
		// 일반적 for문
        for(int i = 0; i < book.getImageList().size(); i++) {
			
		}
		
        // 향상된 for문
		for(AttachImageVO attach : book.getImageList()) {
			
		}*/
        //람다식 활용한 for문
		book.getImageList().forEach(attach ->{
			// 이미지 등록
			attach.setBookId(book.getBookId());
			bookMapper.imageEnroll(attach);
		});
		
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

	@Override
	public BookVO goodsGetDetail(int bookId) {
		return bookMapper.goodsGetDetail(bookId);
	}

	@Transactional
	@Override
	public int goodsModify(BookVO vo) {
		
		int result = bookMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
					
			bookMapper.deleteImageAll(vo.getBookId());

			vo.getImageList().forEach(attach -> {
				
				attach.setBookId(vo.getBookId());
				bookMapper.imageEnroll(attach);
				
			});
		}
		
		return result;
	}

	@Override
	public int goodsDelete(int bookId) {
		return bookMapper.goodsDelete(bookId);
	}

}
