package com.gexingw.aop.controller;

import com.gexingw.aop.dto.IndexRequest;
import com.gexingw.common.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/21
 * @time: 14:21
 */
@RestController
@RequestMapping
public class IndexController {

    @GetMapping
    public R index(IndexRequest request){
        return R.ok(request);
    }

}
