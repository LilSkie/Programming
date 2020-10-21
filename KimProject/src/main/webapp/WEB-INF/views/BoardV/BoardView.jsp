<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글상세 조회 페이지</h2>
	글번호:${boardview.bnumber}<br><br>
	작성자:${boardview.bwriter}<br><br>
	제목:${boardview.btitle}<br><br>
	내용:${boardview.bcontents}<br><br>
	작성날짜:${boardview.bdate}<br><br>
	조회수:${boardview.bhits}<br><br>




</body>
</html>