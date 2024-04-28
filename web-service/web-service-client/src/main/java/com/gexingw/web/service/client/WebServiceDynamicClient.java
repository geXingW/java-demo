package com.gexingw.web.service.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author GeXingW
 */
public class WebServiceDynamicClient {

    public static void main(String[] args) throws Exception {
        // webService地址
        String address = "http://localhost:8080/web-service/UserService?wsdl";
        // 实例化工厂
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();

        // 创建客户端链接
        Client client = jaxWsDynamicClientFactory.createClient(address);

        // 拦截器
        client.getOutFaultInterceptors().add(new ClientInterceptor());

        Object[] result = client.invoke("info", "gexingw");
        System.out.println(result[0]);
    }

}
