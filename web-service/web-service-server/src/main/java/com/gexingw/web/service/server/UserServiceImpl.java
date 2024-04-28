package com.gexingw.web.service.server;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author GeXingW
 */
@Service
@WebService(
        serviceName = "UserService", targetNamespace = "http://service.server.web.gexingw.com",
        endpointInterface = "com.gexingw.web.service.server.UserService"
)
public class UserServiceImpl implements UserService {

    @Override
    public String info(String name) {
        return "User " + name + " at: " + System.currentTimeMillis();
    }

}
