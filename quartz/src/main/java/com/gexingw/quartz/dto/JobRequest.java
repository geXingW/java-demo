package com.gexingw.quartz.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 20:01
 */
@Data
public class JobRequest {

    private String name;

    private String group;

    private String triggerName;

    private String triggerGroup;

    private String cron;
}
