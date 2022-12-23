/**
 * 
 */
$(function(){
	
	
	/* 리스트 페이지 글쓰기 버튼 시작 /bbs/list.jsp */	
	$("#loginAlertBtn").click(function(){		
		alert("로그인 후 게시글을 작성하실 수 있습니다.");
	});	
	$("#writeBtn").click(function(){		
		location.href="/write";
	});
	/* 리스트 페이지 글쓰기 버튼 끝 /bbs/list.jsp */
	
	
	/* 글쓰기 페이지 게시글 등록 시작 /bbs/write.jsp */
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#writeFrm").attr("action", "/writeProc");
			$("#writeFrm").submit();
		}
	
	});	
	
	/* 글쓰기 페이지 게시글 등록 끝 /bbs/write.jsp */
	
	
	/* 게시글 삭제버튼 시작 /bbs/read.jsp */
	$("button#delBtn").click(function(){
		
		let chkTF = confirm("게시글을 삭제하시겠습니까?");
		
		if (chkTF) {
			let nowPage = $("input#nowPage").val().trim();
			let num = $("input#num").val().trim();
					
			let p3 = $("#pKeyField").val().trim();  // p3 : keyField
		    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		    
			let url = "/deleteProc?";
				url += "num="+num+"&nowPage="+nowPage;
				url += "&keyField="+p3;
				url += "&keyWord="+p4;
			location.href=url;
		} else {
			alert("취소하셨습니다.");	
		}
		
	});
	/* 게시글 삭제버튼 끝 /bbs/read.jsp */
	
	
	
	/* 게시글 내용보기페이지에서 수정버튼 시작 /bbs/read.jsp */
	$("td.read>button#modBtn").click(function(){
	
		let nowPage = $("input#nowPage").val().trim();
		let num = $("input#num").val().trim();
				
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
		let url = "/modify?";
			url += "num="+num;
			url += "&nowPage="+nowPage;
			url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
		location.href=url;
	});
	
	/* 게시글 내용보기페이지에서 수정버튼 끝 /bbs/read.jsp */
	
	 /* 댓글 작업 */
	 $(" td button#commentBtn").click(function(){
	 	let c_uId =$("#c_uId").html();
	 	let nowPage = $("#nowPage").val();
	 	let num =$("#num").val();
	 	let c_comment = $("#c_commentIn").val();
	 	let c_num = $("#num").val();
	 	
	 	if(c_uId==""){
	 		alert("로그인은 하셨나요?");
	 	}else{
		 	let url = "/comment?";
		 		url += "&c_comment="+c_comment;
		 		url += "&c_num="+c_num;
		 		url += "&num="+num;
		 		url += "&nowPage="+nowPage;
		 	location.href=url;
	 	}
	 });
	 /* 댓글 작업 끝 */
	 
	 /*댓글 수정 */
	 $("td button#editBtn").click(function(){
	 	let c_uId1 =$("#c_uId1").html();
	 	let c_uId =$("#c_uId").html();
	 	if(c_uId1==c_uId){
		 	$(this).parent().parent().find("#c_comment").attr("readonly",false);
		 	$(this).parent().parent().find("#editBtnOk").show();
		 	$(this).css('display','none');
	 	}else{
	 		alert("댓글쓴 당사자만 쓸 수 있습니다");
	 	}
	 	
	 });
	 
	 
	 $("[id='editBtnOk']").click(function(){
	 	let c_uId =$("#c_uId").html();
	 	let nowPage = $("#nowPage").val();
	 	let num =$("#num").val();
	 	let c_comment = $(this).parent().parent().find("#c_comment").val();
	 	alert(c_comment);
	 	let c_num = $("#c_num").val();
	 	if(c_uId==""){
	 		alert("로그인은 하셨나요?");
	 	}else{
		 	let url = "/commentEdit?";
		 		url += "&c_comment="+c_comment;
		 		url += "&c_num="+c_num;
		 		url += "&num="+num;
		 		url += "&nowPage="+nowPage;
		 	location.href=url;
	 	}
	 });
	 /*댓글 수정 끝*/
	 
	 /* 댓글삭제 */
	 $("[id='delBtnOk']").click(function(){
	 	let c_num = $(this).parent().parent().parent().find("#c_num").val();
	 	let c_uId =$("#c_uId").html();
	 	let num =$("#num").val();
	 	if(c_uId==""){
	 		alert("로그인은 하셨나요?");
	 	}else{
	 		let del = confirm("정말로 삭제하시겠어요?");
	 		if(del){
			 	let url = "/commentDel?";
			 		url += "&c_num="+c_num;
			 		url += "&num="+num;
			 	location.href=url;
	 		}else{
	 			alert("취소하셨습니다");
	 			return false;
	 		}
	 	}
	 });
	 
	 /* 댓글삭제끝 */
	 
	 /* 리댓글 */
	  $("[id='recomment']").click(function(){
	 	let re = $(this).parent().find("#c_comment").html();
	 	$(this).next("#recommentInsert").show();
	 	$(this).next("#recommentInsert").focus();
	 }); 
	 
	  $("[id='reBtnNo']").click(function(){
	 	$(this).parent().parent().hide();
	 });
	 
	 $("[id='reBtn']").click(function(){
	 	let num = $(this).parent().parent().parent().find("#c_num").val();
	 	let reco = $(this).parent().parent().parent().find("#recommentText").val();
	 	let readnum = $("#num").val();
	 	location.href="/recomment?num="+num+"&recomment="+reco+"&readnum="+readnum;
	 });
	 
	 
	 /* 리댓글 끝*/
	 
	/* 게시글 수정페이지에서 수정내용 전송 시작 /bbs/modify.jsp */
	$("td.update>button#modProcBtn").click(function(){
		let subject = $("#subject").val().trim();
		
		//let keyField = $("#keyField").val().trim();
		//let keyWord = $("#keyWord").val().trim();
		
		if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
		
			//alert("keyField : " + keyField + "\nkeyWord : " + keyWord);
			//return;
			$("#modifyFrm").submit();
		}
	
	});	
	/* 게시글 수정페이지에서 수정내용 전송 끝 /bbs/modify.jsp */
	
	
			
	/* 게시글 내용보기페이지에서 답변버튼 시작 /bbs/read.jsp */
	$("td.read>button#replyBtn").click(function(){
	
		let nowPage = $("input#nowPage").val().trim();
		let num = $("input#num").val().trim();
				
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		
		let url = "/reply?";
			url += "num="+num;
			url += "&nowPage="+nowPage;
			url += "&keyField="+p3;
	     	url += "&keyWord="+p4; 
		location.href=url;
	
	});
	/* 게시글 내용보기페이지에서 답변버튼 끝 /bbs/read.jsp */
	
	
	/* 답변입력양식 페이지에서 답변내용 전송 시작 /bbs/reply.jsp */
	$("td.reply>button#replyBtn").click(function(){
		
		let subject = $("#subject").val().trim();
		
		if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {		
			$("#replyFrm").submit();
		}
		
	});
	/* 답변입력양식 페이지에서 답변내용 전송 끝 /bbs/reply.jsp */		
	
	/* 파일 다운로드 시작 /bbs/read.jsp */
	$("div.bbsRead span#downloadFile").click(function(){
		let fileName = $("input#hiddenFname").val().trim();
		location.href="/download?fileName="+fileName;
	});
	
	/* 파일 다운로드 끝 /bbs/read.jsp */
	
	
	/* 리스트페이지 검색 시작 /bbs/list.jsp */	
	$("button#searchBtn").click(function(){
		let keyWord = $("#keyWord").val();    // 검색어에서는 .trim()을 지양하는 추세
		                                                      // 단, 로그인, 회원가입, 회원정보 수정에서 사용하는
                                                              // ID 에는 입력값 전후의 공백을 제거한다. 
		//alert("keyWord : " + keyWord +"\nkeyWord 글자수 : " + keyWord.length);
		if (keyWord=="") {
			alert("검색어를 입력해주세요.");
			$("#keyWord").focus();			
		} else {
			$("#searchFrm").submit();
		}
	});	
	/* 리스트페이지 검색 끝 /bbs/list.jsp */	
	
	
	/* 검색 결과를 유지한 리스트페이지 이동 시작 /bbs/read.jsp */
	$("#listBtn").click(function(){
		let param = $("#nowPage").val().trim();
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	     
		let url = "/list?nowPage=" + param;		    
		    url += "&keyField="+p3;
	     	url += "&keyWord="+p4 ; 
		location.href=url;
	});
	/* 검색 결과를 유지한 리스트페이지 이동 끝 /bbs/read.jsp */
	
	
});
	
	
/* 상세내용 보기 페이지 이동 시작 /bbs/list.jsp => read.jsp */
function read(p1, p2) {
	// p1 : num (게시글의 고유번호, 고유값, 키값 : Key Value)
	// p2 : nowPage (현재페이지)
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let param = "/read?num="+p1;
	     param += "&nowPage="+p2;
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ; 
	location.href=param;
}		
/* 상세내용 보기 페이지 이동 끝 /bbs/list.jsp => read.jsp  */



