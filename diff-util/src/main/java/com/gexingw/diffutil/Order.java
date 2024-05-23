package com.gexingw.diffutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * @author GeXingW
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Order implements Serializable {

    @Id
    private Long id;

    private String orderNo;

    private List<OrderItem> items;

    private OrderShippingAddress shippingAddress;

}
