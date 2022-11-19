package com.gexingw.mybatis.service.impl;

import com.gexingw.common.jdbc.entity.Order;
import com.gexingw.mybatis.dto.SearchParam;
import com.gexingw.mybatis.mapper.OrderMapper;
import com.gexingw.mybatis.service.OrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 15:24
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> search(SearchParam param) {

        return orderMapper.searchPage(param);
    }

    @Override
    public List<Order> search(SearchParam param, RowBounds rowBounds) {
        return orderMapper.searchPage(param, rowBounds);
    }

}
