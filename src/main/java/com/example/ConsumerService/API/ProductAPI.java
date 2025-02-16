package com.example.ConsumerService.API;


import com.example.ConsumerService.DTO.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("product/api/v1")
public interface ProductAPI {

    @PutMapping(value = "/storeProductDetails")
    ResponseEntity<Void> storeProductDetails();

}
