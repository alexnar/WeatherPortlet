<%@ include file="init.jsp" %>

<p>
	<b><liferay-ui:message key="WeatherPortlet.caption"/></b>
</p>

<%
    boolean noConfig = Validator.isNull(cityName);
%>

<portlet:resourceURL id="/weather/json" var="captchaURL">
    <portlet:param name="cityName" value="<%= cityName %>" />
</portlet:resourceURL>



<c:choose>
    <c:when test="<%= noConfig %>">
        <p>
            Please  specify city in portlet configurations.
        </p>
    </c:when>

    <c:otherwise>
            CityName: <%=cityName %><br>
            <%
                WeatherGetter weatherGetter = (WeatherGetter) renderRequest.getAttribute(WeatherGetter.class.getName());
                Map<String, List<Weather>> weatherForecast = weatherGetter.getWeatherByCityForecast(cityName);
            %>
            <% for (Map.Entry<String, List<Weather>> weatherForecastEntry : weatherForecast.entrySet()) { %>
                <%= weatherForecastEntry.getKey() %>
                <br>
                <% List<Weather> weatherList = weatherForecastEntry.getValue(); %>
                 <% for (int i = 0; i < weatherList.size() ; i++) { %>
                   Weather in <%= i %> days:
                   <% Weather weather = weatherList.get(i); %>
                   Temperature: <%= weather.getTemperature() %>
                   Weather description: <%= weather.getWeatherDescription() %>
                   <br>
                 <% } %>
               <br>
            <% } %>

            <a href="<%= captchaURL %>"> Get weather as JSON </a>

    </c:otherwise>
</c:choose>


