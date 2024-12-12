package com.example.demo.Controllers;

import com.example.demo.Models.Cart;
import com.example.demo.Models.User;
import com.example.demo.Services.CartService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public List<Cart> getAllCarts(@PathVariable Long userId) {
        return cartService.findAllByUserId(userId);
    }

    @GetMapping("/{userId}/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long userId, @PathVariable Long cartId) {
        Optional<Cart> cart = cartService.findByIdAndUserId(cartId, userId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}")
    public Cart createCart(@PathVariable Long userId, @RequestBody Cart cart) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setUser(user);
        return cartService.save(cart);
    }

    @PutMapping("/{userId}/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long userId, @PathVariable Long cartId, @RequestBody Cart cartDetails) {
        Optional<Cart> cart = cartService.findByIdAndUserId(cartId, userId);
        if (cart.isPresent()) {
            Cart updatedCart = cart.get();
            updatedCart.setCartProducts(cartDetails.getCartProducts());
            cartService.save(updatedCart);
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long userId, @PathVariable Long cartId) {
        Optional<Cart> cart = cartService.findByIdAndUserId(cartId, userId);
        if (cart.isPresent()) {
            cartService.deleteById(cartId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
