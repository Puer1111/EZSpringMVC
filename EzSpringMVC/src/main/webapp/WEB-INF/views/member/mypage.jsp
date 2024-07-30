<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<h1>회원 정보</h1>
<fieldset>
<legend>마이페이지</legend>
<ul>
	<li>
	<label>아이디</label>
	<span>${member.memberId }</span>
	</li>
	<li>
	<label>이름</label>
	<span>${member.memberName }</span>
	</li>
	<li>
	<label>나이</label>
	<span>${member.memberAge }</span>
	</li>
	<li>
	<label>성별</label>
	<span>${member.memberGender}</span>
	</li>
	<li>
	<label>이메일</label>
	<span>${member.memberEmail }</span>
	</li>
	<li>
	<label>전화번호</label>
	<span>${member.memberPhone }</span>
	</li>
	<li>
	<label>주소</label>
	<span>${member.memberAddress }</span>
	</li>
	
</ul>
</fieldset>

<a href="/index.jsp">메인페이지로 이동</a>
<a href="/member/update.kh">수정하기</a>
<a href="javascript:void(0);" onclick="checkDelete();">회원탈퇴</a>
<script>
function checkDelete(){
	if(confirm("진짜 삭제 하시나요")){
		location.href="/member/delete.kh";
	}
}
</script>
</body>
</html>