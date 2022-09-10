<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="/resources/css/order.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
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

// 주소입력란 버튼 동작(숨김, 등장)
function showAdress(className){
		// 모두 숨기기
		$(".addressInfo_input_div").css('display', 'none');
		
		// 컨텐츠 보이기
		$(".addressInfo_input_div_" + className).css('display','block')
		
		//버튼 색상 
		
		//모든 색상 동일
		$(".address_btn").css('backgroundColor', '#555');
		
		// 지정 색상 변경
		$(".address_btn_"+className).css('backgroundColor', '#3c3838');
}	

</script>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<c:if test = "${member == null}">	<!-- 로그인 x -->	
					<li >
						<a href="/member/login">로그인</a>
					</li>
					<li>
						<a href="/member/join">회원가입</a>
					</li>
				</c:if>
				<c:if test="${member != null }">	<!-- 로그인 o -->		
					<c:if test="${member.admin_ck == 1 }">	<!-- 관리자 계정 -->
						<li><a href="/admin/main">관리자 페이지</a></li>
					</c:if>							
					<li>
						<a id="gnb_logout_button">로그아웃</a>
					</li>
					<li>
						마이룸
					</li>
					<li>
						<a href="/cart/${member.member_id}">장바구니</a>
					</li>
				</c:if>				
				<li>
					고객센터
				</li>			
			</ul>			
		</div>
		<div class="top_area">
			<!-- 로고영역 -->
			<div class="logo_area">
				<a href="/main"><img src="/resources/img/mLogo.png"></a>
			</div>
			<div class="search_area">
                	<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">책 제목</option>
                					<option value="A">작가</option>
                				</select>
                				<input type="text" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/>">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
			</div>
			<div class="login_area">
			
				<!-- 로그인 하지 않은 상태 -->
				<c:if test = "${member == null }">
					<div class="login_button"><a href="/member/login">로그인</a></div>
					<span><a href="/member/join">회원가입</a></span>				
				</c:if>				
				
				<!-- 로그인한 상태 -->
				<c:if test="${ member != null }">
					<div class="login_success_area">
						<span>회원 : ${member.member_name}</span>
						<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="\#,###.##"/></span>
						<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
						<a href="/member/logout.do">로그아웃</a>
					</div>
				</c:if>
				
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="content_area">
				
				<div class="content_subject"><span>장바구니</span></div>

				
				<div class="content_main">
					<!-- 회원 정보 -->
					<div class ="member_info_div">
						<table class="table_text_align_center memberInfo_table">
							<tbody>
								<tr>
									<th style="width: 25%;">주문자</th>
									<td style="width: *">${memberInfo.member_name} | ${memberInfo.member_mail}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 배송지 정보 -->
					<div class="addressInfo_div">
						<div class="addressInfo_button_div">
							<button class="address_btn address_btn_1" onclick="showAdress('1')" style="background-color:#3c3838;">사용자 주소록 사용</button>
							<button class="address_btn address_btn_2" onclick="showAdress('2')">직접 입력</button>
						</div>
						
						<div class="addressInfo_input_div_wrap">
							<div class="addressInfo_input_div addressInfo_input_div_1"  style="display: block">
								div1
							</div>
							<div class="addressInfo_input_div addressInfo_input_div_2" >
								div2
							</div>
						</div>
					</div>
					<!-- 상품 정보 -->
					<!-- 포인트 정보 -->
					<!-- 주문 통합 정보 -->
				</div>

			
		</div>
		
		<!-- Footer 영역 -->
		<div class="footer_nav">
			<div class="footer_nav_container">
				<ul>
					<li>회사소개</li>
					<span class="line">|</span>
					<li>이용약관</li>
					<span class="line">|</span>
					<li>고객센터</li>
					<span class="line">|</span>
					<li>광고문의</li>
					<span class="line">|</span>
					<li>채용정보</li>
					<span class="line">|</span>
				</ul>
			</div>
		</div> <!-- class="footer_nav" -->
		
		<div class="footer">
			<div class="footer_container">
				
				<div class="footer_left">
					<img src="/resources/img/Logo.png">
				</div>
				<div class="footer_right">
					(주) VamBook    대표이사 : OOO
					<br>
					사업자등록번호 : ooo-oo-ooooo
					<br>
					대표전화 : oooo-oooo(발신자 부담전화)
					<br>
					<br>
					COPYRIGHT(C) <strong>kimvampa.tistory.com</strong>	ALL RIGHTS RESERVED.
				</div>
				<div class="clearfix"></div>
			</div>
		</div> <!-- class="footer" -->		
		
	</div>	<!-- class="wrap" -->
</div>	<!-- class="wrapper" -->

<script>

</script>

</body>
</html>