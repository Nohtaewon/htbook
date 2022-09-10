<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="/resources/css/cart.css">
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
		
		setTotalInfo();
		
		// 체크여부에 따른 정보 변화
		$(".individual_cart_checkbox").on("change", function(){
			
			// 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수 , 종류)
			setTotalInfo($(".cart_info_td"));
		});
		
		// 체크박스 전체 선택
		$(".all_check_input").on("click", function(){
			
			// 체크박스 체크or 해제	
			if($(".all_check_input").prop("checked")){
				$(".individual_cart_checkbox").prop("checked", true);
			} else{
				$(".individual_cart_checkbox").prop("checked", false);
			}
			
			// 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수 , 종류)
			setTotalInfo($(".cart_info_td"));
		});
		
		//수량 수정 버튼
		$(".quantity_modify_btn").on("click", function(){
			let cartId = $(this).data("cart_id");
			let bookCount = $(this).parent("td").find("input").val();
			$(".update_cart_id").val(cartId);
			$(".update_book_count").val(bookCount);
			$(".quantity_update_form").submit();
			
			
		});
		
		// 수량 추가/감소 버튼
		$(".plus_btn").on("click", function(){
			let quantity = $(this).parent("div").find("input").val()
			$(this).parent("div").find("input").val(++quantity);
		});
		
		$(".minus_btn").on("click", function(){
			let quantity = $(this).parent("div").find("input").val()
			$(this).parent("div").find("input").val(--quantity);
		});
		
		// 장바구니 삭제 버특
		$(".delete_btn").on("click", function(e){
			e.preventDefault();
		    if (!confirm("해당 물품을 삭제하시겠습니까?")) {
		        return;
		        
		    } else {
				let cartId = $(this).data("cart_id");
		        console.log(cartId)
		        $(".delete_cart_id").val(cartId);
		        $(".quantity_delete_form").submit();
		    }
		    
		});
		

		// 주문 페이지 이동
		$(".order_btn").on("click", function(){
			let fromContents ='';
			let orderNumber = 0; // order 의 index 역활
			$(".cart_info_td").each(function(index, element){
				
				let bookId = $(element).find(".individual_bookId_input").val();
				let bookCount = $(element).find(".individual_bookCount_input").val();
				
				let bookIdInput = "<input name='orders[" + orderNumber + "].bookId' type='hidden' value='" + bookId + "'>";
				fromContents += bookIdInput;
				
				let bookCountInput = "<input name='orders[" + orderNumber + "].book_count' type='hidden' value='" + bookCount + "'>";
				fromContents += bookCountInput
				
				orderNumber += 1;

			});
			
			$(".order_form").html(fromContents);
			console.log(fromContents);
			$(".order_form").submit();

		// i = 몇 번재 객체인지 순서 값 (배열의 index라 생각)
		// obj = i번째에서 접근하는 객체
		$(".image_wrap").each(function(i,obj){
			const bobj = $(obj);
			
			if(bobj.data("bookid")){
				const uploadPath = bobj.data("path");
				const uuid = bobj.data("uuid");
				const fileName = bobj.data("filename")
				
				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
				
				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
				
			} else {
				$(this).find("img").attr('src', '/resources/img/goodsNoImage.png');
			}
		});
		
	});
		
		
	function setTotalInfo(){
		let totalPrice = 0;				// 총 가격
		let totalCount = 0;				// 총 갯수
		let totalKind = 0;				// 총 종류
		let totalPoint = 0;				// 총 마일리지
		let deliveryPrice = 0;			// 배송비
		let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)
		
		// 총 가격,갯수,종류,마일리지 얻기
		$(".cart_info_td").each(function(index, element){
			if($(element).find(".individual_cart_checkbox").is(":checked") === true){ // 체크 일때
				
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				totalCount += parseInt($(element).find(".individual_bookCount_input").val());
				totalKind += 1;
				totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
			}
			
			
			
		})
		
		// 배송비 결정
		if(totalPrice >=30000){
			deliveryPrice = 0;
		} else if (totalPrice ==0){
			deliveryPrice = 0;
		}else {
			deliveryPrice = 3000;
		}
			
		// 최종가격
		finalTotalPrice = totalPrice + deliveryPrice;
		
		// 값 넣기
		
		// 총 가격
		$(".totalPrice_span").text(totalPrice);
		
		// 총 갯수
		$(".totalCount_span").text(totalCount);
		
		// 총 종류
		$(".totalKind_span").text(totalKind);
		
		// 총 마일리지
		$(".totalPoint_span").text(totalPoint);
		
		// 배송비
		$(".delivery_price").text(deliveryPrice);	
		
		// 최종 가격(총 가격 + 배송비)
		// 세자리 컴마 toLocaleString() 객체 사용
		$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());
		
		
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
				<!-- 장바구니 리스트 -->
				
				<div class="content_middle_section"></div>
				<!-- 장바구니 가격 합계 -->
				
				<!-- cartInfo -->
				<div class="content_totalCount_section">
				<!-- 체크박스 전체 여부 -->
				<div class="all_check_input_div">
					<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_chcek_span">전체선택</span>
				</div>
				<table class="subject_table">
					<caption>표 제목 부분</caption>
					<tbody>

						<tr>
							<th class="td_width_1"></th>
							<th class="td_width_2"></th>
							<th class="td_width_3">상품명</th>
							<th class="td_width_4">가격</th>
							<th class="td_width_4">수량</th>
							<th class="td_width_4">합계</th>
							<th class="td_width_4">삭제</th>
						</tr>
					</tbody>
				</table>
				<!-- 수량 조정 -->
				<form action="/cart/update" method="post" class="quantity_update_form">
					<input type="hidden" name="cart_id" class="update_cart_id">
					<input type="hidden" name="book_count" class="update_book_count">
					<input type="hidden" name="member_id" value="${member.member_id}">
				
				</form>
				
				<!-- 삭제 -->
				<form action="/cart/delete" method="post" class="quantity_delete_form">
					<input type="hidden" name="cart_id" class="delete_cart_id"> 
					<input type="hidden" name="member_id" value="${member.member_id }">
				
				</form>
				
				<!-- 주문 form -->
				<form action="/order/${member.member_id}" method="get" class="order_form">
				
				</form>
				<table class="cart_table">
					<caption>표 내용 부분</caption>
					<tbody>
						<c:forEach items="${cartInfo}" var="ci">
							<tr>
								<td class="td_width_1 cart_info_td">
									<input type="checkbox" class="individual_cart_checkbox input_size_20" checked="checked">
									<input type="hidden" class="individual_bookPrice_input" value="${ci.bookPrice}">
									<input type="hidden" class="individual_salePrice_input" value="${ci.sale_price}">
									<input type="hidden" class="individual_bookCount_input" value="${ci.book_count}">
									<input type="hidden" class="individual_totalPrice_input" value="${ci.sale_price * ci.book_count}">
									<input type="hidden" class="individual_point_input" value="${ci.point}">
									<input type="hidden" class="individual_totalPoint_input" value="${ci.total_point}">
									<input type="hidden" class="individual_bookId_input" value="${ci.bookId}">
									
								</td>
								<td class="td_width_2">
									<div class="image_wrap" data-bookid="${ci.imageList[0].bookId }" data-path ="${ci.imageList[0].uploadPath}" data-uuid="${ci.imageList[0].uuid}" data-filename="${ci.imageList[0].fileName}" >
										<img>
									</div>
								</td>
								<td class="td_width_3">${ci.bookName}</td>
								<td class="td_width_4 price_td">
									<del>정가 : <fmt:formatNumber value="${ci.bookPrice}" pattern="#,### 원" /></del><br>
									판매가 : <span class="red_color"><fmt:formatNumber value="${ci.sale_price}" pattern="#,### 원" /></span><br>
									마일리지 : <span class="green_color"><fmt:formatNumber value="${ci.point}" pattern="#,###" /></span>
								</td>
								

								<td class="td_width_4 table_text_align_center">
									<div class="table_text_align_center quantity_div">
										<input type="text" value="${ci.book_count}" class="quantity_input">	
										<button class="quantity_btn plus_btn">+</button>
										<button class="quantity_btn minus_btn">-</button>
									</div>
									<!-- data-칼럼명과 일치 -->
									<a class="quantity_modify_btn" data-cart_id="${ci.cart_id}">변경</a>
								</td>
								<td class="td_width_4 table_text_align_center">
									<fmt:formatNumber value="${ci.sale_price * ci.book_count}" pattern="#,### 원" />
								</td>
								<td class="td_width_4 table_text_align_center">
									<button class="delete_btn" data-cart_id="${ci.cart_id}">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table class="list_table">
				</table>
			</div>
			<!-- 가격 종합 -->
			<div class="content_total_section">
				<div class="total_wrap">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>총 상품 가격</td>
										<td>
											<span class="totalPrice_span">70000</span> 원
										</td>
									</tr>
									<tr>
										<td>배송비</td>
										<td>
											<span class="delivery_price">3000</span>원
										</td>
									</tr>									
									<tr>
										<td>총 주문 상품수</td>
										<td><span class="totalKind_span"></span>종 <span class="totalCount_span"></span>권</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr>
										<td></td>
										<td></td>
									</tr>
								</table>							
							</td>
						</tr>
					</table>
					<div class="boundary_div">구분선</div>
					<table>
						<tr>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 결제 예상 금액</strong>
											</td>
											<td>
												<span class="finalTotalPrice_span">70000</span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td>
								<table>
									<tbody>
										<tr>
											<td>
												<strong>총 적립 예상 마일리지</strong>
											
											</td>
											<td>
												<span class="totalPoint_span">70000</span> 원
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 구매 버튼 영역 -->
			<div class="content_btn_section">
				<a class="order_btn">주문하기</a>
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