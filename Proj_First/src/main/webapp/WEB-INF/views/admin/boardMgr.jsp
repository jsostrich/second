<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<link rel="stylesheet" href="/resources/style/style_admin.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_admin.js"></script>
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
    		<div id="contents" class="bbsList">
    		
			<c:if test="${empty map.keyWord }">
	    		<div id="pageInfo" class="dFlex">
					<span>전체 게시글 :  ${fn:length(list) } 개</span>
					<span>페이지 :  ${vo.nowPage } / ${vo.totalPage }</span>
				</div>				
			</c:if>
			<c:if test="${!empty map.keyWord }">
	    		<div id="pageInfo" class="dFlex">
					<span>검색 결과 :  ${fn:length(list) } 개</span>
					<span>페이지 :  ${vo.nowPage } / ${vo.totalPage }</span>
				</div>				
			</c:if>
			<table id="boardList">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>이름</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>		
					<tr>
						<td colspan="5" class="spaceTd"></td>
					</tr>		
				</thead>
				<tbody>
			<c:if test="${fn:length(list)==0 }">
				<tr>
					<td colspan="5">
					게시물이 없습니다.
					</td>
				</tr>	
			</c:if>
			<c:if test="${fn:length(list)!=0 }">
				<c:forEach var="list" items="${list }" varStatus="status"
					begin="${vo.nowPage*5 - 5}" 
						end="${vo.nowPage*5 - 1}">
					<tr class="prnTr" onclick="read('${list.num }', '${vo.nowPage }')">
						<td>
							<c:if test="${list.depth ==0 }">
							${list.num }  <!-- 답변글이 아님을 의미함 -->
							</c:if>
						</td>
						<td class="subjectTd">
							<c:if test="${list.depth !=0 }">
								<c:forEach var="list2" items="${list }" varStatus="status"
										begin="${vo.nowPage*5 - 5 }" end="${vo.nowPage*5 - 1}">
							&nbsp;&nbsp;<img src='/resources/images/replyImg.png' alt='reply'>
								</c:forEach>
								${list2.subject }
							</c:if>
							${list.subject }
						</td>
						<td>${list.uName }</td>
						<td>${list.regTM }</td>
						<td>${list.readCnt }</td>
					</tr>
				</c:forEach>
			</c:if>
					<tr id="listBtnArea">
						<td colspan="2">
						<c:if test="${empty uId_Session }">
							<button type="button" id="loginAlertBtn" class="listBtnStyle">글쓰기</button>
						</c:if>
						<c:if test="${!empty uId_Session }">
							<button type="button" id="writeBtn" class="listBtnStyle">글쓰기</button>
						</c:if>
						
						</td>
						<td colspan="3">
							<form name="searchFrm" class="dFlex" id="searchFrm">
								<div>
									<select name="keyField" id="keyField">
										<option value="subject" 
										<c:if test="${map.keyField eq 'subject' }">selected	</c:if>
										>제  목</option>
										<option value="uName" 
												<c:if test="${map.keyField eq 'uName' }">selected	</c:if>
										>이  름</option>
										<option value="content" 
												<c:if test="${map.keyField eq 'content' }">selected </c:if>
										>내  용</option>
									</select>
								</div>
								
								<div>
									<input type="text" name="keyWord" id="keyWord"
									  id="keyWord" size="20" maxlength="30" value="${map.keyWord }">
								</div>
								<div>
									<button type="button" id="searchBtn" class="listBtnStyle">검색</button>
								</div>
							</form>
							<!-- 검색결과 유지용 매개변수 데이터시작 -->
							<input type="hidden" id="pKeyField" value="${map.keyField }">
							<input type="hidden" id="pKeyWord" value="${map.keyWord }">
							<!-- 검색결과 유지용 매개변수 데이터끝 -->
						
						</td>
					</tr>  <!-- tr#listBtnArea -->
					
					<tr id="listPagingArea">
					
					<c:set var="ps" value="${(vo.nowBlock-1) * vo.pagePerBlock +1 }"/>
					
					<c:if test="${vo.nowBlock < vo.totalBlock }">
						<c:set var="pe" value="${ps + vo.pagePerBlock -1 }"/>
					</c:if>
					
					<c:if test="${vo.nowBlock >= vo.totalBlock }">
						<c:set var="pe" value="${vo.totalPage }"/>
					</c:if>
					
					<!-- 페이징(= 페이지 나누기) 시작 -->
						<td colspan="5" id="pagingTd">
						<c:if test="${vo.totalPage!=0 }">
							<c:if test="${vo.nowBlock>1 }">
								<span class="moveBlockArea" onclick="moveBlock('${vo.nowBlock - 1 }', '${vo.pagePerBlock }', 'pb')">
								&lt;
								</span>
							</c:if>
							<c:if test="${vo.nowBlock<=1 }">
								<span class="moveBlockArea" ></span>
							</c:if>
					<!-- 페이지 나누기용 페이지 번호 출력 시작  -->
					<c:forEach var="list" varStatus="status" 
						begin="${ps }" end="${pe }">
						<c:if test="${status.count == vo.nowPage }">
							<span class="nowPageNum">${status.count }</span>
						</c:if>
						<c:if test="${status.count != vo.nowPage }">
							<span class="pageNum" onclick="movePage('${status.count+ps -1}')">
									${status.count +ps-1}
							 </span>
						</c:if>
					</c:forEach> 
					<!-- 페이지 나누기용 페이지 번호 출력 끝  -->	
					<c:if test="${vo.totalBlock>vo.nowBlock }"> <!-- 다음 블럭이 남아 있다면 -->
						<span  class="moveBlockArea" onclick="moveBlock('${vo.nowBlock+1 }', '${vo.pagePerBlock }', 'nb')">
							&gt;
							</span>
					</c:if>
					<c:if test="${vo.totalBlock<=vo.nowBlock }">
						 <span class="moveBlockArea"></span>
					</c:if>
				</c:if>
				<c:if test="${vo.totalPage==0}">
					<b>[Paging Area]</b>
				</c:if>
				<br>						
						</td>
					</tr>
					
				</tbody>
			</table>
		
		
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