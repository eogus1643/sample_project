package com.skt.mydata.common.config.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        JSONObject jsonObj = new JSONObject();
		jsonObj.put("err_code", "");
		jsonObj.put("err_msg", e.getMessage());
        request.setAttribute("result", jsonObj);
		e.printStackTrace();

        return "forward:/mydata/pass/home/error";
    }

	@SuppressWarnings("unchecked")
	@ExceptionHandler(BizException.class)
    public String handleBizException(HttpServletRequest request, HttpServletResponse response,  BizException e) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("err_code", e.getErrorCode());
		jsonObj.put("err_msg", e.getErrorMsg());
        request.setAttribute("result", jsonObj);
        e.printStackTrace();
        return "forward:/mydata/pass/home/error";
    }
}
