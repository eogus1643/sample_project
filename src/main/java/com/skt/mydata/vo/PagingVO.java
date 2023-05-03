package com.skt.mydata.vo;

import lombok.Data;

@Data
public class PagingVO {
    
    private int page; //현재페이지번호
    private int size; //리스트 보여야할 개수
    
    

    private String sortDirection;
    private String sortHeader;
    
}




