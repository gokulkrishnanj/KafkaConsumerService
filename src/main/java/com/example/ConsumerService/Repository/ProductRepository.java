package com.example.ConsumerService.Repository;

import com.example.ConsumerService.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductId(String productId);
    boolean existsByProductId(String productId);
}
