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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import edu.weather.servicebuilder.model.WeatherForecast;
import edu.weather.servicebuilder.model.WeatherForecastModel;
import edu.weather.servicebuilder.model.WeatherForecastSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the WeatherForecast service. Represents a row in the &quot;Weather_WeatherForecast&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link WeatherForecastModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WeatherForecastImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastImpl
 * @see WeatherForecast
 * @see WeatherForecastModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class WeatherForecastModelImpl extends BaseModelImpl<WeatherForecast>
	implements WeatherForecastModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a weather forecast model instance should use the {@link WeatherForecast} interface instead.
	 */
	public static final String TABLE_NAME = "Weather_WeatherForecast";
	public static final Object[][] TABLE_COLUMNS = {
			{ "weatherForecastId", Types.BIGINT },
			{ "cityName", Types.VARCHAR },
			{ "resourceName", Types.VARCHAR },
			{ "forecastDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("weatherForecastId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("cityName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("resourceName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("forecastDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table Weather_WeatherForecast (weatherForecastId LONG not null primary key,cityName VARCHAR(75) null,resourceName VARCHAR(75) null,forecastDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Weather_WeatherForecast";
	public static final String ORDER_BY_JPQL = " ORDER BY weatherForecast.weatherForecastId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Weather_WeatherForecast.weatherForecastId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(edu.weather.servicebuilder.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.edu.weather.servicebuilder.model.WeatherForecast"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(edu.weather.servicebuilder.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.edu.weather.servicebuilder.model.WeatherForecast"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static WeatherForecast toModel(WeatherForecastSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		WeatherForecast model = new WeatherForecastImpl();

		model.setWeatherForecastId(soapModel.getWeatherForecastId());
		model.setCityName(soapModel.getCityName());
		model.setResourceName(soapModel.getResourceName());
		model.setForecastDate(soapModel.getForecastDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<WeatherForecast> toModels(
		WeatherForecastSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<WeatherForecast> models = new ArrayList<WeatherForecast>(soapModels.length);

		for (WeatherForecastSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_WEATHER_WEATHER_WEATHERFORECAST_NAME =
		"Weather_Weather_WeatherForecast";
	public static final Object[][] MAPPING_TABLE_WEATHER_WEATHER_WEATHERFORECAST_COLUMNS =
		{
			{ "companyId", Types.BIGINT },
			{ "weatherId", Types.BIGINT },
			{ "weatherForecastId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_WEATHER_WEATHER_WEATHERFORECAST_SQL_CREATE =
		"create table Weather_Weather_WeatherForecast (companyId LONG not null,weatherId LONG not null,weatherForecastId LONG not null,primary key (weatherId, weatherForecastId))";
	public static final boolean FINDER_CACHE_ENABLED_WEATHER_WEATHER_WEATHERFORECAST =
		GetterUtil.getBoolean(edu.weather.servicebuilder.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.Weather_Weather_WeatherForecast"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(edu.weather.servicebuilder.service.util.ServiceProps.get(
				"lock.expiration.time.edu.weather.servicebuilder.model.WeatherForecast"));

	public WeatherForecastModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _weatherForecastId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWeatherForecastId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _weatherForecastId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

	@JSON
	@Override
	public long getWeatherForecastId() {
		return _weatherForecastId;
	}

	@Override
	public void setWeatherForecastId(long weatherForecastId) {
		_weatherForecastId = weatherForecastId;
	}

	@JSON
	@Override
	public String getCityName() {
		if (_cityName == null) {
			return StringPool.BLANK;
		}
		else {
			return _cityName;
		}
	}

	@Override
	public void setCityName(String cityName) {
		_cityName = cityName;
	}

	@JSON
	@Override
	public String getResourceName() {
		if (_resourceName == null) {
			return StringPool.BLANK;
		}
		else {
			return _resourceName;
		}
	}

	@Override
	public void setResourceName(String resourceName) {
		_resourceName = resourceName;
	}

	@JSON
	@Override
	public Date getForecastDate() {
		return _forecastDate;
	}

	@Override
	public void setForecastDate(Date forecastDate) {
		_forecastDate = forecastDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			WeatherForecast.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public WeatherForecast toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (WeatherForecast)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		WeatherForecastImpl weatherForecastImpl = new WeatherForecastImpl();

		weatherForecastImpl.setWeatherForecastId(getWeatherForecastId());
		weatherForecastImpl.setCityName(getCityName());
		weatherForecastImpl.setResourceName(getResourceName());
		weatherForecastImpl.setForecastDate(getForecastDate());

		weatherForecastImpl.resetOriginalValues();

		return weatherForecastImpl;
	}

	@Override
	public int compareTo(WeatherForecast weatherForecast) {
		long primaryKey = weatherForecast.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WeatherForecast)) {
			return false;
		}

		WeatherForecast weatherForecast = (WeatherForecast)obj;

		long primaryKey = weatherForecast.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<WeatherForecast> toCacheModel() {
		WeatherForecastCacheModel weatherForecastCacheModel = new WeatherForecastCacheModel();

		weatherForecastCacheModel.weatherForecastId = getWeatherForecastId();

		weatherForecastCacheModel.cityName = getCityName();

		String cityName = weatherForecastCacheModel.cityName;

		if ((cityName != null) && (cityName.length() == 0)) {
			weatherForecastCacheModel.cityName = null;
		}

		weatherForecastCacheModel.resourceName = getResourceName();

		String resourceName = weatherForecastCacheModel.resourceName;

		if ((resourceName != null) && (resourceName.length() == 0)) {
			weatherForecastCacheModel.resourceName = null;
		}

		Date forecastDate = getForecastDate();

		if (forecastDate != null) {
			weatherForecastCacheModel.forecastDate = forecastDate.getTime();
		}
		else {
			weatherForecastCacheModel.forecastDate = Long.MIN_VALUE;
		}

		return weatherForecastCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{weatherForecastId=");
		sb.append(getWeatherForecastId());
		sb.append(", cityName=");
		sb.append(getCityName());
		sb.append(", resourceName=");
		sb.append(getResourceName());
		sb.append(", forecastDate=");
		sb.append(getForecastDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("edu.weather.servicebuilder.model.WeatherForecast");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>weatherForecastId</column-name><column-value><![CDATA[");
		sb.append(getWeatherForecastId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cityName</column-name><column-value><![CDATA[");
		sb.append(getCityName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceName</column-name><column-value><![CDATA[");
		sb.append(getResourceName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forecastDate</column-name><column-value><![CDATA[");
		sb.append(getForecastDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = WeatherForecast.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			WeatherForecast.class
		};
	private long _weatherForecastId;
	private String _cityName;
	private String _resourceName;
	private Date _forecastDate;
	private WeatherForecast _escapedModel;
}