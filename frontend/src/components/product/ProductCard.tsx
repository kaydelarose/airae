import React from 'react';
import './ProductCard.css';

interface ProductCardProps {
    name: string;
    brand: string | null;
    productType: string; 
    keyIngredients: string[];
    skinTypeCompatibility: string[];
    targetedConcerns: string[];
    price: string;
    fragranceFree: boolean;
    brandLink: string;
    amazonLink: string;
}

const ProductCard: React.FC<ProductCardProps> = ({
    name,
    brand,
    productType,
    keyIngredients,
    skinTypeCompatibility,
    targetedConcerns,
    price,
    fragranceFree,
    brandLink,
    amazonLink,
}) => {
    return (
        <div className="product-card">
            <h3 className="product-name">{name}</h3>
            <p className="product-brand">Brand: {brand || 'N/A'}</p>
            <p className="product-type">Type: {productType}</p>
            <p className="product-price">Price: {price}</p>
            <div className="product-details">
                <p><strong>Key Ingredients:</strong> {keyIngredients.join(', ')}</p>
                <p><strong>Skin Types:</strong> {skinTypeCompatibility.join(', ')}</p>
                <p><strong>Concerns:</strong> {targetedConcerns.join(', ')}</p>
                <p><strong>Fragrance-Free:</strong> {fragranceFree ? 'Yes' : 'No'}</p>
            </div>
            <a href={brandLink} target="_blank" rel="noopener noreferrer" className="brand-link">Visit Brand</a>
            <a href={amazonLink} target="_blank" rel="noopener noreferrer" className="amazon-link"></a>
        </div>
    );
};

export default ProductCard;
