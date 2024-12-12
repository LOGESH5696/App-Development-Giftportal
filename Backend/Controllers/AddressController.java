package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.Address;
import com.example.demo.Models.User;
import com.example.demo.Services.AddressService;
import com.example.demo.Services.UserService;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService; // Add UserService to fetch User by userId

    @GetMapping("/get")
    public List<Address> getAllAddresses() {    
        return addressService.getAllAddresses();
    }
    @GetMapping("/get/{userId}")
    public List<Address> getAllAddressesByUserId(@PathVariable Long userId) {    
        return addressService.getAllAddressesByUserId(userId);
    }


    @PostMapping("/post/{userId}")
    public Address saveAddress(@PathVariable Long userId, @RequestBody Address address) {
        User user = userService.getUserById(userId); // Fetch the user by userId
        address.setUser(user); // Set the user as a foreign key in the Address object
        return addressService.saveAddress(address);
    }   

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
