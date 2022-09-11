package com.ht.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.AttachImageVO;
import com.ht.book.domain.BookVO;
import com.ht.book.domain.CateFilterDTO;
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
	
	@Autowired
	private BookMapper bookMapper;
	
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
	// 검색결과 카테고리 필터 정보
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {
		// 반환할 데이터가 담길 상자 역할 객체 선언 및 초기화
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();
		
		String[] typeArr = cri.getType().split("");
		String [] authorArr;
		
		// Criteria type 변수에 A가 포함된 경우 authorId의 정보가 필요
		// 따라서 getAuthorIdList 호출하여 authorId 값들을 반환받고 Criteria 객체에 추가
		for(String type : typeArr) {
			if(type.equals("A")){
				authorArr = searchMapper.getAuthorIdList(cri.getKeyword());
				// authorArr요소에 authorId가 없는 경우
				if(authorArr.length == 0) {
					return filterInfoList;
				}
				cri.setAuthorArr(authorArr);
			}
		}
		// 필터 정보의 대상이 될 카테고리를 반환해주는 getCateList 호출
		String[] cateList = searchMapper.getCateList(cri);
		
		// 임시로 Criteria 객체에 있는 'cateCode' 값을 저장해 줄 String 타입의 변수
		String tempCateCode = cri.getCateCode();
		// cateList 요소에 있는 카테고리 코드를 Criteria에 cateCode 변수에저장
		// Criteria를 파라미터로 하는 getCateInfo() 호출
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = searchMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}
		cri.setCateCode(tempCateCode);
		return filterInfoList;
	}

	@Override
	public BookVO getGoodsInfo(int bookId) {
		BookVO goodsInfo = searchMapper.getGoodsInfo(bookId);
		goodsInfo.setImageList(bookMapper.getAttachInfo(bookId));
		
		return goodsInfo;
	}

}
