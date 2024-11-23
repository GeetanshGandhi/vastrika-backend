package com.vastrika.backend.categoryrequest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastrika.backend.categoryrequest.model.CategoryRequest;
import com.vastrika.backend.categoryrequest.service.CategoryRequestService;

@RestController
@RequestMapping("/categoryRequest")
@CrossOrigin

public class CategoryRequestController {
   @Autowired
   CategoryRequestService categoryRequestService;

   @PostMapping("/add")
    public CategoryRequest addCategoryRequest(@RequestBody CategoryRequest categoryRequest) {
        return categoryRequestService.addCategoryRequest(categoryRequest);
    }

    @GetMapping("/{id}")
    public CategoryRequest getCategoryRequestById(@PathVariable int id) {
        return categoryRequestService.getCategoryRequestById(id);
    } 

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryRequest(@PathVariable int id) {
        categoryRequestService.deleteCategoryRequest(id);
    }

    @GetMapping("/all")
    public List<CategoryRequest> getAllCategoryRequests() {
        return categoryRequestService.getAllCategoryRequests();
    }

    @GetMapping("/active")
    public List<CategoryRequest> getAllCategoryRequestsTrue() {
        return categoryRequestService.getAllCategoryRequestsTrue();
    }

    @GetMapping("/status/{id}")
    public boolean getCategoryStatusById(@PathVariable int id) {
        return categoryRequestService.getCategoryStatusById(id);
    }
}