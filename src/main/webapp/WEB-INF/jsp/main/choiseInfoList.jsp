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
	
	$(".choise_type_btn").on('click',function(){
		
		var type = $(this).attr('data-choiseType');
		var url = '';
		console.log('test = ' + type);
		
		switch (type) {
		
			case 'plotPlan' : 
				url = '/plotPlan/list';
				break;
			case 'pid' :
				url = '/pid/list';
				break;
			case 'equipment' :
				url = '/equipment/list';
				break;
			case 'instrument' :
				url = '/instrument/list';
				break;
			case 'cfd' :
				url = '/cfd/list';
				break;
			case 'procsModel' :
				url = '/procsModel/list';
				break;
			case 'gmp' :
				url = '/gmp/list';
				break;
			case 'prductAnals' :
				url = '/prductAnals/list';
				break;
		
		}
		
		if (!isEmpty(url)) {
			location.href = url;
		}
		
	});
	
});
</script>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
		
		 
		<div class="header_sub">
			<div class="container">
				<h1 class="sub_title" style="background-color: #3CAEFF;">정보 선택</h1>
			</div>
		</div>
		 
		<div id="wrap">
			<div id="contents">
				<div id="contents__inner">
					<div align="center">
						
						<div style="margin-top: 1rem;">
							<button type="button" class="choise_type_btn" data-choiseType="plotPlan">
								<span class="choise_type_text1">설비정보 도면정보</span><br/>
								<span class="choise_type_text2">Plot plan</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="pid">
								<span class="choise_type_text1">설비정보 도면정보</span><br/>
								<span class="choise_type_text2">P&ID</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="equipment">
								<span class="choise_type_text1">설비정보 설계정보</span><br/>
								<span class="choise_type_text2">Equipment</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="instrument">
								<span class="choise_type_text1">설비정보 설계정보</span><br/>
								<span class="choise_type_text2">Instrument</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="cfd">
								<span class="choise_type_text1">공정정보_공정모사모델</span><br/>
								<span class="choise_type_text2">CFD 모델</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="procsModel">
								<span class="choise_type_text1">공정정보_공정모사모델</span><br/>
								<span class="choise_type_text2">공정모델 검증</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="gmp">
								<span class="choise_type_text1">공정정보_GMP 가이던스</span><br/>
								<span class="choise_type_text2">GMP 기준서</span>
							</button>
							
							<button type="button" class="choise_type_btn" data-choiseType="prductAnals">
								<span class="choise_type_text1">공정정보_GMP 가이던스</span><br/>
								<span class="choise_type_text2">제품 분석/검증</span>
							</button>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

