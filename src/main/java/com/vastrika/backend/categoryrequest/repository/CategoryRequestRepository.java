package com.vastrika.backend.categoryrequest.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vastrika.backend.categoryrequest.model.*;

@Repository
public interface CategoryRequestRepository  extends JpaRepository<CategoryRequest, Integer> {

    List<CategoryRequest> findByCategoryStatusTrue();

}

