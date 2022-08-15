package com.camilo.marketserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilo.marketserver.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
