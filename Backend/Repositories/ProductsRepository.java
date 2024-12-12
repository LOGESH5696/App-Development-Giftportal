package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}

