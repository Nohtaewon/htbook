<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	// 회원가입 버튼 (회원가입 기능 작동)
	$(".join_button").click(function(){
		$("#join_form").attr("action","/member/join");
		$("#join_form").submit();
		console.log("test");
	});
});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<form id="join_form" action="" method="post">
			<div class="wrap">
				<div class="subjecet">
					<span>회원가입</span>
				</div>
				
				<div class="id_wrap">
					<div class="id_name">아이디</div>
					<div class="id_input_box">
						<input class="id_input" name="member_id">
					</div>
				
				</div>
				
				<div class="pw_wrap">
					<div class="pw_name">비밀번호</div>
					<div class="pw_input_box">
						<input class="pw_input" name="member_pw">
					</div>
				
				</div>
				
				<div class="pwck_wrap">
					<div class="pwck_name">비밀번호 확인</div>
					<div class="pwck_input_box">
						<input class="pwck_input">
					</div>
				</div>
				
				<div class="user_wrap">
					<div class="user_name">이름</div>
					 <div>
					 	<input class="user_input" name="member_name">
					 </div>
				</div>
				
				<div class="mail_wrap">
					<div class="mail_name">이메일</div>
					<div class="mail_input_box">
						<input class="user_input" name="member_mail">
					</div>
				</div>
				
				<div class="mail_check_wrap">
					<div class="mail_check_input_box">
						<input class="mail_check_input">
					</div>
					
					<div class="mail check_button">
						<span>인증번호 전송</span>
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class="address_wrap">
					<div class="address_name">주소</div>
					
					<div class="address_input_1_wrap">
						<div class="address_input_1_box">
							<input class="address_input_1" name="member_addr1">
						</div>
						
						<div class="address_button">
							<span>주소 찾기</span>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class ="address_input_2_wrap">
						<div class="address_input_2_box">
							<input class="address_input_2" name="member_addr2">
						</div>
					</div>
					
					<div class ="address_input_3_wrap">
						<div class="address_input_3_box">
							<input class="address_input_3" name="member_addr3">
						</div>
					</div>
				</div>
				
				<div class="join_button_wrap">
					<input type="button" class="join_button" value="가입하기">
				</div>
			</div>
		</form>
	</div>
</body>
</html>