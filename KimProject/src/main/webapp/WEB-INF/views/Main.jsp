<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function Update(){

	location.href="memberupdate?mid=${sessionScope.LoginId}";
		
}

function BoardList(){
	
	location.href="boardlist";
	
}
function Logout(){
	
	location.href="memberlogout"
	
}



</script>
</head>
<body style="text-align:center; margin:auto; width:auto">
<h2>메인 페이지 입니다.</h2>


아이디:${sessionScope.LoginId} 환영합니다.

<c:if test="${sessionScope.LoginId eq 'admin' }">

	<a href="memberlist">회원목록조회</a>
	<button onclick="Update()">수정</button>

</c:if>

<c:if test="${sessionScope.LoginId ne 'admin'}">


</c:if>

<br><button onclick="BoardList()">글목록</button>
<input type="button" onclick="Logout()" value="로그아웃">



</body>
</html>