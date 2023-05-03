<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#updateNotice").on('click',function(){
		
        
		var params = $("#frm").serialize();
		$.ajax({
			type : "POST",
			url : "/bbs/updateNotice",
			data : params,
			success : function(res){
				alert('게시글이 수정 되었습니다.');
				location.href = '/bbs/noticeList';
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
				alert("통신 실패.")
			}
		});
		
		
	});
	
});

</script>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
		
		<div id="wrap_main">
			<div id="contents_main">
				<div id="contents__inner_main">
					
					<form id="frm" method="post">
						<table>
							<tr>
								<th>제목</th>
								<td><input type="text" title="제목" name="title" value="${detail.title}"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea rows="20" cols="50" name="contents">${detail.contents}</textarea></td>
							</tr>
							<tr>
								<th>등록자</th>
								<td>${detail.regId}</td>
							</tr>
							<tr>
								<th>등록일</th>
								<td>${detail.regDate}</td>
							</tr>
							<tr>
								<th>수정자</th>
								<td>${detail.updId}</td>
							</tr>
							<tr>
								<th>수정일</th>
								<td>${detail.updDate}</td>
							</tr>
						</table>
						<input type="hidden" name="seq" value="${detail.seq}">
					</form>
					<div>
						<button id="updateNotice">수정</button>
					</div>
					
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>

