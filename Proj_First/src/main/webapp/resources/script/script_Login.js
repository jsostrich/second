/**
 * 
 */

$(function(){
	
	
	/* 로그인 버튼 전송 실행 */	
	$("#loginBtn").click(function(){		
		fnLoginSbm();		
	});
	
	/* 폼실행 엔터키 이벤트 처리 */	
	$(window).keydown(function(){
		let code = event.keyCode;
		if (code == 13) return false;
	});
	
	/* 폼실행 엔터키 이벤트 처리 */
	$(window).keyup(function(){		
		let code = event.keyCode;
		//alert("code : " + code);
		if (code == "13") fnLoginSbm();
    });
	
	
	function fnLoginSbm() {
		
		let uId = $("#uId").val().trim();
		$("#uId").val(uId);
		let uPw = $("#uPw").val().trim();		
		$("#uPw").val(uPw);
		
		
		if (uId == "") {
			
			$("#uId").focus();
			return;
		} else if (uPw == "") {
			alert("비밀번호를 입력해주세요.");
			$("#uPw").focus();
			return;
		} else {
			$("#loginFrm").submit();
		}
		
		
	}
	
	
	/* 아이디찾기 버튼 전송 실행 */	
	$("#findBtn").click(function(){		
		fnFindSbm();		
	});
	
	
	function fnFindSbm() {
		
		let uName = $("#uName").val().trim();
		$("#uName").val(uName);
		let uEmail = $("#uEmail").val().trim();		
		$("#uEmail").val(uEmail);
		
		
		if (uName == "") {
			$("#uName").focus();
			return;
		} else if (uEmail == "") {
			alert("이메일주소를 입력해주세요.");
			$("#uEmail").focus();
			return;
		} else {
			$("#findFrm").submit();
		}
		
		
	}
	
	
});