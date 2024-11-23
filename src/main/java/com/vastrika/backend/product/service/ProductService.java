package com.vastrika.backend.product.service;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.business.repository.BusinessRepository;
import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    BusinessRepository businessRepository;
    public Product saveProductToDB(Product product, Business business, MultipartFile productImage) throws IOException{
        //processing image and converting it to .jpg format
        BufferedImage bufferedImage = ImageIO.read(productImage.getInputStream());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg",output);
        product.setProductImage(output.toByteArray());

        product.setCity(business.getCity());
        product.setCategory(business.getCategory());
        product.setBusiness(business);
        return productRepository.save(product);
    }

    public Product editProduct(Product newp){
        Product old = productRepository.findById(newp.getProductId()).get();
        old.setPrice(newp.getPrice());
        old.setDiscount(newp.getDiscount());
        old.setDescription(newp.getDescription());
        old.setProductName(newp.getProductName());
        return productRepository.save(old);
    }

    public String updateImage(int productId, MultipartFile productImage) throws  IOException{
        Product prod = productRepository.findById(productId).get();

        BufferedImage bufferedImage = ImageIO.read(productImage.getInputStream());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg",output);
        prod.setProductImage(output.toByteArray());

        productRepository.save(prod);
        return "Success";
    }

    public List<Product> getByOwner(String owner){
        try{
            return productRepository.findAllByBusiness(businessRepository.findById(owner).get());
        } catch (NoSuchElementException e){
            return new ArrayList<>();
        }
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getByIds(List<Integer> prodIds){
        List<Product> out = new ArrayList<>();
        for(int i: prodIds){
            out.add(productRepository.findById(i).get());
        }
        return out;
    }

    //delete if image not uploaded
    public String deleteProduct(Product product){
        productRepository.delete(product);
        return "Success";
    }
}
