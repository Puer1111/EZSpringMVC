<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회</title>
</head>
<body>
	<h1>게시글 상세조회</h1>
	<ul>
		<li>
			<label>제목</label>
			<span>${board.boardTitle }</span>
			
		</li>
		<li>
			<label>작성자</label>
			<span>${board.boardWriter }</span>
		</li>
		<li>
			<label>내용</label>
			<span>${board.boardContent }</span>
		</li>
		<li>
			<label>날짜</label>
			<span>${board.bCreateDate }</span>
		</li>
	</ul>

</body>
</html>