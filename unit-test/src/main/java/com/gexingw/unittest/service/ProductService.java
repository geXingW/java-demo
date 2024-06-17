package com.gexingw.unittest.service;

import com.gexingw.unittest.entity.Product;
import org.springframework.stereotype.Service;

/**
 * @author GeXingW
 */
@Service
public class ProductService {

    public Product getById(Long id) {
        return new Product().setId(id).setName("Product: " + id);
    }

    public void delete(Long id) {
        System.out.println("Product delete...");
    }

    public static String staticMethod() {
        return "Static method called.";
    }
}
