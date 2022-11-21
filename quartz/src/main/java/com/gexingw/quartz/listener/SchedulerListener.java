package com.gexingw.quartz.listener;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 10:43
 */
@Component
public class SchedulerListener implements org.quartz.SchedulerListener {
    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("jobScheduled" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("jobUnscheduled" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("triggerFinalized" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("triggerPaused" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void triggersPaused(String s) {
        System.out.println("triggersPaused" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("triggerResumed" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void triggersResumed(String s) {
        System.out.println("triggersResumed" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("jobAdded" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("jobDeleted" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        System.out.println("jobPaused" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobsPaused(String s) {
        System.out.println("jobsPaused" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        System.out.println("jobResumed" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void jobsResumed(String s) {
        System.out.println("jobsResumed" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerError(String s, SchedulerException e) {
        System.out.println("schedulerError" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerInStandbyMode() {
        System.out.println("schedulerInStandbyMode" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerStarted() {
        System.out.println("schedulerStarted" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerStarting() {
        System.out.println("schedulerStarting" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerShutdown() {
        System.out.println("schedulerShutdown" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("schedulerShuttingdown" + DateFormatUtil.format(new Date()));
    }

    @Override
    public void schedulingDataCleared() {
        System.out.println("schedulingDataCleared" + DateFormatUtil.format(new Date()));
    }
}
