package com.ht.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.book.domain.BookVO;
import com.ht.book.service.SearchService;
import com.ht.common.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.ht.config.RootConfig.class})
@Log4j
public class SearchServiceTests {

	@Autowired
	SearchService service;
	
	@Test
	public void getCateInfoListTest1() {
		Criteria cri = new Criteria();
	
		String type = "TC";
		//String keyword = "테스트";
		String keyword = "없음";	
		String cateCode="103002";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}
	
	@Test
	public void getCateInfoListTest2() {
		Criteria cri = new Criteria();
	
		String type = "AC";
		String keyword = "유홍준";	
		//String keyword = "머스크";	
		String cateCode = "103002";

		cri.setType(type);
		cri.setKeyword(keyword);
		cri.setCateCode(cateCode);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}	

	@Test
	public void getCateInfoListTest3() {
		Criteria cri = new Criteria();
	
		String type = "T";
		String keyword = "테스트";
		//String keyword = "없음";	
		

		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}	
	
	@Test
	public void getCateInfoListTest4() {
		Criteria cri = new Criteria();
	
		String type = "AC";
		//String keyword = "유홍준";	
		String keyword = "머스크";	
		

		cri.setType(type);
		cri.setKeyword(keyword);
		
		System.out.println("List<CateFilterDTO> : " + service.getCateInfoList(cri));
		
	}
	
	/*상품 상세 정보*/
	@Test
	public void getGoodsInfoTest() {
		
		int bookId = 743;
		
		BookVO goodsInfo = service.getGoodsInfo(bookId);
		
		System.out.println("==결과==");
		System.out.println("전체 : " + goodsInfo);
		System.out.println("bookId : " + goodsInfo.getBookId() );
		System.out.println("이미지 정보 : " + goodsInfo.getImageList().isEmpty());
		
		
	}
}
