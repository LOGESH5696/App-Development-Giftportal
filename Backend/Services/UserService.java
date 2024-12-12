package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Cart;
import com.example.demo.Models.User;
import com.example.demo.Repositories.CartRepository;
import com.example.demo.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private CartRepository cartRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        User user2 =  userRepository.save(user);
        Cart cart = new Cart(null, user2, null);
        cartRepository.save(cart);
        return user2;
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
