package com.skt.mydata.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skt.mydata.service.NoticeService;
import com.skt.mydata.vo.BbsVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("backend")
public class NoticeController {
	 
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	//@PostMapping("/noticeList")
	@RequestMapping("/noticeList")
	@ResponseBody
    public Map<String, Object> selectLogList(@RequestParam Map<String, Object> param) throws SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		
		BbsVO vo = new BbsVO();
		try {
			
			resultMap.put("list", noticeService.selectNoticeList(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return resultMap;
    }
	
}
