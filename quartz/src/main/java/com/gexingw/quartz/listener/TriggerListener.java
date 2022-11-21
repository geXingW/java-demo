package com.gexingw.quartz.listener;

import com.gexingw.common.util.DateFormatUtil;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 10:23
 */
@Component
public class TriggerListener implements org.quartz.TriggerListener {

    @Override
    public String getName() {
        return "Custom trigger listener";
    }

    /**
     * 当与监听器相关联的Trigger被触发，Job上的execute()方法将被执行时,Scheduler就调用该方法.
     * @param trigger
     * @param jobExecutionContext
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("triggerFired" + DateFormatUtil.format(new Date()));
    }

    /** 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener 给
     * 了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    /**
    * Scheduler 调用这个方法是在 Trigger 错过触发时。你应该关注此方法中持续时间长的逻辑：
    * 在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小。
    */
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("triggerMisfired" + DateFormatUtil.format(new Date()));
    }

    /**
     * Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。
     * @param trigger
     * @param jobExecutionContext
     * @param completedExecutionInstruction
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("triggerComplete" + DateFormatUtil.format(new Date()));
    }
}
