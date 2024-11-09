package com.kaydelarose.airae.service;

import com.kaydelarose.airae.model.Product;
import com.kaydelarose.airae.repository.ProductRepository;
import com.kaydelarose.airae.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final IngredientService ingredientService;

    @Autowired
    public ProductService(ProductRepository productRepository, IngredientService ingredientService) {
        this.productRepository = productRepository;
        this.ingredientService = ingredientService;
    }

    public Product addProduct(Product product) {
        boolean containsFragrance = product.getKeyIngredients().stream()
                .anyMatch(ingredient -> ingredientService.isFragrance(ingredient));
        product.setFragranceFree(!containsFragrance);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    public Product updateProduct(String productId, Product updatedProduct) {
        updatedProduct.setProductId(productId);
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductsBySkinType(String skinType) {
        return productRepository.findBySkinTypeCompatibilityContains(skinType);
    }

    public List<Product> getProductsByConcern(String concern) {
        return productRepository.findByTargetedConcernsContains(concern);
    }

    public List<Product> filterProducts(String skinType, String concern, Boolean fragranceFree) {
        return productRepository.findAll().stream()
                .filter(product -> (skinType == null || product.getSkinTypeCompatibility().contains(skinType)) &&
                        (concern == null || product.getTargetedConcerns().contains(concern)) &&
                        (fragranceFree == null || product.isFragranceFree() == fragranceFree))
                .collect(Collectors.toList());
    }
}
