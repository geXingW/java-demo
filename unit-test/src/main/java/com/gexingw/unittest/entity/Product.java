package com.gexingw.unittest.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author GeXingW
 */
@Data
@Accessors(chain = true)
public class Product {

    private Long id;

    private String name;

}
