package com.skt.mydata.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skt.mydata.mapper.NoticeMapper;
import com.skt.mydata.service.NoticeService;
import com.skt.mydata.vo.BbsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("noticeService")
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{


	private final NoticeMapper noticeMapper;

	@Override
	public void insertNotice(BbsVO vo) throws Exception {
		noticeMapper.insertNotice(vo);
	}

	@Override
	public List<BbsVO> selectNoticeList(BbsVO vo) throws SQLException {
		return noticeMapper.selectNoticeList(vo);
	}

	@Override
	public int updateNotice(BbsVO vo) throws SQLException {
		return noticeMapper.updateNotice(vo);
	}

	@Override
	public BbsVO selectNotice(BbsVO vo) throws Exception {
		return noticeMapper.selectNotice(vo);
	}



}
