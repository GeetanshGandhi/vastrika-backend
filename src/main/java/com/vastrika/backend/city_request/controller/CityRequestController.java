package com.vastrika.backend.city_request.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastrika.backend.city_request.model.CityRequest;
import com.vastrika.backend.city_request.service.CityRequestService;

@RestController
@RequestMapping("/api/city-requests")
@CrossOrigin
public class CityRequestController {

    @Autowired
    private CityRequestService cityRequestService;

    @PostMapping("/add")
    public ResponseEntity<CityRequest> addCityRequest(@RequestBody CityRequest cityRequest) {
        CityRequest newCityRequest = cityRequestService.addCityRequest(cityRequest);
        return ResponseEntity.ok(newCityRequest);
    }

    @GetMapping("/unresolved")
    public List<CityRequest> getAllUnresolvedRequests() {
        List<CityRequest> unresolvedRequests = cityRequestService.getAllUnresolvedRequests();
        return unresolvedRequests;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CityRequest> updateCityRequestStatus(@PathVariable int id, @RequestParam boolean status) {
        CityRequest updatedRequest = cityRequestService.updateCityRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/deleteAll")
    public boolean deleteall(){
        return cityRequestService.deleteall();
    }
}
