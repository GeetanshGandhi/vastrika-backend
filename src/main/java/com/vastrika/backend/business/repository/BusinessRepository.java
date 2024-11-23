package com.vastrika.backend.business.repository;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {
    List<Business> findAllByApproval(String approval);
    List<Business> findAllByCity(City city);
}
