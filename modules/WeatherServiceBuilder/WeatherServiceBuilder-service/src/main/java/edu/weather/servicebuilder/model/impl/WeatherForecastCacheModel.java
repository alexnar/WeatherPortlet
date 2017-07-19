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

package edu.weather.servicebuilder.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import edu.weather.servicebuilder.model.WeatherForecast;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WeatherForecast in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecast
 * @generated
 */
@ProviderType
public class WeatherForecastCacheModel implements CacheModel<WeatherForecast>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeatherForecastCacheModel)) {
			return false;
		}

		WeatherForecastCacheModel weatherForecastCacheModel = (WeatherForecastCacheModel)obj;

		if (weatherForecastId == weatherForecastCacheModel.weatherForecastId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, weatherForecastId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{weatherForecastId=");
		sb.append(weatherForecastId);
		sb.append(", cityName=");
		sb.append(cityName);
		sb.append(", resourceName=");
		sb.append(resourceName);
		sb.append(", forecastDate=");
		sb.append(forecastDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WeatherForecast toEntityModel() {
		WeatherForecastImpl weatherForecastImpl = new WeatherForecastImpl();

		weatherForecastImpl.setWeatherForecastId(weatherForecastId);

		if (cityName == null) {
			weatherForecastImpl.setCityName(StringPool.BLANK);
		}
		else {
			weatherForecastImpl.setCityName(cityName);
		}

		if (resourceName == null) {
			weatherForecastImpl.setResourceName(StringPool.BLANK);
		}
		else {
			weatherForecastImpl.setResourceName(resourceName);
		}

		if (forecastDate == Long.MIN_VALUE) {
			weatherForecastImpl.setForecastDate(null);
		}
		else {
			weatherForecastImpl.setForecastDate(new Date(forecastDate));
		}

		weatherForecastImpl.resetOriginalValues();

		return weatherForecastImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		weatherForecastId = objectInput.readLong();
		cityName = objectInput.readUTF();
		resourceName = objectInput.readUTF();
		forecastDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(weatherForecastId);

		if (cityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cityName);
		}

		if (resourceName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(resourceName);
		}

		objectOutput.writeLong(forecastDate);
	}

	public long weatherForecastId;
	public String cityName;
	public String resourceName;
	public long forecastDate;
}