package com.kaydelarose.airae.model;

import java.util.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product {
    @Id
    private String productId;
    private String name;
    private String brand;
    private String productType;
    private List<String> keyIngredients;
    private List<String> skinTypeCompatibility;
    private List<String> targetedConcerns;
    private String price;
    private boolean fragranceFree;
    private double rating;
    private String brandLink;
    private String amazonLink;

    public Product(String name, String productType, List<String> skinTypeCompatibility) {
        this.name = name;
        this.productType = productType;
        this.skinTypeCompatibility = skinTypeCompatibility;
        this.keyIngredients = new ArrayList<>();
        this.targetedConcerns = new ArrayList<>();
        this.price = "N/A";
        this.fragranceFree = false;
        this.rating = 0.0;
        this.brandLink = "";
        this.amazonLink = "";
    }

}

