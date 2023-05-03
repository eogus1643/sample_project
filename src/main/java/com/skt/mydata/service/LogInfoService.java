package com.skt.mydata.service;

import java.sql.SQLException;
import java.util.Map;

import com.skt.mydata.vo.LogInfoVO;
import com.skt.mydata.vo.UserInfoVO;

public interface LogInfoService {

	void insertLogInfo (LogInfoVO vo) throws SQLException;
}