/* 리스트페이지 페이징 시작 /bbs/list.jsp */
function movePage(p1) {    // 페이지 이동
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord

	let param = "/list?nowPage="+p1;	    
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ; 
	location.href= param;

}
/* 리스트페이지 페이징 끝 /bbs/list.jsp */


/* 리스트페이지 페이징 블럭이동 시작 /bbs/list.jsp */
function moveBlock(p1, p2, param3) {    
	                // 이전 블럭 또는 이후 블럭으로 이동  p1 => nowBlock-1  또는 nowBlock+1

	let moveBlock = parseInt(p1);    // 이전 블럭의 시작페이지로 이동에 사용하는 소
	let pagePerBlock = parseInt(p2);	
	//alert("p1(moveBlock) : " + p1 + "\np2(pagePerBlock) : " + p2);
	
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	
	if (param3 == 'pb') {
		 param = "/list?nowPage="+(moveBlock*pagePerBlock);
	                                                          // moveBlock : nowBlock - 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ;
	} else if (param3 == 'nb' ) {		
		 param = "/list?nowPage="+(pagePerBlock*(moveBlock-1)+1);   
	                                                          // moveBlock : nowBlock + 1 
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ;
	}

	location.href=param;
}
/* 리스트페이지 페이징 블럭이동 끝 /bbs/list.jsp */
