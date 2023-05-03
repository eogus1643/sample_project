package com.skt.mydata.common.enums;

import lombok.Getter;

@Getter
public enum CommEnum {


	SUCCESS("00000","성공")//API 결과수신

	/**
	 * SDK002 - 정보제공자 조회
	 */
	,INDUSTRY("","업권별")
	,INFO_PROVIDER("","정보제공자")
	,SEARCH_TYPE_I("industry","업권별")
	,SEARCH_TYPE_P("provider","정보제공자")
	/**
	 * SDK004 - 인증준비
	 */
	,INDIVIDUAL("","개별인증")
	,INTEGRATION_1("","통합인증(1단계)")
	,INTEGRATION_2("","통합인증(2단계)")

	/**
	 * SDK006 - 통합인증
	 */
	,INTEGRATE_1("","공동목록조회(1단계)")
	,INTEGRATE_2("","공동상세조회(2단계)")


	/**
	 * SDK003 - 상품조회
	 */
	,PRODUCT_ALL("","전체조회")
	;






	private final String code;
	private final String msg;

    CommEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
