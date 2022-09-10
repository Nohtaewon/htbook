package com.ht.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.book.domain.AttachImageVO;
import com.ht.mapper.AttachMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AttachServiceImpl implements AttachService{

	@Autowired
	private AttachMapper attachMapper;
	
	// 이미지 데이터 반환
	@Override
	public List<AttachImageVO> getAttachList(int bookId) {
		
		log.info("getAttachList............");
		
		return attachMapper.getAttachList(bookId);
	}

}
