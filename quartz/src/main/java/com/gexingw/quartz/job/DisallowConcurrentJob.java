package com.gexingw.quartz.job;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
@DisallowConcurrentExecution
@Component
public class DisallowConcurrentJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(DateFormatUtil.format(new Date()) + "，" + Thread.currentThread().getId()
                + "，" + DisallowConcurrentJob.class);
    }
}
