package com.vastrika.backend.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.service.CartService;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("customerEmail") String customerEmail,
                            @RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity){
        return cartService.addToCart(customerEmail, productId, quantity);
    }

    @PostMapping("/getByCustomer")
    public List<CartItem> getCartByCustomer(@RequestBody Customer customer){
        return cartService.getCartItemsByCustomer(customer);
    }

    @PostMapping("/delete")
    public String deleteFromCart(@RequestParam("customerEmail") String customerEmail,
                                 @RequestParam("productId") int productId){
        return cartService.deletefromCart(customerEmail, productId);
    }

    @PostMapping("/updateQty")
    public CartItem updateQuantity(@RequestParam("customerEmail") String customerEmail,
                                   @RequestParam("productId") int productId,
                                   @RequestParam("newQty") int newQty){
        return cartService.updateQuantity(customerEmail, productId, newQty);
    }
}
