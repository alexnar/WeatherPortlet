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

package edu.weather.servicebuilder.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import edu.weather.servicebuilder.model.Weather;
import edu.weather.servicebuilder.service.WeatherLocalService;
import edu.weather.servicebuilder.service.persistence.WeatherForecastPersistence;
import edu.weather.servicebuilder.service.persistence.WeatherPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the weather local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link edu.weather.servicebuilder.service.impl.WeatherLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.impl.WeatherLocalServiceImpl
 * @see edu.weather.servicebuilder.service.WeatherLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class WeatherLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements WeatherLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link edu.weather.servicebuilder.service.WeatherLocalServiceUtil} to access the weather local service.
	 */

	/**
	 * Adds the weather to the database. Also notifies the appropriate model listeners.
	 *
	 * @param weather the weather
	 * @return the weather that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Weather addWeather(Weather weather) {
		weather.setNew(true);

		return weatherPersistence.update(weather);
	}

	/**
	 * Creates a new weather with the primary key. Does not add the weather to the database.
	 *
	 * @param weatherId the primary key for the new weather
	 * @return the new weather
	 */
	@Override
	public Weather createWeather(long weatherId) {
		return weatherPersistence.create(weatherId);
	}

	/**
	 * Deletes the weather with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherId the primary key of the weather
	 * @return the weather that was removed
	 * @throws PortalException if a weather with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Weather deleteWeather(long weatherId) throws PortalException {
		return weatherPersistence.remove(weatherId);
	}

	/**
	 * Deletes the weather from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weather the weather
	 * @return the weather that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Weather deleteWeather(Weather weather) {
		return weatherPersistence.remove(weather);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Weather.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return weatherPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return weatherPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return weatherPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return weatherPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return weatherPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Weather fetchWeather(long weatherId) {
		return weatherPersistence.fetchByPrimaryKey(weatherId);
	}

	/**
	 * Returns the weather with the primary key.
	 *
	 * @param weatherId the primary key of the weather
	 * @return the weather
	 * @throws PortalException if a weather with the primary key could not be found
	 */
	@Override
	public Weather getWeather(long weatherId) throws PortalException {
		return weatherPersistence.findByPrimaryKey(weatherId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(weatherLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Weather.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weatherId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(weatherLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Weather.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("weatherId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(weatherLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Weather.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weatherId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return weatherLocalService.deleteWeather((Weather)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return weatherPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the weathers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @return the range of weathers
	 */
	@Override
	public List<Weather> getWeathers(int start, int end) {
		return weatherPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of weathers.
	 *
	 * @return the number of weathers
	 */
	@Override
	public int getWeathersCount() {
		return weatherPersistence.countAll();
	}

	/**
	 * Updates the weather in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param weather the weather
	 * @return the weather that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Weather updateWeather(Weather weather) {
		return weatherPersistence.update(weather);
	}

	/**
	 */
	@Override
	public void addWeatherForecastWeather(long weatherForecastId, long weatherId) {
		weatherForecastPersistence.addWeather(weatherForecastId, weatherId);
	}

	/**
	 */
	@Override
	public void addWeatherForecastWeather(long weatherForecastId,
		Weather weather) {
		weatherForecastPersistence.addWeather(weatherForecastId, weather);
	}

	/**
	 */
	@Override
	public void addWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds) {
		weatherForecastPersistence.addWeathers(weatherForecastId, weatherIds);
	}

	/**
	 */
	@Override
	public void addWeatherForecastWeathers(long weatherForecastId,
		List<Weather> weathers) {
		weatherForecastPersistence.addWeathers(weatherForecastId, weathers);
	}

	/**
	 */
	@Override
	public void clearWeatherForecastWeathers(long weatherForecastId) {
		weatherForecastPersistence.clearWeathers(weatherForecastId);
	}

	/**
	 */
	@Override
	public void deleteWeatherForecastWeather(long weatherForecastId,
		long weatherId) {
		weatherForecastPersistence.removeWeather(weatherForecastId, weatherId);
	}

	/**
	 */
	@Override
	public void deleteWeatherForecastWeather(long weatherForecastId,
		Weather weather) {
		weatherForecastPersistence.removeWeather(weatherForecastId, weather);
	}

	/**
	 */
	@Override
	public void deleteWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds) {
		weatherForecastPersistence.removeWeathers(weatherForecastId, weatherIds);
	}

	/**
	 */
	@Override
	public void deleteWeatherForecastWeathers(long weatherForecastId,
		List<Weather> weathers) {
		weatherForecastPersistence.removeWeathers(weatherForecastId, weathers);
	}

	/**
	 * Returns the weatherForecastIds of the weather forecasts associated with the weather.
	 *
	 * @param weatherId the weatherId of the weather
	 * @return long[] the weatherForecastIds of weather forecasts associated with the weather
	 */
	@Override
	public long[] getWeatherForecastPrimaryKeys(long weatherId) {
		return weatherPersistence.getWeatherForecastPrimaryKeys(weatherId);
	}

	/**
	 */
	@Override
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId) {
		return weatherForecastPersistence.getWeathers(weatherForecastId);
	}

	/**
	 */
	@Override
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId,
		int start, int end) {
		return weatherForecastPersistence.getWeathers(weatherForecastId, start,
			end);
	}

	/**
	 */
	@Override
	public List<Weather> getWeatherForecastWeathers(long weatherForecastId,
		int start, int end, OrderByComparator<Weather> orderByComparator) {
		return weatherForecastPersistence.getWeathers(weatherForecastId, start,
			end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getWeatherForecastWeathersCount(long weatherForecastId) {
		return weatherForecastPersistence.getWeathersSize(weatherForecastId);
	}

	/**
	 */
	@Override
	public boolean hasWeatherForecastWeather(long weatherForecastId,
		long weatherId) {
		return weatherForecastPersistence.containsWeather(weatherForecastId,
			weatherId);
	}

	/**
	 */
	@Override
	public boolean hasWeatherForecastWeathers(long weatherForecastId) {
		return weatherForecastPersistence.containsWeathers(weatherForecastId);
	}

	/**
	 */
	@Override
	public void setWeatherForecastWeathers(long weatherForecastId,
		long[] weatherIds) {
		weatherForecastPersistence.setWeathers(weatherForecastId, weatherIds);
	}

	/**
	 * Returns the weather local service.
	 *
	 * @return the weather local service
	 */
	public WeatherLocalService getWeatherLocalService() {
		return weatherLocalService;
	}

	/**
	 * Sets the weather local service.
	 *
	 * @param weatherLocalService the weather local service
	 */
	public void setWeatherLocalService(WeatherLocalService weatherLocalService) {
		this.weatherLocalService = weatherLocalService;
	}

	/**
	 * Returns the weather persistence.
	 *
	 * @return the weather persistence
	 */
	public WeatherPersistence getWeatherPersistence() {
		return weatherPersistence;
	}

	/**
	 * Sets the weather persistence.
	 *
	 * @param weatherPersistence the weather persistence
	 */
	public void setWeatherPersistence(WeatherPersistence weatherPersistence) {
		this.weatherPersistence = weatherPersistence;
	}

	/**
	 * Returns the weather forecast local service.
	 *
	 * @return the weather forecast local service
	 */
	public edu.weather.servicebuilder.service.WeatherForecastLocalService getWeatherForecastLocalService() {
		return weatherForecastLocalService;
	}

	/**
	 * Sets the weather forecast local service.
	 *
	 * @param weatherForecastLocalService the weather forecast local service
	 */
	public void setWeatherForecastLocalService(
		edu.weather.servicebuilder.service.WeatherForecastLocalService weatherForecastLocalService) {
		this.weatherForecastLocalService = weatherForecastLocalService;
	}

	/**
	 * Returns the weather forecast persistence.
	 *
	 * @return the weather forecast persistence
	 */
	public WeatherForecastPersistence getWeatherForecastPersistence() {
		return weatherForecastPersistence;
	}

	/**
	 * Sets the weather forecast persistence.
	 *
	 * @param weatherForecastPersistence the weather forecast persistence
	 */
	public void setWeatherForecastPersistence(
		WeatherForecastPersistence weatherForecastPersistence) {
		this.weatherForecastPersistence = weatherForecastPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("edu.weather.servicebuilder.model.Weather",
			weatherLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"edu.weather.servicebuilder.model.Weather");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WeatherLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Weather.class;
	}

	protected String getModelClassName() {
		return Weather.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = weatherPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = WeatherLocalService.class)
	protected WeatherLocalService weatherLocalService;
	@BeanReference(type = WeatherPersistence.class)
	protected WeatherPersistence weatherPersistence;
	@BeanReference(type = edu.weather.servicebuilder.service.WeatherForecastLocalService.class)
	protected edu.weather.servicebuilder.service.WeatherForecastLocalService weatherForecastLocalService;
	@BeanReference(type = WeatherForecastPersistence.class)
	protected WeatherForecastPersistence weatherForecastPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}