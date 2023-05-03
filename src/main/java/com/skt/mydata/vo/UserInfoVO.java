package com.skt.mydata.vo;

import lombok.Data;

@Data
public class UserInfoVO extends PagingVO {

	private int seq;
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private String lastLoginDt;
	private String regDt;
	private String updDt;
}
