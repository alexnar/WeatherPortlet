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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import edu.weather.servicebuilder.model.WeatherForecast;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WeatherForecast. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastLocalServiceUtil
 * @see edu.weather.servicebuilder.service.base.WeatherForecastLocalServiceBaseImpl
 * @see edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WeatherForecastLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WeatherForecastLocalServiceUtil} to access the weather forecast local service. Add custom service methods to {@link edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWeatherWeatherForecast(long weatherId,
		long weatherForecastId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWeatherWeatherForecasts(long weatherId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the weather forecast to the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WeatherForecast addWeatherForecast(WeatherForecast weatherForecast);

	/**
	* Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	*
	* @param weatherForecastId the primary key for the new weather forecast
	* @return the new weather forecast
	*/
	public WeatherForecast createWeatherForecast(long weatherForecastId);

	/**
	* Deletes the weather forecast from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public WeatherForecast deleteWeatherForecast(
		WeatherForecast weatherForecast);

	/**
	* Deletes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast that was removed
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WeatherForecast deleteWeatherForecast(long weatherForecastId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WeatherForecast fetchWeatherForecast(long weatherForecastId);

	/**
	* Returns the weather forecast with the primary key.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast
	* @throws PortalException if a weather forecast with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WeatherForecast getWeatherForecast(long weatherForecastId)
		throws PortalException;

	/**
	* Updates the weather forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param weatherForecast the weather forecast
	* @return the weather forecast that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WeatherForecast updateWeatherForecast(
		WeatherForecast weatherForecast);

	/**
	* Returns the number of weather forecasts.
	*
	* @return the number of weather forecasts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWeatherForecastsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWeatherWeatherForecastsCount(long weatherId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WeatherForecast> getWeatherForecasts(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId,
		int start, int end, OrderByComparator<WeatherForecast> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the weatherIds of the weathers associated with the weather forecast.
	*
	* @param weatherForecastId the weatherForecastId of the weather forecast
	* @return long[] the weatherIds of weathers associated with the weather forecast
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getWeatherPrimaryKeys(long weatherForecastId);

	public void addWeatherWeatherForecast(long weatherId,
		WeatherForecast weatherForecast);

	public void addWeatherWeatherForecast(long weatherId, long weatherForecastId);

	public void addWeatherWeatherForecasts(long weatherId,
		List<WeatherForecast> weatherForecasts);

	public void addWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds);

	public void clearWeatherWeatherForecasts(long weatherId);

	public void deleteWeatherWeatherForecast(long weatherId,
		WeatherForecast weatherForecast);

	public void deleteWeatherWeatherForecast(long weatherId,
		long weatherForecastId);

	public void deleteWeatherWeatherForecasts(long weatherId,
		List<WeatherForecast> weatherForecasts);

	public void deleteWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds);

	public void setWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds);
}