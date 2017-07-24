package edu.weather.openweathermap.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.weather.api.dto.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON parser class.
 * Provides methods for helping to
 * parse Weather JSON
 */
public class WeatherJsonParser {
    private static final String COORDINATES_JSON_KEY = "coord";
    private static final String LONGITUDE_JSON_KEY = "lon";
    private static final String LATITUDE_JSON_KEY = "lat";
    private static final String WEATHER_MAIN_INFO_JSON_KEY = "main";
    private static final String TEMPERATURE_JSON_KEY = "temp";
    private static final String WEATHER_DESCRIPTION_ARRAY_JSON_KEY = "weather";
    private static final String WEATHER_DESCRIPTION_JSON_KEY = "description";
    private static final String CITY_JSON_KEY = "city";
    private static final String DAY_WEATHER_JSON_KEY = "day";
    private static final String COD_JSON_KEY = "cod";
    private static final String ERROR_404_JSON_VALUE = "404";
    private static final String ERROR_400_JSON_VALUE = "400";

    /**
     * Return weather now from JSON API.
     *
     * @param weatherJson - json String, received from API
     * @return - List of Weather DTO
     */
    public static Weather getWeatherNowFromJson(StringBuilder weatherJson) {
        if (weatherJson.length() == 0) {
            return new Weather.WeatherBuilder().build();
        }
        String jsonString = weatherJson.toString();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        JsonObject weatherJsonObject = jsonElement.getAsJsonObject();

        if (checkJsonError(weatherJsonObject)) {
            return new Weather.WeatherBuilder().build();
        }

        JsonObject coordinatesJsonObject = weatherJsonObject.getAsJsonObject(COORDINATES_JSON_KEY);
        double longitude = coordinatesJsonObject.get(LONGITUDE_JSON_KEY).getAsDouble();
        double latitude = coordinatesJsonObject.get(LATITUDE_JSON_KEY).getAsDouble();

        JsonObject mainInfoJsonObject = weatherJsonObject.getAsJsonObject(WEATHER_MAIN_INFO_JSON_KEY);
        double temperature = mainInfoJsonObject.get(TEMPERATURE_JSON_KEY).getAsDouble();

        JsonArray weatherDescriptionJsonArray = weatherJsonObject.getAsJsonArray(WEATHER_DESCRIPTION_ARRAY_JSON_KEY);
        JsonObject weatherDescription = weatherDescriptionJsonArray.get(0).getAsJsonObject();
        String description = weatherDescription.get(WEATHER_DESCRIPTION_JSON_KEY).getAsString();


        Weather weather = new Weather.WeatherBuilder().setLatitude(latitude).setLongitude(longitude)
                .setTemperature(temperature).setWeatherDescription(description).build();
        return weather;
    }

    /**
     * Return weather forecast from JSON API.
     *
     * @param weatherJson - json String, received from API
     * @return - List of Weather DTO
     */
    public static List<Weather> getWeatherForecastFromJson(StringBuilder weatherJson) {
        if (weatherJson.length() == 0) {
            return new ArrayList<>();
        }
        String jsonString = weatherJson.toString();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        JsonObject weatherJsonObject = jsonElement.getAsJsonObject();

        if (checkJsonError(weatherJsonObject)) {
            return new ArrayList<>();
        }

        JsonObject cityJsonObject = weatherJsonObject.getAsJsonObject(CITY_JSON_KEY);
        JsonObject coordinatesJsonObject = cityJsonObject.getAsJsonObject(COORDINATES_JSON_KEY);
        double longitude = coordinatesJsonObject.get(LONGITUDE_JSON_KEY).getAsDouble();
        double latitude = coordinatesJsonObject.get(LATITUDE_JSON_KEY).getAsDouble();

        JsonArray weatherJsonArray = weatherJsonObject.getAsJsonArray("list");
        List<Weather> weatherForecastList = new ArrayList<>();
        for (JsonElement jsonArrayElement : weatherJsonArray) {
            Weather weather = getSingleWeatherFromForecast(jsonArrayElement, longitude, latitude);
            weatherForecastList.add(weather);
        }
        return weatherForecastList;
    }

    /**
     * Helper method to parse single weather entry
     * in forecast.
     *
     * @return Weather DTO (parsed entry)
     */
    private static Weather getSingleWeatherFromForecast(JsonElement weatherJsonElement, double longitude, double latitude) {
        JsonObject weatherJsonObject = weatherJsonElement.getAsJsonObject();
        JsonObject temperatureJsonObject = weatherJsonObject.getAsJsonObject(TEMPERATURE_JSON_KEY);
        double temperature = temperatureJsonObject.get(DAY_WEATHER_JSON_KEY).getAsDouble();
        JsonArray weatherDescriptionJsonArray = weatherJsonObject.getAsJsonArray(WEATHER_DESCRIPTION_ARRAY_JSON_KEY);
        JsonObject weatherDescription = weatherDescriptionJsonArray.get(0).getAsJsonObject();
        String description = weatherDescription.get(WEATHER_DESCRIPTION_JSON_KEY).getAsString();

        Weather weather = new Weather.WeatherBuilder().setLatitude(latitude).setLongitude(longitude)
                .setTemperature(temperature).setWeatherDescription(description).build();
        return weather;
    }

    /**
     * Check if error occurred in Json
     * For example wrong url specified,
     * can get error in json result such as
     * cod: 404
     *
     * @param jsonObject - Json object
     * @return - true - if error occurred
     * false - if not error occurred
     */
    private static boolean checkJsonError(JsonObject jsonObject) {

        String codeValue = jsonObject.get(COD_JSON_KEY).getAsString();
        boolean isErrorOccurred = false;
        if (codeValue.equals(ERROR_404_JSON_VALUE) || codeValue.equals(ERROR_400_JSON_VALUE)) {
            isErrorOccurred = true;
        }
        return isErrorOccurred;
    }
}

