package com.skt.mydata.mapper;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.skt.mydata.vo.UserInfoVO;


@Mapper
@Repository
public interface UserInfoMapper {

	void insertUserInfo (UserInfoVO vo) throws SQLException;

	UserInfoVO selectUserInfo (UserInfoVO vo) throws SQLException;
	
	int selectUserDuplicationCheck (UserInfoVO vo) throws SQLException;
	
	void updateLastLoginDt (UserInfoVO vo) throws SQLException;
	
	List<?> selectUserInfoList (UserInfoVO vo) throws SQLException;
	
	int selectUserInfoListCnt (UserInfoVO vo) throws SQLException;
}
