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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WeatherForecast service. Represents a row in the &quot;Weather_WeatherForecast&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastModel
 * @see edu.weather.servicebuilder.model.impl.WeatherForecastImpl
 * @see edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl
 * @generated
 */
@ImplementationClassName("edu.weather.servicebuilder.model.impl.WeatherForecastImpl")
@ProviderType
public interface WeatherForecast extends WeatherForecastModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link edu.weather.servicebuilder.model.impl.WeatherForecastImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WeatherForecast, Long> WEATHER_FORECAST_ID_ACCESSOR =
		new Accessor<WeatherForecast, Long>() {
			@Override
			public Long get(WeatherForecast weatherForecast) {
				return weatherForecast.getWeatherForecastId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WeatherForecast> getTypeClass() {
				return WeatherForecast.class;
			}
		};
}