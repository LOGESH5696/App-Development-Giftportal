package com.example.demo.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
