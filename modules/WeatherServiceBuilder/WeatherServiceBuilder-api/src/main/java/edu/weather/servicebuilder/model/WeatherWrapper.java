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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Weather}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Weather
 * @generated
 */
@ProviderType
public class WeatherWrapper implements Weather, ModelWrapper<Weather> {
	public WeatherWrapper(Weather weather) {
		_weather = weather;
	}

	@Override
	public Class<?> getModelClass() {
		return Weather.class;
	}

	@Override
	public String getModelClassName() {
		return Weather.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("weatherId", getWeatherId());
		attributes.put("temperature", getTemperature());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long weatherId = (Long)attributes.get("weatherId");

		if (weatherId != null) {
			setWeatherId(weatherId);
		}

		Double temperature = (Double)attributes.get("temperature");

		if (temperature != null) {
			setTemperature(temperature);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _weather.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _weather.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _weather.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _weather.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<edu.weather.servicebuilder.model.Weather> toCacheModel() {
		return _weather.toCacheModel();
	}

	@Override
	public edu.weather.servicebuilder.model.Weather toEscapedModel() {
		return new WeatherWrapper(_weather.toEscapedModel());
	}

	@Override
	public edu.weather.servicebuilder.model.Weather toUnescapedModel() {
		return new WeatherWrapper(_weather.toUnescapedModel());
	}

	@Override
	public int compareTo(edu.weather.servicebuilder.model.Weather weather) {
		return _weather.compareTo(weather);
	}

	@Override
	public int hashCode() {
		return _weather.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _weather.getPrimaryKeyObj();
	}

	/**
	* Returns the temperature of this weather.
	*
	* @return the temperature of this weather
	*/
	@Override
	public java.lang.Double getTemperature() {
		return _weather.getTemperature();
	}

	@Override
	public java.lang.Object clone() {
		return new WeatherWrapper((Weather)_weather.clone());
	}

	/**
	* Returns the description of this weather.
	*
	* @return the description of this weather
	*/
	@Override
	public java.lang.String getDescription() {
		return _weather.getDescription();
	}

	@Override
	public java.lang.String toString() {
		return _weather.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _weather.toXmlString();
	}

	/**
	* Returns the primary key of this weather.
	*
	* @return the primary key of this weather
	*/
	@Override
	public long getPrimaryKey() {
		return _weather.getPrimaryKey();
	}

	/**
	* Returns the weather ID of this weather.
	*
	* @return the weather ID of this weather
	*/
	@Override
	public long getWeatherId() {
		return _weather.getWeatherId();
	}

	@Override
	public void persist() {
		_weather.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_weather.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this weather.
	*
	* @param description the description of this weather
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_weather.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_weather.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_weather.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_weather.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_weather.setNew(n);
	}

	/**
	* Sets the primary key of this weather.
	*
	* @param primaryKey the primary key of this weather
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_weather.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_weather.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the temperature of this weather.
	*
	* @param temperature the temperature of this weather
	*/
	@Override
	public void setTemperature(java.lang.Double temperature) {
		_weather.setTemperature(temperature);
	}

	/**
	* Sets the weather ID of this weather.
	*
	* @param weatherId the weather ID of this weather
	*/
	@Override
	public void setWeatherId(long weatherId) {
		_weather.setWeatherId(weatherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeatherWrapper)) {
			return false;
		}

		WeatherWrapper weatherWrapper = (WeatherWrapper)obj;

		if (Objects.equals(_weather, weatherWrapper._weather)) {
			return true;
		}

		return false;
	}

	@Override
	public Weather getWrappedModel() {
		return _weather;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _weather.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _weather.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_weather.resetOriginalValues();
	}

	private final Weather _weather;
}