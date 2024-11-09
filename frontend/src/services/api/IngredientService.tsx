import axios from 'axios';
import { Ingredient } from '../../types/IngredientTypes';

const API_BASE_URL = 'http://localhost:8080/api';

export const getAllIngredients = async (): Promise<Ingredient[]> => {
    try {
        const response = await axios.get(`${API_BASE_URL}/ingredients`);
        return response.data;
    } catch (error) {
        console.error("Error fetching all ingredients:", error);
        throw error;
    }
};

export const getIngredientById = async (id: string): Promise<Ingredient> => {
    try {
        const response = await axios.get(`${API_BASE_URL}/ingredients/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching ingredient with ID ${id}:`, error);
        throw error;
    }
};

export const getIngredientsByProperties = async (
    category: string,
    benefit: string
): Promise<Ingredient[]> => {
    try {
        const response = await axios.get(`${API_BASE_URL}/ingredients/search`, {
            params: { category, benefit },
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching ingredients by properties:", error);
        throw error;
    }
};
