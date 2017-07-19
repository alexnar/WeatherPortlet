package edu.weather.portlet.dto;

import edu.weather.api.dto.Weather;

import java.util.List;

public class WeatherForecast {
    private String resourceName;
    private String cityName;
    private List<Weather> weatherList;

    public WeatherForecast(String resourceName, String cityName, List<Weather> weatherList) {
        this.resourceName = resourceName;
        this.cityName = cityName;
        this.weatherList = weatherList;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
