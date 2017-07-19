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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the Weather service. Represents a row in the &quot;Weather_Weather&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link edu.weather.servicebuilder.model.impl.WeatherImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Weather
 * @see edu.weather.servicebuilder.model.impl.WeatherImpl
 * @see edu.weather.servicebuilder.model.impl.WeatherModelImpl
 * @generated
 */
@ProviderType
public interface WeatherModel extends BaseModel<Weather> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a weather model instance should use the {@link Weather} interface instead.
	 */

	/**
	 * Returns the primary key of this weather.
	 *
	 * @return the primary key of this weather
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this weather.
	 *
	 * @param primaryKey the primary key of this weather
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the weather ID of this weather.
	 *
	 * @return the weather ID of this weather
	 */
	public long getWeatherId();

	/**
	 * Sets the weather ID of this weather.
	 *
	 * @param weatherId the weather ID of this weather
	 */
	public void setWeatherId(long weatherId);

	/**
	 * Returns the temperature of this weather.
	 *
	 * @return the temperature of this weather
	 */
	public Double getTemperature();

	/**
	 * Sets the temperature of this weather.
	 *
	 * @param temperature the temperature of this weather
	 */
	public void setTemperature(Double temperature);

	/**
	 * Returns the description of this weather.
	 *
	 * @return the description of this weather
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this weather.
	 *
	 * @param description the description of this weather
	 */
	public void setDescription(String description);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(edu.weather.servicebuilder.model.Weather weather);

	@Override
	public int hashCode();

	@Override
	public CacheModel<edu.weather.servicebuilder.model.Weather> toCacheModel();

	@Override
	public edu.weather.servicebuilder.model.Weather toEscapedModel();

	@Override
	public edu.weather.servicebuilder.model.Weather toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}