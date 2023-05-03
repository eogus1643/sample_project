package com.skt.mydata.vo;

import lombok.Data;

@Data
public class LogInfoVO {

	private int seq;
	private String logType;
	private String userId;
	private String ip;
	private String regDt;
}
