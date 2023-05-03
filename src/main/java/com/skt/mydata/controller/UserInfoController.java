package com.skt.mydata.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skt.mydata.common.util.CommUtils;
import com.skt.mydata.service.UserInfoService;
import com.skt.mydata.vo.UserInfoVO;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("backend")
public class UserInfoController {
	
	private static final Logger log = LogManager.getLogger(CommController.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	
	/**
	  * 회원정보 목록 조회
	  * @param param
	  * @return
	  * @throws SQLException
	  */
	@PostMapping("/userList")
	@ResponseBody
	public Map<String, Object> userList(@RequestParam Map<String, Object> param) throws SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		UserInfoVO vo = mapper.convertValue(param, UserInfoVO.class);
		
		int totalCount = userInfoService.selectUserInfoListCnt(vo);
		int totalPages = CommUtils.totalPage(totalCount, vo.getSize());
		try {
			resultMap.put("list", userInfoService.selectUserInfoList(vo));
			resultMap.put("totalPages", totalPages);
			log.debug("totalCount : " + totalCount);
			log.debug("totalPages : " + totalPages);
		} catch (Exception e) {
			resultMap.put("message", "오류가 발생하였습니다.<br>잠시 후 다시 시도해 주세요.</br>지속 될 경우 관리자에게 문의 바랍니다.");
		}
		return resultMap;
	}
	
	/**
	  * 엑셀 다운로드
	  * @param param
	  * @return
	  * @throws SQLException
	  */
	@PostMapping("/userExcelList")
	@ResponseBody
	 public Map<String, Object> selectUserExcelList(@RequestParam Map<String, Object> param) throws SQLException{
		Map<String, Object> resultMap = new HashMap<>();
		UserInfoVO vo = mapper.convertValue(param, UserInfoVO.class); 
		
		//resultMap.put("list", userInfoService.selectUserExcelList(vo));
		
		return resultMap;
   }
	
	
}
