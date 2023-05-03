<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>NaOH 정제공정 정보제공</title>
<script type="text/javascript">
$(document).ready(function(){
	
// 	$(".btn_white").on('click',function(){
// 		location.href='choiseInfoList';
// 	});


	$("#mvNoticeBtn").on('click',function(){
		location.href='/bbs/noticeList';
	});
	
	$("input[name=test_area]").on('change',function(){
		$("input[name=test_area]").val('');
	});

	
    //input을 datepicker로 선언
    $(".calendar").datepicker({
        dateFormat: 'yy-mm-dd' //달력 날짜 형태
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
        ,changeYear: true //option값 년 선택 가능
        ,changeMonth: true //option값  월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
        ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
        ,buttonText: "선택" //버튼 호버 텍스트              
        ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
        ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
    });                    
    
    //초기값을 오늘 날짜로 설정해줘야 합니다.
    $('#calendar').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
	
});

function fn_checkboxClick(number1, number2, number3) {
	alert(number1 + number2 + number3 + '을/를 선택하였음');
}

</script>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
		
		<div id="wrap_main">
			<div id="contents_main">
				<div id="contents__inner_main">
				
					<div class="calendarDiv">
						<input type="text" class="calendar">
					</div>
					<div class="checkBoxArea">
						<input type="checkbox" id="check1" onclick="fn_checkboxClick('1','2','3');">1
						<input type="checkbox" id="check2" onclick="fn_checkboxClick(4,5,6);">2
						<input type="checkbox" id="check3" onclick="fn_checkboxClick(7,8);">3
					</div>
					<div>
						<input type="text" name="test_area">
					</div>
					
					<div>
						<input type="text" id="aaa">
					</div>
					
					<div>
						<button id="mvNoticeBtn">공지사항으로 이동</button>
					</div>
					
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>

