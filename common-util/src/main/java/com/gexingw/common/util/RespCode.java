package com.gexingw.common.util;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/8
 * @time: 22:54
 */
@Getter
public enum RespCode {
    // 正常响应
    SUCCESS(200000, "success"),

    FAILURE(500000, "failure"),
    ;

    private final int code;
    private final String message;

    RespCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
