package edu.weather.openweathermap.impl;

import edu.weather.api.dto.Weather;
import edu.weather.api.service.WeatherApiService;
import edu.weather.datagetter.impl.DataGetterServiceImpl;
import edu.weather.datagetter.service.DataGetterService;
import edu.weather.logger.service.WeatherAppLoggerService;
import edu.weather.openweathermap.url.ApiUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.AdditionalAnswers.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapJsonTest {

    @Mock
    private WeatherAppLoggerService weatherAppLoggerService;

    @Mock
    private DataGetterService dataGetterServiceMock;

    @InjectMocks
    WeatherApiService openWeatherMapJson = new OpenWeatherMapJson();

    private DataGetterService dataGetterServiceImpl = new DataGetterServiceImpl();

    @Test
    public void getWeatherForecastByCityWithNonExistCity() throws Exception {
        String nonExistCity = "SOME_CITY_THAT_WILL_NEVER_EXISTS";

        String httpGetResultString = "{\"cod\":\"404\",\"message\":\"city not found\"}";
        StringBuilder httpGetResultStringBuilder = new StringBuilder(httpGetResultString);

        /*doNothing().when(weatherAppLoggerService).log(any(), any(), any());*/
        when(dataGetterServiceMock.getDataFromUrl(any())).thenReturn(httpGetResultStringBuilder);


        List<Weather> weatherForecastByCity = openWeatherMapJson.getWeatherForecastByCity(nonExistCity);
        assertTrue(weatherForecastByCity.isEmpty());
    }

    @Test
    public void getWeatherForecastByCityWithExistCity() throws Exception {
        String existCity = "London";
        String url = ApiUrl.getUrlForWeatherForecastByCity(existCity);

        when(dataGetterServiceMock.getDataFromUrl(any())).thenReturn(dataGetterServiceImpl.getDataFromUrl(url));

        List<Weather> weatherForecastByCity = openWeatherMapJson.getWeatherForecastByCity(existCity);
        assertFalse(weatherForecastByCity.isEmpty());
    }




}