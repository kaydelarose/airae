package com.kaydelarose.airae.controller;

import com.kaydelarose.airae.model.Product;
import com.kaydelarose.airae.model.Ingredient;
import com.kaydelarose.airae.service.ProductService;
import com.kaydelarose.airae.service.IngredientService;
import com.kaydelarose.airae.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final IngredientService ingredientService;
    private final RecommendationService recommendationService;

    @Autowired
    public ProductController(ProductService productService, IngredientService ingredientService, RecommendationService recommendationService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/skin-type/{skinType}")
    public ResponseEntity<List<Product>> getProductsBySkinType(@PathVariable String skinType) {
        List<Product> products = productService.getProductsBySkinType(skinType);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/concern/{concern}")
    public ResponseEntity<List<Product>> getProductsByConcern(@PathVariable String concern) {
        List<Product> products = productService.getProductsByConcern(concern);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam(required = false) String skinType,
            @RequestParam(required = false) String concern,
            @RequestParam(required = false) Boolean fragrance) {

        if (skinType != null && skinType.isEmpty()) skinType = null;
        if (concern != null && concern.isEmpty()) concern = null;

        List<Product> products = productService.filterProducts(skinType, concern, fragrance);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getProductDetails(@PathVariable String id) {
        Optional<Product> productOpt = productService.getProductById(id);

        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOpt.get();

        List<Map<String, Object>> ingredientDetails = new ArrayList<>();
        for (String ingredientName : product.getKeyIngredients()) {
            Optional<Ingredient> ingredientOpt = ingredientService.getIngredientByName(ingredientName);

            Map<String, Object> ingredientInfo = new HashMap<>();
            ingredientInfo.put("name", ingredientName);
            ingredientInfo.put("category", ingredientOpt.map(Ingredient::getCategory).orElse("Unknown"));
            ingredientInfo.put("benefits", ingredientOpt.map(Ingredient::getBenefits).orElse(Collections.emptyList()));

            ingredientDetails.add(ingredientInfo);
        }

        boolean containsFragrance = ingredientDetails.stream()
                .anyMatch(detail -> "Fragrance".equalsIgnoreCase((String) detail.get("category")));

        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        response.put("ingredients", ingredientDetails);
        response.put("fragrance", containsFragrance);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/weather-recommendations")
    public ResponseEntity<List<Product>> getWeatherBasedRecommendations(
            @RequestParam double humidity,
            @RequestParam double uvIndex,
            @RequestParam double temperature) {
        List<Product> recommendations = recommendationService.getWeatherBasedRecommendations(humidity, uvIndex, temperature);
        return ResponseEntity.ok(recommendations);
    }
}
