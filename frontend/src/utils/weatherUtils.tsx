export function getWeatherIconUrl(conditionCode: number, isDay: boolean): string {
    const dayOrNight = isDay ? "day" : "night";
    return `https://cdn.weatherapi.com/weather/64x64/${dayOrNight}/${conditionCode}.png`;
}