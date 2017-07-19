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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link edu.weather.servicebuilder.service.http.WeatherForecastServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.http.WeatherForecastServiceSoap
 * @generated
 */
@ProviderType
public class WeatherForecastSoap implements Serializable {
	public static WeatherForecastSoap toSoapModel(WeatherForecast model) {
		WeatherForecastSoap soapModel = new WeatherForecastSoap();

		soapModel.setWeatherForecastId(model.getWeatherForecastId());
		soapModel.setCityName(model.getCityName());
		soapModel.setResourceName(model.getResourceName());
		soapModel.setForecastDate(model.getForecastDate());

		return soapModel;
	}

	public static WeatherForecastSoap[] toSoapModels(WeatherForecast[] models) {
		WeatherForecastSoap[] soapModels = new WeatherForecastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WeatherForecastSoap[][] toSoapModels(
		WeatherForecast[][] models) {
		WeatherForecastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WeatherForecastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WeatherForecastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WeatherForecastSoap[] toSoapModels(
		List<WeatherForecast> models) {
		List<WeatherForecastSoap> soapModels = new ArrayList<WeatherForecastSoap>(models.size());

		for (WeatherForecast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WeatherForecastSoap[soapModels.size()]);
	}

	public WeatherForecastSoap() {
	}

	public long getPrimaryKey() {
		return _weatherForecastId;
	}

	public void setPrimaryKey(long pk) {
		setWeatherForecastId(pk);
	}

	public long getWeatherForecastId() {
		return _weatherForecastId;
	}

	public void setWeatherForecastId(long weatherForecastId) {
		_weatherForecastId = weatherForecastId;
	}

	public String getCityName() {
		return _cityName;
	}

	public void setCityName(String cityName) {
		_cityName = cityName;
	}

	public String getResourceName() {
		return _resourceName;
	}

	public void setResourceName(String resourceName) {
		_resourceName = resourceName;
	}

	public Date getForecastDate() {
		return _forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		_forecastDate = forecastDate;
	}

	private long _weatherForecastId;
	private String _cityName;
	private String _resourceName;
	private Date _forecastDate;
}