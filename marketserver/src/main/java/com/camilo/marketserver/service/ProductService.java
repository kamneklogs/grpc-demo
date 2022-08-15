package com.camilo.marketserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilo.marketserver.model.Product;
import com.camilo.marketserver.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        Product productSaved = productRepository.save(product);
        return this.findById(productSaved.getId());
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}
