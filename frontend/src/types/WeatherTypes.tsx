export interface WeatherData {
    cityName?: string; // Optional if not always present
    temperature: number;
    temperatureF: number;
    feelsLike: number;
    feelsLikeF: number;
    weatherDescription: string;
    weatherIcon: string;
    conditionCode: number;
    uvIndex: number;
    humidity: number;
    windSpeed: number;
    windDirection: string;
    airQuality: number;
    isDay: boolean;
    dewPoint?: number; // Optional if not always present
}
