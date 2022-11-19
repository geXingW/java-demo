package com.gexingw.mybatis.controller;

import com.gexingw.common.jdbc.entity.Order;
import com.gexingw.common.util.R;
import com.gexingw.mybatis.dto.SearchParam;
import com.gexingw.mybatis.service.OrderService;
import com.gexingw.mybatis.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 14:52
 */
@RequestMapping("mybatis")
@RestController
public class MyBatisController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public R index(SearchParam param) {
        // 进行count 查询
//        PageHelper.startPage(param.getPage(), param.getSize());

        // 不进行count查询
        PageHelper.startPage(param.getPage(), param.getSize(), false);
        List<Order> orders =  orderService.search(param);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        // 清除分页数据
        PageHelper.clearPage();

        List<Order> orders2 =  orderService.search(param);

        HashMap<String, Object> result = new HashMap<>();


        return R.ok(PageUtil.format(pageInfo));
    }

    @GetMapping("row-bound")
    public R rowBound(SearchParam param){
        // 使用PageHelper自带的RowBound进行查询
        // new PageRowBounds(param.getPage(), param.getSize()).setCount(true);

        List<Order> orders = orderService.search(param, new RowBounds(param.getPage(), param.getSize()));
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        return R.ok(PageUtil.format(pageInfo));
    }
}
