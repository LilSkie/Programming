<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center; margin:auto; width:auto">
<h2>회원 상세조회 페이지입니다.</h2>
아이디:${memView.mid}<br><br>
비밀번호:${memView.mpassword}<br><br>
이름:${memView.mname}<br><br>
생년월일:${memView.mbirth}<br><br>
주소:${memView.maddress}<br><br>
이메일:${memView.memail}<br><br>
전화번호:${memView.mphone}<br><br>
</body>
</html>