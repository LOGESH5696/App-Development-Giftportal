package com.example.demo.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.CartProduct;
import com.example.demo.Repositories.CartProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProduct> getAllCartProducts() {
        return cartProductRepository.findAll();
    }

    public Optional<CartProduct> getCartProductById(Long id) {
        return cartProductRepository.findById(id);
    }

    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    public void deleteCartProduct(Long id) {
        cartProductRepository.deleteById(id);
    }

    // No update method as CartProduct only contains cart_id and product_id
}
