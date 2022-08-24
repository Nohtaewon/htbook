<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 주소 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
});

/*주소 연동*/
function address(){
	new daum.Postcode({
		oncomplete: function(data){
			// 팝업에서 검새결과 항목 클릭시 작동될 코드
			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
				
                // 주소변수 문자열과 참고항목 문자열을 합치기
                addr += extraAddr;
            
            } else {
                addr +=' ';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.(제이쿼리)
            $(".address_input_1").val(data.zonecode);
            $(".address_input_2").val(addr);

            // 커서를 상세주소 필드로 이동한다.(제이쿼리)
            $(".address_input3").attr("readonly",false);
            $(".address_input3").focus();
	
			
		}
	}).open();
}



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
					 	<input class="user_input_box" name="member_name">
					 </div>
				</div>
				
				<div class="mail_wrap">
					<div class="mail_name">이메일</div>
					<div class="mail_input_box">
						<input class="mail_input" name="member_mail">
					</div>
				</div>
				
								
				<div class="address_wrap">
					<div class="address_name">주소</div>
					
					<div class="address_input_1_wrap">
						<div class="address_input_1_box">
							<input class="address_input_1" name="member_addr1" readonly="readonly">
						</div>
						
						<div class="address_button" onclick="address()">
							<span>주소 찾기</span>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class ="address_input_2_wrap">
						<div class="address_input_2_box">
							<input class="address_input_2" name="member_addr2" readonly="readonly">
						</div>
					</div>
					
					<div class ="address_input_3_wrap">
						<div class="address_input_3_box">
							<input class="address_input_3" name="member_addr3" readonly="readonly">
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