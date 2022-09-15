package com.camilo.marketserver.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.camilo.marketserver.model.Product;
import com.camilo.marketserver.service.ProductService;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ProductService userService;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100000; i++) {
            userService.save(new Product(i, "Product " + i, "Description " + i));
        }

    }
}