<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <li><a href="/book/main">관리자 페이지</a></li>
                    </c:if>                 
                    <li>
                        <a id = "gnb_logout_button">로그아웃</a>
                    </li>
                                       
                    <li>
                        <a href="/cart/${member.member_id }">장바구니</a>
                    </li>
                </c:if>                        
            </ul> 	
		</div>
		
		<div class="top_area">
			<div class="logo_area">
				<a href="/main"><img src="resources/img/r2.png"></a>
			</div>
		<div class="search_area">
			<div class="search_wrap">
           		<form id="searchForm" action="/search" method="get">
           			<div class="search_input">
           				<select name="type">
           					<option value="T">책 제목</option>
           					<option value="A">작가</option>
           				</select>
           				<input type="text" name="keyword">
               			<button class='btn search_btn'>검 색</button>                				
           			</div>
           		</form>
           	</div>
		</div>
		<div class="login_area">
			<!-- 로그인 하지 않은 상태 -->
			<c:if test = "${member == null }">
				<div class="login_button"><a href="/member/login">로그인</a> </div>
				<span><a href="/member/join">회원가입</a></span>
			</c:if>
	
			<!-- 로그인한 상태 -->
				<c:if test="${ member != null }">
					<div class="login_success_area">
						<span>회원 : ${member.member_name}</span>
						<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="\#,###.##"/></span>
						<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
					</div>
				</c:if>
		</div>
		<div class="clearfix"></div>
		</div>
		
		
	<div class="navi_bar_area">
		<div class="dropdown">
			<button class="dropbtn">국내 
		      <i class="fa fa-caret-down"></i>
		    </button>
		    <div class="dropdown-content">
				<c:forEach items="${cate1}" var="cate"> 
		    		<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
		    	</c:forEach>      		      		      
		    </div>	
		</div>
		<div class="dropdown">
			<button class="dropbtn">국외 
		      <i class="fa fa-caret-down"></i>
		    </button>
		    <div class="dropdown-content">
				<c:forEach items="${cate2}" var="cate"> 
		    		<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
		    	</c:forEach>      		      		      
		    </div>	
		</div>
	</div>
	</div>
</div>
	
</body>
</html>