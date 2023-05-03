package com.skt.mydata.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

	@RequestMapping("/main")
	public String main(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{

		return "/main/main";
	}



}
