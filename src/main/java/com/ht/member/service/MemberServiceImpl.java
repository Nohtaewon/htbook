package com.ht.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.mapper.MemberMapper;
import com.ht.member.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		memberMapper.memberJoin(member);
		
	}

	@Override
	public int idCheck(String member_id) {
			
		return memberMapper.idCheck(member_id);
		
	}

	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		
		return memberMapper.memberLogin(member);
	}

	@Override
	public MemberVO getMemberInfo(String member_id) {

		return memberMapper.getMemberInfo(member_id);
	}

}
