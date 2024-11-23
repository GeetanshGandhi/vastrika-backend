package com.vastrika.backend.orders.repository;

import com.vastrika.backend.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value="DESC orders", nativeQuery = true)
    List<Object[]> getOrderDescription();

    @Query(value = "SELECT * FROM orders WHERE customer_email= :cmail ORDER BY order_id DESC", nativeQuery = true)
    List<Orders> findAllByCustomer(@Param("cmail") String customerEmail);
}
