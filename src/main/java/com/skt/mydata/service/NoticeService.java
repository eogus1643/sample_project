package com.skt.mydata.service;

import java.util.List;

import com.skt.mydata.vo.BbsVO;

public interface NoticeService {

	void insertNotice (BbsVO vo) throws Exception;

	List<BbsVO> selectNoticeList(BbsVO vo) throws Exception;

	int updateNotice (BbsVO vo) throws Exception;

	BbsVO selectNotice(BbsVO vo) throws Exception;
}
