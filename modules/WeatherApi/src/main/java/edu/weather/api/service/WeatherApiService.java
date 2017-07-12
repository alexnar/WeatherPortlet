package edu.weather.api.service;


import edu.weather.api.dto.Weather;

import java.util.List;

/**
 * WeatherApiService interface, provides methods
 * for working with weather API.
 */
public interface WeatherApiService {
    /**
     * Get weather information now
     */
    Weather getWeatherNow();

    /**
     * Get weather forecast
     */
    List<Weather> getWeatherForecast();

    /**
     * Get weather forecast
     */
    List<Weather> getWeatherForecastByCity(String city);

    /**
     * Get name of api resource
     *
     * @return - name of api resource
     */
    String getApiName();
}
