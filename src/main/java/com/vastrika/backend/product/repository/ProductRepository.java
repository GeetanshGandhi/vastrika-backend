package com.vastrika.backend.product.repository;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByBusiness(Business business);
}
