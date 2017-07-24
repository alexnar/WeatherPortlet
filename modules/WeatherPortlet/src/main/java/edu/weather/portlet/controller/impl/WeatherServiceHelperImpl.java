package edu.weather.portlet.controller.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.*;
import edu.weather.api.dto.Weather;
import edu.weather.portlet.controller.service.WeatherGetter;
import edu.weather.portlet.controller.service.WeatherServiceHelper;
import edu.weather.portlet.dto.WeatherForecast;
import edu.weather.servicebuilder.service.WeatherForecastLocalService;
import edu.weather.servicebuilder.service.WeatherLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        name = "WeatherServiceHelperImpl",
        immediate = true,
        service = WeatherServiceHelper.class
)
public class WeatherServiceHelperImpl implements WeatherServiceHelper {

    @Reference
    private WeatherGetter weatherGetter;

    @Reference
    private WeatherForecastLocalService weatherForecastLocalService;

    @Reference
    private WeatherLocalService weatherLocalService;


    @Override
    public void addWeatherForecastListWithRelationships(List<WeatherForecast> weatherForecastListDto) {
        for (WeatherForecast weatherForecastDto : weatherForecastListDto) {
            addWeatherForecastWithRelationships(weatherForecastDto);
        }
    }

    @Override
    public List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecastListByCity(String cityName) {
        DynamicQuery dynamicQuery = weatherForecastLocalService.dynamicQuery();


        Criterion criterionCity = RestrictionsFactoryUtil.eq("cityName", cityName);

        dynamicQuery.add(criterionCity);

        List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts =
                weatherForecastLocalService.dynamicQuery(dynamicQuery);

        Calendar nowCalendar = Calendar.getInstance();
        Calendar forecastCalendar = Calendar.getInstance();

        List<edu.weather.servicebuilder.model.WeatherForecast> weathersActual = new ArrayList<>();
        for (edu.weather.servicebuilder.model.WeatherForecast weatherForecastEntry : weatherForecasts) {
            forecastCalendar.setTime(weatherForecastEntry.getForecastDate());
            if (nowCalendar.get(Calendar.YEAR) == forecastCalendar.get(Calendar.YEAR) &&
                    nowCalendar.get(Calendar.MONTH) == forecastCalendar.get(Calendar.MONTH) &&
                    nowCalendar.get(Calendar.DATE) == forecastCalendar.get(Calendar.DATE)) {
                weathersActual.add(weatherForecastEntry);
            }
        }
        return weathersActual;
    }

    @Override
    public List<edu.weather.servicebuilder.model.WeatherForecast> processWeatherForecastByCity(String cityName) {
        List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecastModelList =
                getWeatherForecastListByCity(cityName);

        if (!weatherForecastModelList.isEmpty()) {
            return weatherForecastModelList;
        }

        List<WeatherForecast> weatherForecastDtoList = weatherGetter.getWeatherByCityForecast(cityName);
        addWeatherForecastListWithRelationships(weatherForecastDtoList);
        weatherForecastModelList = getWeatherForecastListByCity(cityName);
        return weatherForecastModelList;
    }

    @Override
    public List<WeatherForecast> translateWeatherForecastListModelToDto(
            List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecastListModel) {
        List<WeatherForecast> weatherForecastListDto = new ArrayList<>();
        for (edu.weather.servicebuilder.model.WeatherForecast weatherForecastModel : weatherForecastListModel) {
            long weatherForecastId = weatherForecastModel.getWeatherForecastId();
            String cityName = weatherForecastModel.getCityName();
            String resourceName = weatherForecastModel.getResourceName();
            List<edu.weather.servicebuilder.model.Weather> weatherListModel
                    = weatherLocalService.getWeatherForecastWeathers(weatherForecastId);
            List<Weather> weatherListDto = new ArrayList<>();
            for (edu.weather.servicebuilder.model.Weather weatherModel : weatherListModel) {
                Weather weatherDto = translateWeatherModelToDto(weatherModel);
                weatherListDto.add(weatherDto);
            }
            WeatherForecast weatherForecastDto = new WeatherForecast(resourceName, cityName, weatherListDto);
            weatherForecastListDto.add(weatherForecastDto);
        }
        return weatherForecastListDto;
    }


