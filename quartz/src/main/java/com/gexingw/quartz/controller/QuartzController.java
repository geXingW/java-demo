package com.gexingw.quartz.controller;

import com.gexingw.common.util.R;
import com.gexingw.common.util.RespCode;
import com.gexingw.quartz.dto.JobRequest;
import com.gexingw.quartz.service.QuartzService;
import com.gexingw.quartz.util.JobBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/20
 * @time: 16:25
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {

    @Autowired
    QuartzService quartzService;

    @GetMapping
    public R index() {
        return R.ok();
    }

    @PostMapping
    public R create(@RequestBody JobRequest request) {
        // 检查是否存在这个Bean。如果不存在，则不能进行添加
        Object bean = JobBeanUtil.getJob(request.getName());
        if (bean == null) {
            return R.failure(RespCode.CREAT_FAILURE, "Bean不存在！");
        }

        if (!quartzService.addJob(request)) {
            return R.failure(RespCode.CREAT_FAILURE);
        }

        return R.ok();
    }

    @PostMapping("pause")
    public R pause(@RequestBody JobRequest request){
        if(!quartzService.pauseJob(request)){
            return R.failure(RespCode.JOB_PAUSE_FAILURE);
        }

        return R.ok();
    }

    @PostMapping("resume")
    public R resume(@RequestBody JobRequest request){
        if(!quartzService.resumeJob(request)){
            return R.failure(RespCode.JOB_RESUME_FAILURE);
        }

        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody JobRequest request) {
        if (!quartzService.reScheduleJob(request)) {
            return R.failure(RespCode.UPDATE_FAILURE);
        }

        return R.ok();
    }

    @DeleteMapping
    public R delete(@RequestBody JobRequest request) {
        if (!quartzService.deleteJob(request)) {
            return R.failure(RespCode.DELETE_FAILURE);
        }

        return R.ok();
    }
}
