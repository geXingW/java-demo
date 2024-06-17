package com.gexingw.unittest.service;

import com.gexingw.unittest.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author GeXingW
 */
@Service
public class OrderService {

    @Autowired
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Order getById(Long id) {
        System.out.println("OrderService called.");
        String user = this.getOrderUser(id);

        return new Order().setId(id)
                .setTotalAmount(BigDecimal.valueOf(15).multiply(BigDecimal.valueOf(id)))
                .setProduct(productService.getById(id))
                .setUser(user);
    }

    public void delete(Long id) {
        System.out.println("Order delete...");
        productService.delete(id);
    }

    private String getOrderUser(Long id) {
        return "Order User: " + id;
    }

    public static String staticMethod() {
        return "Order static method called.";
    }

}
