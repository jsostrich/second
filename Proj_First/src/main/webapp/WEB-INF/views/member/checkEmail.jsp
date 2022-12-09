<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>인증번호 입력</title>
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
				<form id="findFrm" name="findFrm" method="post" >
					<p style="font-size:20px; font-weight: 700; color:#6b6b6b;">
						&nbsp;&nbsp;&nbsp;인증번호 입력
					</p>
					
	        		<p>&nbsp;&nbsp;&nbsp;&nbsp;이메일로 발송된 인증번호를 입력하세요</p>
		        	<div id="findArea">	        	
		        		<div id="findInput">
		        			<input type="text" name="verify" placeholder="인증번호 입력" id="verify">
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