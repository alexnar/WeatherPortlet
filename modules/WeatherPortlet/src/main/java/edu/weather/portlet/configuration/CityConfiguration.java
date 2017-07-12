package edu.weather.portlet.configuration;


import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "edu.weather.portlet.configuration.CityConfiguration")
public interface CityConfiguration {
    String CITY_NAME_FIELD = "cityName";

    @Meta.AD(required = false, deflt = "Moscow")
    String getCityName();
}