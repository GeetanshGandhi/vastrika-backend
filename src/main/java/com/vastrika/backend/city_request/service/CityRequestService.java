package com.vastrika.backend.city_request.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrika.backend.city_request.model.CityRequest;
import com.vastrika.backend.city_request.repository.CityRequestRepository;

@Service
public class CityRequestService {

    @Autowired
    private CityRequestRepository cityRequestRepository;
    public CityRequest addCityRequest(CityRequest cityRequest) {
        return cityRequestRepository.save(cityRequest);
    }

    public List<CityRequest> getAllUnresolvedRequests() {
        return cityRequestRepository.findByCityRequestStatusFalse();
    }

    public CityRequest updateCityRequestStatus(int requestId, boolean status) {
        CityRequest cityRequest = cityRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));

        cityRequest.setCityRequestStatus(status);
        return cityRequestRepository.save(cityRequest);
    }

    public boolean deleteall(){
        cityRequestRepository.deleteAll();
        return true;
    }
}
