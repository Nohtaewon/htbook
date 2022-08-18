package com.ht.author.service;


import com.ht.author.domain.AuthorVO;

public interface AuthorService {

	// 작가 등록
	public void authorEnroll(AuthorVO author) throws Exception;
}
