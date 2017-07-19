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

package edu.weather.servicebuilder.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link WeatherForecast}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecast
 * @generated
 */
@ProviderType
public class WeatherForecastWrapper implements WeatherForecast,
	ModelWrapper<WeatherForecast> {
	public WeatherForecastWrapper(WeatherForecast weatherForecast) {
		_weatherForecast = weatherForecast;
	}

	@Override
	public Class<?> getModelClass() {
		return WeatherForecast.class;
	}

	@Override
	public String getModelClassName() {
		return WeatherForecast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("weatherForecastId", getWeatherForecastId());
		attributes.put("cityName", getCityName());
		attributes.put("resourceName", getResourceName());
		attributes.put("forecastDate", getForecastDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long weatherForecastId = (Long)attributes.get("weatherForecastId");

		if (weatherForecastId != null) {
			setWeatherForecastId(weatherForecastId);
		}

		String cityName = (String)attributes.get("cityName");

		if (cityName != null) {
			setCityName(cityName);
		}

		String resourceName = (String)attributes.get("resourceName");

		if (resourceName != null) {
			setResourceName(resourceName);
		}

		Date forecastDate = (Date)attributes.get("forecastDate");

		if (forecastDate != null) {
			setForecastDate(forecastDate);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _weatherForecast.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _weatherForecast.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _weatherForecast.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _weatherForecast.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<edu.weather.servicebuilder.model.WeatherForecast> toCacheModel() {
		return _weatherForecast.toCacheModel();
	}

	@Override
	public edu.weather.servicebuilder.model.WeatherForecast toEscapedModel() {
		return new WeatherForecastWrapper(_weatherForecast.toEscapedModel());
	}

	@Override
	public edu.weather.servicebuilder.model.WeatherForecast toUnescapedModel() {
		return new WeatherForecastWrapper(_weatherForecast.toUnescapedModel());
	}

	@Override
	public int compareTo(
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		return _weatherForecast.compareTo(weatherForecast);
	}

	@Override
	public int hashCode() {
		return _weatherForecast.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _weatherForecast.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new WeatherForecastWrapper((WeatherForecast)_weatherForecast.clone());
	}

	/**
	* Returns the city name of this weather forecast.
	*
	* @return the city name of this weather forecast
	*/
	@Override
	public java.lang.String getCityName() {
		return _weatherForecast.getCityName();
	}

	/**
	* Returns the resource name of this weather forecast.
	*
	* @return the resource name of this weather forecast
	*/
	@Override
	public java.lang.String getResourceName() {
		return _weatherForecast.getResourceName();
	}

	@Override
	public java.lang.String toString() {
		return _weatherForecast.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _weatherForecast.toXmlString();
	}

	/**
	* Returns the forecast date of this weather forecast.
	*
	* @return the forecast date of this weather forecast
	*/
	@Override
	public Date getForecastDate() {
		return _weatherForecast.getForecastDate();
	}

	/**
	* Returns the primary key of this weather forecast.
	*
	* @return the primary key of this weather forecast
	*/
	@Override
	public long getPrimaryKey() {
		return _weatherForecast.getPrimaryKey();
	}

	/**
	* Returns the weather forecast ID of this weather forecast.
	*
	* @return the weather forecast ID of this weather forecast
	*/
	@Override
	public long getWeatherForecastId() {
		return _weatherForecast.getWeatherForecastId();
	}

	@Override
	public void persist() {
		_weatherForecast.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_weatherForecast.setCachedModel(cachedModel);
	}

	/**
	* Sets the city name of this weather forecast.
	*
	* @param cityName the city name of this weather forecast
	*/
	@Override
	public void setCityName(java.lang.String cityName) {
		_weatherForecast.setCityName(cityName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_weatherForecast.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_weatherForecast.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_weatherForecast.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast date of this weather forecast.
	*
	* @param forecastDate the forecast date of this weather forecast
	*/
	@Override
	public void setForecastDate(Date forecastDate) {
		_weatherForecast.setForecastDate(forecastDate);
	}

	@Override
	public void setNew(boolean n) {
		_weatherForecast.setNew(n);
	}

	/**
	* Sets the primary key of this weather forecast.
	*
	* @param primaryKey the primary key of this weather forecast
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_weatherForecast.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_weatherForecast.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the resource name of this weather forecast.
	*
	* @param resourceName the resource name of this weather forecast
	*/
	@Override
	public void setResourceName(java.lang.String resourceName) {
		_weatherForecast.setResourceName(resourceName);
	}

	/**
	* Sets the weather forecast ID of this weather forecast.
	*
	* @param weatherForecastId the weather forecast ID of this weather forecast
	*/
	@Override
	public void setWeatherForecastId(long weatherForecastId) {
		_weatherForecast.setWeatherForecastId(weatherForecastId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeatherForecastWrapper)) {
			return false;
		}

		WeatherForecastWrapper weatherForecastWrapper = (WeatherForecastWrapper)obj;

		if (Objects.equals(_weatherForecast,
					weatherForecastWrapper._weatherForecast)) {
			return true;
		}

		return false;
	}

	@Override
	public WeatherForecast getWrappedModel() {
		return _weatherForecast;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _weatherForecast.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _weatherForecast.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_weatherForecast.resetOriginalValues();
	}

	private final WeatherForecast _weatherForecast;
}