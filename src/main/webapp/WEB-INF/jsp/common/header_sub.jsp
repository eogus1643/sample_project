<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header_sub">
	<div class="container" style="height: 4rem;">
		<div class="header_sub_wrap">
			<a href="javascript:history.back();" class="header__back" style="cursor: pointer;">이전</a>
			<h1 class="logo_title">
				<c:if test="${!empty vo.menuName}">${vo.menuName}</c:if>
			</h1>
		</div>
	</div>
</div><!-- header -->