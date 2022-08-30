package com.ht.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ht.config.RootConfig;
import com.ht.mapper.MemberMapper;
import com.ht.member.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
public class MemberMapperTests {

		@Autowired
		private MemberMapper memeberMapper;
		
//		//회원가입 테스트
//		@Test
//		public void memberJoin() throws Exception {
//			MemberVO member = new MemberVO();
//			
//			member.setMember_id("test_id");
//			member.setMember_pw("test_pw");
//			member.setMember_name("test_name");
//			member.setMember_mail("test_mail");
//			member.setMember_addr1("test_addr1");
//			member.setMember_addr2("test_addr2");
//			member.setMember_addr3("test_addr3");
//			
//			memeberMapper.memberJoin(member);
//		}
		
//		//아이디 중복검사
//		@Test
//		public void memberIdChk()throws Exception{
//			String id = "testid"; // 존재하는 아이디
//			String id2 = "test123"; // 존재하지 않는 아이디
//			memeberMapper.idCheck(id);
//			memeberMapper.idCheck(id2);
//			log.info("존재하는 아이디:(1)= " + memeberMapper.idCheck(id) );
//			log.info("존재하지않는 아이디: (0)= " + memeberMapper.idCheck(id2) );
//		}
		
		// 로그인 쿼리 테스트
		@Test
		public void memberLogin() throws Exception{
			
			MemberVO member = new MemberVO();
			
			// 올바르지 않은 아이디 비번 
//			member.setMember_id("test");
//			member.setMember_pw("1234");
			
			
			// 올바른 아이디 비번
			member.setMember_id("test");
			member.setMember_pw("123");
			
			memeberMapper.memberLogin(member);
			log.info("결과값" +memeberMapper.memberLogin(member));
		}
}
