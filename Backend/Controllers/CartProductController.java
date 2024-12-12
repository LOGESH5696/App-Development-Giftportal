package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Models.CartProduct;
import com.example.demo.Services.CartProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart-products")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/get")
    public ResponseEntity<List<CartProduct>> getAllCartProducts() {
        List<CartProduct> items = cartProductService.getAllCartProducts();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CartProduct> getCartProductById(@PathVariable Long id) {
        Optional<CartProduct> cartProduct = cartProductService.getCartProductById(id);
        return cartProduct.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/post")
    public ResponseEntity<CartProduct> addCartProduct(@RequestBody CartProduct cartProduct) {
        CartProduct newItem = cartProductService.saveCartProduct(cartProduct);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartProduct(@PathVariable Long id) {
        try {
            cartProductService.deleteCartProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
