package com.vastrika.backend.DeliveryEmployee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface DeliveryEmployeeRepository extends JpaRepository < DeliveryEmployee,String>{
   @Query(value= "SELECT * FROM DeliveryEmployee where city= :city", nativeQuery=true)
   List<DeliveryEmployee> getDeliveryEmployeeByCity(@Param("city") String city);
}
