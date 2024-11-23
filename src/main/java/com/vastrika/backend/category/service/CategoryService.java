package com.vastrika.backend.category.service;

import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public String addCategory(String catName){
        if(categoryRepository.findById(catName).isPresent()) return "Already Present";
        return categoryRepository.save(new Category(catName)).toString();
    }

    public Category getCategoryByName(String catName){
        return categoryRepository.findById(catName).get();
    }

    public List<String> getAllCategories(){
        List<String> allCats = new ArrayList<>();
        List<Category> fetched = categoryRepository.findAll();
        for(Category c: fetched) allCats.add(c.getCategoryName());
        return allCats;
    }
}
