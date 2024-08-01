<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<c:if test="${fn:length(sList) == 0 }">
		<tr>
		<td colspan="5">No data</td>
		</tr>
	</c:if>

	<c:forEach items="${sList}" var="board">
	<tr>
		<td>${board.boardNo }</td>
		<td><a href="/board/detail.kh?boardNo=${board.boardNo}&currentPage=${currentPage}">${board.boardTitle }</a></td>
		<td>${board.boardWriter }</td>
		<td>${board.boardContent }</td>
		<td>${board.bCreateDate }</td>
		<td>${board.boardFilename }</td>
	</tr>
	</c:forEach>
	<tr>
	
		<td colspan="5" align="center">
		<c:url value="/board/search.kh" var="searchUrl">
		<c:param name="searchCondition" value="${searchCondition}"></c:param>
				<c:param name="searchKeyword" value="${searchKeyword }"></c:param>
		</c:url>
		<%-- <c:url value="/board/search.kh" var="prevUrl">
				<c:param name="currentPage" value="${startNavi-1}"></c:param>
				<c:param name="searchCondition" value="${searchCondition}"></c:param>
				<c:param name="searchKeyword" value="${searchKeyword }"></c:param>
		</c:url> --%>
		<c:if test="${currentPage ne 1 }">
			<a href="${searchUrl}&currentPage=${startNavi-1}">이전</a>
		</c:if>			
		<c:forEach begin="${startNavi}" end="${endNavi}" var="i">
		<%-- <c:url value="/board/search.kh" var="pageUrl">
				<c:param name="currentPage" value="${i}"></c:param>
				<c:param name="searchCondition" value="${searchCondition}"></c:param>
				<c:param name="searchKeyword" value="${searchKeyword }"></c:param>
		</c:url>--%>			
		<a href="${searchUrl}&currentPage=${i}">${i}</a>
		</c:forEach>
		<%-- <c:url value="/board/search.kh" var="nextUrl">
				<c:param name="currentPage" value="${endNavi+1}"></c:param>
				<c:param name="searchCondition" value="${searchCondition}"></c:param>
				<c:param name="searchKeyword" value="${searchKeyword }"></c:param>
		</c:url> --%>	
		<c:if test="${endNavi ne naviTotalCount }">
			<a href="${searchUrl}&currentPage=${endNavi+1}">다음</a>
		</c:if>	
		</td>
		<tr>
			<td colspan="4" align="center">
				<form action="/board/search.kh" method="post">
					<select name="searchCondition">
						<option value="all" <c:if test ="${searchCondition eq'all'}">select</c:if>>전체</option>
						<option value="writer" <c:if test ="${searchCondition eq 'writer'}">select</c:if>>작성자</option>
						<option value="title"  <c:if test ="${searchCondition eq 'title'}">select</c:if>>제목</option>
						<option value="content" <c:if test ="${searchCondition eq 'content'}">select</c:if>>내용</option>	
					</select>
				<input type="text" name="searchKeyword" placeholder="검색어를 입력하슈" value="${searchKeyword }">
				<input type="submit" value="검색"> 
				</form>
			</td>
			<td>
				<a href="board/register.kh">글쓰기</a>
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