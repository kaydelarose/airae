package com.kaydelarose.airae.repository;

import com.kaydelarose.airae.model.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.*;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Optional<Ingredient> findById();
    List<Ingredient> findByCategoryAndBenefitsContaining(String category, String benefit);
    Optional<Ingredient> findByName(String name);
}
