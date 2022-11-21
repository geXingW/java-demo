package com.gexingw.quartz.job;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 该任务不允许并行调度
 * 判断依据是：是否有相同的JobKey
 * JobKey = name + group
 * <p>
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 14:52
 */
@PersistJobDataAfterExecution
@Component
public class PersistDataJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        Date lastRunTime = (Date) jobDataMap.get("lastRunTime");

        System.out.println(DateFormatUtil.format(new Date()) + "，" + Thread.currentThread().getId()
                + "，" + PersistDataJob.class.getName() + "，" + DateFormatUtil.format(lastRunTime));
        jobDataMap.put("lastRunTime", new Date());
    }
}
