package com.vastrika.backend.city.repository;

import com.vastrika.backend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    City findByCityName(String cityName);
    City findByCityNameAndState(String cityName, String state);
    List<City> findAllByState(String state);
}

