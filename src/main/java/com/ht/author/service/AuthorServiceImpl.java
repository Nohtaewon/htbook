package com.ht.author.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.author.domain.AuthorVO;
import com.ht.common.Criteria;
import com.ht.mapper.AuthorMapper;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class AuthorServiceImpl implements AuthorService{
	

	@Autowired
	AuthorMapper authorMapper;
	
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {

		authorMapper.authorEnroll(author);
		
	}

	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		log.info(cri);
		return authorMapper.authorGetList(cri);
	}

	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		log.info(cri);
		return authorMapper.authorGetTotal(cri);
	}

}
