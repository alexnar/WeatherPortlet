package edu.weather.portlet.controller.service;

import edu.weather.api.dto.Weather;

import java.util.List;
import java.util.Map;

public interface WeatherGetter {
    Map<String, List<Weather>> getWeatherByCityForecast(String city);
}
