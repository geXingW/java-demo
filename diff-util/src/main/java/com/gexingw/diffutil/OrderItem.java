package com.gexingw.diffutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author GeXingW
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderItem implements Serializable {

    @Id
    private Long id;

    private Long orderId;

    private String orderNo;

    private Long productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productNum;

}
