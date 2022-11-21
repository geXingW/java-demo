package com.gexingw.quartz.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 15:11
 */
@Configuration
public class QuartzConfig {

    @Autowired
    Scheduler scheduler;

    @Autowired
    JobListener jobListener;

    @Autowired
    TriggerListener triggerListener;

    @Autowired
    SchedulerListener schedulerListener;

//    public JobDetail job1() {
//        return JobBuilder.newJob(Job1.class)
//                .withIdentity("job-1")
//                .build();
//    }
//
//    public SimpleTrigger trigger1() {
//        return TriggerBuilder.newTrigger()
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
//                .build();
//    }
//
//    public JobDetail job2() {
//        return JobBuilder.newJob(Job1.class)
//                .withIdentity("job-2")
//                .build();
//    }
//
//    public SimpleTrigger trigger2() {
//        return TriggerBuilder.newTrigger()
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
//                .build();
//    }
//
//    public JobDetail job3() {
//        return JobBuilder.newJob(Job1.class)
//                .withIdentity("job-3")
//                .build();
//    }
//
//    public SimpleTrigger trigger3() {
//        return TriggerBuilder.newTrigger()
//                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
//                .build();
//    }

    @PostConstruct
    public void initSchedule() throws SchedulerException {
//        scheduler.scheduleJob(job1(), trigger1());
//        scheduler.scheduleJob(job2(), trigger2());
//        scheduler.scheduleJob(job3(), trigger3());



        scheduler.getListenerManager().addJobListener(jobListener);
        scheduler.getListenerManager().addTriggerListener(triggerListener);
        scheduler.getListenerManager().addSchedulerListener(schedulerListener);
    }

}
