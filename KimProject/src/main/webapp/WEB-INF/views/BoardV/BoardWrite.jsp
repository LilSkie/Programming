<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function Write() {

		BoardWriteForm.submit();

	}
</script>
</head>
<body style="text-align: center; margin: auto; width: auto">
	<form action="boardwrite" method="post" name="BoardWriteForm">	
		<h2>글작성 페이지입니다.</h2>
		작성자:<input type="text" name="bwriter" id="bwriter"><br><br>
		비밀번호:<input type="password" name="bpassword" id="bpassword"><br><br>
		제목:<input type="text" name="btitle" id="btitle"><br><br>
		내용:<textarea name="bcontents" id="bcontents" rows="5"></textarea><br><br>
	</form>
	<button onclick="Write()">글작성</button>
</body>
</html>