    /**
     * This method receive WeatherForecast DTO, mapping it in
     * WeatherForecast and Weather models, which have many to many
     * relationship and add result to database.
     * <p>
     * If such weather forecast already exists in database, and it is
     * actual, then weather forecast would not be added in database.
     *
     * @param weatherForecast - Weather Forecast DTO. This DTO is received
     *                        from Weather Getter.
     */
    private void addWeatherForecastWithRelationships(WeatherForecast weatherForecastDto) {
        edu.weather.servicebuilder.model.WeatherForecast weatherForecastModel =
                translateWeatherForecastDtoToModel(weatherForecastDto);

        List<Weather> weatherForecastListDto = weatherForecastDto.getWeatherList();
        boolean isWeatherForecastListEmpty = weatherForecastListDto.isEmpty();
        boolean isForecastExists = checkIfForecastExists(weatherForecastModel);

        if (isForecastExists || isWeatherForecastListEmpty) {
            return;
        }

        weatherForecastLocalService.addWeatherForecast(weatherForecastModel);

        List<Weather> weatherDtoList = weatherForecastDto.getWeatherList();
        for (Weather weatherDto : weatherDtoList) {
            edu.weather.servicebuilder.model.Weather weatherModel = translateWeatherDtoToModel(weatherDto);
            weatherLocalService.addWeather(weatherModel);
            long weatherForecastModelId = weatherForecastModel.getWeatherForecastId();
            weatherLocalService.addWeatherForecastWeather(weatherForecastModelId, weatherModel);
        }
    }

    /**
     * Helper method for translating WeatherForecast DTO
     * to WeatherForecast model
     */
    private edu.weather.servicebuilder.model.WeatherForecast translateWeatherForecastDtoToModel(
            WeatherForecast weatherForecastDto) {

        long id = CounterLocalServiceUtil.increment();
        edu.weather.servicebuilder.model.WeatherForecast weatherForecastModel
                = weatherForecastLocalService.createWeatherForecast(id);

        weatherForecastModel.setResourceName(weatherForecastDto.getResourceName());
        weatherForecastModel.setCityName(weatherForecastDto.getCityName());
        weatherForecastModel.setForecastDate(new Date());
        return weatherForecastModel;
    }

    /**
     * Helper method for translating Weather DTO
     * to Weather model
     */
    private edu.weather.servicebuilder.model.Weather translateWeatherDtoToModel(Weather weatherDto) {
        long id = CounterLocalServiceUtil.increment();
        edu.weather.servicebuilder.model.Weather weatherModel = weatherLocalService.createWeather(id);

        String weatherDescription = weatherDto.getWeatherDescription();
        double temperature = weatherDto.getTemperature();
        weatherModel.setDescription(weatherDescription);
        weatherModel.setTemperature(temperature);

        return weatherModel;
    }

    /**
     * Helper method for translating Weather model
     * to Weather DTO
     */
    private Weather translateWeatherModelToDto( edu.weather.servicebuilder.model.Weather weatherModel) {
        Double temperature = weatherModel.getTemperature();
        String description = weatherModel.getDescription();
        Weather weather = new Weather.WeatherBuilder().setTemperature(temperature).
                setWeatherDescription(description).build();
        return weather;
    }

    /**
     * Check if such weather forecast already exists in database, and it is
     * actual.
     *
     * @return - true if exists
     * false if not exists
     */
    private boolean checkIfForecastExists(edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {

        DynamicQuery dynamicQuery = weatherForecastLocalService.dynamicQuery();

        String resourceName = weatherForecast.getResourceName();
        String cityName = weatherForecast.getCityName();

        Criterion criterionCity = RestrictionsFactoryUtil.eq("cityName", cityName);
        Criterion criterionResource = RestrictionsFactoryUtil.eq("resourceName", resourceName);

        Criterion criterionResult = RestrictionsFactoryUtil.and(criterionCity, criterionResource);
        dynamicQuery.add(criterionResult);

        List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts =
                weatherForecastLocalService.dynamicQuery(dynamicQuery);

        Calendar nowCalendar = Calendar.getInstance();
        Calendar forecastCalendar = Calendar.getInstance();

        List<edu.weather.servicebuilder.model.WeatherForecast> weathersFiltered = new ArrayList<>();
        for (edu.weather.servicebuilder.model.WeatherForecast weatherForecastEntry : weatherForecasts) {
            forecastCalendar.setTime(weatherForecastEntry.getForecastDate());
            if (nowCalendar.get(Calendar.YEAR) == forecastCalendar.get(Calendar.YEAR) &&
                    nowCalendar.get(Calendar.MONTH) == forecastCalendar.get(Calendar.MONTH) &&
                    nowCalendar.get(Calendar.DATE) == forecastCalendar.get(Calendar.DATE)) {
                weathersFiltered.add(weatherForecastEntry);
            }
        }
        boolean isForecastExists = !weathersFiltered.isEmpty();
        return isForecastExists;
    }

}
