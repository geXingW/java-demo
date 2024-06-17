package com.gexingw.unittest.controller;

import com.alibaba.fastjson2.JSON;
import com.gexingw.unittest.entity.Order;
import com.gexingw.unittest.service.OrderService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @SneakyThrows
    public void testInfo() {
        // 期望返回的对象
        Order order = new Order(1L);
        // 对OrderService的返回值进行模拟
        Mockito.doReturn(order).when(orderService).getById(order.getId());

        // 构造一个请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/" + order.getId()).accept(MediaType.APPLICATION_JSON);

        // 执行请求
        ResultActions resultActions = mockMvc.perform(requestBuilder);
        // 验证Http响应状态码
        resultActions.andExpect(status().isOk());
        // 使用JsonPath验证查询到的Id为1
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(order.getId()));
        // 获取响应对象
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        // 解析响应内容，进行复杂验证
        Order respObj = JSON.parseObject(response.getContentAsString(), Order.class);
        Assertions.assertEquals(order.getId(), respObj.getId());
    }

}
