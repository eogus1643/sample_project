package com.skt.mydata.response;

public class CommonResponse<T> {

	private String code = "0000";   //결과코드. 0000 : 성공, 0400 : 파라미터 오류, 0500 : 시스템 오류
	private String msg;             //에러메시지
	private T result;               //결과객체

	public CommonResponse() {}

	public CommonResponse(String code, String msg, T result) {
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}