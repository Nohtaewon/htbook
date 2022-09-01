package com.ht.mapper;

import com.ht.member.domain.MemberVO;

public interface MemberMapper {

		//회원가입
		public void memberJoin(MemberVO member);
		
		// 아이디 중복 검사
		public int idCheck(String member_id);
		
		// 로그인
		public MemberVO memberLogin(MemberVO member);
		
		// 주문자 주소 정보
		public MemberVO getMemberInfo(String member_id);
}
