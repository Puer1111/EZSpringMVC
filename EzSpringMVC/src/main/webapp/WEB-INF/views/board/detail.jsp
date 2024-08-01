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
		<li><label>제목</label> 
			<span>${board.boardTitle }</span>
		</li>
		<li><label>작성자</label> 
			<span>${board.boardWriter }</span>
		</li>
		<li><label>내용</label> 
			<span>${board.boardContent }</span>
		</li>
		<li><label>날짜</label> 
		
			<span>${board.bCreateDate }</span>
		</li>
		<li>
			<label>첨부파일</label> 
			<a href="/resources/bUploadFiles/${board.boardFileRename}"download>${board.boardFilename }</a>
		</li>
	</ul>
	<br>
	<div>
		<button type="button" onclick="ShowUpdateForm(${board.boardNo});">수정하기</button>
		<button type="button" onclick="deleteOne(${board.boardNo});">삭제하기</button>
		<button type="button" onclick="goList();">목록가기</button>
		<button type="button" onclick="goback();">뒤로가기</button>
		
	</div>

	<script>
  	function ShowUpdateForm(boardNo){
  		location.href="/board/update.kh?boardNo="+boardNo;
  	}
	
	function deleteOne(boardNo){
		 // 위에 버튼은 전달값 밑에 boardNo 는 매개변수
		 // == var boardNo = ${board.boardNo}
		 if(confirm("삭제함?")){
		 location.href="/board/delete.kh?boardNo="+boardNo;
		 }
	}
	function goList(){
		location.href="/board/list.kh";
		//location.href="/board/list.kh?currentPage=${param.currentPage}";
		/* param 객체을 적어주면 currentPage 를 굳이 controller 에서 ReqeustParam 하지 않고 model.addAttribute 하지 않아도 된다 */
		/* param 에  저장이 된다. jsp 끼리 공유함 */
	}		
	function goback(){
		history.go(-1);
	}
	</script>
</body>
</html>