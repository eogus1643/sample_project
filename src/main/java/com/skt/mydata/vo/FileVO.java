package com.skt.mydata.vo;

import lombok.Data;

@Data
public class FileVO {

	private int rownum;
	private int seq;
	private String realFileName;
	private String serverFileName;
	private String extsn;
	private int fileSize;
	private String filePath;
	private String deleteYn;
	private String regDt;
	private String delDt;
	private String gubun;
	private int[] seqArr;

	/**paging*/
	private int page;
	private int pageSize = 10;
}



