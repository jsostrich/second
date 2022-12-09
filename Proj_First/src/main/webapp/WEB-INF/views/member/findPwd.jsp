<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>비밀번호 찾기</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_Login.js?ver=123"></script>
</head>

<body>
    <div id="wrap">
    	
    	<!--  헤더템플릿 시작 -->
		<%@ include file="/ind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="dFlex">
    	
<%--     		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/ind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div> 
--%>
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="findDiv">
				<br><br><br>
				<form id="findFrm" name="findFrm" method="post">
					<p style="font-size:20px; font-weight: 700; color:#6b6b6b;">
						&nbsp;&nbsp;&nbsp;비밀번호 찾기
					</p>
					
	        		<p>&nbsp;&nbsp;&nbsp;&nbsp;가입된 아이디와 이메일 주소를 입력하세요</p>
		        	<div id="findArea">	        	
		        		<div id="findInput">
		        			<input type="text" name="uId" placeholder="아이디 입력" id="uId">
		        			<input type="text" name="uEmail" placeholder="이메일 입력" id="uEmail">
		        		</div>
		        		
		        		<button type="button" id="findBtn">확인</button>
		        		
		        	</div>
		        	<!-- div#loginArea -->
	        	
	        	</form>

    		</div>
    		<!-- 실제 작업 영역 끝 div.loginDiv -->
    		    	
    	</main>
    	<!--  main#main  -->
    
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
        
    </div>
    <!-- div#wrap -->

</body>

</html>