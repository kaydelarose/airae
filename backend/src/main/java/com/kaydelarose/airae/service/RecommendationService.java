package com.kaydelarose.airae.service;

import com.kaydelarose.airae.model.Product;
import com.kaydelarose.airae.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    @Autowired
    private ProductRepository productRepository;

    public List<String> getProductRecommendations(String skinType, String concern, double humidity, double uvIndex, double temperature) {
        List<String> recommendations = new ArrayList<>();

        recommendations.add("Daily SPF is essential, even in low UV conditions.");

        if ("acne-prone".equals(concern)) {
            if (humidity < 50 && "oily".equals(skinType)) {
                recommendations.add("Consider using salicylic acid or benzoyl peroxide for acne management.");
            } else if (humidity > 70) {
                recommendations.add("Use lightweight, non-comedogenic products to prevent clogged pores.");
            }
            recommendations.add("Look for products with tea tree oil or niacinamide to reduce acne.");
        }

        if ("sensitive".equals(concern)) {
            recommendations.add("Opt for fragrance-free and alcohol-free products to reduce irritation.");
            recommendations.add("For SPF, choose mineral sunscreens with zinc oxide or titanium dioxide.");
            recommendations.add("Soothing ingredients like aloe vera or calendula can help calm your skin.");
        }

        if ("dry".equals(skinType)) {
            if (temperature < 10) {
                recommendations.add("Use a rich moisturizer with ceramides and hyaluronic acid.");
                recommendations.add("Consider adding squalane or shea butter for extra moisture.");
            }
            if (humidity < 40) {
                recommendations.add("A humidifier may help combat dryness indoors.");
            }
        }

        if ("combination".equals(skinType)) {
            recommendations.add("Use lightweight moisturizers with hyaluronic acid for hydration.");
            recommendations.add("Apply niacinamide-based products to control oil on the T-zone.");
        }

        if (uvIndex > 5) {
            recommendations.add("High UV index detected. Layer SPF with antioxidant serums like vitamin C.");
        }
        if (temperature < 5) {
            recommendations.add("Cold weather detected. Use rich moisturizers to protect your skin barrier.");
        }

        return recommendations;
    }

    public List<Product> getWeatherBasedRecommendations(double humidity, double uvIndex, double temperature) {
        List<Product> recommendations = new ArrayList<>();

        Optional<Product> sunscreen = Optional.ofNullable(
                uvIndex > 5 ? getRecommendedProduct("sunscreen", "high SPF") : getRecommendedProduct("sunscreen", "daily wear")
        );
        sunscreen.ifPresentOrElse(
                recommendations::add,
                () -> recommendations.add(new Product("Generic Sunscreen", "sunscreen", List.of("all")))
        );

        Optional<Product> moisturizer = Optional.ofNullable(
                humidity < 40 ? getRecommendedProduct("moisturizer", "hydrating") :
                        humidity > 70 ? getRecommendedProduct("moisturizer", "lightweight") :
                                getRecommendedProduct("moisturizer", "balanced")
        );
        moisturizer.ifPresentOrElse(
                recommendations::add,
                () -> recommendations.add(productRepository.findByName("CeraVe Moisturizing Cream"))
        );

        Optional<Product> cleanser = Optional.ofNullable(
                temperature < 10 ? getRecommendedProduct("cleanser", "hydrating") : getRecommendedProduct("cleanser", "refreshing")
        );
        cleanser.ifPresentOrElse(
                recommendations::add,
                () -> recommendations.add(productRepository.findByName("Cosmic Dew Water Cleanser"))
        );

        return recommendations;
    }

    private Product getRecommendedProduct(String productType) {
        return productRepository.findTopByProductType(productType);
    }

    private Product getRecommendedProduct(String productType, String targetedConcern) {
        return productRepository.findTopByProductTypeAndTargetedConcernsContaining(productType, targetedConcern);
    }
}
