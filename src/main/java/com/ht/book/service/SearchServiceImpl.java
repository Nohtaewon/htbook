package com.ht.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.BookVO;
import com.ht.common.Criteria;
import com.ht.mapper.BookMapper;
import com.ht.mapper.SearchMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
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
		
		return searchMapper.getGoodsList(cri);
	}

	@Override
	public int goodsGetTotal(Criteria cri) {

		log.info("goodsGetTotal().......");
		
		return searchMapper.goodsGetTotal(cri);
	}

}
