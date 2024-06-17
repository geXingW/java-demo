package com.gexingw.unittest.feign;

import com.gexingw.unittest.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author GeXingW
 */
@Service
public interface OldUserFeign {

    User getByUsername(String username);

}
