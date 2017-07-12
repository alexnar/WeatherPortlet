<%@ include file="init.jsp" %>

<%
    boolean noConfig = Validator.isNull(cityName);
%>
<h1> Print version</h1>
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
    </c:otherwise>
</c:choose>