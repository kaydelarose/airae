import axios from 'axios';

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
  

export const fetchProducts = async (filters: Filters): Promise<Product[]> => {
  const queryParams = new URLSearchParams();
  if (filters.skinType) queryParams.append("skinType", filters.skinType);
  if (filters.concern) queryParams.append("concern", filters.concern);
  if (filters.fragrance !== null) queryParams.append("fragrance", String(filters.fragrance));

  const response = await fetch(`/api/products/filter?${queryParams.toString()}`);
  if (!response.ok) {
    throw new Error(`Failed to fetch products: ${response.statusText}`);
  }
  return response.json();
};


export const fetchWeatherRecommendations = async (humidity: number, uvIndex: number, temperature: number) => {
    try {
        const response = await axios.get('http://localhost:8080/api/products/weather-recommendations', {
            params: { humidity, uvIndex, temperature },
            headers: {
                'Content-Type': 'application/json',
            },
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching weather-based recommendations:', error);
        throw error;
    }
};
