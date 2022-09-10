package com.ht.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.mapper.AttachMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.ht.config.RootConfig.class})
@Log4j
public class AttachMapperTests {

	@Autowired
	private AttachMapper attachMapper;
	
	// 이미지 정보 반환
	@Test
	public void getAttachListTests() {
		int bookId = 732;
		System.out.println("이미지 정보:"+attachMapper.getAttachList(bookId));
		
	}
}
