package com.skt.mydata.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.skt.mydata.vo.UserInfoVO;

public interface UserInfoService {

	void insertUserInfo (UserInfoVO vo) throws Exception;

	UserInfoVO selectUserInfo (UserInfoVO vo) throws SQLException;
	
	Map<String, Object> selectUserDuplicationCheck (UserInfoVO vo) throws Exception;
	
	void updateLastLoginDt (UserInfoVO vo) throws SQLException;
	
	List<?> selectUserInfoList (UserInfoVO vo) throws SQLException;
	
	int selectUserInfoListCnt (UserInfoVO vo) throws SQLException;
}
