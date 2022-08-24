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

// 유효성 검사
var idCheck = false;            // 아이디
var idckCheck = false;            // 아이디 중복 검사
var pwCheck = false;            // 비번
var pwRECheck = false;			// 비번 형식
var pwckCheck = false;            // 비번 확인
var pwckcorCheck = false;        // 비번 확인 일치 확인
var userCheck = false;            // 이름
var mailCheck = false;            // 이메일
var mailRECheck = false;		  // 이메일 형식
var addressCheck = false         // 주소

$(document).ready(function(){
	// 회원가입 버튼 (회원가입 기능 작동)
	$(".join_button").click(function(){
		// 입력값 변수 선언
		var id = $('.id_input').val();
		var pw = $('.pw_input').val();
		var pwck = $('.pwck_input').val();
		var user = $('.user_input').val();
		var mail = $('.mail_input').val();
		var addr = $('.address_input_3').val();
		
		//아이디 유효성 검사
		if(id == ""){
			$('.final_id_ck').css('display','block');
			idCheck = false;
		}else{
			$('.final_id_ck').css('display','none');
			idCheck = true;
		}
		
		//비밀번호 유효성 검사
		if(pw == ""){
			$('.final_pw_ck').css('display','block');
			pwCheck = false;
		}else{
			$('.final_pw_ck').css('display','none');
			pwCheck = true;
		}
		
		//비밀번호 확인 유효성 검사
		if(pwck == ""){
			$('.final_pwck_ck').css('display','block');
			pwckCheck = false;
		}else{
			$('.final_pwck_ck').css('display','none');
			pwckCheck = true;
		}
		
		//이름 유효성 검사
		if(user == ""){
			$('.final_user_ck').css('display','block');
			userCheck = false;
		}else{
			$('.final_user_ck').css('display','none');
			userCheck = true;
		}
		
		//이메일 유효성 검사
		if(mail == ""){
			$('.final_mail_ck').css('display','block');
			mailCheck = false;
		}else{
			$('.final_mail_ck').css('display','none');
			mailCheck = true;
		}
		
		//주소 유효성 검사
		if(addr == ""){
			$('.final_addr_ck').css('display','block');
			addressCheck = false;
			console.log("주소null")
		}else{
			$('.final_addr_ck').css('display','none');
			addressCheck = true;
			console.log("주소")
		}
		
		
		//최종 유효성 검사
		if(idCheck && idckCheck && pwCheck && pwckcorCheck && pwRECheck && userCheck && mailCheck && mailRECheck && addressCheck){
			alert("가입이 완료되었습니다.");
			$("#join_form").attr("action","/member/join");
			$("#join_form").submit();
			
		}else{
			alert("작성하지 않은 곳이 있습니다.");
			return false;
		}

		

	});
	

	
	// id 입력감지
	// propertychange change keyup paste input : 이벤트 변화감지
	$('.id_input').on("propertychange change keyup paste input", function(){
		
		//console.log("keyup 테스트");
		
		// 아이디 체크 Ajax 처리
		var member_id = $('.id_input').val();
		var data = {member_id: member_id}

		$.ajax({
			type :"post",
			url: "/member/memberIdChk", 
			data : data,
			success: function(result){
				//console.log("성공 여부" + result);
				if(result == 'success'){
					$('.id_input_re_Success').css("display","inline-block");
					$('.id_input_re_Erorr').css("display","none")
					idckCheck = true;
					
				} else if(result == 'fali'){
					$('.id_input_re_Erorr').css("display","inline-block");
					$('.id_input_re_Success').css("display","none");
					idckCheck = false;
					
				}
				
				
			}
		});
		
	});
	// ajax 종료	
	
	//비밀번호 입력 감지
	$('.pwck_input').on("propertychange change keyup paste input", function(){
		var pw = $('.pw_input').val();
		var pwck = $('.pwck_input').val();
		$('.final_pwck_ck').css('display', 'none');
		
		var pwWarnMsg = $(".pw_input_box_warn"); 
		
		// 비밀번호 정규식 확인
	    if(pwFormCheck(pw)){
	    	pwWarnMsg.html("");
	    	pwWarnMsg.css("display", "inline-block");
	    	pwRECheck = true;
	    	
	    } else {
	    	pwWarnMsg.html("최소 8 자이상, 하나 이상의 숫자 와 특수 문자 를 비밀번호로 설정해주세요");
	    	pwWarnMsg.css("display", "inline-block");
	    	pwRECheck = false;
	    }    
		
		
		
		//비밀번호 일치 확인
		if(pw==pwck){
			$('.pwck_input_success').css('display','block');
			$('.pwck_input_error').css('display','none');
			pwckcorCheck = true;
		}else {
			$('.pwck_input_success').css('display','none');
			$('.pwck_input_error').css('display','block');
			pwckcorCheck = false;
		}
	});
	
	//이메일 입력 감지
	$('.mail_input').on("propertychange change keyup paste input", function(){
		var mailWarnMsg = $(".mail_input_box_warn"); 
		var email = $('.mail_input').val();
		console.log("test")
	    
		/* 이메일 형식 유효성 검사 */
	    if(mailFormCheck(email)){
	    	mailWarnMsg.html("");
	    	mailWarnMsg.css("display", "inline-block");
	    	mailRECheck = true;
	    	
	    } else {
	    	mailWarnMsg.html("올바르지 못한 이메일 형식입니다.");
	    	mailWarnMsg.css("display", "inline-block");
	    	mailRECheck = false;
	        return false;
	    }    
	});
});




