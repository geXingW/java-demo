package com.gexingw.web.service.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "UserService", targetNamespace = "http://service.server.web.gexingw.com")
public interface UserService {

    @WebMethod
    String info(@WebParam(name = "name") String name);

}
