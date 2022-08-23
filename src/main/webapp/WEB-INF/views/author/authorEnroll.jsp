<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
@charset "UTF-8";
*{
	margin: 0;
	padding:0;
}
a{
	text-decoration: none;
}
ul{
    list-style: none;
}
/* 화면 전체 렙 */
.wrapper{
	width: 100%;
}
/* content 랩 */
.wrap{
	width : 1080px;
	margin: auto;
}
/* 홈페이지 기능 네비 */ 
.top_gnb_area{
	width: 100%;
    height: 50px;
    background-color: #f0f0f1;
    position:relative;
}
.top_gnb_area .list{
	position: absolute;
    top: 0px;
    right: 0;
    
}
.top_gnb_area .list li{
	list-style: none;	
    float : left;
    padding: 13px 15px 0 10px;
    font-weight: 900;
    cursor: pointer;
}

/* 관리제 페이지 상단 현페이지 정보 */
.admin_top_wrap{
    height:110px;
    line-height: 110px;
    background-color: #5080bd;
    margin-bottom: 15px;
}
.admin_top_wrap>span{
    margin-left: 30px;
    display:inline-block;
    color: white;
    font-size: 50px;
    font-weight: bolder;
}
/* 관리자 wrap(네비+컨텐츠) */
.admin_wrap{
    
    
}

/* 관리자페이지 네비 영역 */
.admin_navi_wrap{
    width: 20%;
    height: 300px;
    float:left;
    height: 100%;
}
.admin_navi_wrap li{
    display: block;
    height: 80px;
    line-height: 80px;
    text-align: center;
}
.admin_navi_wrap li a{
    display: block;
    height: 100%;
    width: 95%;
    margin: 0 auto;
    cursor: pointer;
    font-size: 30px;
    font-weight: bolder;
}
.admin_navi_wrap li a:link {color: black;}
.admin_navi_wrap li a:visited {color: black;}
.admin_navi_wrap li a:active {color: black;}
.admin_navi_wrap li a:hover {color: black;}
 
.admin_list_03{
    background-color: #c8c8c8;
} 


/* 관리자페이지 컨텐츠 영역 */
.admin_content_wrap{
    width: 80%;
    float:left;
    height: 100%;

    
}
/* 관리자 컨텐츠 제목 영역 */
.admin_content_subject{	
    font-size: 40px;
    font-weight: bolder;
    padding-left: 15px;
    background-color: #6AAFE6;
    height: 80px;
    line-height: 80px;
    color: white;	
}
/* 관리자 컨텐츠 메인 영역 */
.form_section{
	width: 95%;
    margin-left: 2%;
    margin-top: 20px;
    border: 1px solid #dbdde2;
    background-color: #efefef;	
}
.form_section_title{
	padding: 20px 35px;	
}
.form_section_title label{
	display: block;
    font-size: 20px;
    font-weight: 800;
}
.form_section_content{
	padding: 20px 35px;
    border-top: 1px solid #dbdde2;	
}
.form_section_content input{
	width: 98%;
    height: 25px;
    font-size: 20px;
    padding: 5px 1%;
}
.form_section_content select{
	width: 98%;
    height: 35px;
    font-size: 20px;
    text-align-last: center;
}

/* 입력란 공란 경고 태그 */
.form_section_content span{	
	display: none;
    padding-top: 10px;
    text-align: center;
    color: #e05757;
    font-weight: 300;    
}



/* 버튼 영역 */
.btn_section{
	text-align: center;
	margin: 80px 0;
}
.btn{
    min-width: 180px;
    padding: 4px 30px;
    font-size: 25px;
    font-weight: 600;
    line-height: 40px;
}
.enroll_btn{
	background-color: #dbdde2;
	margin-left:15px;
}
</style>
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
						<li><a class="admin_list_01" href="#">상품
								등록</a></li>
						<li><a class="admin_list_02" href="#">상품
								관리</a></li>
						<lI><a class="admin_list_03" href="/author/authorEnroll">작가
								등록</a></lI>
						<lI><a class="admin_list_04" href="/author/authorManage">작가
								관리</a></lI>
						<lI><a class="admin_list_05">회원 관리</a></lI>
					</ul>
				</div>
				<div class="admin_content_wrap">
					<div class="admin_content_subject"><span>작가 등록</span></div>
						<div class="admin_content_main">
	                    	<form action="/author/authorEnroll" method="post" id="enrollForm">
	                    		<div class="form_section">
	                    			<div class="form_section_title">
	                    				<label>작가 이름</label>
	                    			</div>
	                    			<div class="form_section_content">
	                    				<input name="authorName">
	                    				<span id="warn_authorName">작가 이름을 입력해주세요.</span>
	                    			</div>
	                    		</div>
	                    		<div class="form_section">
	                    			<div class="form_section_title">
	                    				<label>소속 국가</label>
	                    			</div>
	                    			<div class="form_section_content">
	                    				<select name="nationId">
	                    					<option value="none" selected>=== 선택 ===</option>
	                    					<option value="01">국내</option>
	                    					<option value="02">국외</option>
	                    				</select>
	                    				<span id="warn_nationId">소속 국가를 선택해주세요.</span>
	                    			</div>
	                    		</div>
	                    		<div class="form_section">
	                    			<div class="form_section_title">
	                    				<label>작가소개</label>
	                    			</div>
	                    			<div class="form_section_content">
	                    				<input name="authorIntro" type="text">
	                    				<span id="warn_authorIntro">작가 소개를 입력해주세요.</span>
	                    			</div>
	                    		</div>
	                   		</form>
	                   			<div class="btn_section">
	                   				<button id="cancelBtn" class="btn">취 소</button>
		                    		<button id="enrollBtn" class="btn enroll_btn">등 록</button>
		                    	</div> 
                    </div>
	
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- class="wrap" -->
	</div>
	<!-- class="wrapper" -->
</body>
<script>
/* 등록 버튼 */
$("#enrollBtn").click(function(){  
	// 검사 통과 유무 변수
	let nameCheck = false;	// 작가 이름
	let nationCheck = false;	// 소속 국가
	let introCheck = false;	// 작가 소개
	
	// 입력값 변수
	let authorName = $('input[name=authorName]').val();
	let nationId = $('select[name=nationId]').val();
	let authorIntro = $('input[name=authorIntro]').val();
	
	// 공란 경고 span태그
	let wAuthorName = $('#warn_authorName');
	let wNationId = $('#warn_nationId');
	let wAuthorIntro = $('#warn_authorIntro');
	
	// 공란 체크
    if(authorName ===''){
        wAuthorName.css('display', 'block');
        nameCheck = false;
    } else{
        wAuthorName.css('display', 'none');
        nameCheck = true;
    }
    
    if(nationId ==='none'){
        wNationId.css('display', 'block');
        nationCheck = false;
    } else{
        wNationId.css('display', 'none');
        nationCheck = true;
    }    
    
    if(authorIntro ===''){
        wAuthorIntro.css('display', 'block');
        introCheck = false;
    } else{
        wAuthorIntro.css('display', 'none');
        introCheck = true;
    }
    
    // 최종 검사
    if(nameCheck && nationCheck && introCheck){
        $("#enrollForm").submit();    
    } else{
        return;
    }

});
 
/* 취소 버튼 */
$("#cancelBtn").click(function(){
    location.href="/admin/authorManage"
});


</script>
</html>