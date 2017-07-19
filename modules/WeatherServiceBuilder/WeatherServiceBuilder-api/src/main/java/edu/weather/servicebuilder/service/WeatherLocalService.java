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

import edu.weather.servicebuilder.model.Weather;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Weather. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherLocalServiceUtil
 * @see edu.weather.servicebuilder.service.base.WeatherLocalServiceBaseImpl
 * @see edu.weather.servicebuilder.service.impl.WeatherLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WeatherLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WeatherLocalServiceUtil} to access the weather local service. Add custom service methods to {@link edu.weather.servicebuilder.service.impl.WeatherLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWeatherForecastWeather(long weatherForecastId,
		long weatherId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasWeatherForecastWeathers(long weatherForecastId);

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
	* Adds the weather to the database. Also notifies the appropriate model listeners.
	*
	* @param weather the weather
	* @return the weather that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Weather addWeather(Weather weather);

	/**
	* Creates a new weather with the primary key. Does not add the weather to the database.
	*
	* @param weatherId the primary key for the new weather
	* @return the new weather
	*/
	public Weather createWeather(long weatherId);

	/**
	* Deletes the weather from the database. Also notifies the appropriate model listeners.
	*
	* @param weather the weather
	* @return the weather that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Weather deleteWeather(Weather weather);

	/**
	* Deletes the weather with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherId the primary key of the weather
	* @return the weather that was removed
	* @throws PortalException if a weather with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Weather deleteWeather(long weatherId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Weather fetchWeather(long weatherId);

	/**
	* Returns the weather with the primary key.
	*
	* @param weatherId the primary key of the weather
	* @return the weather
	* @throws PortalException if a weather with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Weather getWeather(long weatherId) throws PortalException;

	/**
	* Updates the weather in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param weather the weather
	* @return the weather that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Weather updateWeather(Weather weather);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWeatherForecastWeathersCount(long weatherForecastId);

	/**
	* Returns the number of weathers.
	*
	* @return the number of weathers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWeathersCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId,
		int start, int end, OrderByComparator<Weather> orderByComparator);

	/**
	* Returns a range of all the weathers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @return the range of weathers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Weather> getWeathers(int start, int end);

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
	* Returns the weatherForecastIds of the weather forecasts associated with the weather.
	*
	* @param weatherId the weatherId of the weather
	* @return long[] the weatherForecastIds of weather forecasts associated with the weather
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getWeatherForecastPrimaryKeys(long weatherId);

	public void addWeatherForecastWeather(long weatherForecastId,
		Weather weather);

	public void addWeatherForecastWeather(long weatherForecastId, long weatherId);

	public void addWeatherForecastWeathers(long weatherForecastId,
		List<Weather> weathers);

	public void addWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds);

	public void clearWeatherForecastWeathers(long weatherForecastId);

	public void deleteWeatherForecastWeather(long weatherForecastId,
		Weather weather);

	public void deleteWeatherForecastWeather(long weatherForecastId,
		long weatherId);

	public void deleteWeatherForecastWeathers(long weatherForecastId,
		List<Weather> weathers);

	public void deleteWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds);

	public void setWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds);
}