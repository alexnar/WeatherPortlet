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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WeatherForecastLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastLocalService
 * @generated
 */
@ProviderType
public class WeatherForecastLocalServiceWrapper
	implements WeatherForecastLocalService,
		ServiceWrapper<WeatherForecastLocalService> {
	public WeatherForecastLocalServiceWrapper(
		WeatherForecastLocalService weatherForecastLocalService) {
		_weatherForecastLocalService = weatherForecastLocalService;
	}

	@Override
	public boolean hasWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		return _weatherForecastLocalService.hasWeatherWeatherForecast(weatherId,
			weatherForecastId);
	}

	@Override
	public boolean hasWeatherWeatherForecasts(long weatherId) {
		return _weatherForecastLocalService.hasWeatherWeatherForecasts(weatherId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _weatherForecastLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _weatherForecastLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _weatherForecastLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _weatherForecastLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _weatherForecastLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the weather forecast to the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was added
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast addWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return _weatherForecastLocalService.addWeatherForecast(weatherForecast);
	}

	/**
	* Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	*
	* @param weatherForecastId the primary key for the new weather forecast
	* @return the new weather forecast
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast createWeatherForecast(
		long weatherForecastId) {
		return _weatherForecastLocalService.createWeatherForecast(weatherForecastId);
	}

	/**
	* Deletes the weather forecast from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was removed
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast deleteWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return _weatherForecastLocalService.deleteWeatherForecast(weatherForecast);
	}

	/**
	* Deletes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast that was removed
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast deleteWeatherForecast(
		long weatherForecastId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _weatherForecastLocalService.deleteWeatherForecast(weatherForecastId);
	}

	@Override
	public edu.weather.servicebuilder.model.WeatherForecast fetchWeatherForecast(
		long weatherForecastId) {
		return _weatherForecastLocalService.fetchWeatherForecast(weatherForecastId);
	}

	/**
	* Returns the weather forecast with the primary key.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast getWeatherForecast(
		long weatherForecastId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _weatherForecastLocalService.getWeatherForecast(weatherForecastId);
	}

	/**
	* Updates the weather forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was updated
	*/
	@Override
	public edu.weather.servicebuilder.model.WeatherForecast updateWeatherForecast(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return _weatherForecastLocalService.updateWeatherForecast(weatherForecast);
	}

	/**
	* Returns the number of weather forecasts.
	*
	* @return the number of weather forecasts
	*/
	@Override
	public int getWeatherForecastsCount() {
		return _weatherForecastLocalService.getWeatherForecastsCount();
	}

	@Override
	public int getWeatherWeatherForecastsCount(long weatherId) {
		return _weatherForecastLocalService.getWeatherWeatherForecastsCount(weatherId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _weatherForecastLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _weatherForecastLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _weatherForecastLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _weatherForecastLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		int start, int end) {
		return _weatherForecastLocalService.getWeatherForecasts(start, end);
	}

	@Override
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId) {
		return _weatherForecastLocalService.getWeatherWeatherForecasts(weatherId);
	}

	@Override
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId, int start, int end) {
		return _weatherForecastLocalService.getWeatherWeatherForecasts(weatherId,
			start, end);
	}

	@Override
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherWeatherForecasts(
		long weatherId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<edu.weather.servicebuilder.model.WeatherForecast> orderByComparator) {
		return _weatherForecastLocalService.getWeatherWeatherForecasts(weatherId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _weatherForecastLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _weatherForecastLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the weatherIds of the weathers associated with the weather forecast.
	*
	* @param weatherForecastId the weatherForecastId of the weather forecast
	* @return long[] the weatherIds of weathers associated with the weather forecast
	*/
	@Override
	public long[] getWeatherPrimaryKeys(long weatherForecastId) {
		return _weatherForecastLocalService.getWeatherPrimaryKeys(weatherForecastId);
	}

	@Override
	public void addWeatherWeatherForecast(long weatherId,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		_weatherForecastLocalService.addWeatherWeatherForecast(weatherId,
			weatherForecast);
	}

	@Override
	public void addWeatherWeatherForecast(long weatherId, long weatherForecastId) {
		_weatherForecastLocalService.addWeatherWeatherForecast(weatherId,
			weatherForecastId);
	}

	@Override
	public void addWeatherWeatherForecasts(long weatherId,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		_weatherForecastLocalService.addWeatherWeatherForecasts(weatherId,
			weatherForecasts);
	}

	@Override
	public void addWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		_weatherForecastLocalService.addWeatherWeatherForecasts(weatherId,
			weatherForecastIds);
	}

	@Override
	public void clearWeatherWeatherForecasts(long weatherId) {
		_weatherForecastLocalService.clearWeatherWeatherForecasts(weatherId);
	}

	@Override
	public void deleteWeatherWeatherForecast(long weatherId,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		_weatherForecastLocalService.deleteWeatherWeatherForecast(weatherId,
			weatherForecast);
	}

	@Override
	public void deleteWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		_weatherForecastLocalService.deleteWeatherWeatherForecast(weatherId,
			weatherForecastId);
	}

	@Override
	public void deleteWeatherWeatherForecasts(long weatherId,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		_weatherForecastLocalService.deleteWeatherWeatherForecasts(weatherId,
			weatherForecasts);
	}

	@Override
	public void deleteWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		_weatherForecastLocalService.deleteWeatherWeatherForecasts(weatherId,
			weatherForecastIds);
	}

	@Override
	public void setWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		_weatherForecastLocalService.setWeatherWeatherForecasts(weatherId,
			weatherForecastIds);
	}

	@Override
	public WeatherForecastLocalService getWrappedService() {
		return _weatherForecastLocalService;
	}

	@Override
	public void setWrappedService(
		WeatherForecastLocalService weatherForecastLocalService) {
		_weatherForecastLocalService = weatherForecastLocalService;
	}

	private WeatherForecastLocalService _weatherForecastLocalService;
}