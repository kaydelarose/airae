package com.kaydelarose.airae.service;

import com.kaydelarose.airae.model.Ingredient;
import com.kaydelarose.airae.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Validated
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> getIngredientById(String ingredientId) {
        return ingredientRepository.findById();
    }

    public Optional<Ingredient> getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getIngredientsByProperties(String category, String benefit) {
        return ingredientRepository.findByCategoryAndBenefitsContaining(category, benefit);
    }

    public boolean isFragrance(String ingredientName) {
        Optional<Ingredient> ingredient = ingredientRepository.findByName(ingredientName);
        return ingredient.map(ing -> "Fragrance".equalsIgnoreCase(ing.getCategory())).orElse(false);
    }

    public String categorizeIngredient(String ingredientName) {
        Optional<Ingredient> ingredient = ingredientRepository.findByName(ingredientName);
        return ingredient.map(Ingredient::getCategory).orElse("Unknown");
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(String ingredientId, Ingredient updatedIngredient) {
        updatedIngredient.setId(ingredientId);
        return ingredientRepository.save(updatedIngredient);
    }

    public void deleteIngredient(String ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

}
