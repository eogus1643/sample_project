package com.skt.mydata.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.skt.mydata.mapper.UserInfoMapper;
import com.skt.mydata.service.UserInfoService;
import com.skt.mydata.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userInfoService")
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{


	private final UserInfoMapper userInfoMapper;

	@Override
	public void insertUserInfo(UserInfoVO vo) throws Exception {
		userInfoMapper.insertUserInfo(vo);
	}

	@Override
	public UserInfoVO selectUserInfo(UserInfoVO vo) throws SQLException {
		return userInfoMapper.selectUserInfo(vo);
	}

	@Override
	public Map<String, Object> selectUserDuplicationCheck(UserInfoVO vo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = userInfoMapper.selectUserDuplicationCheck(vo); 
		if (cnt < 1) {
			resultMap.put("result", "Y");
			resultMap.put("message", "사용 가능한 아이디 입니다.");
		} else {
			resultMap.put("result", "N");
			resultMap.put("message", "이미 사용중인 아이디 입니.");
		}
		return resultMap;
	}

	@Override
	public void updateLastLoginDt(UserInfoVO vo) throws SQLException {
		userInfoMapper.updateLastLoginDt(vo);
	}

	@Override
	public List<?> selectUserInfoList(UserInfoVO vo) throws SQLException {
		return userInfoMapper.selectUserInfoList(vo);
	}

	@Override
	public int selectUserInfoListCnt(UserInfoVO vo) throws SQLException {
		return userInfoMapper.selectUserInfoListCnt(vo);
	}


}
