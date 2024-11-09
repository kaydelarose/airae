import React from 'react';
import ProductCard from './ProductCard';
import './ProductList.css';

interface Product {
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

interface ProductListProps {
  products: Product[];
}

const ProductList: React.FC<ProductListProps> = ({ products }) => {
  const categorizedProducts = {
    cleanser: products.filter(product => product.productType === 'cleanser'),
    moisturizer: products.filter(product => product.productType === 'moisturizer'),
    sunscreen: products.filter(product => product.productType === 'sunscreen'),
  };

  const descriptions = {
    cleanser: "Cleansers remove dirt, oil, and impurities from your skin, helping to keep it fresh and clear.",
    moisturizer: "Moisturizers hydrate the skin, improving texture and preventing dryness.",
    sunscreen: "Sunscreens protect against harmful UV rays, reducing skin damage and the risk of sunburn.",
  };

  return (
    <div className="product-list-container">
      {Object.entries(categorizedProducts).map(([type, products]) => (
        <div key={type} className="product-category">
          <h2 className="category-title">{type.charAt(0).toUpperCase() + type.slice(1)}</h2>
          <p className="category-description">{descriptions[type as keyof typeof descriptions]}</p>
  
          <div className="horizontal-scroll">
            {products.length > 0 ? (
              products.map((product, index) => (
                <div key={index} className="scroll-item">
                  <ProductCard {...product} />
                </div>
              ))
            ) : (
              <p>No products available for this category with the current filters.</p>
            )}
          </div>
        </div>
      ))}
    </div>
  );  
};

export default ProductList;
