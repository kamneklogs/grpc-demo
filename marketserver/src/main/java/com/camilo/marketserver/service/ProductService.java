package com.camilo.marketserver.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.camilo.marketserver.model.Product;

@Service
public class ProductService {

    private final Map<Integer, Product> products = new HashMap<>();

    public Product save(Product product) {
        products.put(product.getId(), product);
        return this.findById(product.getId());
    }

    public Iterable<Product> findAll() {
        return products.values();
    }

    public Product findById(int id) {
        return products.get(id);
    }

    public void delete(Product product) {
        products.remove(product.getId());
    }
}
