package com.skt.mydata.controller;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.mydata.common.util.CommUtils;
import com.skt.mydata.service.LogInfoService;
import com.skt.mydata.service.UserInfoService;
import com.skt.mydata.vo.LogInfoVO;
import com.skt.mydata.vo.UserInfoVO;


@Controller
@RequestMapping("backend")
public class CommController {
	private static final Logger log = LogManager.getLogger(CommController.class);
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	@Resource(name="logInfoService")
	private LogInfoService logInfoService;
	
	/**
	 * 중복 아이디 체크
	 */
	@PostMapping("/userIdDuplicationCheck")
	@ResponseBody
	public Map<String, Object> userIdDuplicationCheck(@RequestParam Map<String, Object> param) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		UserInfoVO uvo = new UserInfoVO();
		uvo.setUserId(param.get("userId").toString());
		try {
			resultMap = userInfoService.selectUserDuplicationCheck(uvo);
		} catch (Exception e) {
			resultMap.put("message", "오류가 발생하였습니다.<br>잠시 후 다시 시도해 주세요.</br>지속 될 경우 관리자에게 문의 바랍니다.");
		}
		
		return resultMap;
	}
	
	/**
	 * 회원가입
	 */
	@PostMapping("/signup")
	@ResponseBody
	public Map<String, Object> signup(@RequestParam Map<String, Object> param) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		
		UserInfoVO uvo = new UserInfoVO();
		log.debug("param tostring = " + param.toString());
		uvo.setUserId(param.get("userId").toString());
		uvo.setUserName(param.get("userName").toString());
		uvo.setUserPassword(CommUtils.encrypt_sha256(param.get("userPassword").toString()));
		uvo.setUserPhoneNumber(param.get("userPhoneNumber").toString());
		uvo.setUserEmail(param.get("userEmail").toString());
		try {
			userInfoService.insertUserInfo(uvo);
			resultMap.put("result", "Y");
			resultMap.put("message", "회원가입 되었습니다.</br>로그인페이지로 이동됩니다.");
		} catch (Exception e) {
			resultMap.put("message", "오류가 발생하였습니다.<br>잠시 후 다시 시도해 주세요.</br>지속 될 경우 관리자에게 문의 바랍니다.");
		}
		
		return resultMap;
	}
	
	/**
	 * 로그인
	 * @return
	 * @throws UnknownHostException 
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> userLogin(@RequestParam Map<String, Object> param,HttpSession session,HttpServletRequest req) throws NoSuchAlgorithmException, SQLException, UnknownHostException{
		Map<String, Object> resultMap = new HashMap<>();
		UserInfoVO vo = new UserInfoVO();
		vo.setUserId(param.get("userId").toString());
		vo = userInfoService.selectUserInfo(vo);
		
		if(vo != null) {
			
			//비밀번호 일치 확인
			String password = CommUtils.encrypt_sha256(param.get("userPassword").toString());
			if ( !vo.getUserPassword().equals(password) ) {
				resultMap.put("result", "N");
				resultMap.put("message", "아이디 혹은 비밀번호를 다시 확인하여 주시기 바랍니다.");
				return resultMap;
			}
			resultMap.put("result", "Y");
			LogInfoVO logvo = new LogInfoVO();
			logvo.setUserId(vo.getUserId());
			logvo.setLogType("LOG_TYPE0001");
			logvo.setIp(CommUtils.getClientIP(req));
			
			if (StringUtils.isBlank(vo.getLastLoginDt())) {
				resultMap.put("message", "회원가입 후 최초 접속입니다.<br> 마지막 접속 IP : "+logvo.getIp());
			} else {
				resultMap.put("message", "마지막 접속시간 : "+vo.getLastLoginDt()+"<br> 마지막 접속 IP : "+logvo.getIp());
			}
			
			logInfoService.insertLogInfo(logvo);
			userInfoService.updateLastLoginDt(vo);
			
			/*
			adminLoginService.updateAdminLogin(vo);
			vo.setLogType("login");
			adminLoginService.insertAdminLoginLog(vo);
			session.setAttribute("svo", vo);
			session.setMaxInactiveInterval(30*60);
			*/
		}else {
			resultMap.put("result", "N");
			resultMap.put("message", "관리자로 등록 되어 있는 아이디가 아닙니다.");
		}
		
		log.info(resultMap);
		return resultMap;
	}
	
	/*
	@PostMapping("/userSessionCheck")
	@ResponseBody
	public Map<String, Object> userSessionCheck(HttpServletRequest req, @RequestParam Map<String, Object> param,HttpSession session) throws SQLException{
		Map<String, Object> resultMap = new HashMap<>();
		AdminLoginVo svo = (AdminLoginVo) session.getAttribute("svo");
		if(svo == null) {
			resultMap.put("result", "N");
			resultMap.put("message", "세션이 만료 되었습니다.\n 로그인을 다시 해주십시오.");		
		}else {
			resultMap.put("result", "Y");
		}
		return resultMap;
	}
	*/
	
	/*
	@PostMapping("/logOut")
	@ResponseBody
	public Map<String, Object> userLogOut(HttpServletRequest req, @RequestParam Map<String, Object> param,HttpSession session) throws SQLException{
		Map<String, Object> resultMap = new HashMap<>();
		AdminLoginVo vo = (AdminLoginVo) session.getAttribute("svo");
		vo.setRecentIp(getRemoteAddr(req));
		vo.setLogType("logout");
		adminLoginService.insertAdminLoginLog(vo);
		session.removeAttribute("svo");	
		AdminLoginVo svo = (AdminLoginVo) session.getAttribute("svo");
		if(svo == null) {
			resultMap.put("result", "Y");
			resultMap.put("message", "로그아웃 되었습니다.");	
		}else {
			resultMap.put("result", "N");
			resultMap.put("message", "로그아웃이 안되었습니다.");	
		}
		return resultMap;
	}
	*/
	
}
