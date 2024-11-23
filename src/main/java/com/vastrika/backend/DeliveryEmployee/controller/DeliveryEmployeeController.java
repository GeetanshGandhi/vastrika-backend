package com.vastrika.backend.DeliveryEmployee.controller;

import java.util.List;

import com.vastrika.backend.DeliveryEmployee.repository.DeliveryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;
import com.vastrika.backend.DeliveryEmployee.service.DeliveryEmployeeService;


@RestController
@CrossOrigin
@RequestMapping("/deliveryEmployee")
public class DeliveryEmployeeController {
    
    @Autowired
    DeliveryEmployeeService deliveryEmployeeService;
    @Autowired
    DeliveryEmployeeRepository derepo;

    @PostMapping("/create")
    public ResponseEntity<DeliveryEmployee> createDeliveryEmployee(@RequestBody DeliveryEmployee employee) {
        DeliveryEmployee newEmployee= deliveryEmployeeService.createDeliveryEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }
    
    @GetMapping("/getAll")
    public List<DeliveryEmployee> getAllDeliveryEmployees(){
        return deliveryEmployeeService.getAllDeliveryEmployees();
    }

    @GetMapping("/getByCity")
    public List<DeliveryEmployee> getDeliveryEmployeeByCity(@RequestParam("city") String city){
        return deliveryEmployeeService.getDeliveryEmployeeByCity(city);
    }

    @GetMapping("/getByID")
    public DeliveryEmployee getDeliveryEmployeeById(@RequestParam("mailID") String id){
        return deliveryEmployeeService.getDeliveryEmployeeById(id);
    }

    @PostMapping("/delete")
    public boolean deleteDeliveryEmployee(@RequestParam("mailID") String id){
        return deliveryEmployeeService.deleteDeliveryEmployee(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginEmployee(@RequestBody DeliveryEmployee deliveryEmployee){
        String res = deliveryEmployeeService.loginDevEmp(deliveryEmployee);
        if(res.equals("Invalid")){
            return new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
