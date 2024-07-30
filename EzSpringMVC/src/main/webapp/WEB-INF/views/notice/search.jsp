<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>공지사항 목록 조회</title>
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
		<th>조회수</th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(sList) == 0 }">
		<tr>
		<td colspan="5">No data</td>
		</tr>
		</c:when>
	
<%-- 
	<c:if test="${fn:length(sList) == 0 }">
	<tr>
	<td colspan="5">No data</td>
	</tr>
	</c:if> --%>
<%-- 	<c:if test="${fn:length(sList) > 0 }"> --%>
		<c:otherwise>
			<c:forEach items="${sList}" var="notice">
			<tr>
				<td>${notice.noticeNo }</td>
				<td><a href="/notice/detail.kh?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
				<td>${notice.noticeWriter }</td>
				<td>${notice.noticeContent }</td>
				<td>${notice.updateDate }</td>
				<td>${notice.viewCount }</td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<%-- </c:if> --%>
<%-- 	<tr>
		<td colspan="5" align="center">
			<c:if test="${currentPage ne 1 }">
					<a href="/notice/list.kh?currentPage=${startNavi-1}">이전</a>
				</c:if>
				
				<c:forEach begin="${startNavi}" end="${endNavi}" var="i">
					<c:if test="${currentPage eq i }">
						<span style="font-weight:bolder; font-size:30px"></span>
					</c:if>
				<c:if test="${ currentPage ne i}">
					<a href="/notice/list.kh?currentPage=${i}">${i}</a>
			<!-- /c:if> -->
				</c:forEach>
				
					<c:if test="${currentPage ne naviTotalCount }">
						<a href="/notice/list.kh?currentPage=${endNavi+1}">다음</a>
					</c:if>	
		</td>
	</tr> --%>
	<tr> 
		<td colspan="4" align="center">
		<form action="/notice/search.kh" method="post">
			<select name="searchCondition">
			
			<option value="all" 
			<c:if test="${searchCondition eq 'all' }">selected</c:if>>전체</option>		
			<option value="writer" 
			<c:if test="${searchCondition eq 'writer' }">selected</c:if>>작성자</option>
			<option value="subject" 
			<c:if test="${searchCondition eq 'subject' }">selected</c:if>>제목</option>
			<option value="content" 
			<c:if test="${searchCondition eq 'content' }">selected</c:if>>내용</option>
		
			</select>
			<input type="text" name ="searchKeyword" placeholder="검색어를 입력하시오" value="${searchKeyword}">
			<input type="submit" value="검색">
		</form>
		</td>
		<td>
			<button type="button">글쓰기</button>
		</td>
	</tr>
		</table>
	</body>
</html>