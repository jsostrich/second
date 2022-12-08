<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인영역 LNB 메뉴</title>
</head>
<body>
	<nav id="mainLNB">
		<ul id="lnbMainMenu">
	    	<c:if test="${empty uId_Session }">
	    		<li class="lnbMainLi"><a href="#">로그인NO</a></li>
				<li class="lnbMainLi"><a href="#">어서오세요</a></li>
				<li class="lnbMainLi"><a href="#">menu3</a></li>
				<li class="lnbMainLi"><a href="#">menu4</a></li>
				<li class="lnbMainLi"><a href="#">menu5</a></li>
	    	</c:if>		
	    	<c:if test="${!empty uId_Session }">
	    		<li class="lnbMainLi"><a href="#">로그인YES</a></li>
				<li class="lnbMainLi"><a href="#">menu2</a></li>
				<li class="lnbMainLi"><a href="#">menu3</a></li>
				<li class="lnbMainLi"><a href="#">menu4</a></li>
				<li class="lnbMainLi"><a href="#">menu5</a></li>
	    	</c:if>
		</ul>
	</nav>
</body>
</html>