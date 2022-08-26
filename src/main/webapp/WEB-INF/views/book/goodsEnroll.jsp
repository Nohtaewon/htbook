<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="../resources/css/book/goodsenroll.css">

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
						<li><a class="admin_list_01" href="/book/goodsEnroll">상품 등록</a></li>
						<li><a class="admin_list_02" href="/book/goodsManage">상품 관리</a></li>
						<lI><a class="admin_list_03" href="/author/authorEnroll">작가
								등록</a></lI>
						<lI><a class="admin_list_04" href="/author/authorManage">작가
								관리</a></lI>
						<lI><a class="admin_list_05">회원 관리</a></lI>
					</ul>
				</div>
				<div class="admin_content_wrap">
					<div class="admin_content_subject">
						<span>상품 등록</span>
					</div>
					<div class="admin_content_main">
						<form action="/book/goodsEnroll" method="post" id="enrollForm">
							<div class="form_section">
								<div class="form_section_title">
									<label>책 제목</label>
								</div>
								<div class="form_section_content">
									<input name="bookName">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>작가</label>
								</div>
								<div class="form_section_content">
									<input name="authorId" value="0">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>출판일</label>
								</div>
								<div class="form_section_content">
									<input name="publeYear">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>출판사</label>
								</div>
								<div class="form_section_content">
									<input name="publisher">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>책 카테고리</label>
								</div>
								<div class="form_section_content">
									<input name="cateCode">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>상품 가격</label>
								</div>
								<div class="form_section_content">
									<input name="bookPrice" value="0">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>상품 재고</label>
								</div>
								<div class="form_section_content">
									<input name="bookStock" value="0">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>상품 할인율</label>
								</div>
								<div class="form_section_content">
									<input name="bookDiscount" value="0">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>책 소개</label>
								</div>
								<div class="form_section_content">
									<input name="bookIntro">
								</div>
							</div>
							<div class="form_section">
								<div class="form_section_title">
									<label>책 목차</label>
								</div>
								<div class="form_section_content">
									<input name="bookContents">
								</div>
							</div>
						</form>
						<div class="btn_section">
							<button id="cancelBtn" class="btn">취 소</button>
							<button id="enrollBtn" class="btn enroll_btn">등 록</button>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- class="wrap" -->
	</div>
	<!-- class="wrapper" -->
</body>
<script>
let enrollForm = $("#enrollForm")

// 취소 버튼
$("#cancelBtn").click(function(){
	location.href="/book/goodsManage"
});

// 상품 등록 버튼
$("#enrollBtn").on("click", function(e){
	e.preventDefault();
	enrollForm.submit();
});
</script>
</html>