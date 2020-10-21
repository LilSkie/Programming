<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<SCRIPT src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></SCRIPT>	
<script>
	//회원가입
	function Join() {

		MemberJoin.submit();

	}
	//아이디
	function idFn(){
	var inputId = document.getElementById("mid").value;
	$.ajax({
		type : "post",
		url : "idoverlap",
		data : {"mid" : inputId},
	 	dataType : "text",
	 	success : function(result){	
	 		
	 		if(result == "OK"){
	 			idEq.style.color ="green";	 			
	 			document.getElementById("idEq").innerHTML="사용가능한 아이디입니다.";
	 			
	 		
	 		}else{
	 			
	 			idEq.style.color ="red";
	 			document.getElementById("idEq").innerHTML="사용이 불가능한 아이디입니다.";

	 		}
	 	},
	 	error : function(){
	 		alert("ajax 실패!!"); 		
	 	}
	});
	}
		

	//비밀번호
	function pwdCheck() {
		//비밀번호를 위한 정규식
		//소문자,숫자,특수문자가 포함되고 자릿수는 8~16자리 정규식
		var exp = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
		var pwd = document.getElementById("mpassword").value;
		var pwdch = document.getElementById("pwdch");
		if (pwd.match(exp)) {
			pwdch.style.color = "green";
			pwdch.innerHTML = "비밀번호 형식 맞습니다.";


		} else {
			pwdch.style.color = "red";
			pwdch.innerHTML = "비밀번호 형식이 맞지 않습니다.";

		}
	}
	//전화번호  
	function phonecheck() {
		var exp = /^\d{3}-\d{4}-\d{4}$/;
		var ph = document.getElementById("mphone").value;
		var phch = document.getElementById("phone");
		if (ph.match(exp)) {
			phch.style.color = "green";
			phch.innerHTML = "전화번호 형식이 맞습니다."
		} else {
			phch.style.color = "red";
			phch.innerHTML = "전화번호 형식이 맞지 않습니다."
		}
	}
	
	
	 function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("mextraaddress").value = extraAddr;
	                
	                } else {
	                    document.getElementById("mextraaddress").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('mpostcode').value = data.zonecode;
	                document.getElementById("maddress").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("mdetailaddress").focus();
	            }
	        }).open();
	    }
	 
	 
     function phonecheck(){
         var exp = /^\d{3}-\d{4}-\d{4}$/;
         var ph = document.getElementById("mphone").value;
         var phch = document.getElementById("mphone");
         if(ph.match(exp)){
             phch.style.color="green";
             phch.innerHTML = "전화번호 형식이 맞습니다."
         }else{
             phch.style.color="red";
             phch.innerHTML="전화번호 형식이 맞지 않습니다."
         }



     }

</script>



</head>
<body style="text-align: center; margin: auto; width: auto">
		카카오 아이디: ${kakaoId}
		카카오 아이디: ${naverId}
	<form action="memberjoin" method="post" name="MemberJoin" enctype="multipart/form-data">
		<h2>회원가입 페이지 입니다.</h2>
			<c:choose>
			<c:when test="${kakaoId ne null}">
			아이디 : <input type="text" name="mid" id="mid" onkeyup="idFn()"><br><span id="idEq"></span>
			<input type="hidden" name="kakaoId" value="${kakaoId}"><br>
			<span id="idEq"></span>
			</c:when>
			<c:when test="${naverId ne null}">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idFn()">
				<input type="hidden" name="naverId" value="${naverId}"><br>
				<span id="idEq"></span> 
			</c:when>
			<c:otherwise>
				아이디 : <input type="text" name="mid" id="mid" onkeyup="idFn()">
				<span id="idEq"></span> 	
			</c:otherwise>
			</c:choose>
			<br><br>
			비밀번호:<input type="text" name="mpassword" id="mpassword" placeholder="비밀번호를 입력하세요." onkeyup="pwdCheck()">
			<span style="font-size: 4px" id="pwdch"></span><br><br>
			 생년월일:<input type="date" name="mbirth" id="mbirth"><br><br>
			 이름:<input type="text" name="mname" id="mname"><br><br>
			 주소:<input type="text" id="mpostcode" name="mpostcode" placeholder="우편번호">
                <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" id="maddress" name="maddress" placeholder="주소"><br>
                <input type="text" id="mdetailaddress" name="mdetailaddress" placeholder="상세주소">
                <input type="text" id="mextraaddress" name="mextraaddress" placeholder="참고항목"><br><br>
			 이메일:<input type="email" name="memail" id="memail"><br><br>
			 전화번호:<input type="text" name="mphone" id="mphone" onkeyup="phonecheck()" onblur="disappear()">
				   <span id="phch"></span><br>
			 프로필사진:<input type="file" name="mfile" id="mfile " value="파일">
	</form>
	<button onclick="Join()">회원가입</button>
</body>
</html>