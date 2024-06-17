package com.gexingw.unittest.mapper;

import com.gexingw.unittest.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * @author GeXingW
 */
@Mapper
public interface OrderMapper {

    default Order selectById(Long id) {
        return new Order().setId(id).setTotalAmount(BigDecimal.valueOf(15).multiply(BigDecimal.valueOf(id)));
    }

}
