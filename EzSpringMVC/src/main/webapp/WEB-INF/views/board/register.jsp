<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>게시물 등록</h1>
	<form action="/board/register.kh" method="post">
		<fieldset>
			<legend>게시글 등록</legend>
			제목: <input type="text" name="boardTitle"><br> 
			내용: <textarea cols="50" rows="4" name="boardContent"></textarea><br> 
			글쓴이: <span>${memberId }</span> <br>
			첨부파일:<input type="file"> <br> 
			<input type="submit" value="등록">
		</fieldset>
	</form>
</body>
</html>