<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 내용 수정</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<form action="/board/update.kh" method="post" enctype="multipart/form-data">
	<input type="hidden" name="boardNo" value="${board.boardNo }">
	<input type="hidden" name="boardFilename" value="${board.boardFilename }">
	<input type="hidden" name="boardFileRename" value="${board.boardFileRename }">
	<input type="hidden" name="boardFilepath" value="${board.boardFilepath }">
	<input type="hidden" name="boardFilelength" value="${board.boardFilelength }">
		<fieldset>
			<legend>게시글 수정</legend>
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="boardTitle" value="${board.boardTitle }">
				</li>
			
				<li>
					<label>작성자</label>
					<span>${board.boardWriter }</span>
				</li>
			
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="boardContent">${board.boardContent }</textarea>
				</li>
			   	<li>
					<label>첨부파일</label>
					<c:if test="${not empty board.boardFilename }">
						<span><a href="../resources/bUploadFiles/${board.boardFilename }">${board.boardFilename }</a></span>
					</c:if>
					<input type="file" name="reloadFile">
				</li>
			</ul>
		</fieldset>
		<div>
			<input type="submit" value="수정하기">
			<button type="button" onclick="goBack();">뒤로가기</button>
		</div>
	</form>
	<script>
	function goBack(){
		history.go(-1);
	}
	</script>
</body>
</html>