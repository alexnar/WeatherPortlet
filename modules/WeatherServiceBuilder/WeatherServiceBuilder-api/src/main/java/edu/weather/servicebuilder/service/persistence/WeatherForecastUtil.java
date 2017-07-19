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

package edu.weather.servicebuilder.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import edu.weather.servicebuilder.model.WeatherForecast;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the weather forecast service. This utility wraps {@link edu.weather.servicebuilder.service.persistence.impl.WeatherForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastPersistence
 * @see edu.weather.servicebuilder.service.persistence.impl.WeatherForecastPersistenceImpl
 * @generated
 */
@ProviderType
public class WeatherForecastUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(WeatherForecast weatherForecast) {
		getPersistence().clearCache(weatherForecast);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WeatherForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WeatherForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WeatherForecast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WeatherForecast> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WeatherForecast update(WeatherForecast weatherForecast) {
		return getPersistence().update(weatherForecast);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WeatherForecast update(WeatherForecast weatherForecast,
		ServiceContext serviceContext) {
		return getPersistence().update(weatherForecast, serviceContext);
	}

	/**
	* Caches the weather forecast in the entity cache if it is enabled.
	*
	* @param weatherForecast the weather forecast
	*/
	public static void cacheResult(WeatherForecast weatherForecast) {
		getPersistence().cacheResult(weatherForecast);
	}

	/**
	* Caches the weather forecasts in the entity cache if it is enabled.
	*
	* @param weatherForecasts the weather forecasts
	*/
	public static void cacheResult(List<WeatherForecast> weatherForecasts) {
		getPersistence().cacheResult(weatherForecasts);
	}

	/**
	* Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	*
	* @param weatherForecastId the primary key for the new weather forecast
	* @return the new weather forecast
	*/
	public static WeatherForecast create(long weatherForecastId) {
		return getPersistence().create(weatherForecastId);
	}

	/**
	* Removes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast that was removed
	* @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	*/
	public static WeatherForecast remove(long weatherForecastId)
		throws edu.weather.servicebuilder.exception.NoSuchForecastException {
		return getPersistence().remove(weatherForecastId);
	}

	public static WeatherForecast updateImpl(WeatherForecast weatherForecast) {
		return getPersistence().updateImpl(weatherForecast);
	}

	/**
	* Returns the weather forecast with the primary key or throws a {@link NoSuchForecastException} if it could not be found.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast
	* @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	*/
	public static WeatherForecast findByPrimaryKey(long weatherForecastId)
		throws edu.weather.servicebuilder.exception.NoSuchForecastException {
		return getPersistence().findByPrimaryKey(weatherForecastId);
	}

	/**
	* Returns the weather forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast, or <code>null</code> if a weather forecast with the primary key could not be found
	*/
	public static WeatherForecast fetchByPrimaryKey(long weatherForecastId) {
		return getPersistence().fetchByPrimaryKey(weatherForecastId);
	}

	public static java.util.Map<java.io.Serializable, WeatherForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the weather forecasts.
	*
	* @return the weather forecasts
	*/
	public static List<WeatherForecast> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the weather forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @return the range of weather forecasts
	*/
	public static List<WeatherForecast> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the weather forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of weather forecasts
	*/
	public static List<WeatherForecast> findAll(int start, int end,
		OrderByComparator<WeatherForecast> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the weather forecasts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of weather forecasts
	*/
	public static List<WeatherForecast> findAll(int start, int end,
		OrderByComparator<WeatherForecast> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the weather forecasts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of weather forecasts.
	*
	* @return the number of weather forecasts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return long[] of the primaryKeys of weathers associated with the weather forecast
	*/
	public static long[] getWeatherPrimaryKeys(long pk) {
		return getPersistence().getWeatherPrimaryKeys(pk);
	}

	/**
	* Returns all the weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return the weathers associated with the weather forecast
	*/
	public static List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk) {
		return getPersistence().getWeathers(pk);
	}

	/**
	* Returns a range of all the weathers associated with the weather forecast.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the weather forecast
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @return the range of weathers associated with the weather forecast
	*/
	public static List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk, int start, int end) {
		return getPersistence().getWeathers(pk, start, end);
	}

	/**
	* Returns an ordered range of all the weathers associated with the weather forecast.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the weather forecast
	* @param start the lower bound of the range of weather forecasts
	* @param end the upper bound of the range of weather forecasts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of weathers associated with the weather forecast
	*/
	public static List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk, int start, int end,
		OrderByComparator<edu.weather.servicebuilder.model.Weather> orderByComparator) {
		return getPersistence().getWeathers(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return the number of weathers associated with the weather forecast
	*/
	public static int getWeathersSize(long pk) {
		return getPersistence().getWeathersSize(pk);
	}

	/**
	* Returns <code>true</code> if the weather is associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	* @return <code>true</code> if the weather is associated with the weather forecast; <code>false</code> otherwise
	*/
	public static boolean containsWeather(long pk, long weatherPK) {
		return getPersistence().containsWeather(pk, weatherPK);
	}

	/**
	* Returns <code>true</code> if the weather forecast has any weathers associated with it.
	*
	* @param pk the primary key of the weather forecast to check for associations with weathers
	* @return <code>true</code> if the weather forecast has any weathers associated with it; <code>false</code> otherwise
	*/
	public static boolean containsWeathers(long pk) {
		return getPersistence().containsWeathers(pk);
	}

	/**
	* Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	*/
	public static void addWeather(long pk, long weatherPK) {
		getPersistence().addWeather(pk, weatherPK);
	}

	/**
	* Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weather the weather
	*/
	public static void addWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather) {
		getPersistence().addWeather(pk, weather);
	}

	/**
	* Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers
	*/
	public static void addWeathers(long pk, long[] weatherPKs) {
		getPersistence().addWeathers(pk, weatherPKs);
	}

	/**
	* Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers
	*/
	public static void addWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		getPersistence().addWeathers(pk, weathers);
	}

	/**
	* Clears all associations between the weather forecast and its weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast to clear the associated weathers from
	*/
	public static void clearWeathers(long pk) {
		getPersistence().clearWeathers(pk);
	}

	/**
	* Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	*/
	public static void removeWeather(long pk, long weatherPK) {
		getPersistence().removeWeather(pk, weatherPK);
	}

	/**
	* Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weather the weather
	*/
	public static void removeWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather) {
		getPersistence().removeWeather(pk, weather);
	}

	/**
	* Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers
	*/
	public static void removeWeathers(long pk, long[] weatherPKs) {
		getPersistence().removeWeathers(pk, weatherPKs);
	}

	/**
	* Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers
	*/
	public static void removeWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		getPersistence().removeWeathers(pk, weathers);
	}

	/**
	* Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers to be associated with the weather forecast
	*/
	public static void setWeathers(long pk, long[] weatherPKs) {
		getPersistence().setWeathers(pk, weatherPKs);
	}

	/**
	* Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers to be associated with the weather forecast
	*/
	public static void setWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		getPersistence().setWeathers(pk, weathers);
	}

	public static WeatherForecastPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WeatherForecastPersistence, WeatherForecastPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WeatherForecastPersistence.class);
}