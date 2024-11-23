package com.vastrika.backend.city_request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vastrika.backend.city_request.model.CityRequest;

@Repository
public interface CityRequestRepository extends JpaRepository<CityRequest, Integer> {
    List<CityRequest> findByCityRequestStatusFalse();  
}
