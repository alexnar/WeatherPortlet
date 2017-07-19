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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import edu.weather.servicebuilder.exception.NoSuchWeatherException;
import edu.weather.servicebuilder.model.Weather;

/**
 * The persistence interface for the weather service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.persistence.impl.WeatherPersistenceImpl
 * @see WeatherUtil
 * @generated
 */
@ProviderType
public interface WeatherPersistence extends BasePersistence<Weather> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WeatherUtil} to access the weather persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the weather in the entity cache if it is enabled.
	*
	* @param weather the weather
	*/
	public void cacheResult(Weather weather);

	/**
	* Caches the weathers in the entity cache if it is enabled.
	*
	* @param weathers the weathers
	*/
	public void cacheResult(java.util.List<Weather> weathers);

	/**
	* Creates a new weather with the primary key. Does not add the weather to the database.
	*
	* @param weatherId the primary key for the new weather
	* @return the new weather
	*/
	public Weather create(long weatherId);

	/**
	* Removes the weather with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherId the primary key of the weather
	* @return the weather that was removed
	* @throws NoSuchWeatherException if a weather with the primary key could not be found
	*/
	public Weather remove(long weatherId) throws NoSuchWeatherException;

	public Weather updateImpl(Weather weather);

	/**
	* Returns the weather with the primary key or throws a {@link NoSuchWeatherException} if it could not be found.
	*
	* @param weatherId the primary key of the weather
	* @return the weather
	* @throws NoSuchWeatherException if a weather with the primary key could not be found
	*/
	public Weather findByPrimaryKey(long weatherId)
		throws NoSuchWeatherException;

	/**
	* Returns the weather with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param weatherId the primary key of the weather
	* @return the weather, or <code>null</code> if a weather with the primary key could not be found
	*/
	public Weather fetchByPrimaryKey(long weatherId);

	@Override
	public java.util.Map<java.io.Serializable, Weather> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the weathers.
	*
	* @return the weathers
	*/
	public java.util.List<Weather> findAll();

	/**
	* Returns a range of all the weathers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @return the range of weathers
	*/
	public java.util.List<Weather> findAll(int start, int end);

	/**
	* Returns an ordered range of all the weathers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of weathers
	*/
	public java.util.List<Weather> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Weather> orderByComparator);

	/**
	* Returns an ordered range of all the weathers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of weathers
	*/
	public java.util.List<Weather> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Weather> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the weathers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of weathers.
	*
	* @return the number of weathers
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of weather forecasts associated with the weather.
	*
	* @param pk the primary key of the weather
	* @return long[] of the primaryKeys of weather forecasts associated with the weather
	*/
	public long[] getWeatherForecastPrimaryKeys(long pk);

	/**
	* Returns all the weather forecasts associated with the weather.
	*
	* @param pk the primary key of the weather
	* @return the weather forecasts associated with the weather
	*/
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk);

	/**
	* Returns a range of all the weather forecasts associated with the weather.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the weather
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @return the range of weather forecasts associated with the weather
	*/
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the weather forecasts associated with the weather.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the weather
	* @param start the lower bound of the range of weathers
	* @param end the upper bound of the range of weathers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of weather forecasts associated with the weather
	*/
	public java.util.List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<edu.weather.servicebuilder.model.WeatherForecast> orderByComparator);

	/**
	* Returns the number of weather forecasts associated with the weather.
	*
	* @param pk the primary key of the weather
	* @return the number of weather forecasts associated with the weather
	*/
	public int getWeatherForecastsSize(long pk);

	/**
	* Returns <code>true</code> if the weather forecast is associated with the weather.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPK the primary key of the weather forecast
	* @return <code>true</code> if the weather forecast is associated with the weather; <code>false</code> otherwise
	*/
	public boolean containsWeatherForecast(long pk, long weatherForecastPK);

	/**
	* Returns <code>true</code> if the weather has any weather forecasts associated with it.
	*
	* @param pk the primary key of the weather to check for associations with weather forecasts
	* @return <code>true</code> if the weather has any weather forecasts associated with it; <code>false</code> otherwise
	*/
	public boolean containsWeatherForecasts(long pk);

	/**
	* Adds an association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPK the primary key of the weather forecast
	*/
	public void addWeatherForecast(long pk, long weatherForecastPK);

	/**
	* Adds an association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecast the weather forecast
	*/
	public void addWeatherForecast(long pk,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast);

	/**
	* Adds an association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPKs the primary keys of the weather forecasts
	*/
	public void addWeatherForecasts(long pk, long[] weatherForecastPKs);

	/**
	* Adds an association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecasts the weather forecasts
	*/
	public void addWeatherForecasts(long pk,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts);

	/**
	* Clears all associations between the weather and its weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather to clear the associated weather forecasts from
	*/
	public void clearWeatherForecasts(long pk);

	/**
	* Removes the association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPK the primary key of the weather forecast
	*/
	public void removeWeatherForecast(long pk, long weatherForecastPK);

	/**
	* Removes the association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecast the weather forecast
	*/
	public void removeWeatherForecast(long pk,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast);

	/**
	* Removes the association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPKs the primary keys of the weather forecasts
	*/
	public void removeWeatherForecasts(long pk, long[] weatherForecastPKs);

	/**
	* Removes the association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecasts the weather forecasts
	*/
	public void removeWeatherForecasts(long pk,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts);

	/**
	* Sets the weather forecasts associated with the weather, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecastPKs the primary keys of the weather forecasts to be associated with the weather
	*/
	public void setWeatherForecasts(long pk, long[] weatherForecastPKs);

	/**
	* Sets the weather forecasts associated with the weather, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather
	* @param weatherForecasts the weather forecasts to be associated with the weather
	*/
	public void setWeatherForecasts(long pk,
		java.util.List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts);
}