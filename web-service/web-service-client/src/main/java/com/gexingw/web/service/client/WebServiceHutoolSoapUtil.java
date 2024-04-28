package com.gexingw.web.service.client;

import cn.hutool.http.webservice.SoapClient;

/**
 * @author GeXingW
 */
public class WebServiceHutoolSoapUtil {

    public static void main(String[] args) {

        SoapClient soapClient = SoapClient.create("http://localhost:8080/web-service/UserService")
                .setMethod("tns:info", "http://service.server.web.gexingw.com")
                .setParam("name", "GeXingW", false);
        System.out.println(soapClient.getMsgStr(true));

        System.out.println(soapClient.send());
    }

}
