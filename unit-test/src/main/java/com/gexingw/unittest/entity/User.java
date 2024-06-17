package com.gexingw.unittest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author GeXingW
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private Integer id;

    private String username;

    private String password;

    private String birthday;

    private String sex;

}
