package com.gexingw.unittest.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GeXingW
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest implements Serializable {

    private String username;

    private String password;

}
