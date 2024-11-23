package com.vastrika.backend.categoryrequest.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrika.backend.categoryrequest.model.CategoryRequest;
import com.vastrika.backend.categoryrequest.repository.CategoryRequestRepository;

@Service
public class CategoryRequestService {

    @Autowired
    CategoryRequestRepository categoryRequestRepository;

    public CategoryRequest getCategoryRequestById (int categoryRequestId){
         return categoryRequestRepository.findById(categoryRequestId).get();
    }

    public CategoryRequest addCategoryRequest(CategoryRequest categoryRequest) {
        return categoryRequestRepository.save(categoryRequest);
    }

    public void deleteCategoryRequest(int categoryRequestId) {
        categoryRequestRepository.deleteById(categoryRequestId);
    }

    public List<CategoryRequest> getAllCategoryRequests() {
        return categoryRequestRepository.findAll();
    }

    public List<CategoryRequest> getAllCategoryRequestsTrue() {
        return categoryRequestRepository.findByCategoryStatusTrue();
    }

    public boolean getCategoryStatusById(int categoryRequestId){
        return categoryRequestRepository.findById(categoryRequestId).get().getCategoryStatus();
    }
}
