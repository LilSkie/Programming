<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>테스 홈 페이지 입니다.</h2>
<button onclick="location.href='memberjoinForm'">회원가입</button>
<button onclick="location.href='memberloginForm'">로그인</button>

	<h2>카카오로 회원가입</h2>
	<a href="kakaojoin">
	
		<img src="${pageContext.request.contextPath}/resources/img/카카오회원가입.png/">
	</a>
	<h2>네이비로 회원가입</h2>
	<a href="naverjoin">
	
		<img src="${pageContext.request.contextPath}/resources/img/네이버회원가입.PNG/">
	
	</a>

</body>
</html>