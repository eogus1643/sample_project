package com.skt.mydata.common.config.exception;





import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.skt.mydata.common.enums.ErrorCode;

@Controller
public class ErrController implements ErrorController {

	@RequestMapping(value = "/mydata/pass/home/error")
	public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response){

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		ModelAndView mv = null;

		if(Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()) {
			//mv = new ModelAndView("error/error-404");
			mv = new ModelAndView("error/error");
		} else {
			JSONObject jsonObj = (JSONObject) request.getAttribute("result");

			if(ErrorCode.TOKEN_CREATE_ERROR.getCode().equals(jsonObj.get("err_code")) || "40101".equals(jsonObj.get("err_code"))) {
				try {
					//TODO Pass HOME 으로 변경
					//response.sendRedirect("/mydata/pass/service");
					mv = new ModelAndView("error/error");
				} catch(Exception e) {
					mv = new ModelAndView("error/error");
					mv.addObject("err_msg", jsonObj.get("err_msg"));
					//e.printStackTrace();
				}
			} else {
				mv = new ModelAndView("error/error");
				mv.addObject("err_msg", jsonObj.get("err_msg"));
			}
		}
		mv.addObject("tokenId", request.getParameter("tokenId"));

        return mv;
    }

    @RequestMapping(value = "/mydata/pass/home/error", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<JSONObject> handleCustomException(HttpServletRequest request){
    	JSONObject result = (JSONObject) request.getAttribute("result");
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	return new ResponseEntity<>(result, new HttpHeaders(), Integer.valueOf(status.toString()));
    }

}
