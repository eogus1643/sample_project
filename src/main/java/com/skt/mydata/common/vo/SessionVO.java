package com.skt.mydata.common.vo;

import lombok.Data;

@Data
public class SessionVO {

	private String userKey;
	private String userCi;
	private String tokenId;
	private String uKey;		//인증계 PROD 테이블에서 쓰는 KEY
	private String userGender;
	private int userAgeGroup;
}
