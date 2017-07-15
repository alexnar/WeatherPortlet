package edu.weather.portlet.controller.service;

import edu.weather.api.dto.Weather;
import edu.weather.portlet.dto.WeatherForecast;

import java.util.List;
import java.util.Map;

public interface WeatherGetter {
    List<WeatherForecast> getWeatherByCityForecast(String city);
}
