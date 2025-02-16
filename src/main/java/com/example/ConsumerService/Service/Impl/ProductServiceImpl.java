package com.example.ConsumerService.Service.Impl;

import com.example.ConsumerService.DTO.ProductDTO;
import com.example.ConsumerService.Entity.Product;
import com.example.ConsumerService.Repository.ProductRepository;
import com.example.ConsumerService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    ProductDTO productDetails = new ProductDTO();

    @KafkaListener(topics = "PRODUCT_DETAILS", groupId = "PRODUCT_DETAIL_GROUP")
    public void addProductDetails(ProductDTO productDTO){
        System.out.println("productDTO:"+productDTO.getProductName());
        productDetails = productDTO;
    }

    public void  storeProductDetails(){
        String productId = productDetails.getProductId();
        Product product = new Product();
        if(productRepository.existsByProductId(productId)){
            product = productRepository.findByProductId(productId);
        }
        product.setProductId(productDetails.getProductId());
        product.setProductName(productDetails.getProductName());
        product.setProductQuantity(productDetails.getProductQuantity());
        product.setProductPrice(productDetails.getProductPrice());
        productRepository.save(product);
    }
}
