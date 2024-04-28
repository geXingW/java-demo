package com.gexingw.web.service.client;

import lombok.NoArgsConstructor;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author GeXingW
 */
public class ClientInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public ClientInterceptor() {
        super(Phase.PREPARE_SEND);
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) {
        List<Header> headers = soapMessage.getHeaders();

        Document document = DOMUtils.createDocument();
        Element authorityElement = document.createElement("authority");
        Element usernameElement = document.createElement("username");
        Element passwordElement = document.createElement("password");

        usernameElement.setTextContent("admin");
        passwordElement.setTextContent("123456");

        authorityElement.appendChild(usernameElement).appendChild(passwordElement);

        headers.add(0, new Header(new QName("authority"), authorityElement));
    }

}
