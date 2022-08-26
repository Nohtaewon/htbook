<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="../resources/css/author/authorManage.css">
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
						<span>작가 관리</span>
					</div>
					<div class="author_table_wrap">
						<!-- 게시물 o -->
						<c:if test="${listCheck != 'empty' }">
							<table class="author_table">
								<thead>
									<tr>
										<td class="th_column_1">작가 번호</td>
										<td class="th_column_2">작가 이름</td>
										<td class="th_column_3">작가 국가</td>
										<td class="th_column_4">등록 날짜</td>
										<td class="th_column_5">수정 날짜</td>
									</tr>
								</thead>
								<c:forEach items="${list}" var="li">
									<tr>
										<td><c:out value="${li.authorId}"></c:out></td>
										<td>
											<a href='<c:out value="${li.authorId}" />' class="move">
												<c:out value="${li.authorName}"></c:out>
											</a>
										</td>
										<td><c:out value="${li.nationName}"></c:out></td>
										<td><fmt:formatDate value="${li.regDate}"
												pattern="yyyy-MM-dd" /></td>
										<td><fmt:formatDate value="${li.updateDate}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<!-- 게시물 x -->
						<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				등록된 작가가 없습니다.
                			</div>
                		</c:if> 
					</div>
					
					<!-- 검색 영역 -->
					<div class="search_wrap">
						<form action="/author/authorManage" method="get" id=:searchForm>
							<div class="search_input">
								<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
								<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"></c:out>'>
								<input type="hidden" name="amount" value='${pageMaker.cri.amount }'>
								<button class='btn search_btn'>Search</button>
							</div>
						</form>
					</div>
					<!-- 페이지 이동 인터페이스 영역 -->
					<div class="pageMaker_wrap">

						<ul class="pageMaker">

							<!-- 이전 버튼 -->
							<c:if test="${pageMaker.prev}">
								<li class="pageMaker_btn prev"><a
									href="${pageMaker.pageStart - 1}">Prev</a></li>
							</c:if>

							<!-- 페이지 번호 -->
							<c:forEach begin="${pageMaker.pageStart}"
								end="${pageMaker.pageEnd}" var="num">
								<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
									<a href="${num}">${num}</a>
								</li>
							</c:forEach>

							<!-- 다음 버튼 -->
							<c:if test="${pageMaker.next}">
								<li class="pageMaker_btn next"><a
									href="${pageMaker.pageEnd + 1 }">Next</a></li>
							</c:if>

						</ul>

					</div>
					
					<form id="moveForm" action="/author/authorManage" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					</form>



				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- class="wrap" -->
	<!-- class="wrapper" -->
</body>
<script>
$(document).ready(function() {

	let result = '<c:out value="${enroll_result}"/>';

	checkResult(result);

	function checkResult(result) {

		if (result === '') {
			return;
		}

		alert("작가'${enroll_result}' 을 등록하였습니다.");

	}
	
	function checkmResult(mresult){
		
		if(mresult === '1'){
			alert("작가 정보 수정을 완료하였습니다.");
		}else if(mresult === '0'){
			alert("작가 정보 수정을 하지 못하였습니다.");
		}
	}
});	
let moveForm = $('#moveForm');
 
// 페이지 이동 버튼 
$(".pageMaker_btn a").on("click", function(e){
    
    e.preventDefault();
    
    moveForm.find("input[name='pageNum']").val($(this).attr("href"));
    
    moveForm.submit();

});
let searchForm = $('#searchForm');

// 검색 버튼 동작 
$("#searchForm button").on("click", function(e){
	
	e.preventDefault();
	
	// 검색 키워드 유효성 검사 
	if(!searchForm.find("input[name='keyword']").val()){
		alert("키워드를 입력하십시오");
		return false;
	}
	
	searchForm.find("input[name='pageNum']").val("1");
	
	searchForm.submit();
	
});
// 작가 상세 페이지 이동
$(".move").on("click", function(e){
	
	e.preventDefault();
	
	moveForm.append("<input type='hidden' name='authorId' value='"+ $(this).attr("href") + "'>");
	moveForm.attr("action", "/author/authorDetail");
	moveForm.submit();
	
});

</script>
</html>