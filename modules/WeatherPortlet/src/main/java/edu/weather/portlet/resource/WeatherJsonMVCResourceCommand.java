package edu.weather.portlet.resource;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import edu.weather.api.dto.Weather;
import edu.weather.portlet.controller.service.WeatherGetter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Component(
        property = {
                "javax.portlet.name=edu_weather_portlet_impl_WeatherPortlet",
                "mvc.command.name=/weather/json"
        },
        service = MVCResourceCommand.class
)
public class WeatherJsonMVCResourceCommand implements MVCResourceCommand {
    @Reference
    WeatherGetter weatherGetter;

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        try {
            PrintWriter printWriter = resourceResponse.getWriter();
            String cityName = resourceRequest.getParameter("cityName");
            Map<String, List<Weather>> weatherMap = weatherGetter.getWeatherByCityForecast(cityName);
            Gson gson = new Gson();
            String jsonWeather = gson.toJson(weatherMap);
            printWriter.write(jsonWeather);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
