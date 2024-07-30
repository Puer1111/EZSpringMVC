<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
<form action="/member/update.kh" method="POST">
	<input type="hidden" name="memberId" value="${member.memberId }">
	
	<c:if test="${member.memberAge ne 0 }">
	<input type="hidden" name="memberAge" value="${member.memberAge }">
	</c:if>
	
	<c:if test="${member.memberGender ne null }">
	<input type="hidden" name="memberGender" value="${member.memberGender }">
	</c:if>
<fieldset>
<legend>회원 정보 수정</legend>
<ul>
	<li>
	<label>아이디</label>
	<span>${member.memberId }</span>
	<!-- span 은 값이 안넘어감 -->
	</li>
	
	<li>
	<label>비밀번호</label>
	<input type="password" name="memberPw" value="${member.memberPw }">
	</li>
	
	<li>
	<label>이름</label>
	<span>${member.memberName }</span>
	</li>
	
	<c:if test="${member.memberAge eq 0 }">	
	<li>	
	<label>나이</label>
	<input type="number" name="memberAge" value="${member.memberAge }">
	</li>
	</c:if>
	
	<c:if test="${member.memberGender eq null }">
	<li>
	<label>성별</label>
	남자 <input type="radio" name="memberGender" value="M">
	여자 <input type="radio" name="memberGender" value="F">
	</li>
	</c:if>	
	
	<li>
	<label>이메일</label>
	<input type="text" name="memberEmail" value="${member.memberEmail }">
	</li>
	
	<li>
	<label>전화번호</label>
	<input type="text" name="memberPhone" value="${member.memberPhone }">
	</li>
	
	<li>
	<label>주소</label>
	<input type="text" name="memberAddress" value="${member.memberAddress }">
	</li>
</ul>
<div>
	<input type="submit" value="수정하기">
</div>
</fieldset>
</form>
</body>
</html>