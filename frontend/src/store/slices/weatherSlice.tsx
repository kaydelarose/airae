import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { getWeatherData } from '../../services/api/WeatherService';
import { WeatherData } from '../../types/WeatherTypes';

interface WeatherState {
    data: WeatherData | null;
    loading: boolean;
    error: string | null;
}

const initialState: WeatherState = {
    data: null,
    loading: false,
    error: null,
};

// Async thunk for fetching weather data
export const fetchWeather = createAsyncThunk(
    'weather/fetchWeather',
    async ({ latitude, longitude }: { latitude: string; longitude: string }, thunkAPI) => {
        try {
            const data = await getWeatherData(latitude, longitude);
            return data;
        } catch (error) {
            console.error("Failed to fetch weather data:", error);
            return thunkAPI.rejectWithValue("Failed to fetch weather data");
        }
    }
);

const weatherSlice = createSlice({
    name: 'weather',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchWeather.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchWeather.fulfilled, (state, action) => {
                state.loading = false;
                state.data = action.payload as WeatherData; // Cast to WeatherData for type safety
            })
            .addCase(fetchWeather.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload as string;
            });
    },
});

export default weatherSlice.reducer;
