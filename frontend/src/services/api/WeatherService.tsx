import axios from 'axios';
import { WeatherData } from '../../types/WeatherTypes';

const API_BASE_URL = 'http://localhost:8080/api';

export const getWeatherData = async (
    latitude: string,
    longitude: string
): Promise<WeatherData> => {
    try {
        const response = await axios.get(`${API_BASE_URL}/weather`, {
            params: { latitude, longitude },
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching weather data:", error);
        throw error;
    }
};
