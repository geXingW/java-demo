package com.gexingw.mybatis.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 15:36
 */
@Data
public class SearchParam {

    private int page = 0;

    private int size = 10;

    private int offset = 0;

    private BigInteger id;

    /**
     * 状态
     */
    private Integer state;

//    public int getOffset(){
//        return page
//    }
}
