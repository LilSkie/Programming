<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function Login() {

		MemberLogin.submit();

	}

	
</script>





</head>
<body style="text-align:center; margin:auto; width:auto">
	<h2>로그인 페이지 입니다.</h2>
	<form action="memberlogin" method="post" name="MemberLogin">
		아이디:<input type="text" name="mid" id="mid"><br><br>
		비밀번호:<input type="password" name="mpassword" id="mpassword"><br><br>	
	</form>
	<button onclick="Login()">로그인</button>



</body>
</html>