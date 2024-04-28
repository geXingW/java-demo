package com.gexingw.web.service.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author GeXingW
 */
public class WebServiceProxyClient {

    public static void main(String[] args) {
        String address = "http://localhost:8080/web-service/UserService?wsdl";
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(address);
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);

        jaxWsProxyFactoryBean.getOutFaultInterceptors().add(new ClientInterceptor());

        UserService userService = (UserService) jaxWsProxyFactoryBean.create();

        String result = userService.info("gexingw");
        System.out.println(result);
    }

}
