<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="/resources/css/book/userGoodsDetail.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- 주문 form -->
	<form action="/order/${member.member_id}" method="get" class="order_form">
		<input type="hidden" name="orders[0].bookId" value="${goodsInfo.bookId}">
		<input type="hidden" name="orders[0].book_count" value="">
	</form>
	
	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">
				<ul class="list">
					<c:if test="${member == null}">
						<!-- 로그인 x -->
						<li><a href="/member/login">로그인</a></li>
						<li><a href="/member/join">회원가입</a></li>
					</c:if>
					<c:if test="${member != null }">
						<!-- 로그인 o -->
						<c:if test="${member.admin_ck == 1 }">
							<!-- 관리자 계정 -->
							<li><a href="/admin/main">관리자 페이지</a></li>
						</c:if>
						<li><a id="gnb_logout_button">로그아웃</a></li>
						<li>마이룸</li>
						<li><a href="/cart/${member.member_id }">장바구니</a></li>
					</c:if>
					<li>고객센터</li>
				</ul>
			</div>
			<div class="top_area">
				<!-- 로고영역 -->
				<div class="logo_area">
					<a href="/main"><img src="/resources/img/r2.png"></a>
				</div>
				<div class="search_area">
					<div class="search_wrap">
						<form id="searchForm" action="/search" method="get">
							<div class="search_input">
								<select name="type">
									<option value="T">책 제목</option>
									<option value="A">작가</option>
								</select> <input type="text" name="keyword"
									value="<c:out value="${pageMaker.cri.keyword }"/>">
								<button class='btn search_btn'>검 색</button>
							</div>
						</form>
					</div>
				</div>
				<div class="login_area">

					<!-- 로그인 하지 않은 상태 -->
					<c:if test="${member == null }">
						<div class="login_button">
							<a href="/member/login">로그인</a>
						</div>
						<span><a href="/member/join">회원가입</a></span>
					</c:if>

					<!-- 로그인한 상태 -->
					<c:if test="${ member != null }">
						<div class="login_success_area">
							<span>회원 : ${member.member_name}</span> <span>충전금액 : <fmt:formatNumber
									value="${member.money }" pattern="\#,###.##" /></span> <span>포인트
								: <fmt:formatNumber value="${member.point }" pattern="#,###" />
							</span> <a href="/member/logout">로그아웃</a>
						</div>
					</c:if>

				</div>

			</div>

			<div class="content_area">
				<div class="line"></div>
				<div class="content_top">
					<div class="ct_left_area">
						<div class="image_wrap"
							data-bookid="${goodsInfo.imageList[0].bookId}"
							data-path="${goodsInfo.imageList[0].uploadPath}"
							data-uuid="${goodsInfo.imageList[0].uuid}"
							data-filename="${goodsInfo.imageList[0].fileName}">
							<img>
						</div>
					</div>
					<div class="ct_right_area">
						<div class="title">
							<h1>${goodsInfo.bookName}</h1>
						</div>
						<div class="line"></div>
						<div class="author">
							<span> ${goodsInfo.authorName} 지음 </span> 
							<span>|</span> 
							<span>${goodsInfo.publisher} </span> 
							<span>|</span> 
							<span class="publeyear">${goodsInfo.publeYear} </span>
						</div>
						<div class="line"></div>
						<div class="price">
							<div class="sale_price">
								정가 :<fmt:formatNumber value="${goodsInfo.bookPrice}" pattern="#,### 원" />
							</div>
							<div class="discount_price">
								판매가 : <span class="discount_price_number"><fmt:formatNumber value="${goodsInfo.bookPrice - (goodsInfo.bookPrice*goodsInfo.bookDiscount)}"
										pattern="#,### 원" /></span> [
								<fmt:formatNumber value="${goodsInfo.bookDiscount*100}" pattern="###" /> %
								<fmt:formatNumber value="${goodsInfo.bookPrice*goodsInfo.bookDiscount}" pattern="#,### 원" /> 할인]
							</div>
							<div>
								적립 포인트 : <span class="point_span"></span>원
							</div>
							
						</div>
						<div class="line"></div>
						<div class="button">
							<div class="button_quantity"> 
							주문수량 
							<input type="text" class="quantity_input" value="1">
								<span>
									<button class="plus_btn">+</button>
									<button class="minus_btn">-</button>
								</span>
							</div>
							<div class="button_set">
								<a class="btn_cart">장바구니 담기</a> 
								<a class="btn_buy">바로구매</a>
							</div>
						</div>
					</div>
				</div>
				<div class="line"></div>
				<div class="content_middle">
					<div class="book_intro">${goodsInfo.bookIntro}</div>
					<div class="book_content">${goodsInfo.bookContents }</div>
				</div>
				<div class="line"></div>
				<div class="content_bottom">리뷰</div>
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
			</div>
			<!-- class="footer_nav" -->

			<div class="footer">
				<div class="footer_container">

					<div class="footer_left">
						<img src="">
					</div>
					<div class="footer_right">
						(주) HTBook 대표이사 : OOO <br> 사업자등록번호 : ooo-oo-ooooo <br>
						대표전화 : oooo-oooo(발신자 부담전화) <br> <br> COPYRIGHT(C) <strong>kimvampa.tistory.com</strong>
						ALL RIGHTS RESERVED.
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<!-- class="footer" -->

		</div>
		<!-- class="wrap" -->
	</div>
	<!-- class="wrapper" -->


