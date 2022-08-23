package com.ht.member.service;

import com.ht.member.domain.MemberVO;


public interface MemberService {
	//회원가입
	public void memberJoin(MemberVO member) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(String member_id) throws Exception;
}
