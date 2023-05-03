package com.skt.mydata.common.enums;

import lombok.Getter;

@Getter
public enum BizErrCode {

	PARAMETER_INVALID("BIZ_0001","파라미터의 형식 또는 데이터가 올바르지 않습니다.")
	,SESSION_EXPIRATION("BIZ_0002","세션이 만료되었습니다.\n재로그인 후 다시 시도해주세요.")
	;
	private final String code;
	private final String msg;

	BizErrCode(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