</body>
<script>
$(document).ready(function(){
	// 수량 버튼 조작
	let quantity = $(".quantity_input").val();
	$(".plus_btn").on("click", function(){
		$(".quantity_input").val(++quantity);
	});
	$(".minus_btn").on("click", function(){
		if(quantity > 1){
			$(".quantity_input").val(--quantity);	
		}
	});
	
	// 서버로 전송할 데이터
	const form = {
			member_id : '${member.member_id}',
			bookId : '${goodsInfo.bookId}',
			book_count : ''
	}
	
	// 장바구니 추가 버튼
	$(".btn_cart").on("click", function(e){
		//console.log("test")
		form.book_count = $(".quantity_input").val();
		//console.log(form.book_count)
		$.ajax({
			url: '/cart/add',
			type: 'POST',
			data: form,
			success: function(result){
				cartAlert(result);
			}
		})
	});
	
	function cartAlert(result){
		if(result == '0'){
			alert("장바구니에 추가를 하지 못하였습니다.");
		} else if(result == '1'){
			alert("장바구니에 추가되었습니다.");
		} else if(result == '2'){
			alert("장바구니에 이미 추가되어져 있습니다.");
		} else if(result == '5'){
			alert("로그인이 필요합니다.");	
		}
	}
	
	
	// 이미지 삽입
	const bobj = $(".image_wrap");
	
	if(bobj.data("bookid")){
		const uploadPath = bobj.data("path");
		const uuid = bobj.data("uuid");
		const fileName = bobj.data("filename");
		
		const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
		
		bobj.find("img").attr('src', '/book/display?fileName=' + fileCallPath);
	} else {
		bobj.find("img").attr('src', '/resources/img/goodsNoImage.png');
	}
	
	// publeyear
	const year = "${goodsInfo.publeYear}";
	
	let tempYear = year.substr(0,10);
	
	let yearArray = tempYear.split("-")
	let publeYear = yearArray[0] + "년 " + yearArray[1] + "월 " + yearArray[2] + "일";
	
	$(".publeyear").html(publeYear);
	
	// 포인트 삽입
	let salePrice = "${goodsInfo.bookPrice - (goodsInfo.bookPrice*goodsInfo.bookDiscount)}"
	let point = salePrice*0.05;
	point = Math.floor(point);
	$(".point_span").text(point);
	

	// 바로구매 버튼
	$(".btn_buy").on("click", function(){
		let bookCount = $(".quantity_input").val();
		$(".order_form").find("input[name='orders[0].book_count']").val(bookCount);
		$(".order_form").submit();
	});

	
});	
</script>
</html>