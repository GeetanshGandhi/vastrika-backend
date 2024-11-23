package com.vastrika.backend.business.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
@CrossOrigin
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/getAll")
    public List<Business> getAll(){
        return businessService.getAll();
    }

    @PostMapping("/register")
    public String registerBusiness(@RequestParam("businessDet") String businessString,
                                   @RequestParam("pinCode") String pinCode,
                                   @RequestParam("categoryName") String categoryName){
        ObjectMapper mapper = new ObjectMapper();
        Business business = null;
        try {
            business = mapper.readValue(businessString, Business.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return businessService.registerBusiness(business, pinCode, categoryName);
    }
    @PostMapping("/updateCityAndCat")
    public Business updateCityCat(@RequestParam("ownerEmail") String ownerEmail,
                                @RequestParam("pinCode") String pinCode,
                                @RequestParam("categoryName") String catName){
        return businessService.updateCityCat(ownerEmail, pinCode, catName);
    }

    @PostMapping("/login")
    public String businessLogin(@RequestBody Business business){
        return businessService.loginBusiness(business);
    }

    @PostMapping("/changeApproval")
    public String changeApprovalStat(@RequestBody Business business,
                                    @RequestParam("status") String status){
        return businessService.changeApproval(business,status);
    }

    @GetMapping("/getUnchecked")
    public List<Business> getUnchecked(){
        return businessService.getUncheckedApprovals();
    }

    @PostMapping("/getByCity")
    public List<Business> getAllByCity(@RequestBody String pinCode){
        return businessService.findBusinessByCity(pinCode);
    }

    @PostMapping("/getByEmail")
    public Business getBusinessByEmail(@RequestBody String ownerEmail){
        return businessService.getByEmail(ownerEmail);
    }
}
