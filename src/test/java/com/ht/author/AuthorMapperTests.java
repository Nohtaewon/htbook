package com.ht.author;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.author.domain.AuthorVO;
import com.ht.common.Criteria;
import com.ht.config.RootConfig;
import com.ht.mapper.AuthorMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {com.ht.config.RootConfig.class})
@Log4j
public class AuthorMapperTests {

	@Autowired
	private AuthorMapper mapper;
	/*
	@Test
	public void authorEnroll() throws Exception{
		
		AuthorVO author = new AuthorVO();
		
		author.setNationId("01");
		author.setAuthorName("테스트");
		author.setAuthorIntro("테스트 소개");
		
		mapper.authorEnroll(author);
	}
	
	// 작가 목록 테스트
	@Test
	public void authorGetListTest() throws Exception{
		Criteria cri = new Criteria(1, 10);
		cri.setKeyword("t");
		List<AuthorVO> list = mapper.authorGetList(cri);
		log.info(list);
		for(int i = 0; i<list.size(); i++) {
			System.out.println("list"+i+"..........."+list.get(i));
		}
	}
	
	@Test
	public void authorGetTotalTest() throws Exception{
		
		Criteria cri = new Criteria();
		cri.setKeyword("test");
		int total = mapper.authorGetTotal(cri);
		System.out.println("total...."+total);
	}
	 */
	/*
	@Test
	public void authorGetDetailTest() {
		int authorId = 30;
		AuthorVO author = mapper.authorGetDetail(authorId);
		System.out.println("author........" + author);
	}*/
	
	@Test
	public void authorModifyTest() {
		AuthorVO author = new AuthorVO();
		
		author.setAuthorId(1);
		System.out.println("수정 전..." + mapper.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("수정");
		author.setNationId("01");
		author.setAuthorIntro("소개 수정 하였습니다");
		
		mapper.authorModify(author);
		System.out.println("수정 후..." + mapper.authorGetDetail(author.getAuthorId()));
	}
	
	
	
	
	
	
	
	
	
}
