<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>헤더템플릿</title>
</head>
<body>

	<header id="header" class="dFlex">
		<!-- 로고, GNB -->
		<div id="headerLogo">
			<a href="/"> <img src="/resources/images/headerLogo.png" alt="헤더로고이미지" style="height:100px"> </a>
		</div>
		<nav id="gnb">

			<ul id="mainMenu" class="dFlex">

				<c:if test="${empty uId_Session }">
					<li class="mainLi"><a href="/">HOME</a></li>
					<li>|</li>
					<li class="mainLi"><a href="#">공지사항</a></li>
					<li>|</li>
					<li class="mainLi"><a href="/list">게시판</a></li>
					<li>|</li>
					<li class="mainLi"><a href="/login">로그인</a></li>
					<li>|</li>
					<li class="mainLi"><a href="/joinAgreement">회원가입</a></li>
				</c:if>
				<c:if test="${!empty uId_Session }">
					<c:choose>
						<c:when test="${user.grade == 2 }">
							<li class="mainLi"><a href="/">HOME</a></li>
							<li>|</li>
							<li class="mainLi"><a href="#">공지사항</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/list">게시판</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/logout">로그아웃(${user.grade })</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/myPage">마이페이지</a></li>
						</c:when>
						<c:otherwise>
							<li class="mainLi"><a href="/">HOME</a></li>
							<li>|</li>
							<li class="mainLi"><a href="#">공지사항</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/list">게시판</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/logout">로그아웃(${user.grade })</a></li>
							<li>|</li>
							<li class="mainLi"><a href="/adminPage">관리자페이지</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>

			</ul>

		</nav>
	</header>
	<!--  header#header  -->
	<hr class="sepLine">

</body>
</html>