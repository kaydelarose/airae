import { useState, useEffect } from 'react';
import Filter from '../components/FilterOptions/Filter';
import ProductList from '../components/product/ProductList';
import { fetchProducts } from '../services/api/ProductService';

interface Filters {
  skinType?: string;
  concern?: string;
  fragrance?: boolean | null;
}

interface Product {
  productType: string;
  name: string;
  brand: string | null;
  skinTypeCompatibility: string[];
  targetedConcerns: string[];
  price: string;
  fragranceFree: boolean;
  rating: number;
  brandLink: string;
  amazonLink: string;
  keyIngredients: string[];
}

const ProductPage = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [filters, setFilters] = useState<Filters>({
    skinType: '',
    concern: '',
    fragrance: null,
  });

  useEffect(() => {
    const loadProducts = async () => {
      try {
        const filteredProducts = await fetchProducts(filters);
        console.log("Fetched products:", filteredProducts);
        setProducts(filteredProducts);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };

    loadProducts();
  }, [filters]);

  const handleFilterChange = (newFilters: Filters) => {
    console.log("New filters applied:", newFilters);
    setFilters(newFilters);
  };
  

  return (
    <div className="product-page">
      <Filter onFilterChange={handleFilterChange} />
      <ProductList products={products} />
    </div>
  );
};

export default ProductPage;
