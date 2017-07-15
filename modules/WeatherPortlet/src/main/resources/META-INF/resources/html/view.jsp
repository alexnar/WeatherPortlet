<%@ include file="init.jsp" %>



<portlet:resourceURL id="/weather/json" var="jsonUrl">
    <portlet:param name="cityName" value="${cityName}" />
</portlet:resourceURL>


<c:choose>
    <c:when test="${cityName.equals('')}">
        City name, is not specified.
    </c:when>
    <c:otherwise>
        <h1>CityName: <i>${cityName}</i></h1><br>
        <c:forEach items="${weatherForecastList}" var="weatherForecast">
            <c:set var="resourceName" value="${weatherForecast.getResourceName()}"/>
            <c:set var="weatherList" value="${weatherForecast.getWeatherList()}"/>
            <h1>${resourceName}</h1>
            <c:forEach items="${weatherList}" var="weather">
               <c:set var="temperature" value="${weather.getTemperature()}"/>
               <c:set var="weatherDescription" value="${weather.getWeatherDescription()}"/>
               Temperature: ${temperature} Description: ${weatherDescription} <br>
            </c:forEach>
        </c:forEach>


        <a href="<%= jsonUrl %>"> Get weather as JSON </a>
    </c:otherwise>
</c:choose>

