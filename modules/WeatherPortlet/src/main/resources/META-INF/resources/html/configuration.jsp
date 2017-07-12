<%@ include file="init.jsp" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<liferay-portlet:actionURL portletConfiguration="<%=true %>" var="configActionURL" />

<aui:form action="<%= configActionURL %>" method="post" name="fm">

    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input label="City Name" name="cityName" value="<%=cityName%>" />

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>