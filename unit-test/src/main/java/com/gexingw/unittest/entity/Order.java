package com.gexingw.unittest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author GeXingW
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Order {

    private Long id;

    private BigDecimal totalAmount;

    private Product product;

    private String user;

    public Order(Long id) {
        this.id = id;
        this.totalAmount = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(id));
    }

}
