package com.example.demo.Services;

import com.example.demo.Models.Cart;
import com.example.demo.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Optional<Cart> findByIdAndUserId(Long cartId, Long userId) {
        return cartRepository.findByIdAndUserId(cartId, userId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteById(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
