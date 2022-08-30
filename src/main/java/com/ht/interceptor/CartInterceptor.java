package com.ht.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ht.member.domain.MemberVO;

public class CartInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		MemberVO loginid = (MemberVO)session.getAttribute("member");
		

		if(loginid == null) { 
			
			response.sendRedirect("/main"); // 메인페이지로 리다이렉트
			
			return false;
		}

		return true;
	}

}
