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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edu.weather.servicebuilder.service.http.WeatherServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.http.WeatherServiceSoap
 * @generated
 */
@ProviderType
public class WeatherSoap implements Serializable {
	public static WeatherSoap toSoapModel(Weather model) {
		WeatherSoap soapModel = new WeatherSoap();

		soapModel.setWeatherId(model.getWeatherId());
		soapModel.setTemperature(model.getTemperature());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static WeatherSoap[] toSoapModels(Weather[] models) {
		WeatherSoap[] soapModels = new WeatherSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WeatherSoap[][] toSoapModels(Weather[][] models) {
		WeatherSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WeatherSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WeatherSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WeatherSoap[] toSoapModels(List<Weather> models) {
		List<WeatherSoap> soapModels = new ArrayList<WeatherSoap>(models.size());

		for (Weather model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WeatherSoap[soapModels.size()]);
	}

	public WeatherSoap() {
	}

	public long getPrimaryKey() {
		return _weatherId;
	}

	public void setPrimaryKey(long pk) {
		setWeatherId(pk);
	}

	public long getWeatherId() {
		return _weatherId;
	}

	public void setWeatherId(long weatherId) {
		_weatherId = weatherId;
	}

	public Double getTemperature() {
		return _temperature;
	}

	public void setTemperature(Double temperature) {
		_temperature = temperature;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _weatherId;
	private Double _temperature;
	private String _description;
}