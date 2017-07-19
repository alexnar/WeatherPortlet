/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package edu.weather.servicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WeatherForecast. This utility wraps
 * {@link edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastLocalService
 * @see edu.weather.servicebuilder.service.base.WeatherForecastLocalServiceBaseImpl
 * @see edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl
 * @generated
 */
@ProviderType
public class WeatherForecastLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		return getService()
				   .hasWeatherWeatherForecast(weatherId, weatherForecastId);
	}

	public static boolean hasWeatherWeatherForecasts(long weatherId) {
		return getService().hasWeatherWeatherForecasts(weatherId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the weather forecast to the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was added
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast addWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return getService().addWeatherForecast(weatherForecast);
	}

	/**
	* Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	*
	* @param weatherForecastId the primary key for the new weather forecast
	* @return the new weather forecast
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast createWeatherForecast(
		long weatherForecastId) {
		return getService().createWeatherForecast(weatherForecastId);
	}

	/**
	* Deletes the weather forecast from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was removed
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast deleteWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return getService().deleteWeatherForecast(weatherForecast);
	}

	/**
	* Deletes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast that was removed
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast deleteWeatherForecast(
		long weatherForecastId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWeatherForecast(weatherForecastId);
	}

	public static edu.weather.servicebuilder.model.WeatherForecast fetchWeatherForecast(
		long weatherForecastId) {
		return getService().fetchWeatherForecast(weatherForecastId);
	}

	/**
	* Returns the weather forecast with the primary key.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast getWeatherForecast(
		long weatherForecastId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWeatherForecast(weatherForecastId);
	}

	/**
	* Updates the weather forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was updated
	*/
	public static edu.weather.servicebuilder.model.WeatherForecast updateWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return getService().updateWeatherForecast(weatherForecast);
	}

	/**
	* Returns the number of weather forecasts.
	*
	* @return the number of weather forecasts
	*/
	public static int getWeatherForecastsCount() {
		return getService().getWeatherForecastsCount();
	}

	public static int getWeatherWeatherForecastsCount(long weatherId) {
		return getService().getWeatherWeatherForecastsCount(weatherId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the weather forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @return the range of weather forecasts
	*/
	public static java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		int start, int end) {
		return getService().getWeatherForecasts(start, end);
	}

	public static java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId) {
		return getService().getWeatherWeatherForecasts(weatherId);
	}

	public static java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId, int start, int end) {
		return getService().getWeatherWeatherForecasts(weatherId, start, end);
	}

	public static java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<edu.weather.servicebuilder.model.WeatherForecast> orderByComparator) {
		return getService()
				   .getWeatherWeatherForecasts(weatherId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the weatherIds of the weathers associated with the weather forecast.
	*
	* @param weatherForecastId the weatherForecastId of the weather forecast
	* @return long[] the weatherIds of weathers associated with the weather forecast
	*/
	public static long[] getWeatherPrimaryKeys(long weatherForecastId) {
		return getService().getWeatherPrimaryKeys(weatherForecastId);
	}

	public static void addWeatherWeatherForecast(long weatherId,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		getService().addWeatherWeatherForecast(weatherId, weatherForecast);
	}

	public static void addWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		getService().addWeatherWeatherForecast(weatherId, weatherForecastId);
	}

	public static void addWeatherWeatherForecasts(long weatherId,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		getService().addWeatherWeatherForecasts(weatherId, weatherForecasts);
	}

	public static void addWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		getService().addWeatherWeatherForecasts(weatherId, weatherForecastIds);
	}

	public static void clearWeatherWeatherForecasts(long weatherId) {
		getService().clearWeatherWeatherForecasts(weatherId);
	}

	public static void deleteWeatherWeatherForecast(long weatherId,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		getService().deleteWeatherWeatherForecast(weatherId, weatherForecast);
	}

	public static void deleteWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		getService().deleteWeatherWeatherForecast(weatherId, weatherForecastId);
	}

	public static void deleteWeatherWeatherForecasts(long weatherId,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		getService().deleteWeatherWeatherForecasts(weatherId, weatherForecasts);
	}

	public static void deleteWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		getService().deleteWeatherWeatherForecasts(weatherId, weatherForecastIds);
	}

	public static void setWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		getService().setWeatherWeatherForecasts(weatherId, weatherForecastIds);
	}

	public static WeatherForecastLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WeatherForecastLocalService, WeatherForecastLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WeatherForecastLocalService.class);
}