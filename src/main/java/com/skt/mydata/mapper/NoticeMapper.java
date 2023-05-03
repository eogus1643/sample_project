package com.skt.mydata.mapper;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.skt.mydata.vo.BbsVO;


@Mapper
@Repository
public interface NoticeMapper {

	void insertNotice (BbsVO vo) throws SQLException;

	List<BbsVO> selectNoticeList(BbsVO vo) throws SQLException;

	int updateNotice (BbsVO vo) throws SQLException;

	BbsVO selectNotice(BbsVO vo) throws SQLException;
}
