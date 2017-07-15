package edu.weather.datagetter.impl;

import edu.weather.datagetter.exception.DataHttpGetException;
import edu.weather.datagetter.service.DataGetterService;
import edu.weather.logger.impl.WeatherAppLoggerImpl;
import edu.weather.logger.service.WeatherAppLoggerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DataGetterServiceImplTest {


    @Mock
    private WeatherAppLoggerService weatherAppLoggerService;

    @InjectMocks
    DataGetterService dataGetterService = new DataGetterServiceImpl();


    @Test(expected = DataHttpGetException.class)
    public void getDataFromUrlWithNonExistentUrl() throws Exception {
        doNothing().when(weatherAppLoggerService).log(any(), any(), any());
        dataGetterService.getDataFromUrl("no_such_url");
    }

    @Test
    public void getDataFromUrlWithNullUrl() throws Exception {
        StringBuilder result = dataGetterService.getDataFromUrl(null);
        assertEquals(result.toString(), "");
    }

    @Test
    public void getDataFromUrlWithRightUrl() throws Exception {
        StringBuilder result = dataGetterService.getDataFromUrl("https://www.google.com");
        assertNotEquals(result.length(), 0);
    }

}