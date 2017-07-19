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

import edu.weather.servicebuilder.exception.NoSuchForecastException;
import edu.weather.servicebuilder.model.WeatherForecast;

/**
 * The persistence interface for the weather forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.persistence.impl.WeatherForecastPersistenceImpl
 * @see WeatherForecastUtil
 * @generated
 */
@ProviderType
public interface WeatherForecastPersistence extends BasePersistence<WeatherForecast> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WeatherForecastUtil} to access the weather forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the weather forecast in the entity cache if it is enabled.
	*
	* @param weatherForecast the weather forecast
	*/
	public void cacheResult(WeatherForecast weatherForecast);

	/**
	* Caches the weather forecasts in the entity cache if it is enabled.
	*
	* @param weatherForecasts the weather forecasts
	*/
	public void cacheResult(java.util.List<WeatherForecast> weatherForecasts);

	/**
	* Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	*
	* @param weatherForecastId the primary key for the new weather forecast
	* @return the new weather forecast
	*/
	public WeatherForecast create(long weatherForecastId);

	/**
	* Removes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast that was removed
	* @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	*/
	public WeatherForecast remove(long weatherForecastId)
		throws NoSuchForecastException;

	public WeatherForecast updateImpl(WeatherForecast weatherForecast);

	/**
	* Returns the weather forecast with the primary key or throws a {@link NoSuchForecastException} if it could not be found.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast
	* @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	*/
	public WeatherForecast findByPrimaryKey(long weatherForecastId)
		throws NoSuchForecastException;

	/**
	* Returns the weather forecast with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param weatherForecastId the primary key of the weather forecast
	* @return the weather forecast, or <code>null</code> if a weather forecast with the primary key could not be found
	*/
	public WeatherForecast fetchByPrimaryKey(long weatherForecastId);

	@Override
	public java.util.Map<java.io.Serializable, WeatherForecast> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the weather forecasts.
	*
	* @return the weather forecasts
	*/
	public java.util.List<WeatherForecast> findAll();

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
	public java.util.List<WeatherForecast> findAll(int start, int end);

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
	public java.util.List<WeatherForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeatherForecast> orderByComparator);

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
	public java.util.List<WeatherForecast> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeatherForecast> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the weather forecasts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of weather forecasts.
	*
	* @return the number of weather forecasts
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return long[] of the primaryKeys of weathers associated with the weather forecast
	*/
	public long[] getWeatherPrimaryKeys(long pk);

	/**
	* Returns all the weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return the weathers associated with the weather forecast
	*/
	public java.util.List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk);

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
	public java.util.List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk, int start, int end);

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
	public java.util.List<edu.weather.servicebuilder.model.Weather> getWeathers(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<edu.weather.servicebuilder.model.Weather> orderByComparator);

	/**
	* Returns the number of weathers associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @return the number of weathers associated with the weather forecast
	*/
	public int getWeathersSize(long pk);

	/**
	* Returns <code>true</code> if the weather is associated with the weather forecast.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	* @return <code>true</code> if the weather is associated with the weather forecast; <code>false</code> otherwise
	*/
	public boolean containsWeather(long pk, long weatherPK);

	/**
	* Returns <code>true</code> if the weather forecast has any weathers associated with it.
	*
	* @param pk the primary key of the weather forecast to check for associations with weathers
	* @return <code>true</code> if the weather forecast has any weathers associated with it; <code>false</code> otherwise
	*/
	public boolean containsWeathers(long pk);

	/**
	* Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	*/
	public void addWeather(long pk, long weatherPK);

	/**
	* Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weather the weather
	*/
	public void addWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather);

	/**
	* Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers
	*/
	public void addWeathers(long pk, long[] weatherPKs);

	/**
	* Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers
	*/
	public void addWeathers(long pk,
		java.util.List<edu.weather.servicebuilder.model.Weather> weathers);

	/**
	* Clears all associations between the weather forecast and its weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast to clear the associated weathers from
	*/
	public void clearWeathers(long pk);

	/**
	* Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPK the primary key of the weather
	*/
	public void removeWeather(long pk, long weatherPK);

	/**
	* Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weather the weather
	*/
	public void removeWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather);

	/**
	* Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers
	*/
	public void removeWeathers(long pk, long[] weatherPKs);

	/**
	* Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers
	*/
	public void removeWeathers(long pk,
		java.util.List<edu.weather.servicebuilder.model.Weather> weathers);

	/**
	* Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weatherPKs the primary keys of the weathers to be associated with the weather forecast
	*/
	public void setWeathers(long pk, long[] weatherPKs);

	/**
	* Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the weather forecast
	* @param weathers the weathers to be associated with the weather forecast
	*/
	public void setWeathers(long pk,
		java.util.List<edu.weather.servicebuilder.model.Weather> weathers);
}