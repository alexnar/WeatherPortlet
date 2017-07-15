<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="edu.weather.portlet.configuration.CityConfiguration" %>
<%@ page import="edu.weather.portlet.controller.service.WeatherGetter" %>
<%@ page import="edu.weather.api.dto.Weather" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<c:set var="cityName" value="${portletPreferences.getValue('cityName', cityName)}"/>
