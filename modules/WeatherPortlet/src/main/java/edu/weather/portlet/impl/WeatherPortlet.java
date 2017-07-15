package edu.weather.portlet.impl;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import edu.weather.api.dto.Weather;
import edu.weather.portlet.configuration.CityConfiguration;
import edu.weather.portlet.controller.service.WeatherGetter;
import edu.weather.portlet.dto.WeatherForecast;
import org.osgi.service.component.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author nai
 */
@Component(
        configurationPid = "edu.weather.portlet.configuration.CityConfiguration",
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Weather Portlet",
                "javax.portlet.init-param.template-path=/html/",
                "javax.portlet.init-param.view-template=/html/view.jsp",
                "javax.portlet.init-param.print-template=/html/print.jsp",
                "javax.portlet.init-param.config-template=/html/configuration.jsp",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.portlet-mode=text/html;view,print,test",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class WeatherPortlet extends MVCPortlet {

    private static final String CITY_NAME_PREFERENCE_EMPTY = "CITY NAME PREFERENCE IS EMPTY";

    @Reference
    WeatherGetter weatherGetter;

    @Override
    public void doView(RenderRequest renderRequest,
                       RenderResponse renderResponse) throws IOException, PortletException {


        String defaultCityName = cityConfiguration.getCityName();

        PortletPreferences preferences = renderRequest.getPreferences();
        String cityName = preferences.getValue("cityName", CITY_NAME_PREFERENCE_EMPTY);

        if (cityName.equals(CITY_NAME_PREFERENCE_EMPTY)) {
            cityName = defaultCityName;
        }

        List<WeatherForecast> weatherForecastList = weatherGetter.getWeatherByCityForecast(cityName);

        renderRequest.setAttribute("cityName", cityName);
        renderRequest.setAttribute("weatherForecastList", weatherForecastList);

        super.doView(renderRequest, renderResponse);
    }


    @Override
    public void doPrint(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        String defaultCityName = cityConfiguration.getCityName();

        PortletPreferences preferences = renderRequest.getPreferences();
        String cityName = preferences.getValue("cityName", CITY_NAME_PREFERENCE_EMPTY);

        if (cityName.equals(CITY_NAME_PREFERENCE_EMPTY)) {
            cityName = defaultCityName;
        }

        List<WeatherForecast> weatherForecastList = weatherGetter.getWeatherByCityForecast(cityName);

        renderRequest.setAttribute("cityName", cityName);
        renderRequest.setAttribute("weatherForecastList", weatherForecastList);

        super.doPrint(renderRequest, renderResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        cityConfiguration = ConfigurableUtil.createConfigurable(CityConfiguration.class, properties);
    }

    private volatile CityConfiguration cityConfiguration;
}