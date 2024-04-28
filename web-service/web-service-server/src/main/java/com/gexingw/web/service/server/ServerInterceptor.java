package com.gexingw.web.service.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

/**
 * @author GeXingW
 */
@Slf4j
@Component
public class ServerInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private SAAJInInterceptor saa = new SAAJInInterceptor();

    public ServerInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }

    @SneakyThrows
    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        // 获取消息内容
        SOAPMessage content = soapMessage.getContent(SOAPMessage.class);
        if (content == null) {
            // 走默认处理
            this.saa.handleMessage(soapMessage);

            // 获取消息内容
            content = soapMessage.getContent(SOAPMessage.class);
        }

        SOAPHeader soapHeader = null;
        try {
            soapHeader = content.getSOAPHeader();
        } catch (Exception e) {
            log.error("获取消息头失败", e);
        }

        if (soapHeader != null) {
            throw new Fault(new IllegalArgumentException("认证信息异常！"));
        }

        NodeList usernameNodeList = soapHeader.getElementsByTagName("username");
        NodeList passwordNodeList = soapHeader.getElementsByTagName("password");
        String username = usernameNodeList.item(0).getTextContent();
        String password = passwordNodeList.item(0).getTextContent();

        if (!"admin".equals(username) || !"123456".equals(password)) {
            log.warn("认证失败！");
            throw new SOAPException("认证失败！");
        }

        log.info("认证成功！");
    }

}
