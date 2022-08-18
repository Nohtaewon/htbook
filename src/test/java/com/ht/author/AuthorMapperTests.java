package com.ht.author;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.author.domain.AuthorVO;
import com.ht.config.RootConfig;
import com.ht.mapper.AuthorMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= RootConfig.class)
public class AuthorMapperTests {

	@Autowired
	private AuthorMapper mapper;
	
	@Test
	public void authorEnroll() throws Exception{
		
		AuthorVO author = new AuthorVO();
		
		author.setNationId("01");
		author.setAuthorName("테스트");
		author.setAuthorIntro("테스트 소개");
		
		mapper.authorEnroll(author);
	}
}
