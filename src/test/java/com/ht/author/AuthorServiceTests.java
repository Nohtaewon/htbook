package com.ht.author;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.author.domain.AuthorVO;
import com.ht.author.service.AuthorService;
import com.ht.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= RootConfig.class)
@Log4j
public class AuthorServiceTests {

	@Autowired
	private AuthorService service;
	/*
	@Test
	public void authorEnrollTest() throws Exception{
		
		AuthorVO author = new AuthorVO();
		
		author.setNationId("01");
		author.setAuthorName("서비스 테스트");
		author.setAuthorIntro("서비스 소개");
		
		service.authorEnroll(author);
	}*/
	
	@Test
	public void authorGetDetailTest() throws Exception{
		int authorId=20;
		log.info(service.authorGetDetail(authorId));
	}
}
