package com.ht.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.config.RootConfig;
import com.ht.member.domain.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class MemberMapperTests {

		@Autowired
		private MemberMapper memeberMapper;
		
		//회원가입 테스트
		@Test
		public void memberJoin() throws Exception {
			MemberVO member = new MemberVO();
			
			member.setMember_id("test_id");
			member.setMember_pw("test_pw");
			member.setMember_name("test_name");
			member.setMember_mail("test_mail");
			member.setMember_addr1("test_addr1");
			member.setMember_addr2("test_addr2");
			member.setMember_addr3("test_addr3");
			
			memeberMapper.memberJoin(member);
		}
}
