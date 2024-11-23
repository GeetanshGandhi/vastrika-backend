package com.vastrika.backend.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Value("${project.image}")
    private String imageFilePath;

    @PostMapping("/saveNew")
    public String saveNewProduct(
            @RequestParam("productDet") String productString,
            @RequestParam("businessDet") String businessString,
            @RequestParam("productImage") MultipartFile inputImage){
        //save to db
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try{
            product = mapper.readValue(productString, Product.class);
        } catch (JsonProcessingException e){
            return "Invalid product";
        }
        Business business = null;
        try{
            business = mapper.readValue(businessString, Business.class);
        } catch(JsonProcessingException e){
            return "Invalid product";
        }
        try {
            Product dbOut = productService.saveProductToDB(product, business, inputImage);
            return "Success";
        } catch (IOException e) {
            return "Failure";
        }
    }

    @PostMapping("/getByOwner")
    public List<Product> getProductsByOwner(@RequestBody String ownerEmail){
        return productService.getByOwner(ownerEmail);
    }

    @PostMapping("/delete")
    public String deleteProd(@RequestBody Product product){
        return productService.deleteProduct(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProd(){
        return productService.getAll();
    }

    @PostMapping("/getByIds")
    public List<Product> getAllProductsByIds(@RequestBody List<Integer> ids){
        return productService.getByIds(ids);
    }

    @PostMapping("/updateImage")
    public String updateProdImg(@RequestParam("productId") int productId,
                                @RequestParam("productImage") MultipartFile productImage){
        try {
            return productService.updateImage(productId, productImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Failure";
        }
    }
}
