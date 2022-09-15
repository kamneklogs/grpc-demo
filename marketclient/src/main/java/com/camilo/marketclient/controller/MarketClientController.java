package com.camilo.marketclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camilo.marketclient.grpcclient.ProductsGrpcClient;
import com.camilo.marketclient.model.Product;

@RestController
@RequestMapping("/product")
public class MarketClientController {

    private final ProductsGrpcClient productsGrpcClient;

    @Autowired
    public MarketClientController(ProductsGrpcClient productsGrpcClient) {
        this.productsGrpcClient = productsGrpcClient;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productsGrpcClient.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productsGrpcClient.getProductById(id));
    }
}
