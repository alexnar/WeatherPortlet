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

import edu.weather.servicebuilder.model.Weather;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Weather in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Weather
 * @generated
 */
@ProviderType
public class WeatherCacheModel implements CacheModel<Weather>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeatherCacheModel)) {
			return false;
		}

		WeatherCacheModel weatherCacheModel = (WeatherCacheModel)obj;

		if (weatherId == weatherCacheModel.weatherId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, weatherId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{weatherId=");
		sb.append(weatherId);
		sb.append(", temperature=");
		sb.append(temperature);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Weather toEntityModel() {
		WeatherImpl weatherImpl = new WeatherImpl();

		weatherImpl.setWeatherId(weatherId);
		weatherImpl.setTemperature(temperature);

		if (description == null) {
			weatherImpl.setDescription(StringPool.BLANK);
		}
		else {
			weatherImpl.setDescription(description);
		}

		weatherImpl.resetOriginalValues();

		return weatherImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		weatherId = objectInput.readLong();

		temperature = objectInput.readDouble();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(weatherId);

		objectOutput.writeDouble(temperature);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long weatherId;
	public double temperature;
	public String description;
}