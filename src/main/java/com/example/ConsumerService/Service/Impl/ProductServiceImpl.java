package com.example.ConsumerService.Service.Impl;

import com.example.ConsumerService.DTO.ProductDTO;
import com.example.ConsumerService.Entity.Product;
import com.example.ConsumerService.Repository.ProductRepository;
import com.example.ConsumerService.Service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    List<ProductDTO> productDetailsList = new ArrayList<>();

    @KafkaListener(topics = "PRODUCT_DETAILS", groupId = "PRODUCT_DETAIL_GROUP")
    public void addProductDetails(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("message:"+message);
         productDetailsList.add(objectMapper.readValue(message, ProductDTO.class));
    }

    public void  storeProductDetails(){
        List<Product> productLists = new ArrayList<>();
        List<String> productIdList = new ArrayList<>();
        for(ProductDTO productDTO: productDetailsList){
            productIdList.add(productDTO.getProductId());
        }
        List<Product> productList = productRepository.findAllByProductIdIn(productIdList);
        for(Product product1:productList){
            for(ProductDTO productDTO: productDetailsList){
                if(productDTO.getProductId().equals(product1.getProductId())){
                    product1.setProductName(productDTO.getProductName());
                    product1.setProductPrice(productDTO.getProductPrice());
                    product1.setProductQuantity(productDTO.getProductQuantity());
                    productLists.add(product1);
                }
            }

        }
        productRepository.saveAll(productLists);
        productDetailsList.clear();
    }
}
