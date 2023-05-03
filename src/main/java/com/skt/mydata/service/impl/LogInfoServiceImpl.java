package com.skt.mydata.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.skt.mydata.mapper.LogInfoMapper;
import com.skt.mydata.service.LogInfoService;
import com.skt.mydata.vo.LogInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("logInfoService")
@RequiredArgsConstructor
public class LogInfoServiceImpl implements LogInfoService{


	private final LogInfoMapper logInfoMapper;

	@Override
	public void insertLogInfo(LogInfoVO vo) throws SQLException {
		logInfoMapper.insertLogInfo(vo);
	}


}
