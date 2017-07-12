package edu.weather.portlet.controller.impl;

import edu.weather.api.dto.Weather;
import edu.weather.api.service.WeatherApiService;
import edu.weather.portlet.controller.service.WeatherGetter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Weather Getter class.
 * Provides getting weather forecast
 * from all registered sources (services)
 */
@Component(
        name = "WeatherGetterImpl",
        immediate = true,
        service = WeatherGetter.class
)
public class WeatherGetterImpl implements WeatherGetter {

    @Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE, bind = "bind", unbind = "unbind",
            service = WeatherApiService.class, policy = ReferencePolicy.DYNAMIC)
    private Map<String, WeatherApiService> weatherApiServiceMap;

    @Override
    public Map<String, List<Weather>> getWeatherByCityForecast(String city) {
        Map<String, List<Weather>> weatherForecast = new HashMap<>();
        for (Map.Entry<String, WeatherApiService> weatherApiServiceEntry : weatherApiServiceMap.entrySet()) {
            String weatherResourceName = weatherApiServiceEntry.getKey();

            WeatherApiService weatherApiService = weatherApiServiceEntry.getValue();
            List<Weather> weatherList = weatherApiService.getWeatherForecastByCity(city);

            weatherForecast.put(weatherResourceName, weatherList);
        }
        return weatherForecast;
    }

    /**
     * Bind service.
     * Add service in weatherApiServiceMap.
     *
     * @param weatherApiService - service to bind
     */
    protected void bind(WeatherApiService weatherApiService) {
        if (weatherApiServiceMap == null) {
            weatherApiServiceMap = new HashMap<>();
        }
        weatherApiServiceMap.put(weatherApiService.getApiName(), weatherApiService);
    }

    /**
     * Unbind service.
     * Remove service from weatherApiServiceMap
     *
     * @param weatherApiService - service to remove
     */
    protected void unbind(WeatherApiService weatherApiService) {
        weatherApiServiceMap.remove(weatherApiService.getApiName());
    }
}
