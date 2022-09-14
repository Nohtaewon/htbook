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
<link rel="stylesheet" href="../resources/css/admin/orderList.css">
</head>
<body>
<%@include file="../common/header.jsp" %>

	<form id="deleteForm" action="/book/orderCancle" method="post">
         <input type="hidden" name="order_id">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		<input type="hidden" name="member_id" value="${member.member_id}">
    </form>
	<div class="admin_content_wrap">
		<div class="admin_content_subject">
			<span>주문 현황</span>
		</div>
		<div class="author_table_wrap">
			<!-- 게시물 o -->
			<c:if test="${listCheck != 'empty' }">
				<table class="order_table">
	                    	<colgroup>
	                    		<col width="21%">
	                    		<col width="20%">
	                    		<col width="20%">
	                    		<col width="20%">
	                    		<col width="19%%">
	                    	</colgroup>
	                    		<thead>
	                    			<tr>
	                    				<td class="th_column_1">주문 번호</td>
	                    				<td class="th_column_2">주문 아이디</td>
	                    				<td class="th_column_3">주문 날짜</td>
	                    				<td class="th_column_4">주문 상태</td>
	                    				<td class="th_column_5">취소</td>
	                    			</tr>
	                    		</thead>
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td><c:out value="${list.order_id}"></c:out> </td>
	                    			<td><c:out value="${list.member_id}"></c:out></td>
	                    			<td><fmt:formatDate value="${list.order_date}" pattern="yyyy-MM-dd"/></td>
	                    			<td><c:out value="${list.order_state}"/></td>
	                    			<td>
	                    				<c:if test="${list.order_state == '배송준비' }">
											<button class="delete_btn" data-order_id="${list.order_id}">취소</button>
										</c:if>
	                    			</td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>
			</c:if>
			<!-- 게시물 x -->
			<c:if test="${listCheck == 'empty'}">
				<div class="table_empty">주문이 없습니다.</div>
			</c:if>
		</div>

		<!-- 검색 영역 -->
		<div class="search_wrap">
			<form action="/book/orderList" method="get" id=:searchForm>
				<div class="search_input">
					<input type="text" name="keyword"
						value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
					<input type="hidden" name="pageNum"
						value='<c:out value="${pageMaker.cri.pageNum}"></c:out>'>
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
				<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}"
					var="num">
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
		<form id="moveForm" action="/admin/orderList" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
			<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		</form>
	</div>
	<%@include file="../common/footer.jsp" %>
</body>
<script>
	$(document).ready(function() {
		let searchForm = $('#searchForm');
		let moveForm = $('#moveForm');
		
		/* 작거 검색 버튼 동작 */
		$("#searchForm button").on("click", function(e){
			
			e.preventDefault();
			
			/* 검색 키워드 유효성 검사 */
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하십시오");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			
			searchForm.submit();
			
		});
		
		
		/* 페이지 이동 버튼 */
		$(".pageMaker_btn a").on("click", function(e){
			
			e.preventDefault();
			
			console.log($(this).attr("href"));
			
			moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			
			moveForm.submit();
			
		});
		
	});
	
	$(".delete_btn").on("click", function(e){
		e.preventDefault();
		
		let id = $(this).data("order_id");
		
		$("#deleteForm").find("input[name='order_id']").val(id);
		$("#deleteForm").submit();
	});
</script>
</html>