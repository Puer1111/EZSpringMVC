<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<h1>공지사항</h1>
	<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>내용</th>
		<th>날짜</th>
		<th>파일명</th>
	</tr>
<c:forEach items="${bList}" var="board">
	<tr>
		<td>${board.boardNo }</td>
		<td><a href="/board/detail.kh?boardNo=${board.boardNo}&currentPage=${currentPage}">${board.boardTitle }</a></td>
		<td>${board.boardWriter }</td>
		<td>${board.boardContent }</td>
		<td>${board.bCreateDate }</td>
		<c:if test="${not empty board.boardFilename }">
		<td>O</td>
		</c:if>
		<c:if test="${ empty board.boardFilename }">
		<td>X</td>
		</c:if>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="center">
			<c:if test="${currentPage ne 1 }">
					<a href="/board/list.kh?currentPage=${startNavi-1}">이전</a>
				</c:if>			
				<c:forEach begin="${startNavi}" end="${endNavi}" var="i">
					<a href="/board/list.kh?currentPage=${i}">${i}</a>
				</c:forEach>
				
					<c:if test="${currentPage ne naviTotalCount }">
						<a href="/board/list.kh?currentPage=${endNavi+1}">다음</a>
					</c:if>	
		</td>
		<tr>
			<td colspan="4" align="center">
				<form action="/board/search.kh" method="post">
					<select name="searchCondition">
						<option value="all" <c:if test ="${searchCondition =='all'}">select</c:if>>전체</option>
						<option value="writer" <c:if test ="${searchCondition =='writer'}">select</c:if>>작성자</option>
						<option value="title" <c:if test ="${searchCondition =='title'}">select</c:if>>제목</option>
						<option value="content" <c:if test ="${searchCondition =='content'}">select</c:if>>내용</option>	
					</select>
				<input type="text" name="searchKeyword" placeholder="검색어를 입력하슈">
				<input type="submit" value="검색"> 
				</form>
			</td>
			<td>
				<a href="/board/register.kh">글쓰기</a>
			</td>
		</tr>
			
		</table>
		<script>
		var message="${message}";
		if(message){
			alert(message);
		}
		</script>
</body>
</html>