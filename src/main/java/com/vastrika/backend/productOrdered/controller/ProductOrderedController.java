package com.vastrika.backend.productOrdered.controller;

import java.util.List;

import com.vastrika.backend.productOrdered.repository.ProductOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.service.ProductOrderedService;

@RestController
@CrossOrigin
@RequestMapping("/prodOrd")
public class ProductOrderedController {

    @Autowired
    ProductOrderedService productOrderedService;
    @Autowired
    ProductOrderedRepository porepo;

    @GetMapping("/all")
    List<ProductOrdered> getall(){
        return porepo.findAll();
    }

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

    @PostMapping("/getByDestCity")
    public List<DevEmpPOPayload> getByDestination(@RequestBody String city){
        return productOrderedService.getByDestinationAndStat(city);
    }

    @PostMapping("/getByDestAndStatAndEmp")
    public List<DevEmpPOPayload> getByDestAndStatAndEmp(@RequestParam("city") String city,
                                                        @RequestParam("emp") String empEmail){
        return productOrderedService.getByDestAndStatAndEmp(city, empEmail);
    }

    @PostMapping("/acceptOrderByDevemp")
    public String handleOrderAcceptanceByDevemp(@RequestParam("orderId") int orderId,
                                                @RequestParam("productId") int productId,
                                                @RequestParam("devemp") String empEmail){
        return productOrderedService.handleOrderAcceptance(orderId, productId, empEmail);
    }

    @PostMapping("/finish")
    public String finishOrder(@RequestParam("orderId") int orderId,
                              @RequestParam("productId") int productId,
                              @RequestParam("empEmail") String empEmail,
                              @RequestParam("otp") int otp){
        return productOrderedService.finishOrder(orderId, productId, empEmail, otp);
    }
}