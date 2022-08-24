<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/resources/css/member/join.css">
<script type="text/javascript">
$(document).ready(function(){
	// 회원가입 버튼 (회원가입 기능 작동)
	$(".join_button").click(function(){
		$("#join_form").attr("action","/member/join");
		$("#join_form").submit();
		console.log("test");
	});
	
	// propertychange change keyup paste input : 이벤트 변화감지
	$('.id_input').on("propertychange change keyup paste input", function(){
		
		//console.log("keyup 테스트");
		
		// 아이디 체크 Ajax 처리
		const member_id = $('.id_input').val();
		const data = {member_id: member_id}

		$.ajax({
			type :"post",
			url: "/member/memberIdChk", 
			data : data,
			success: function(result){
				//console.log("성공 여부" + result);
				if(result == 'success'){
					$('.id_input_re_Success').css("display","inline-block");
					$('.id_input_re_Erorr').css("display","none")
					
				} else if(result == 'fali'){
					$('.id_input_re_Erorr').css("display","inline-block");
					$('.id_input_re_Success').css("display","none")
					
				}
				
				
			}
		});
		
	});
	// ajax 종료
	

	/* 인증번호 이메일 전송 */
	$(".mail_check_button").click(function(){
		var email = $(".mail_input").val();  // 입력한 이메일
		
	    $.ajax({
	        
	        type:"GET",
	        url:"mailCheck?email=" + email
	                
	    });
	});
	
});



</script>

<style type="text/css">

</style>
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
					<span class="id_input_re_Success">사용 가능한 아이디입니다.</span>
					<span class="id_input_re_Erorr">이미 존재하는 아이디입니다.</span>
				
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
						<input class="mail_input" name="member_mail">
					</div>
				
					<div class="mail_check_wrap">
					
						<div class="mail_check_input_box" id="mail_check_input_box_false">
							<input class="mail_check_input" disabled="disabled">
						</div>
					
						<div class="mail_check_button">
							<span>인증번호 전송</span>
						</div>
						<div class="clearfix"></div>
					</div>
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