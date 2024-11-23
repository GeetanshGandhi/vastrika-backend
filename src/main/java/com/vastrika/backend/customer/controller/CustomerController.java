package com.vastrika.backend.customer.controller;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/loginCustomer")
    public String loginCustomer(@RequestBody Customer customer){
        return customerService.loginCustomer(customer);
    }

    @PostMapping("/registerCustomer")
    public String registerCustomer(@RequestBody Customer customer){
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<Customer> updateAddress(@RequestParam("email") String email,
                                        @RequestParam("hno") String hno,
                                        @RequestParam("sbname") String sbname,
                                        @RequestParam("lmark") String lmark,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state){
        try{
            Customer res = customerService.updateAddress(email, hno, sbname, lmark, city, state);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateMobile")
    public ResponseEntity<Customer> updatePhone(@RequestParam("customerEmail") String customerEmail,
                                                @RequestParam("newNumber") String newnumber){
        try{
            Customer res = customerService.updatePhoneNumber(customerEmail, newnumber);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public List<Customer> getAll(){
        return customerService.getAll();
    }
}

