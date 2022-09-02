package com.ht.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.AttachImageVO;
import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateVO;
import com.ht.common.Criteria;
import com.ht.mapper.AttachMapper;
import com.ht.mapper.BookMapper;
import com.ht.mapper.SearchMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	// 상품 검색
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = searchMapper.getAuthorIdList(cri.getKeyword());
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}
		
		List<BookVO> list = searchMapper.getGoodsList(cri);
		
		list.forEach(book->{
			int bookId = book.getBookId();
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			book.setImageList(imageList);
		});
			
		return list;
	}

	@Override
	public int goodsGetTotal(Criteria cri) {

		log.info("goodsGetTotal().......");
		
		return searchMapper.goodsGetTotal(cri);
	}

	@Override
	public List<CateVO> getCateCode1() {

		return searchMapper.getCateCode1();
	}

	@Override
	public List<CateVO> getCateCode2() {

		return searchMapper.getCateCode2();
	}

}
