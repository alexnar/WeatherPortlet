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

import edu.weather.servicebuilder.model.WeatherForecast;
import edu.weather.servicebuilder.service.WeatherForecastLocalService;
import edu.weather.servicebuilder.service.persistence.WeatherForecastPersistence;
import edu.weather.servicebuilder.service.persistence.WeatherPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the weather forecast local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see edu.weather.servicebuilder.service.impl.WeatherForecastLocalServiceImpl
 * @see edu.weather.servicebuilder.service.WeatherForecastLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class WeatherForecastLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements WeatherForecastLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link edu.weather.servicebuilder.service.WeatherForecastLocalServiceUtil} to access the weather forecast local service.
	 */

	/**
	 * Adds the weather forecast to the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherForecast the weather forecast
	 * @return the weather forecast that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WeatherForecast addWeatherForecast(WeatherForecast weatherForecast) {
		weatherForecast.setNew(true);

		return weatherForecastPersistence.update(weatherForecast);
	}

	/**
	 * Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	 *
	 * @param weatherForecastId the primary key for the new weather forecast
	 * @return the new weather forecast
	 */
	@Override
	public WeatherForecast createWeatherForecast(long weatherForecastId) {
		return weatherForecastPersistence.create(weatherForecastId);
	}

	/**
	 * Deletes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherForecastId the primary key of the weather forecast
	 * @return the weather forecast that was removed
	 * @throws PortalException if a weather forecast with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WeatherForecast deleteWeatherForecast(long weatherForecastId)
		throws PortalException {
		return weatherForecastPersistence.remove(weatherForecastId);
	}

	/**
	 * Deletes the weather forecast from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherForecast the weather forecast
	 * @return the weather forecast that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WeatherForecast deleteWeatherForecast(
		WeatherForecast weatherForecast) {
		return weatherForecastPersistence.remove(weatherForecast);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(WeatherForecast.class,
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
		return weatherForecastPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return weatherForecastPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return weatherForecastPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return weatherForecastPersistence.countWithDynamicQuery(dynamicQuery);
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
		return weatherForecastPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public WeatherForecast fetchWeatherForecast(long weatherForecastId) {
		return weatherForecastPersistence.fetchByPrimaryKey(weatherForecastId);
	}

	/**
	 * Returns the weather forecast with the primary key.
	 *
	 * @param weatherForecastId the primary key of the weather forecast
	 * @return the weather forecast
	 * @throws PortalException if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast getWeatherForecast(long weatherForecastId)
		throws PortalException {
		return weatherForecastPersistence.findByPrimaryKey(weatherForecastId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(weatherForecastLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WeatherForecast.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weatherForecastId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(weatherForecastLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(WeatherForecast.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"weatherForecastId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(weatherForecastLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WeatherForecast.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("weatherForecastId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return weatherForecastLocalService.deleteWeatherForecast((WeatherForecast)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return weatherForecastPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the weather forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @return the range of weather forecasts
	 */
	@Override
	public List<WeatherForecast> getWeatherForecasts(int start, int end) {
		return weatherForecastPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of weather forecasts.
	 *
	 * @return the number of weather forecasts
	 */
	@Override
	public int getWeatherForecastsCount() {
		return weatherForecastPersistence.countAll();
	}

	/**
	 * Updates the weather forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param weatherForecast the weather forecast
	 * @return the weather forecast that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WeatherForecast updateWeatherForecast(
		WeatherForecast weatherForecast) {
		return weatherForecastPersistence.update(weatherForecast);
	}

	/**
	 */
	@Override
	public void addWeatherWeatherForecast(long weatherId, long weatherForecastId) {
		weatherPersistence.addWeatherForecast(weatherId, weatherForecastId);
	}

	/**
	 */
	@Override
	public void addWeatherWeatherForecast(long weatherId,
		WeatherForecast weatherForecast) {
		weatherPersistence.addWeatherForecast(weatherId, weatherForecast);
	}

	/**
	 */
	@Override
	public void addWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		weatherPersistence.addWeatherForecasts(weatherId, weatherForecastIds);
	}

	/**
	 */
	@Override
	public void addWeatherWeatherForecasts(long weatherId,
		List<WeatherForecast> weatherForecasts) {
		weatherPersistence.addWeatherForecasts(weatherId, weatherForecasts);
	}

	/**
	 */
	@Override
	public void clearWeatherWeatherForecasts(long weatherId) {
		weatherPersistence.clearWeatherForecasts(weatherId);
	}

	/**
	 */
	@Override
	public void deleteWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		weatherPersistence.removeWeatherForecast(weatherId, weatherForecastId);
	}

	/**
	 */
	@Override
	public void deleteWeatherWeatherForecast(long weatherId,
		WeatherForecast weatherForecast) {
		weatherPersistence.removeWeatherForecast(weatherId, weatherForecast);
	}

	/**
	 */
	@Override
	public void deleteWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		weatherPersistence.removeWeatherForecasts(weatherId, weatherForecastIds);
	}

	/**
	 */
	@Override
	public void deleteWeatherWeatherForecasts(long weatherId,
		List<WeatherForecast> weatherForecasts) {
		weatherPersistence.removeWeatherForecasts(weatherId, weatherForecasts);
	}

	/**
	 * Returns the weatherIds of the weathers associated with the weather forecast.
	 *
	 * @param weatherForecastId the weatherForecastId of the weather forecast
	 * @return long[] the weatherIds of weathers associated with the weather forecast
	 */
	@Override
	public long[] getWeatherPrimaryKeys(long weatherForecastId) {
		return weatherForecastPersistence.getWeatherPrimaryKeys(weatherForecastId);
	}

	/**
	 */
	@Override
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId) {
		return weatherPersistence.getWeatherForecasts(weatherId);
	}

	/**
	 */
	@Override
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId,
		int start, int end) {
		return weatherPersistence.getWeatherForecasts(weatherId, start, end);
	}

	/**
	 */
	@Override
	public List<WeatherForecast> getWeatherWeatherForecasts(long weatherId,
		int start, int end, OrderByComparator<WeatherForecast> orderByComparator) {
		return weatherPersistence.getWeatherForecasts(weatherId, start, end,
			orderByComparator);
	}

	/**
	 */
	@Override
	public int getWeatherWeatherForecastsCount(long weatherId) {
		return weatherPersistence.getWeatherForecastsSize(weatherId);
	}

	/**
	 */
	@Override
	public boolean hasWeatherWeatherForecast(long weatherId,
		long weatherForecastId) {
		return weatherPersistence.containsWeatherForecast(weatherId,
			weatherForecastId);
	}

	/**
	 */
	@Override
	public boolean hasWeatherWeatherForecasts(long weatherId) {
		return weatherPersistence.containsWeatherForecasts(weatherId);
	}

	/**
	 */
	@Override
	public void setWeatherWeatherForecasts(long weatherId,
		long[] weatherForecastIds) {
		weatherPersistence.setWeatherForecasts(weatherId, weatherForecastIds);
	}

	/**
	 * Returns the weather local service.
	 *
	 * @return the weather local service
	 */
	public edu.weather.servicebuilder.service.WeatherLocalService getWeatherLocalService() {
		return weatherLocalService;
	}

	/**
	 * Sets the weather local service.
	 *
	 * @param weatherLocalService the weather local service
	 */
	public void setWeatherLocalService(
		edu.weather.servicebuilder.service.WeatherLocalService weatherLocalService) {
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
	public WeatherForecastLocalService getWeatherForecastLocalService() {
		return weatherForecastLocalService;
	}

	/**
	 * Sets the weather forecast local service.
	 *
	 * @param weatherForecastLocalService the weather forecast local service
	 */
	public void setWeatherForecastLocalService(
		WeatherForecastLocalService weatherForecastLocalService) {
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
		persistedModelLocalServiceRegistry.register("edu.weather.servicebuilder.model.WeatherForecast",
			weatherForecastLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"edu.weather.servicebuilder.model.WeatherForecast");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WeatherForecastLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WeatherForecast.class;
	}

	protected String getModelClassName() {
		return WeatherForecast.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = weatherForecastPersistence.getDataSource();

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

	@BeanReference(type = edu.weather.servicebuilder.service.WeatherLocalService.class)
	protected edu.weather.servicebuilder.service.WeatherLocalService weatherLocalService;
	@BeanReference(type = WeatherPersistence.class)
	protected WeatherPersistence weatherPersistence;
	@BeanReference(type = WeatherForecastLocalService.class)
	protected WeatherForecastLocalService weatherForecastLocalService;
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