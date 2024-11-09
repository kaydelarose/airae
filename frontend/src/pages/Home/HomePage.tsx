import { useDispatch, useSelector } from 'react-redux';
import { RootState, AppDispatch } from '../../store';
import { fetchWeather } from '../../store/slices/weatherSlice';
import popularCities from '../../constants/cities';
import React, { useState } from 'react';
import { FaTemperatureHigh, FaTint, FaSun, FaWind } from 'react-icons/fa';
import { BsCloud } from 'react-icons/bs'; 
import { FaExternalLinkAlt } from 'react-icons/fa';
import serumImage from '../../assets/images/serum-hp.jpg';
import './HomePage.css';

function HomePage() {
    const [selectedCity, setSelectedCity] = useState('');
    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');

    const dispatch = useDispatch<AppDispatch>();
    const weatherData = useSelector((state: RootState) => state.weather.data);
    const loading = useSelector((state: RootState) => state.weather.loading);
    const error = useSelector((state: RootState) => state.weather.error);

    const handleCityChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const city = popularCities.find(city => city.name === e.target.value);
        setSelectedCity(e.target.value);

        if (city) {
            setLatitude(city.lat);
            setLongitude(city.lon);
            dispatch(fetchWeather({ latitude: city.lat, longitude: city.lon }));
        } else {
            setLatitude('');
            setLongitude('');
        }
    };

    const handleFetchManualCoordinates = () => {
        if (latitude && longitude) {
            dispatch(fetchWeather({ latitude, longitude }));
        }
    };

    return (
        <div className="home-container">

            <section id="app-description" className="app-description-section">
                <div className="description-content">
                    <h2>About Airae</h2>
                    <p>
                        Airae is a skincare guide that combines weather insights with a robust product filtering search engine. 
                        Explore climate data, read case studies, and find skincare products tailored to your skin type and specific concerns. 
                        Airae helps you make informed choices by providing a comprehensive platform for skincare research and discovery.
                    </p>

                </div>
                <div className="description-image">
                    <img src={serumImage} alt="Serum" className="serum-image"/>
                </div>
            </section>


            <section id="weather-section">
                <div className="input-section">
                    <p className="intro-text">Select a city or enter coordinates manually to view the weather data:</p>
                    
                    <div className="location-inputs">
                        <label htmlFor="city-select" className="label">Select a City:</label>
                        <select id="city-select" onChange={handleCityChange} value={selectedCity} className="dropdown">
                            <option value="">Select a City</option>
                            {popularCities.map(city => (
                                <option key={city.name} value={city.name}>
                                    {city.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="manual-coordinates">
                        <label className="label">Enter Coordinates Manually:</label>
                        <input type="text" placeholder="Latitude" value={latitude} onChange={(e) => setLatitude(e.target.value)} className="input-field" />
                        <input type="text" placeholder="Longitude" value={longitude} onChange={(e) => setLongitude(e.target.value)} className="input-field" />
                        <button onClick={handleFetchManualCoordinates} className="fetch-button">Fetch Weather</button>
                    </div>
                </div>

                <div className="weather-display">
                    {loading && <p>Loading...</p>}
                    {error && <p>Error: {error}</p>}
                    {weatherData && (
                        <>
                            <div className="weather-card">
                                <FaTemperatureHigh className="weather-icon" />
                                <p>{weatherData.temperature} °C</p>
                                <span>Temperature</span>
                            </div>
                            <div className="weather-card">
                                <FaTint className="weather-icon" />
                                <p>{weatherData.humidity} %</p>
                                <span>Humidity</span>
                            </div>
                            <div className="weather-card">
                                <FaSun className="weather-icon" />
                                <p>{weatherData.uvIndex}</p>
                                <span>UV Index</span>
                            </div>
                            <div className="weather-card">
                                <FaWind className="weather-icon" />
                                <p>{weatherData.windSpeed} m/s</p>
                                <span>Wind Speed</span>
                            </div>
                            <div className="weather-card">
                                <BsCloud className="weather-icon" />
                                <p>{weatherData.airQuality}</p>
                                <span>Air Quality</span>
                            </div>
                        </>
                    )}
                    <div className="weather-api-credit">
                        <a href="https://www.weatherapi.com/" title="Free Weather API" target="_blank" rel="noopener noreferrer">
                            <img src="//cdn.weatherapi.com/v4/images/weatherapi_logo.png" alt="Weather data by WeatherAPI.com" className="weatherapi-icon" />
                            Powered by WeatherAPI.com
                        </a>
                    </div>

                </div>
            </section>

            <section id="case-studies" className="case-studies-container">
                <h2>How Weather Affects Your Skin</h2>
                <p>Explore studies and insights on how different weather conditions impact skin health:</p>

                <div className="case-study">
                    <h3>Weather and Skin Health</h3>
                    <p>Weather conditions significantly impact skin health. Climate changes, seasonal variations, and different humidity levels all play a role in skin hydration, barrier function, and overall skin health.</p>
                    <div className="references">
                        <p>References:</p>
                        <ul>
                            <li><a href="https://www.researchgate.net/publication/259649837_Effects_of_climate_changes_on_skin_diseases" target="_blank" rel="noopener noreferrer">Effects of Climate Changes on Skin Diseases <FaExternalLinkAlt /></a></li>
                            <li><a href="https://www.wunderground.com/article/health/skin-health/news/2024-08-15-skin-health-in-different-regional-climates" target="_blank" rel="noopener noreferrer">Skin Health in Different Regional Climates <FaExternalLinkAlt /></a></li>
                            <li><a href="https://www.researchgate.net/publication/50303235_Influence_of_season_on_some_skin_properties_Winter_vs_summer_as_experienced_by_354_Shanghaiese_women_of_various_ages" target="_blank" rel="noopener noreferrer">Influence of Season on Skin Properties <FaExternalLinkAlt /></a></li>
                        </ul>
                    </div>
                </div>

                <div className="case-study">
                    <h3>UV Index and Skin Protection</h3>
                    <p>High UV levels increase the risk of skin damage and cancer. Sunscreen and other protective measures are essential to safeguard skin health.</p>
                    <div className="references">
                        <p>References:</p>
                        <ul>
                            <li><a href="https://pmc.ncbi.nlm.nih.gov/articles/PMC5742376/" target="_blank" rel="noopener noreferrer">Patterns of UV Exposure and Skin Cancer Risk <FaExternalLinkAlt /></a></li>
                            <li><a href="https://pmc.ncbi.nlm.nih.gov/articles/PMC7759112/" target="_blank" rel="noopener noreferrer">The Efficacy of Sunscreen in Skin Cancer Prevention <FaExternalLinkAlt /></a></li>
                            <li><a href="https://www.cancerresearchuk.org/about-cancer/causes-of-cancer/sun-uv-and-cancer/the-uv-index-and-sunburn-risk" target="_blank" rel="noopener noreferrer">The UV Index and Sunburn Risk <FaExternalLinkAlt /></a></li>
                        </ul>
                    </div>
                </div>

                <div className="case-study">
                    <h3>Humidity and Skin Hydration</h3>
                    <p>Humidity levels affect skin hydration. Low humidity can lead to dry skin, while high humidity can contribute to oil production and breakouts.</p>
                    <div className="references">
                        <p>References:</p>
                        <ul>
                            <li><a href="https://pubmed.ncbi.nlm.nih.gov/27306376/" target="_blank" rel="noopener noreferrer">Impact of Air Humidity on Skin Health <FaExternalLinkAlt /></a></li>
                            <li><a href="https://journals.sagepub.com/doi/full/10.1177/1420326X211030998" target="_blank" rel="noopener noreferrer">Effect of Indoor Conditions on Skin Condition <FaExternalLinkAlt /></a></li>
                            <li><a href="https://onlinelibrary.wiley.com/doi/abs/10.1111/jdv.13301" target="_blank" rel="noopener noreferrer">Environmental Humidity and Skin Barrier Function <FaExternalLinkAlt /></a></li>
                        </ul>
                    </div>
                </div>

                <div className="case-study">
                    <h3>Air Quality and Skin Health</h3>
                    <p>Air pollution can lead to various skin issues, including inflammation and premature aging. Antioxidant skincare can help combat the effects of pollutants.</p>
                    <div className="references">
                        <p>References:</p>
                        <ul>
                            <li><a href="https://www.researchgate.net/publication/376594325_The_Burden_of_Air_Pollution_on_Skin_Health_a_Brief_Report_and_Call_to_Action" target="_blank" rel="noopener noreferrer">The Burden of Air Pollution on Skin Health <FaExternalLinkAlt /></a></li>
                            <li><a href="https://pmc.ncbi.nlm.nih.gov/articles/PMC7838324/" target="_blank" rel="noopener noreferrer">Air Pollution and Skin Disorders <FaExternalLinkAlt /></a></li>
                            <li><a href="https://ijdvl.com/effects-of-air-pollution-on-the-skin-a-review/" target="_blank" rel="noopener noreferrer">Effects of Air Pollution on the Skin: A Review <FaExternalLinkAlt /></a></li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default HomePage;
