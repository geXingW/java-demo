package com.gexingw.diffutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javers.core.metamodel.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author GeXingW
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderShippingAddress implements Serializable {

    @Id
    private Long id;

    private Long orderId;

    private String orderNo;

    private String userName;

    private String userPhone;

    private LocalDateTime deliveryTime;

}
