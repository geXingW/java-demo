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
    SUCCESS(200000, "操作成功！"),

    // 异常响应
    FAILURE(500000, "系统异常，请稍后重试！"),

    QUERY_FAILURE(500001, "查询失败！"),
    CREAT_FAILURE(500002, "插入失败！"),
    UPDATE_FAILURE(500003, "更新失败！"),
    DELETE_FAILURE(500004, "删除失败！"),
    JOB_PAUSE_FAILURE(500005, "Job暂停失败！"),
    JOB_RESUME_FAILURE(500006, "Job重启失败！"),
    ;

    private final int code;
    private final String message;

    RespCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
