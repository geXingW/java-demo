package com.gexingw.web.service.server;

import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.xml.ws.Endpoint;

/**
 * @author GeXingW
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class WebServiceConfiguration {

    private final Bus bus;

    private final UserService userService;

    private final ServerInterceptor serverInterceptor;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/UserService");
        endpoint.getInInterceptors().add(serverInterceptor);

        return endpoint;
    }

}
