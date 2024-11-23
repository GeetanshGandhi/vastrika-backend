package com.vastrika.backend.cart.repository;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.model.CartId;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, CartId> {
    List<CartItem> findAllByCustomer(Customer customer);

    CartItem findByCustomerAndProduct(Customer customer, Product product);

}
