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

package edu.weather.servicebuilder.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import edu.weather.servicebuilder.exception.NoSuchForecastException;
import edu.weather.servicebuilder.model.WeatherForecast;
import edu.weather.servicebuilder.model.impl.WeatherForecastImpl;
import edu.weather.servicebuilder.model.impl.WeatherForecastModelImpl;
import edu.weather.servicebuilder.service.persistence.WeatherForecastPersistence;
import edu.weather.servicebuilder.service.persistence.WeatherPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the weather forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeatherForecastPersistence
 * @see edu.weather.servicebuilder.service.persistence.WeatherForecastUtil
 * @generated
 */
@ProviderType
public class WeatherForecastPersistenceImpl extends BasePersistenceImpl<WeatherForecast>
	implements WeatherForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WeatherForecastUtil} to access the weather forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WeatherForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastModelImpl.FINDER_CACHE_ENABLED,
			WeatherForecastImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastModelImpl.FINDER_CACHE_ENABLED,
			WeatherForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WeatherForecastPersistenceImpl() {
		setModelClass(WeatherForecast.class);
	}

	/**
	 * Caches the weather forecast in the entity cache if it is enabled.
	 *
	 * @param weatherForecast the weather forecast
	 */
	@Override
	public void cacheResult(WeatherForecast weatherForecast) {
		entityCache.putResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastImpl.class, weatherForecast.getPrimaryKey(),
			weatherForecast);

		weatherForecast.resetOriginalValues();
	}

	/**
	 * Caches the weather forecasts in the entity cache if it is enabled.
	 *
	 * @param weatherForecasts the weather forecasts
	 */
	@Override
	public void cacheResult(List<WeatherForecast> weatherForecasts) {
		for (WeatherForecast weatherForecast : weatherForecasts) {
			if (entityCache.getResult(
						WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
						WeatherForecastImpl.class,
						weatherForecast.getPrimaryKey()) == null) {
				cacheResult(weatherForecast);
			}
			else {
				weatherForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all weather forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WeatherForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the weather forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WeatherForecast weatherForecast) {
		entityCache.removeResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastImpl.class, weatherForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WeatherForecast> weatherForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WeatherForecast weatherForecast : weatherForecasts) {
			entityCache.removeResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
				WeatherForecastImpl.class, weatherForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new weather forecast with the primary key. Does not add the weather forecast to the database.
	 *
	 * @param weatherForecastId the primary key for the new weather forecast
	 * @return the new weather forecast
	 */
	@Override
	public WeatherForecast create(long weatherForecastId) {
		WeatherForecast weatherForecast = new WeatherForecastImpl();

		weatherForecast.setNew(true);
		weatherForecast.setPrimaryKey(weatherForecastId);

		return weatherForecast;
	}

	/**
	 * Removes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherForecastId the primary key of the weather forecast
	 * @return the weather forecast that was removed
	 * @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast remove(long weatherForecastId)
		throws NoSuchForecastException {
		return remove((Serializable)weatherForecastId);
	}

	/**
	 * Removes the weather forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the weather forecast
	 * @return the weather forecast that was removed
	 * @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast remove(Serializable primaryKey)
		throws NoSuchForecastException {
		Session session = null;

		try {
			session = openSession();

			WeatherForecast weatherForecast = (WeatherForecast)session.get(WeatherForecastImpl.class,
					primaryKey);

			if (weatherForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(weatherForecast);
		}
		catch (NoSuchForecastException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected WeatherForecast removeImpl(WeatherForecast weatherForecast) {
		weatherForecast = toUnwrappedModel(weatherForecast);

		weatherForecastToWeatherTableMapper.deleteLeftPrimaryKeyTableMappings(weatherForecast.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(weatherForecast)) {
				weatherForecast = (WeatherForecast)session.get(WeatherForecastImpl.class,
						weatherForecast.getPrimaryKeyObj());
			}

			if (weatherForecast != null) {
				session.delete(weatherForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (weatherForecast != null) {
			clearCache(weatherForecast);
		}

		return weatherForecast;
	}

	@Override
	public WeatherForecast updateImpl(WeatherForecast weatherForecast) {
		weatherForecast = toUnwrappedModel(weatherForecast);

		boolean isNew = weatherForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (weatherForecast.isNew()) {
				session.save(weatherForecast);

				weatherForecast.setNew(false);
			}
			else {
				weatherForecast = (WeatherForecast)session.merge(weatherForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
			WeatherForecastImpl.class, weatherForecast.getPrimaryKey(),
			weatherForecast, false);

		weatherForecast.resetOriginalValues();

		return weatherForecast;
	}

	protected WeatherForecast toUnwrappedModel(WeatherForecast weatherForecast) {
		if (weatherForecast instanceof WeatherForecastImpl) {
			return weatherForecast;
		}

		WeatherForecastImpl weatherForecastImpl = new WeatherForecastImpl();

		weatherForecastImpl.setNew(weatherForecast.isNew());
		weatherForecastImpl.setPrimaryKey(weatherForecast.getPrimaryKey());

		weatherForecastImpl.setWeatherForecastId(weatherForecast.getWeatherForecastId());
		weatherForecastImpl.setCityName(weatherForecast.getCityName());
		weatherForecastImpl.setResourceName(weatherForecast.getResourceName());
		weatherForecastImpl.setForecastDate(weatherForecast.getForecastDate());

		return weatherForecastImpl;
	}

	/**
	 * Returns the weather forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the weather forecast
	 * @return the weather forecast
	 * @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchForecastException {
		WeatherForecast weatherForecast = fetchByPrimaryKey(primaryKey);

		if (weatherForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return weatherForecast;
	}

	/**
	 * Returns the weather forecast with the primary key or throws a {@link NoSuchForecastException} if it could not be found.
	 *
	 * @param weatherForecastId the primary key of the weather forecast
	 * @return the weather forecast
	 * @throws NoSuchForecastException if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast findByPrimaryKey(long weatherForecastId)
		throws NoSuchForecastException {
		return findByPrimaryKey((Serializable)weatherForecastId);
	}

	/**
	 * Returns the weather forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the weather forecast
	 * @return the weather forecast, or <code>null</code> if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
				WeatherForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WeatherForecast weatherForecast = (WeatherForecast)serializable;

		if (weatherForecast == null) {
			Session session = null;

			try {
				session = openSession();

				weatherForecast = (WeatherForecast)session.get(WeatherForecastImpl.class,
						primaryKey);

				if (weatherForecast != null) {
					cacheResult(weatherForecast);
				}
				else {
					entityCache.putResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
						WeatherForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
					WeatherForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return weatherForecast;
	}

	/**
	 * Returns the weather forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param weatherForecastId the primary key of the weather forecast
	 * @return the weather forecast, or <code>null</code> if a weather forecast with the primary key could not be found
	 */
	@Override
	public WeatherForecast fetchByPrimaryKey(long weatherForecastId) {
		return fetchByPrimaryKey((Serializable)weatherForecastId);
	}

	@Override
	public Map<Serializable, WeatherForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WeatherForecast> map = new HashMap<Serializable, WeatherForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WeatherForecast weatherForecast = fetchByPrimaryKey(primaryKey);

			if (weatherForecast != null) {
				map.put(primaryKey, weatherForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
					WeatherForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WeatherForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WEATHERFORECAST_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (WeatherForecast weatherForecast : (List<WeatherForecast>)q.list()) {
				map.put(weatherForecast.getPrimaryKeyObj(), weatherForecast);

				cacheResult(weatherForecast);

				uncachedPrimaryKeys.remove(weatherForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WeatherForecastModelImpl.ENTITY_CACHE_ENABLED,
					WeatherForecastImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the weather forecasts.
	 *
	 * @return the weather forecasts
	 */
	@Override
	public List<WeatherForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the weather forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @return the range of weather forecasts
	 */
	@Override
	public List<WeatherForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the weather forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of weather forecasts
	 */
	@Override
	public List<WeatherForecast> findAll(int start, int end,
		OrderByComparator<WeatherForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the weather forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of weather forecasts
	 */
	@Override
	public List<WeatherForecast> findAll(int start, int end,
		OrderByComparator<WeatherForecast> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WeatherForecast> list = null;

		if (retrieveFromCache) {
			list = (List<WeatherForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WEATHERFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEATHERFORECAST;

				if (pagination) {
					sql = sql.concat(WeatherForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WeatherForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WeatherForecast>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the weather forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WeatherForecast weatherForecast : findAll()) {
			remove(weatherForecast);
		}
	}

	/**
	 * Returns the number of weather forecasts.
	 *
	 * @return the number of weather forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEATHERFORECAST);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the primaryKeys of weathers associated with the weather forecast.
	 *
	 * @param pk the primary key of the weather forecast
	 * @return long[] of the primaryKeys of weathers associated with the weather forecast
	 */
	@Override
	public long[] getWeatherPrimaryKeys(long pk) {
		long[] pks = weatherForecastToWeatherTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the weathers associated with the weather forecast.
	 *
	 * @param pk the primary key of the weather forecast
	 * @return the weathers associated with the weather forecast
	 */
	@Override
	public List<edu.weather.servicebuilder.model.Weather> getWeathers(long pk) {
		return getWeathers(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the weathers associated with the weather forecast.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the weather forecast
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @return the range of weathers associated with the weather forecast
	 */
	@Override
	public List<edu.weather.servicebuilder.model.Weather> getWeathers(long pk,
		int start, int end) {
		return getWeathers(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the weathers associated with the weather forecast.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the weather forecast
	 * @param start the lower bound of the range of weather forecasts
	 * @param end the upper bound of the range of weather forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of weathers associated with the weather forecast
	 */
	@Override
	public List<edu.weather.servicebuilder.model.Weather> getWeathers(long pk,
		int start, int end,
		OrderByComparator<edu.weather.servicebuilder.model.Weather> orderByComparator) {
		return weatherForecastToWeatherTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of weathers associated with the weather forecast.
	 *
	 * @param pk the primary key of the weather forecast
	 * @return the number of weathers associated with the weather forecast
	 */
	@Override
	public int getWeathersSize(long pk) {
		long[] pks = weatherForecastToWeatherTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the weather is associated with the weather forecast.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPK the primary key of the weather
	 * @return <code>true</code> if the weather is associated with the weather forecast; <code>false</code> otherwise
	 */
	@Override
	public boolean containsWeather(long pk, long weatherPK) {
		return weatherForecastToWeatherTableMapper.containsTableMapping(pk,
			weatherPK);
	}

	/**
	 * Returns <code>true</code> if the weather forecast has any weathers associated with it.
	 *
	 * @param pk the primary key of the weather forecast to check for associations with weathers
	 * @return <code>true</code> if the weather forecast has any weathers associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsWeathers(long pk) {
		if (getWeathersSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPK the primary key of the weather
	 */
	@Override
	public void addWeather(long pk, long weatherPK) {
		WeatherForecast weatherForecast = fetchByPrimaryKey(pk);

		if (weatherForecast == null) {
			weatherForecastToWeatherTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherPK);
		}
		else {
			weatherForecastToWeatherTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherPK);
		}
	}

	/**
	 * Adds an association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weather the weather
	 */
	@Override
	public void addWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather) {
		WeatherForecast weatherForecast = fetchByPrimaryKey(pk);

		if (weatherForecast == null) {
			weatherForecastToWeatherTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weather.getPrimaryKey());
		}
		else {
			weatherForecastToWeatherTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weather.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPKs the primary keys of the weathers
	 */
	@Override
	public void addWeathers(long pk, long[] weatherPKs) {
		long companyId = 0;

		WeatherForecast weatherForecast = fetchByPrimaryKey(pk);

		if (weatherForecast == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = companyProvider.getCompanyId();
		}

		weatherForecastToWeatherTableMapper.addTableMappings(companyId, pk,
			weatherPKs);
	}

	/**
	 * Adds an association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weathers the weathers
	 */
	@Override
	public void addWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		addWeathers(pk,
			ListUtil.toLongArray(weathers,
				edu.weather.servicebuilder.model.Weather.WEATHER_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the weather forecast and its weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast to clear the associated weathers from
	 */
	@Override
	public void clearWeathers(long pk) {
		weatherForecastToWeatherTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPK the primary key of the weather
	 */
	@Override
	public void removeWeather(long pk, long weatherPK) {
		weatherForecastToWeatherTableMapper.deleteTableMapping(pk, weatherPK);
	}

	/**
	 * Removes the association between the weather forecast and the weather. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weather the weather
	 */
	@Override
	public void removeWeather(long pk,
		edu.weather.servicebuilder.model.Weather weather) {
		weatherForecastToWeatherTableMapper.deleteTableMapping(pk,
			weather.getPrimaryKey());
	}

	/**
	 * Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPKs the primary keys of the weathers
	 */
	@Override
	public void removeWeathers(long pk, long[] weatherPKs) {
		weatherForecastToWeatherTableMapper.deleteTableMappings(pk, weatherPKs);
	}

	/**
	 * Removes the association between the weather forecast and the weathers. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weathers the weathers
	 */
	@Override
	public void removeWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		removeWeathers(pk,
			ListUtil.toLongArray(weathers,
				edu.weather.servicebuilder.model.Weather.WEATHER_ID_ACCESSOR));
	}

	/**
	 * Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weatherPKs the primary keys of the weathers to be associated with the weather forecast
	 */
	@Override
	public void setWeathers(long pk, long[] weatherPKs) {
		Set<Long> newWeatherPKsSet = SetUtil.fromArray(weatherPKs);
		Set<Long> oldWeatherPKsSet = SetUtil.fromArray(weatherForecastToWeatherTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeWeatherPKsSet = new HashSet<Long>(oldWeatherPKsSet);

		removeWeatherPKsSet.removeAll(newWeatherPKsSet);

		weatherForecastToWeatherTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeWeatherPKsSet));

		newWeatherPKsSet.removeAll(oldWeatherPKsSet);

		long companyId = 0;

		WeatherForecast weatherForecast = fetchByPrimaryKey(pk);

		if (weatherForecast == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = companyProvider.getCompanyId();
		}

		weatherForecastToWeatherTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newWeatherPKsSet));
	}

	/**
	 * Sets the weathers associated with the weather forecast, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather forecast
	 * @param weathers the weathers to be associated with the weather forecast
	 */
	@Override
	public void setWeathers(long pk,
		List<edu.weather.servicebuilder.model.Weather> weathers) {
		try {
			long[] weatherPKs = new long[weathers.size()];

			for (int i = 0; i < weathers.size(); i++) {
				edu.weather.servicebuilder.model.Weather weather = weathers.get(i);

				weatherPKs[i] = weather.getPrimaryKey();
			}

			setWeathers(pk, weatherPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return WeatherForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the weather forecast persistence.
	 */
	public void afterPropertiesSet() {
		weatherForecastToWeatherTableMapper = TableMapperFactory.getTableMapper("Weather_Weather_WeatherForecast",
				"companyId", "weatherForecastId", "weatherId", this,
				weatherPersistence);
	}

	public void destroy() {
		entityCache.removeCache(WeatherForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("Weather_Weather_WeatherForecast");
	}

	@ServiceReference(type = CompanyProvider.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = WeatherPersistence.class)
	protected WeatherPersistence weatherPersistence;
	protected TableMapper<WeatherForecast, edu.weather.servicebuilder.model.Weather> weatherForecastToWeatherTableMapper;
	private static final String _SQL_SELECT_WEATHERFORECAST = "SELECT weatherForecast FROM WeatherForecast weatherForecast";
	private static final String _SQL_SELECT_WEATHERFORECAST_WHERE_PKS_IN = "SELECT weatherForecast FROM WeatherForecast weatherForecast WHERE weatherForecastId IN (";
	private static final String _SQL_COUNT_WEATHERFORECAST = "SELECT COUNT(weatherForecast) FROM WeatherForecast weatherForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "weatherForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WeatherForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WeatherForecastPersistenceImpl.class);
}