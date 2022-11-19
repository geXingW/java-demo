package com.gexingw.mybatis.service;

import com.gexingw.common.jdbc.entity.Order;
import com.gexingw.mybatis.dto.SearchParam;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 15:24
 */
public interface OrderService {
    List<Order> search(SearchParam param);

    List<Order> search(SearchParam param, RowBounds rowBounds);


}