//주소연동
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
            $(".address_input_3").attr("readonly",false);
            $(".address_input_3").focus();
	
			
		}
	}).open();
}

//입력 비밀번호 유효성 검사
function pwFormCheck(pw){
	var form2 = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
	return form2.test(pw);

}


//입력 이메일 형식 유효성 검사
function mailFormCheck(email){
	var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	// 해석을 하면 매개변수 email이 form에 저장된 정규표현식에 부합할 경우 true를 부합하지 않을 경우 false
	return form.test(email);

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
					<span class="final_id_ck" style="color : #be0000;">아이디를 입력해주세요</span>
				</div>
				
				<div class="pw_wrap">
					<div class="pw_name">비밀번호</div>
					<div class="pw_input_box">
						<input class="pw_input" name="member_pw">
					</div>
					<span class="final_pw_ck" style="color : #be0000;">비밀번호를 입력해 주세요.</span>
				</div>
				
				<div class="pwck_wrap">
					<div class="pwck_name">비밀번호 확인</div>
					<div class="pwck_input_box">
						<input class="pwck_input">
					</div>
					<sapn class="pw_input_box_warn" style="color : #be0000;"></sapn>
					<span class="final_pwck_ck" style="color : #be0000;">입력하신 비밀번호를 확인해 주세요.</span>
					<span class="pwck_input_success">비밀번호가 일치합니다.</span>
					<span class="pwck_input_error">비밀번호가 일치하지 않습니다.</span>
				</div>
				
				<div class="user_wrap">
					<div class="user_name">이름</div>
					 <div class="user_input_box">
					 	<input class="user_input" name="member_name">
					 </div>
					 <span class="final_user_ck" style="color : #be0000;">이름을 입력해주세요.</span>
				</div>
				
				<div class="mail_wrap">
					<div class="mail_name">이메일</div>
					<div class="mail_input_box">
						<input class="mail_input" name="member_mail">
					</div>
					<span class="final_mail_ck" style="color : #be0000;">이메일을 입력해주세요.</span>
					<span class="mail_input_box_warn" style="color : #be0000;"></span>
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
					<span class="final_addr_ck" style="color : #be0000;">주소를 입력해주세요.</span>
					
				</div>
				
				<div class="join_button_wrap">
					<input type="button" class="join_button" value="가입하기">
				</div>
			</div>
		</form>
	</div>
</body>
</html>