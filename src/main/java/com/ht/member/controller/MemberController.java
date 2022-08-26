package com.ht.member.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	
	
	
	//회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void loginGET() {
		log.info("회원가입 페이지 호출");
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPost(MemberVO member) throws Exception{
		log.info("join Post 호출");
		
		
		String rawPw= ""; //인코딩 전 비밀번호
		String encodePw=""; // 인코딩 후 비밀번호
		
		rawPw= member.getMember_pw(); // 비밀번호 데이터
		encodePw = pwEncoder.encode(rawPw); // 인코딩
		member.setMember_pw(encodePw); // 인코딩된 비밀번호 member객체 넣기
		
		
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
		String rawPw="";
		String encodePw="";
		
		MemberVO loginid = memberservice.memberLogin(member);
		log.info(loginid);
		
		if(loginid != null) {
			rawPw = member.getMember_pw();
			encodePw = loginid.getMember_pw();
			
			if(true == pwEncoder.matches(rawPw, encodePw)) {
				loginid.setMember_pw(""); // 인코딩된 비밀번호 정보 지움
				session.setAttribute("member", loginid);
				return "redirect:/main";
			}else {
				log.info("로그인실패 : 인코딩 비밀번호 불일치");
				rttr.addFlashAttribute("result",0);
				return "redirect:/member/login";
			}
			
		}else {
			log.info("로그인실패 : 아이디 존재X");
			rttr.addFlashAttribute("result",0);
			return "redirect:/member/login";
		
		}

	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutMainGET(HttpServletRequest request) throws Exception{
		
		log.info("logoutMainGET 호출");
		
		HttpSession session = request.getSession();
		
		session.invalidate(); // session 값 제거
		
		return "redirect:/main";
	}
	
    // 비동기 방식 로그아웃
    @RequestMapping(value="logout", method=RequestMethod.POST)
    @ResponseBody
    public void logoutPOST(HttpServletRequest request) throws Exception{
        
        log.info("logoutPOST 비동기 호출");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
    }

}
