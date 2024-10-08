package com.vastrika.backend.productOrdered.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.service.ProductOrderedService;

@RestController
@CrossOrigin
@RequestMapping("/prodOrd")
public class ProductOrderedController {

    @Autowired
    ProductOrderedService productOrderedService;

    @PostMapping("/getByBusAndStat")
    public List<ProductOrdered> getByBusAndStat(@RequestParam("ownerEmail") String ownerEmail,
                                                @RequestParam("status") String status){
        return productOrderedService.getByBusinessAndStatus(ownerEmail, status);
    }

    @PostMapping("/getAllForAdmin")
    public List<ProductOrdered> getAllForAdmin(@RequestParam("status") String status){
        return productOrderedService.getAllForAdmin(status);
    }

    @PostMapping("/setToDispatched")
    public String packedToDispatched(@RequestParam("productId") int productId,
                                           @RequestParam("orderId") int orderId){
        return productOrderedService.packedToDispatched(orderId, productId);
    }

    @PostMapping("/setToInCity")
    public String DispatchedToInCity(@RequestParam("productId") int productId,
                                           @RequestParam("orderId") int orderId){
        return productOrderedService.dispatchedToInCity(orderId, productId);
    }

    @PostMapping("/updateForBusiness")
    public String dispatchOrderForDelivery(@RequestParam("productId") int productId,
                                           @RequestParam("orderId") int orderId){
        return productOrderedService.dispatchOrderForDelivery(orderId, productId);
    }

    @PostMapping("cancel")
    public String cancelOrder(@RequestParam("orderId") int orderId,
                              @RequestParam("productId") int productId,
                              @RequestParam("reason") String reason){
        return productOrderedService.cancelOrder(orderId, productId, reason);
    }
}
