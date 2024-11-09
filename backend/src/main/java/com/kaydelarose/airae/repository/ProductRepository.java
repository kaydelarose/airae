package com.kaydelarose.airae.repository;

import com.kaydelarose.airae.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByBrand(String brand);
    List<Product> findBySkinTypeCompatibilityContains(String skinType);
    List<Product> findByTargetedConcernsContains(String concern);
    Product findTopByProductTypeAndTargetedConcernsContaining(String productType, String targetedConcern);
    Product findTopByProductType(String productType);
    Product findByName(String name);
}
