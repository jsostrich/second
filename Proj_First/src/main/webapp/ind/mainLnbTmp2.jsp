<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li class="lnbMainLi"><a href="#">menu1</a></li>
				<li class="lnbMainLi"><a href="#">menu2</a></li>
				<li class="lnbMainLi"><a href="#">menu3</a></li>
				<li class="lnbMainLi"><a href="#">menu4</a></li>
				<li class="lnbMainLi"><a href="#">menu5</a></li>
			</c:if>
			<c:if test="${!empty uId_Session }">
				<c:choose>
					<c:when test="${user.grade == 2 }">
						<li class="lnbMainLi"><a href="/memberMod">회원정보수정</a></li>
						<li class="lnbMainLi"><a href="/memberQuit">회원탈퇴</a></li>
						<li class="lnbMainLi"><a href="#">menu3</a></li>
						<li class="lnbMainLi"><a href="#">menu4</a></li>
						<li class="lnbMainLi"><a href="#">menu5</a></li>
					</c:when>
					<c:otherwise>
						<li class="lnbMainLi"><a href="/memberMgr">회원 관리</a></li>
						<li class="lnbMainLi"><a href="/boardMgr">게시글 관리</a></li>
						<li class="lnbMainLi"><a href="#">menu3</a></li>
						<li class="lnbMainLi"><a href="#">menu4</a></li>
						<li class="lnbMainLi"><a href="#">menu5</a></li>
					</c:otherwise>
				</c:choose>
			</c:if>
		</ul>
	</nav>
</body>
</html>