package com.example.demo.Repositories;

import com.example.demo.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    List<Cart> findByUserId(Long userId);
    
    Optional<Cart> findByIdAndUserId(Long cartId, Long userId);
}
