package com.gexingw.common.jdbc.enums;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 15:51
 */
@Getter
public enum OrderStatus {
    /**
     * 已下单，未支付
     */
    UN_PAY(0, "待支付"),

    /**
     * 已下单，已支付
     */
    PAID(1, "已支付"),

    /**
     * 未支付，已取消
     */
    CANCELED(2, "已取消"),
    ;


    private Integer code;


    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
