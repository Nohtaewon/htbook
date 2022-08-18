package com.ht.author;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.author.domain.AuthorVO;
import com.ht.author.service.AuthorService;
import com.ht.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= RootConfig.class)
public class AuthorServiceTests {

	@Autowired
	private AuthorService service;
	
	@Test
	public void authorEnrollTest() throws Exception{
		
		AuthorVO author = new AuthorVO();
		
		author.setNationId("01");
		author.setAuthorName("서비스 테스트");
		author.setAuthorIntro("서비스 소개");
		
		service.authorEnroll(author);
	}
}
