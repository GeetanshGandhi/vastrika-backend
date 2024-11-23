package com.vastrika.backend.city.service;

import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public String addCity(String pinCode, String cityName, String state, String description, MultipartFile cityIcon) throws IOException {

        if(pinCode.length()!=6) return "Invalid pincode";
        for(int i = 0; i<6; i++){
            char c = pinCode.charAt(i);
            if(c<48 || c>57) return "Invalid pincode";
        }
        for(int i = 0; i<cityName.length(); i++){
            if(!Character.isLetter(cityName.charAt(i))) return "Invalid Name";
        }
        City city = new City();
        city.setPinCode(pinCode);
        city.setState(state);
        city.setCityName(cityName);
        city.setDescription(description);
        city.setIconType(cityIcon.getContentType());
        city.setIcon(cityIcon.getBytes());
        try{
            cityRepository.save(city);
            return "Success";
        } catch(Exception e){
            return "Failure";
        }
    }

    public City getCityByName(String sname){
        City found = cityRepository.findByCityName(sname);
        if(found==null) return new City();
        return found;
    }

    public City getCityById(String cityId){
        return cityRepository.findById(cityId).get();
    }

    public List<City> getCitiesByState(String state){
        return cityRepository.findAllByState(state);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
    public List<String> getAllString(){
        List<City> alls = cityRepository.findAll();
        List<String> allstr = new ArrayList<>();
        for(City i: alls){
            allstr.add(i.getCityName());
        }
        return allstr;
    }

    public String updateCityIcon(String pinCode, MultipartFile icon) throws IOException{
        City city = cityRepository.findById(pinCode).get();
        city.setIcon(icon.getBytes());
        city.setIconType(icon.getContentType());
        cityRepository.save(city);
        return "success";
    }

}
