package com.ht.book.service;

import java.util.List;

import com.ht.book.domain.AttachImageVO;

public interface AttachService {

	// 이미지 데이터 반환
	public List<AttachImageVO> getAttachList(int bookId);
}
