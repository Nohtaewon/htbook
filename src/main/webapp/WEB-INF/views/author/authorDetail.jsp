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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
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
    margin-bottom:15px;
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

.admin_list_04{
    background-color: #c8c8c8;
} 


/* 관리자페이지 컨텐츠 영역 */
.admin_content_wrap{
    width: 80%;
    float:left;
    min-height:700px;
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
.form_section_content textarea{
    width: 98%;
    height: 170px;
    font-size: 20px;	
}
.form_section_content select{
	width: 98%;
    height: 35px;
    font-size: 20px;
    text-align-last: center;
}
.input_block{
	background-color:#f9f9f9;	
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
.modify_btn{
	background-color: #b8d3e8;
	margin-left:15px;
}


/* float 속성 해제 */
.clearfix{
	clear: both;
}
</style>
</head>
<body>
<div class="admin_content_wrap">
	<div class="admin_content_subject"><span>작가 상세</span></div>
	<div class="admin_content_main">
		<div class="form_section">
			<div class="form_section_title">
				<label>작가 번호</label>
			</div>
			<div class="form_section_content">
				<input class="input_block" name="authorId" readonly="readonly" value="<c:out value='${authorInfo.authorId }'></c:out>">
				
			</div>
		</div>                    
		<div class="form_section">
			<div class="form_section_title">
				<label>작가 이름</label>
			</div>
			<div class="form_section_content">
				<input class="input_block" name="authorName" readonly="readonly" value="<c:out value='${authorInfo.authorName }'></c:out>" >
			</div>
		</div>
		<div class="form_section">
			<div class="form_section_title">
				<label>소속 국가</label>
			</div>
			<div class="form_section_content">
				<select class="input_block" name="nationId" >
					<option value="none" selected disabled="disabled">=== 선택 ===</option>
					<option value="01" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '01' ?'selected':''}"/>>국내</option>
					<option value="02" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '02' ?'selected':''}"/>>국외</option>
				</select>
			</div>
		</div>
		<div class="form_section">
			<div class="form_section_title">
				<label>작가소개</label>
			</div>
			<div class="form_section_content">
				<textarea class="input_block" name="authorIntro" readonly="readonly"><c:out value='${authorInfo.authorIntro }'/></textarea>
			</div>
		</div>
		<div class="form_section">
			<div class="form_section_title">
				<label>등록 날짜</label>
			</div>
			<div class="form_section_content">
				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.regDate}" pattern="yyyy-MM-dd"/>">
			</div>
		</div>
		                   		                   		<div class="form_section">
			<div class="form_section_title">
				<label>수정 날짜</label>
			</div>
			<div class="form_section_content">
				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.updateDate}" pattern="yyyy-MM-dd"/>">
	   			</div>
	   		</div>
	   			<div class="btn_section">
	   				<button id="cancelBtn" class="btn">작가 목록</button>
	     			<button id="modifyBtn" class="btn modify_btn">수 정</button>
		     	</div> 
		    </div>                    
		</div>
	
		<form id="moveForm" method="get">
			<input type="hidden" name="authorId" value='<c:out value="${authorInfo.authorId }"/>'>
			<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
			<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
			<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
		</form>
</body>
<script>
	let moveForm = $("#moveForm");
	
	// 작가 관리 페이지 이동 버튼
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		
		$("input[name=authorId]").remove();
		moveForm.attr("action", "/author/authorManage")
		moveForm.submit();
	});
	
	// 작가 수정페이지 이동 버튼
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
		
		moveForm.attr("action", "/author/authorModify");
		moveForm.submit();
	});

</script>
</html>