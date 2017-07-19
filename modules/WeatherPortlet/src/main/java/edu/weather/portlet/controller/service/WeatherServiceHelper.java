package edu.weather.portlet.controller.service;

import edu.weather.portlet.dto.WeatherForecast;

import java.util.List;

/**
 * Weather service provide methods, for
 * simplify working with WeatherServiceBuilder-Api.
 */
public interface WeatherServiceHelper {

    /**
     * This method receive WeatherForecast DTO, mapping it in
     * WeatherForecast and Weather models, which have many to many
     * relationship and add result to database.
     *
     * If  weather forecast entry already exists in database, and it is
     * actual, then weather forecast would not be added in database.
     *
     * @param weatherForecast - Weather Forecast DTO. This DTO is received
     *                        from Weather Getter.
     */
    void addWeatherForecastListWithRelationships(List<WeatherForecast> weatherForecastDto);

    /**
     * Get WeatherForecast list by city name.
     *
     * @param cityName - city name
     * @return - List of WeatherForecast models
     */
    List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecastListByCity(String cityName);

    /**
     * Process Weather Forecast.
     * If WeatherForecast already exists in
     * database than just return it.
     * Else get Weather forecast via WeatherGetter,
     * and add result to database
     *
     * @param cityName - city name
     * @return - List of WeatherForecast models
     */
    List<edu.weather.servicebuilder.model.WeatherForecast> processWeatherForecastByCity(String cityName);

    /**
     * Translate WeatherForecast model to WeatherForecast DTO
     *
     * @param weatherForecastModel - WeatherForecast model
     * @return - WeatherForecast DTO
     */
    List<WeatherForecast> translateWeatherForecastListModelToDto(
            List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecastListModel);


}
