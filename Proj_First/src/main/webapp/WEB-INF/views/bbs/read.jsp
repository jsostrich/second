<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글내용 보기</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<link rel="stylesheet" href="/resources/style/style_BBS.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_BBS.js"></script>
</head>

<body>
    <div id="wrap">
    	
    	<!--  헤더템플릿 시작 -->
		<%@ include file="/ind/headerTmp.jsp" %>
    	<!--  헤더템플릿 끝 -->    	
    	
    	
    	<main id="main" class="dFlex">
    	
    		<div id="lnb">
	    		<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="/ind/mainLnbTmp.jsp" %>
	    		<!--  메인 LNB 템플릿 끝 -->    	
    		</div>
    		
    		
	    	<!-- 실제 작업 영역 시작 -->
    		<div id="contents" class="bbsRead">

				<!--  게시글 상세보기 페이지 내용 출력 시작 -->
				<h2>${result.subject }</h2>
				
				<table id="readTbl">
					<tbody id="readTblBody">
						<tr>
							<td>작성자</td>  <!-- td.req 필수입력 -->
							<td>${result.uName }/${uId_Session }</td>
							<td>등록일</td>  <!-- td.req 필수입력 -->
							<td>${result.regTM }</td>
						</tr>
						<tr>
							<td>첨부파일</td> <!-- td.req 필수입력 -->
							<fmt:parseNumber value="${result.fileSize }"
								type="number" var="fileSize"/>
							<c:if test="${fileSize > 1024 }">
								<c:set var="fUnit" value="KBytes"/>
								<c:set var="fileSize2" value="${fileSize /1024}"/>
							</c:if>
							<c:if test="${fileSize <= 1024 }">
							<c:set var="fUnit" value="Bytes"/>
								<c:set var="fileSize2" value="${fileSize }"/>
							</c:if>
							<td colspan="3">
								<input type="hidden" name="fileName" 
									value="${result.fileName }" id="hiddenFname">
								<c:if test="${!empty result.fileName }">
									<span id="downloadFile">${result.fileName }</span>							
									(<span>${fileSize2 }${fUnit }</span>)
								</c:if>
								<c:if test="${empty result.fileName }">
									등록된 파일이 없습니다.
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4" id="readContentTd"><pre>${result.content }
							</pre></td>
						</tr>
						<tr>
							<td colspan="4" > 댓글! </td>
						</tr>
						<tr>
						<td >
						ID : </td>
						<td colspan="3" id="c_uId">${uId_Session }</td>
						</tr>			
						<tr>		
						<td colspan="4" >
						<textarea name="c_comment" style="width:600px;float: left;" 
							id="c_comment">여기에 댓글 입력</textarea>
						<button type="button" id="commentBtn" >댓글입력</button>
						</td>
						</tr>
					</tbody>
					 
					<tfoot id="readTblFoot">	
						<tr>
							<td colspan="4" id="footTopSpace"></td>							
						</tr>			     
						<tr>
							<td colspan="4" id="articleInfoTd">
								<span>조회수 : ${result.readCnt }</span>
								<span>IP : ${result.ip }</span>							
							</td>							
						</tr>
						<tr>
							<td colspan="4" id="hrTd"><hr></td>							
						</tr>
						<tr>
							<td colspan="4" id="btnAreaTd" class="read">
								<button type="button" id="listBtn">
								<c:if test="${null eq result.keyWord }">
									리스트
									</c:if>
									<c:if test="${!null eq result.keyWord }">
									검색목록
									</c:if> </button>
								<button type="button" id="replyBtn">답 변</button>
								<c:if test="${!empty uId_Session }">
									<c:if test="${uId_Session eq result.uId }">
										<button type="button" id="delBtn">삭 제</button>
										<button type="button" id="modBtn">수 정</button>
									</c:if>
								</c:if>
							</td>
						</tr>
					</tfoot>
					 
				</table>
				<input type="hidden" name="nowPage" 
					value="${map.nowPage }" id="nowPage">
				<input type="hidden" name="num" value="${map.num }" id="num">
				
				<!-- 검색어전송 시작 -->
				<input type="hidden" id="pKeyField" value="${map.keyField }">
				<input type="hidden" id="pKeyWord" value="${map.keyWord }">
				<input type="hidden" name="uName" value="${result.uName }" >
				<!-- 검색어전송 끝 -->
			  
				<!--  게시글 상세보기 페이지 내용 출력 끝 -->
				

    		</div>
    		<!-- 실제 작업 영역 끝 -->
    		    	
    	</main>
    	<!--  main#main  -->
    
        	   	
    	<!--  푸터템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp" %>
    	<!--  푸터템플릿 끝 -->  
        
    </div>
    <!-- div#wrap -->

</body>

</html>