<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="wrap">
			<!-- gnv_area -->
			<div class="top_gnb_area">
				<ul class="list">
					<li><a href="#">메인 페이지</a></li>
					<li><a href="#">로그아웃</a></li>
					<li>고객센터</li>
				</ul>
			</div>
			<!-- top_subject_area -->
			<div class="admin_top_wrap">
				<span>관리자 페이지</span>

			</div>
			<!-- contents-area -->
			<div class="admin_wrap">
				<!-- 네비영역 -->
				<div class="admin_navi_wrap">
					<ul>
						<li><a class="admin_list_01" href="#">상품
								등록</a></li>
						<li><a class="admin_list_02" href="#">상품
								관리</a></li>
						<lI><a class="admin_list_03" href="/author/authorEnroll">작가
								등록</a></lI>
						<lI><a class="admin_list_04" href="/author/authorManage">작가
								관리</a></lI>
						<lI><a class="admin_list_05">회원 관리</a></lI>
					</ul>
				</div>
				<div class="admin_content_wrap">
					<div class="admin_content_subject">
						<span>작가 관리</span>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- class="wrap" -->
	</div>
	<!-- class="wrapper" -->
</body>
<script>
$(document).ready(function(){
    
	let result = '<c:out value="${enroll_result}"/>';
    
    checkResult(result);
    
    function checkResult(result){
        
        if(result === ''){
            return;
        }
        
        alert("작가'${enroll_result}' 을 등록하였습니다.");
        
    }
 
});
</script>
</html>