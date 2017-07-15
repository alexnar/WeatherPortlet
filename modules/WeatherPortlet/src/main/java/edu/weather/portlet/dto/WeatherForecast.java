package edu.weather.portlet.dto;

import edu.weather.api.dto.Weather;

import java.util.List;

public class WeatherForecast {
    private String resourceName;
    private List<Weather> weatherList;

    public WeatherForecast(String resourceName, List<Weather> weatherList) {
        this.resourceName = resourceName;
        this.weatherList = weatherList;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}
