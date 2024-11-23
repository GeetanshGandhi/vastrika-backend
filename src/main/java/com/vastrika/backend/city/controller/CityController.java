package com.vastrika.backend.city.controller;

import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.category.repository.CategoryRepository;
import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import com.vastrika.backend.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/add")
    public String addCity(@RequestParam("pinCode") String pinCode,
                          @RequestParam("cityName") String cityName,
                          @RequestParam("state") String state,
                          @RequestParam("description") String description,
                          @RequestParam("cityIcon") MultipartFile cityIcon){
        try {
            return cityService.addCity(pinCode, cityName, state, description, cityIcon);
        } catch (IOException e) {
            return "Failure";
        }
    }

    @PostMapping("/getByName")
    public City getCityByCityName(@RequestBody String city){
        return cityService.getCityByName(city);
    }
    
    @PostMapping("/getById")
    public City getCityByCityId(@RequestBody String cityId){
        return cityService.getCityById(cityId);
    }

    @PostMapping("/getbyState")
    public List<City> getCitiesByState(@RequestBody String state){
        return cityService.getCitiesByState(state);
    }

    @GetMapping("/getAll")
    public List<City> getAll(){
        return cityService.getAllCities();
    }

    @GetMapping("/getAllStr")
    public List<String> getAllStringCity(){
        return cityService.getAllString();
    }
    @PostMapping("/updateIcon")
    public String updateCityIcon(@RequestParam("pinCode") String pinCode,
                                 @RequestParam("cityIcon") MultipartFile icon){
        try {
            cityService.updateCityIcon(pinCode, icon);
            return "Success";
        } catch (IOException e) {
            return "Failure";
        }
    }

}
