package com.ht.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.author.domain.AuthorVO;
import com.ht.mapper.AuthorMapper;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorMapper authorMapper;
	
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {

		authorMapper.authorEnroll(author);
		
	}

}
