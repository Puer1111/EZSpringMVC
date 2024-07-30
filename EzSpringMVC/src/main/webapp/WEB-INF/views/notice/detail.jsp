<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세조회</title>
</head>
<body>
	 <h1>공지사항 상세조회</h1>
	<ul>
		<li>
			<label>번호</label>
			<span>${notice.noticeNo }</span> <br>
			<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
 		</li>
		<li>
			<label>제목:</label>
			<span>${notice.noticeSubject }</span><br>
		</li>
		<li>
			<label>내용:</label>
			<span>${notice.noticeContent }</span><br>
		</li>
		<li>
			<label>작성자:</label>
			<span>${notice.noticeWriter }</span>
		</li>
	</ul>	
	<br> <br>
	<button type="button" onclick="showUpdateForm();">수정하기</button>
	<button type="button" onclick="noticeDelete();">삭제하기</button>
	<button type="button" onclick="goList();">목록으로이동</button>

	<script>
	function goList(){
		location.href="/notice/list.kh";
	}
	function noticeDelete(){
		if(confirm("진짜 지우나요")){
		var inputTag=document.querySelector("#noticeNo");
 		location.href="/notice/delete.kh?noticeNo="+inputTag.value;
 		alert("삭제 되었습니다");
		}else{
			alert("취소하였습니다");
		}
		/* document 가 input 의 hidden 대체한다 */
/* 		locationh.href="/notice/delete.kh?noticeNo=${notice.noticeNo}";
 */
	}	
	function showUpdateForm(){
		location.href="/notice/update.kh?noticeNo=${notice.noticeNo}";
	}	
	
	</script>

</body>
</html>