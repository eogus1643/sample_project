package com.skt.mydata.mapper;


import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.skt.mydata.vo.LogInfoVO;


@Mapper
@Repository
public interface LogInfoMapper {

	void insertLogInfo (LogInfoVO vo) throws SQLException;
}
