package com.ht.member.service;

import com.ht.member.domain.MemberVO;


public interface MemberService {
	//회원가입
	public void memberJoin(MemberVO member) throws Exception;
}
