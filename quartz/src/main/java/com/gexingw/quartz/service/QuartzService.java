package com.gexingw.quartz.service;

import com.gexingw.quartz.dto.JobRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 17:11
 */
public interface QuartzService {
    boolean addJob(JobRequest request);

    boolean pauseJob(JobRequest request);

    boolean resumeJob(JobRequest request);

    boolean reScheduleJob(JobRequest request);

    boolean deleteJob(JobRequest request);
}
