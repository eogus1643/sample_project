<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#regNoticeBtn").on('click',function(){
		location.href='/bbs/regNotice';
	});
	
});

function fn_update(seq) {
	console.log(seq);
	location.href='/bbs/updNotice?seq='+seq;
}

function fn_delete(seq) {
	
	console.log(seq);
	$('#seq').val(seq);
	
	var params = $("#frm").serialize();
	$.ajax({
		type : "POST",
		url : "/bbs/deleteNotice",
		data : params,
		success : function(res){
			alert('게시글이 수정 되었습니다.');
			location.href = '/bbs/noticeList';
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
			alert("통신 실패.")
		}
	});
	
}
</script>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
		
		<div id="wrap_main">
			<div id="contents_main">
				<div id="contents__inner_main">
					
					<div>
						<button id="regNoticeBtn">등록</button>
					</div>
					
					<table style="border: 1px solid #000000;">
						<colgroup>
							<col width="5%;">
							<col width="15%;">
							<col width="20%;">
							<col width="15%;">
							<col width="15%;">
							<col width="15%;">
							<col width="15%;">
						</colgroup>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>내용</th>
							<th>등록자</th>
							<th>등록일</th>
							<th>수정자</th>
							<th>수정일</th>
						</tr>
						<c:forEach items="${list}" var="ll">
							<tr>
								<td style="text-align: center;">${ll.seq}</td>
								<td><a href="javascript:fn_update('${ll.seq}')">${ll.title}</a></td>
								<td>${ll.contents}</td>
								<td>${ll.regId}</td>
								<td>${ll.regDate}</td>
								<td>${ll.updId}</td>
								<td>${ll.updDate}</td>
								<td><button onclick="javascript:fn_delete('${ll.seq}');">삭제</button></td>
							</tr>
						</c:forEach>
					</table>
					
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>

