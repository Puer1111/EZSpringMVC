<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>공지사항 등록</title>
	</head>
	<body>
	<h1>공지사항 등록</h1>
	
	<form action="/notice/register.kh" method="post">
	글쓴이: <span>${memberId }</span> <br>
	제목: <input type="text" name="noticeSubject"> <br>
	내용: <textarea rows="4" cols="50" name="noticeContent"></textarea>
	<button>등록</button>
	</form>	
	
	</body>
</html>