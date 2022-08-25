<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/member/main.css">
<title>Insert title here</title>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<h1>gnb area</h1>
		</div>
		
		<div class="top_area">
			<h1>logo area</h1>
		</div>
		
		<div class="search_area">
			<h1>Search area</h1>
		</div>
		
		<div class="login_area">
			<!-- 로그인 하지 않은 상태 -->
			<c:if test = "${member == null }">
				<div class="login_button"><a href="/member/login">로그인</a> </div>
				<span><a href="/member/join">회원가입</a></span>
			</c:if>
	
			<!-- 로그인 한 상태 -->
			<c:if test = "${member != null }">
				<div class="login_success_area">
					<span>회원 : ${member.member_name}</span>
					<span>충전금액 : ${member.money}</span>
					<span>포인트 : ${member.point}</span>
				</div>
			</c:if>
				</div>
	</div>
</div>
	
</body>
</html>