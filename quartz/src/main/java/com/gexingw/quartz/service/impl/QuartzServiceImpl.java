package com.gexingw.quartz.service.impl;

import com.gexingw.quartz.dto.JobRequest;
import com.gexingw.quartz.service.QuartzService;
import com.gexingw.quartz.util.JobBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 17:12
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public boolean addJob(JobRequest request) {
        try {
            // 构建JobDetail
            JobDetail jobDetail = JobBuilder.newJob(JobBeanUtil.getJob(request.getName()).getClass())
                    .withIdentity(request.getName(), request.getGroup())
                    .build();

            // 构建Trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(request.getTriggerName(), request.getTriggerGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(request.getCron()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

            return true;
        } catch (Exception e) {
            log.error("Job create failure with exception:" + e);
            return false;
        }
    }

    @Override
    public boolean pauseJob(JobRequest request) {
        try {
//            SimpleScheduleBuilder
//                    CronScheduleBuilder
//            CalendarIntervalScheduleBuilder
//            DailyTimeIntervalScheduleBuilder
            scheduler.pauseJob(JobKey.jobKey(request.getName(), request.getGroup()));
            return true;
        } catch (Exception e) {
            log.error("Job create failure with exception:" + e);

            return false;
        }
    }

    @Override
    public boolean resumeJob(JobRequest request) {
        try {
            scheduler.resumeJob(JobKey.jobKey(request.getName(), request.getGroup()));
            return true;
        } catch (Exception e) {
            log.error("Job create failure with exception:" + e);
            return false;
        }
    }

    @Override
    public boolean reScheduleJob(JobRequest request) {
        try {
            // 重新构建Trigger
            TriggerKey triggerKey = TriggerKey.triggerKey(request.getTriggerName(), request.getTriggerGroup());

            // 重新构建Cron
            CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(request.getCron());

            // 获取老的trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按照新的Cron执行
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronSchedule).build();

            // 按照新的trigger重新设置job
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (Exception e) {
            log.error("Job reschedule failure with exception:" + e);
            return false;
        }
    }

    @Override
    public boolean deleteJob(JobRequest request) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(request.getTriggerName(), request.getTriggerGroup());
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(request.getName(), request.getGroup()));

            return true;
        } catch (Exception e) {
            log.error("Job delete failure with exception:" + e);
            return false;
        }
    }

}
