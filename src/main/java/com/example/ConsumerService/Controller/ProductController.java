package com.example.ConsumerService.Controller;

import com.example.ConsumerService.API.ProductAPI;
import com.example.ConsumerService.DTO.ProductDTO;
import com.example.ConsumerService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductAPI {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Void> storeProductDetails(){
        productService.storeProductDetails();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
