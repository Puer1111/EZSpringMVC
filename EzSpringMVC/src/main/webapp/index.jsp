<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Web</title>
</head>
<body>
<h1>환영합니다 스프링 웹입니다</h1>
<c:if test="${memberId eq null }">
<form action="/member/login.kh" method="post">
ID: <input type="text" name="memberId"> <br>
PW: <input type="password" name="memberPw"> <br>
<input type="submit" value = "로그인"><a href="/member/register.kh">회원가입</a>
</form>
</c:if>
<c:if test="${memberId ne null }">
${memberName }님 환영합니다.
<a href="/member/logout.kh">로그아웃</a>
<a href="/member/mypage.kh">마이페이지</a>
<c:if test="${memberId eq  'admin' }">
<a href="/notice/register.kh">공지사항 등록</a>
<a href="/board/register.kh">보드 등록</a>
</c:if>
</c:if>
</body>
</html>