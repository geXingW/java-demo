package com.gexingw.unittest.controller;

import com.gexingw.unittest.entity.Order;
import com.gexingw.unittest.exception.InvalidParamException;
import com.gexingw.unittest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GeXingW
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Order info(@PathVariable Long id) {
        return orderService.getById(id);
    }


}
