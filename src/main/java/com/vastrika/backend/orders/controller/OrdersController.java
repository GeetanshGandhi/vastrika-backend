package com.vastrika.backend.orders.controller;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/desc")
    public List<Object[]> getDesc(){
        return ordersService.getTableDesc();
    }

    @PostMapping("/add")
    public String addNewOrder(@RequestBody NewOrderData newOrderData){
        return ordersService.addOrder(newOrderData);
    }

    @PostMapping("/getByCustomer")
    public List<PlacedOrderData> getByCustomer(@RequestBody Customer customer){
        return ordersService.getOrdersByCustomer(customer);
    }
}
