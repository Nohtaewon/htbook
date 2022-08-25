package com.ht.member.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ht.member.domain.MemberVO;
import com.ht.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/member")
public class MemberController {
	@Autowired
	private MemberService memberservice;
	
	
	
	
	//회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		log.info("회원가입 페이지 호출");
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberVO member) throws Exception{
		log.info("join Post 호출");
		
		memberservice.memberJoin(member);
		
		return "redirect:/main";
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void joinGET() {
		log.info("로그인 페이지 호출");
	}
	
	//아이디 중복 검사
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChk(String member_id) throws Exception{
		//log.info("memberIdChk() 메소드 호출 : 아이디 중복검사");
		
		int result =  memberservice.idCheck(member_id);
		log.info("결과값 = "+ result);
		
		if(result !=0) {
			return "fali"; //중복 아이디존재
		} else {
			
			return "success"; // 중복 아이디 x
		}
	}
	
	//로그인
	@RequestMapping(value = "login", method =RequestMethod.POST )
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
//		log.info("loginPOST 호출");
//		log.info("전달된 데이터 : " + member);
		
		HttpSession session = request.getSession();
		MemberVO loginid = memberservice.memberLogin(member);
		
		if(loginid == null) {
			int result = 0;
			rttr.addFlashAttribute("result", result);
			log.info("로그인실패");
			return "redirect:/member/login";
		}
		log.info("로그인성공");
		session.setAttribute("member", loginid);
		return "redirect:/main";
	}

}
