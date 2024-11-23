package com.vastrika.backend.cart.service;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.repository.CartItemRepository;
import com.vastrika.backend.customer.controller.CustomerController;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.repository.CustomerRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    public String addToCart(String customerEmail, int productId, int quantity){
        Customer customer = customerRepository.findById(customerEmail).get();
        Product product = productRepository.findById(productId).get();
        CartItem found = cartItemRepository.findByCustomerAndProduct(customer,product);
        if(found!=null) return "Exist";
        CartItem cartItem = new CartItem(customer,product,quantity);
        cartItemRepository.save(cartItem);
        return "success";
    }

    public List<CartItem> getCartItemsByCustomer(Customer customer){
        return cartItemRepository.findAllByCustomer(customer);
    }

    public String deletefromCart(String customerEmail, int productId){
        Customer customer = customerRepository.findById(customerEmail).get();
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);
        try{
            cartItemRepository.delete(cartItem);
            return "Success";
        } catch (Exception e){
            return "Failure";
        }
    }

    public CartItem updateQuantity(String customerEmail, int productId, int newQty){
        Customer customer = customerRepository.findById(customerEmail).get();
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);
        cartItem.setQuantity(newQty);
        return cartItemRepository.save(cartItem);
    }
}
