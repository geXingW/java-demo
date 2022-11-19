package com.gexingw.mybatis.mapper;

import com.gexingw.common.jdbc.entity.Order;
import com.gexingw.mybatis.dto.SearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 15:24
 */
@Mapper
public interface OrderMapper {
    List<Order> searchPage(SearchParam param);

    List<Order> searchPage(SearchParam param, RowBounds rowBounds);

}
