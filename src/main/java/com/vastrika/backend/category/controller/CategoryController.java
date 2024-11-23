package com.vastrika.backend.category.controller;

import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public String addCategory(@RequestBody String categoryname){
        return categoryService.addCategory(categoryname.replace("\"",""));
    }

    @PostMapping("/getByName")
    public Category getCategoryByCategoryName(@RequestBody String cname){
        return categoryService.getCategoryByName(cname);
    }

    @GetMapping("/getAll")
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
