<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center; margin:auto; width:auto;">
	<h2>리스트 목록입니다</h2>
	<table border="1">
	<tr>
	<th>아이디</th><th>비밀번호</th><th>이름</th><th>생년월일</th>
	<th>주소</th><th>이메일</th><th>전화번호</th><th>삭제</th><th>조회</th>
	</tr>	
	<c:forEach var="member" items="${memberList}">
	<tr>
	<td>${member.mid}</td>
	<td>${member.mpassword}</td>
	<td>${member.mname}</td>
	<td>${member.mbirth}</td>
	<td>${member.maddress}</td>
	<td>${member.memail}</td>
	<td>${member.mphone}</td>
	<td>${member.mfilename}</td>
	<td><a href="memberdelete?mid=${member.mid}"><button>삭제</button></a></td>
	<td><a href="memberview?mid=${member.mid}"><button>조회</button></a></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>