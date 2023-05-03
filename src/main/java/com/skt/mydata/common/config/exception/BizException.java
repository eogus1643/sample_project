package com.skt.mydata.common.config.exception;

import com.skt.mydata.common.enums.ErrorCode;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -2116671122895194101L;
	private String errorCode;
	private String errorMsg;

	public BizException(ErrorCode bizErrCode) {
		super(bizErrCode.getMsg());
		this.errorCode = bizErrCode.getCode();
		this.errorMsg = bizErrCode.getMsg();
	}

	public BizException(String errCode, String errMsg) {
		super(errMsg);
		this.errorCode = errCode;
		this.errorMsg = errMsg;
	}

	public BizException(String errMsg) {
		super(errMsg);
		this.errorCode = "";
		this.errorMsg = errMsg;
	}

}
