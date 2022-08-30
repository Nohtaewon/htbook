<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/member/main.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	// 로그아웃 버튼 작동
	$("#gnb_logout_button").click(function(){
		//alert("버튼 작동");
		
		$.ajax({
			type:"POST",
			url:"/member/logout",
			success:function(data){
				alert("로그아웃 성공");
				document.location.reload();
			}
		});
	});	
	
});

	
</script>
<title>Insert title here</title>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
            <ul class="list">
            	<!-- 로그인 X -->
            	<c:if test="${member == null }">
	                <li >
	                    <a href="/member/login">로그인</a>
	                </li>
	                
	                <li>
	                    <a href="/member/join">회원가입</a>
	                </li>
            	</c:if>
            	
                <!-- 로그인 O -->
                <c:if test="${member != null }"> 
                	<!-- 관리자 계정 -->  
                    <c:if test="${member.admin_ck == 1 }">
                        <li><a href="/admin/main">관리자 페이지</a></li>
                    </c:if>                 
                    <li>
                        <a id = "gnb_logout_button">로그아웃</a>
                    </li>
                    
                    <li>
                        마이룸
                    </li>
                    
                    <li>
                        <a href="/cart/${member.member_id }">장바구니</a>
                    </li>
                </c:if>             
                <li>
                    고객센터
                </li>           
            </ul> 	
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
					<a href="/member/logout">로그아웃</a>
				</div>
			</c:if>
				</div>
	</div>
</div>
	
</body>
</html>