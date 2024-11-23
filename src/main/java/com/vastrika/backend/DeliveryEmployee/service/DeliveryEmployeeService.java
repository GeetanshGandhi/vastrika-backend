package com.vastrika.backend.DeliveryEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;
import com.vastrika.backend.DeliveryEmployee.repository.DeliveryEmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryEmployeeService {
   
    @Autowired
    private DeliveryEmployeeRepository deliveryEmployeeRepository;

    public DeliveryEmployee createDeliveryEmployee(DeliveryEmployee deliveryEmployee) {
        return deliveryEmployeeRepository.save(deliveryEmployee);
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees() {
        return deliveryEmployeeRepository.findAll();
    }

   public List<DeliveryEmployee> getDeliveryEmployeeByCity(String city){
        return deliveryEmployeeRepository.getDeliveryEmployeeByCity(city);
    }

    public DeliveryEmployee getDeliveryEmployeeById(String mailID) {
        Optional<DeliveryEmployee> employee = deliveryEmployeeRepository.findById(mailID);
        return employee.orElse(null);
    }

    public boolean deleteDeliveryEmployee(String mailID) {
        if (deliveryEmployeeRepository.existsById(mailID)) {
            deliveryEmployeeRepository.deleteById(mailID);
            return true;
        }
        return false;
    }

    public String loginDevEmp(DeliveryEmployee deliveryEmployee){
        Optional<DeliveryEmployee> existing = deliveryEmployeeRepository.findById(deliveryEmployee.getEmployeeEmail());
        if(existing.isEmpty()){
            return "NotFound";
        }
        if(deliveryEmployee.getPassword().equals(existing.get().getPassword())){
            return existing.get().toString();
        }
        return "Invalid";
    }
}
