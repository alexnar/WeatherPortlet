package edu.weather.openweathermap.impl;

import edu.weather.api.dto.Weather;
import edu.weather.api.service.WeatherApiService;
import edu.weather.datagetter.exception.DataHttpGetException;
import edu.weather.datagetter.service.DataGetterService;
import edu.weather.logger.service.WeatherAppLoggerService;
import edu.weather.openweathermap.parser.WeatherXmlParser;
import edu.weather.openweathermap.url.ApiUrl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of WeatherApiService, for
 * working with OpenWeatherMap in JSON format.
 */
@Component(
        name = "OpenWeatherMapXml",
        immediate = true,
        service = WeatherApiService.class
)
public class OpenWeatherMapXml implements WeatherApiService {

    private static final String API_RESOURCE_NAME = "OpenWeatherMap (Xml API)";

    @Reference
    private DataGetterService dataGetterService;

    @Reference
    private WeatherAppLoggerService logger;

    private static final String GET_DATA_ERROR = "While getting data, error occurred. " +
            "Check your connection and url you specified";


    @Override
    public Weather getWeatherNow() {
        String url = ApiUrl.getUrlForWeatherNow();
        StringBuilder weatherData = new StringBuilder();
        try {
            weatherData = dataGetterService.getDataFromUrl(url);
        } catch (DataHttpGetException e) {
            logger.log(Level.WARNING, GET_DATA_ERROR, e);
            System.out.println(GET_DATA_ERROR);
        }
        if (weatherData.length() == 0) {
            return new Weather.WeatherBuilder().build();
        }
        Weather weather = WeatherXmlParser.getWeatherNowFromXml(weatherData);
        return weather;
    }

    @Override
    public List<Weather> getWeatherForecast() {
        String url = ApiUrl.getUrlForWeatherForecast();
        StringBuilder weatherData = new StringBuilder();
        try {
            weatherData = dataGetterService.getDataFromUrl(url);
        } catch (DataHttpGetException e) {
            logger.log(Level.WARNING, GET_DATA_ERROR, e);
            System.out.println(GET_DATA_ERROR);
        }
        if (weatherData.length() == 0) {
            return new ArrayList<>();
        }
        List<Weather> weatherForecastList = WeatherXmlParser.getWeatherForecastFromXml(weatherData);
        return weatherForecastList;
    }

    @Override
    public List<Weather> getWeatherForecastByCity(String city) {
        city = city.replace(" ", "%20");
        String url = ApiUrl.getUrlForWeatherForecastByCity(city);
        StringBuilder weatherData = new StringBuilder();
        try {
            weatherData = dataGetterService.getDataFromUrl(url);
        } catch (DataHttpGetException e) {
            logger.log(Level.WARNING, GET_DATA_ERROR, e);
            System.out.println(GET_DATA_ERROR);
        }
        if (weatherData.length() == 0) {
            return new ArrayList<>();
        }
        List<Weather> weatherForecastList = WeatherXmlParser.getWeatherForecastFromXml(weatherData);
        return weatherForecastList;
    }

    @Override
    public String getApiName() {
        return API_RESOURCE_NAME;
    }

}
