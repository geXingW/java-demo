package com.gexingw.quartz.listener;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 10:23
 */
@Component
public class JobListener implements org.quartz.JobListener {
    @Override
    public String getName() {
        return "Custom job listener";
    }

    /**
     * Scheduler在JobDetail将要被执行时调用这个方法.
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        Class<? extends Job> jobClass = jobExecutionContext.getJobDetail().getJobClass();
        System.out.println(jobClass + "，执行时间：" + DateFormatUtil.format(jobExecutionContext.getFireTime()));
    }

    /**
     * Scheduler在JobDetail即将被执行，但又被TriggerListerner否决时会调用该方法.
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        Class<? extends Job> jobClass = jobExecutionContext.getJobDetail().getJobClass();
        System.out.println(jobClass + "，否决时间" + DateFormatUtil.format(jobExecutionContext.getFireTime()));
    }

    /**
     * Scheduler在JobDetail被执行之后调用这个方法.
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        Class<? extends Job> jobClass = jobExecutionContext.getJobDetail().getJobClass();
        System.out.println(jobClass + "，执行完毕：" + DateFormatUtil.format(jobExecutionContext.getFireTime()));
    }
}
