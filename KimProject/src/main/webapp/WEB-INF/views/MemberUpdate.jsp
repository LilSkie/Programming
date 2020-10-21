<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="memberupdateprocess" method="post" name="MemberUpdate">
		<h2>수정 페이지입니다.</h2>
		아이디:<input type="text" name="mid" id="mid" value="${memberUpdate.mid}" readonly><br><br>
		비밀번호:<input type="password" name="mpassword" id="mpssword" value="${memberUpdate.mpassword}"><br><br>
		이름:<input type="text" name="mname" id="mname" value="${memberUpdate.mname}"><br><br> 
		생년월일:<input type="date" name="mbirth" id="mbirth" value="${memberUpdate.mbirth}"><br><br>
		주소:<input type="text" name="maddress" id="maddress" value="${memberUpdate.maddress}"><br><br>
		이메일:<input type="text" name="memail" id="memail" value="${memberUpdate.memail}"><br><br>
		전화번호:<input type="text" name="mphone" id="mphone" value="${memberUpdate.mphone}"><br><br>
		<input type="submit" value="수정">
	</form>

</body>
</html>