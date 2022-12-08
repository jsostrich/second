<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 중복 체크</title>
    <link rel="stylesheet" href="/resources/style/style_Common.css">    
    <script src="/resources/source/jquery-3.6.0.min.js"></script>
    <script src="/resources/script/script_Join.js?ver=123"></script>
</head>

<body>
    <div id="wrap_Popup">
    	
    	<div>
    		<h1>${uId }</h1>
    		<br>
    		<span id ="uId">${uId }</span>
				<span>${msg }<hr><div id="closeBtnArea">
						<input type="button" id="close" value="${result }">
					</div>
			</span>
    	</div>
    </div>
    <!-- div#wrap -->

</body>

</html>






