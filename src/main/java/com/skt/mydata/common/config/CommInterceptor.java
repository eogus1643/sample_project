package com.skt.mydata.common.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommInterceptor implements HandlerInterceptor{
	private final ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		try {
			if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return;
			final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
			final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

			log.info("Request URL : {}", request.getRequestURI());
			if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
				if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
					log.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
				}
			}
			if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")){
				if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
					log.info("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//		HttpSession session = request.getSession();
//		SessionVO svo = (SessionVO) session.getAttribute("svo");
//		if(svo == null) {
//			log.error("SESSION ERROR :: {}", BizErrCode.SESSION_EXPIRATION.getMsg());
//
//			if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {//AJAX 호출 여부
//				response.sendError(HttpServletResponse.SC_FORBIDDEN, BizErrCode.SESSION_EXPIRATION.getMsg());
//			} else {
//				response.sendRedirect("/setToken");//Pass URL로 리턴필요
//			}
//
//			return false;
//		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}



}
