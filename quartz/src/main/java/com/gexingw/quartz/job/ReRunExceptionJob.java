package com.gexingw.quartz.job;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 9:50
 */
@PersistJobDataAfterExecution
@Component
public class ReRunExceptionJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        try {

            System.out.println(DateFormatUtil.format(new Date()) + "，" + Thread.currentThread().getId()
                    + "，" + ReRunExceptionJob.class);

            if (!jobDataMap.containsKey("ok")) {
                throw new RuntimeException("手动触发下一次");
            }else{
                System.out.println("手动触发任务重新执行");
            }

        } catch (RuntimeException e) {
            System.out.println("执行失败：" + e);
            try {
                context.getScheduler().scheduleJob(
                        TriggerBuilder.newTrigger().startNow().forJob(context.getJobDetail()).build()
                );
            } catch (SchedulerException ex) {
                System.out.println("重新调度失败！");
            }

            jobDataMap.put("ok", true);
        }
    }
}
