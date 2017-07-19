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

import edu.weather.servicebuilder.exception.NoSuchWeatherException;
import edu.weather.servicebuilder.model.Weather;
import edu.weather.servicebuilder.model.impl.WeatherImpl;
import edu.weather.servicebuilder.model.impl.WeatherModelImpl;
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
 * The persistence implementation for the weather service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeatherPersistence
 * @see edu.weather.servicebuilder.service.persistence.WeatherUtil
 * @generated
 */
@ProviderType
public class WeatherPersistenceImpl extends BasePersistenceImpl<Weather>
	implements WeatherPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WeatherUtil} to access the weather persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WeatherImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherModelImpl.FINDER_CACHE_ENABLED, WeatherImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherModelImpl.FINDER_CACHE_ENABLED, WeatherImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WeatherPersistenceImpl() {
		setModelClass(Weather.class);
	}

	/**
	 * Caches the weather in the entity cache if it is enabled.
	 *
	 * @param weather the weather
	 */
	@Override
	public void cacheResult(Weather weather) {
		entityCache.putResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherImpl.class, weather.getPrimaryKey(), weather);

		weather.resetOriginalValues();
	}

	/**
	 * Caches the weathers in the entity cache if it is enabled.
	 *
	 * @param weathers the weathers
	 */
	@Override
	public void cacheResult(List<Weather> weathers) {
		for (Weather weather : weathers) {
			if (entityCache.getResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
						WeatherImpl.class, weather.getPrimaryKey()) == null) {
				cacheResult(weather);
			}
			else {
				weather.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all weathers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WeatherImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the weather.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Weather weather) {
		entityCache.removeResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherImpl.class, weather.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Weather> weathers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Weather weather : weathers) {
			entityCache.removeResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
				WeatherImpl.class, weather.getPrimaryKey());
		}
	}

	/**
	 * Creates a new weather with the primary key. Does not add the weather to the database.
	 *
	 * @param weatherId the primary key for the new weather
	 * @return the new weather
	 */
	@Override
	public Weather create(long weatherId) {
		Weather weather = new WeatherImpl();

		weather.setNew(true);
		weather.setPrimaryKey(weatherId);

		return weather;
	}

	/**
	 * Removes the weather with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weatherId the primary key of the weather
	 * @return the weather that was removed
	 * @throws NoSuchWeatherException if a weather with the primary key could not be found
	 */
	@Override
	public Weather remove(long weatherId) throws NoSuchWeatherException {
		return remove((Serializable)weatherId);
	}

	/**
	 * Removes the weather with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the weather
	 * @return the weather that was removed
	 * @throws NoSuchWeatherException if a weather with the primary key could not be found
	 */
	@Override
	public Weather remove(Serializable primaryKey)
		throws NoSuchWeatherException {
		Session session = null;

		try {
			session = openSession();

			Weather weather = (Weather)session.get(WeatherImpl.class, primaryKey);

			if (weather == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWeatherException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(weather);
		}
		catch (NoSuchWeatherException nsee) {
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
	protected Weather removeImpl(Weather weather) {
		weather = toUnwrappedModel(weather);

		weatherToWeatherForecastTableMapper.deleteLeftPrimaryKeyTableMappings(weather.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(weather)) {
				weather = (Weather)session.get(WeatherImpl.class,
						weather.getPrimaryKeyObj());
			}

			if (weather != null) {
				session.delete(weather);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (weather != null) {
			clearCache(weather);
		}

		return weather;
	}

	@Override
	public Weather updateImpl(Weather weather) {
		weather = toUnwrappedModel(weather);

		boolean isNew = weather.isNew();

		Session session = null;

		try {
			session = openSession();

			if (weather.isNew()) {
				session.save(weather);

				weather.setNew(false);
			}
			else {
				weather = (Weather)session.merge(weather);
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

		entityCache.putResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
			WeatherImpl.class, weather.getPrimaryKey(), weather, false);

		weather.resetOriginalValues();

		return weather;
	}

	protected Weather toUnwrappedModel(Weather weather) {
		if (weather instanceof WeatherImpl) {
			return weather;
		}

		WeatherImpl weatherImpl = new WeatherImpl();

		weatherImpl.setNew(weather.isNew());
		weatherImpl.setPrimaryKey(weather.getPrimaryKey());

		weatherImpl.setWeatherId(weather.getWeatherId());
		weatherImpl.setTemperature(weather.getTemperature());
		weatherImpl.setDescription(weather.getDescription());

		return weatherImpl;
	}

	/**
	 * Returns the weather with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the weather
	 * @return the weather
	 * @throws NoSuchWeatherException if a weather with the primary key could not be found
	 */
	@Override
	public Weather findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWeatherException {
		Weather weather = fetchByPrimaryKey(primaryKey);

		if (weather == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWeatherException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return weather;
	}

	/**
	 * Returns the weather with the primary key or throws a {@link NoSuchWeatherException} if it could not be found.
	 *
	 * @param weatherId the primary key of the weather
	 * @return the weather
	 * @throws NoSuchWeatherException if a weather with the primary key could not be found
	 */
	@Override
	public Weather findByPrimaryKey(long weatherId)
		throws NoSuchWeatherException {
		return findByPrimaryKey((Serializable)weatherId);
	}

	/**
	 * Returns the weather with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the weather
	 * @return the weather, or <code>null</code> if a weather with the primary key could not be found
	 */
	@Override
	public Weather fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
				WeatherImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Weather weather = (Weather)serializable;

		if (weather == null) {
			Session session = null;

			try {
				session = openSession();

				weather = (Weather)session.get(WeatherImpl.class, primaryKey);

				if (weather != null) {
					cacheResult(weather);
				}
				else {
					entityCache.putResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
						WeatherImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
					WeatherImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return weather;
	}

	/**
	 * Returns the weather with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param weatherId the primary key of the weather
	 * @return the weather, or <code>null</code> if a weather with the primary key could not be found
	 */
	@Override
	public Weather fetchByPrimaryKey(long weatherId) {
		return fetchByPrimaryKey((Serializable)weatherId);
	}

	@Override
	public Map<Serializable, Weather> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Weather> map = new HashMap<Serializable, Weather>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Weather weather = fetchByPrimaryKey(primaryKey);

			if (weather != null) {
				map.put(primaryKey, weather);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
					WeatherImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Weather)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WEATHER_WHERE_PKS_IN);

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

			for (Weather weather : (List<Weather>)q.list()) {
				map.put(weather.getPrimaryKeyObj(), weather);

				cacheResult(weather);

				uncachedPrimaryKeys.remove(weather.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WeatherModelImpl.ENTITY_CACHE_ENABLED,
					WeatherImpl.class, primaryKey, nullModel);
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
	 * Returns all the weathers.
	 *
	 * @return the weathers
	 */
	@Override
	public List<Weather> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the weathers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @return the range of weathers
	 */
	@Override
	public List<Weather> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the weathers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of weathers
	 */
	@Override
	public List<Weather> findAll(int start, int end,
		OrderByComparator<Weather> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the weathers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of weathers
	 */
	@Override
	public List<Weather> findAll(int start, int end,
		OrderByComparator<Weather> orderByComparator, boolean retrieveFromCache) {
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

		List<Weather> list = null;

		if (retrieveFromCache) {
			list = (List<Weather>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WEATHER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEATHER;

				if (pagination) {
					sql = sql.concat(WeatherModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Weather>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Weather>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the weathers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Weather weather : findAll()) {
			remove(weather);
		}
	}

	/**
	 * Returns the number of weathers.
	 *
	 * @return the number of weathers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEATHER);

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
	 * Returns the primaryKeys of weather forecasts associated with the weather.
	 *
	 * @param pk the primary key of the weather
	 * @return long[] of the primaryKeys of weather forecasts associated with the weather
	 */
	@Override
	public long[] getWeatherForecastPrimaryKeys(long pk) {
		long[] pks = weatherToWeatherForecastTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the weather forecasts associated with the weather.
	 *
	 * @param pk the primary key of the weather
	 * @return the weather forecasts associated with the weather
	 */
	@Override
	public List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk) {
		return getWeatherForecasts(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the weather forecasts associated with the weather.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the weather
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @return the range of weather forecasts associated with the weather
	 */
	@Override
	public List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk, int start, int end) {
		return getWeatherForecasts(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the weather forecasts associated with the weather.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WeatherModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the weather
	 * @param start the lower bound of the range of weathers
	 * @param end the upper bound of the range of weathers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of weather forecasts associated with the weather
	 */
	@Override
	public List<edu.weather.servicebuilder.model.WeatherForecast> getWeatherForecasts(
		long pk, int start, int end,
		OrderByComparator<edu.weather.servicebuilder.model.WeatherForecast> orderByComparator) {
		return weatherToWeatherForecastTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of weather forecasts associated with the weather.
	 *
	 * @param pk the primary key of the weather
	 * @return the number of weather forecasts associated with the weather
	 */
	@Override
	public int getWeatherForecastsSize(long pk) {
		long[] pks = weatherToWeatherForecastTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the weather forecast is associated with the weather.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPK the primary key of the weather forecast
	 * @return <code>true</code> if the weather forecast is associated with the weather; <code>false</code> otherwise
	 */
	@Override
	public boolean containsWeatherForecast(long pk, long weatherForecastPK) {
		return weatherToWeatherForecastTableMapper.containsTableMapping(pk,
			weatherForecastPK);
	}

	/**
	 * Returns <code>true</code> if the weather has any weather forecasts associated with it.
	 *
	 * @param pk the primary key of the weather to check for associations with weather forecasts
	 * @return <code>true</code> if the weather has any weather forecasts associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsWeatherForecasts(long pk) {
		if (getWeatherForecastsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPK the primary key of the weather forecast
	 */
	@Override
	public void addWeatherForecast(long pk, long weatherForecastPK) {
		Weather weather = fetchByPrimaryKey(pk);

		if (weather == null) {
			weatherToWeatherForecastTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherForecastPK);
		}
		else {
			weatherToWeatherForecastTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherForecastPK);
		}
	}

	/**
	 * Adds an association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecast the weather forecast
	 */
	@Override
	public void addWeatherForecast(long pk,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		Weather weather = fetchByPrimaryKey(pk);

		if (weather == null) {
			weatherToWeatherForecastTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherForecast.getPrimaryKey());
		}
		else {
			weatherToWeatherForecastTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, weatherForecast.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPKs the primary keys of the weather forecasts
	 */
	@Override
	public void addWeatherForecasts(long pk, long[] weatherForecastPKs) {
		long companyId = 0;

		Weather weather = fetchByPrimaryKey(pk);

		if (weather == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = companyProvider.getCompanyId();
		}

		weatherToWeatherForecastTableMapper.addTableMappings(companyId, pk,
			weatherForecastPKs);
	}

	/**
	 * Adds an association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecasts the weather forecasts
	 */
	@Override
	public void addWeatherForecasts(long pk,
		List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		addWeatherForecasts(pk,
			ListUtil.toLongArray(weatherForecasts,
				edu.weather.servicebuilder.model.WeatherForecast.WEATHER_FORECAST_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the weather and its weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather to clear the associated weather forecasts from
	 */
	@Override
	public void clearWeatherForecasts(long pk) {
		weatherToWeatherForecastTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPK the primary key of the weather forecast
	 */
	@Override
	public void removeWeatherForecast(long pk, long weatherForecastPK) {
		weatherToWeatherForecastTableMapper.deleteTableMapping(pk,
			weatherForecastPK);
	}

	/**
	 * Removes the association between the weather and the weather forecast. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecast the weather forecast
	 */
	@Override
	public void removeWeatherForecast(long pk,
		edu.weather.servicebuilder.model.WeatherForecast weatherForecast) {
		weatherToWeatherForecastTableMapper.deleteTableMapping(pk,
			weatherForecast.getPrimaryKey());
	}

	/**
	 * Removes the association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPKs the primary keys of the weather forecasts
	 */
	@Override
	public void removeWeatherForecasts(long pk, long[] weatherForecastPKs) {
		weatherToWeatherForecastTableMapper.deleteTableMappings(pk,
			weatherForecastPKs);
	}

	/**
	 * Removes the association between the weather and the weather forecasts. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecasts the weather forecasts
	 */
	@Override
	public void removeWeatherForecasts(long pk,
		List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		removeWeatherForecasts(pk,
			ListUtil.toLongArray(weatherForecasts,
				edu.weather.servicebuilder.model.WeatherForecast.WEATHER_FORECAST_ID_ACCESSOR));
	}

	/**
	 * Sets the weather forecasts associated with the weather, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecastPKs the primary keys of the weather forecasts to be associated with the weather
	 */
	@Override
	public void setWeatherForecasts(long pk, long[] weatherForecastPKs) {
		Set<Long> newWeatherForecastPKsSet = SetUtil.fromArray(weatherForecastPKs);
		Set<Long> oldWeatherForecastPKsSet = SetUtil.fromArray(weatherToWeatherForecastTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeWeatherForecastPKsSet = new HashSet<Long>(oldWeatherForecastPKsSet);

		removeWeatherForecastPKsSet.removeAll(newWeatherForecastPKsSet);

		weatherToWeatherForecastTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeWeatherForecastPKsSet));

		newWeatherForecastPKsSet.removeAll(oldWeatherForecastPKsSet);

		long companyId = 0;

		Weather weather = fetchByPrimaryKey(pk);

		if (weather == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = companyProvider.getCompanyId();
		}

		weatherToWeatherForecastTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newWeatherForecastPKsSet));
	}

	/**
	 * Sets the weather forecasts associated with the weather, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the weather
	 * @param weatherForecasts the weather forecasts to be associated with the weather
	 */
	@Override
	public void setWeatherForecasts(long pk,
		List<edu.weather.servicebuilder.model.WeatherForecast> weatherForecasts) {
		try {
			long[] weatherForecastPKs = new long[weatherForecasts.size()];

			for (int i = 0; i < weatherForecasts.size(); i++) {
				edu.weather.servicebuilder.model.WeatherForecast weatherForecast =
					weatherForecasts.get(i);

				weatherForecastPKs[i] = weatherForecast.getPrimaryKey();
			}

			setWeatherForecasts(pk, weatherForecastPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return WeatherModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the weather persistence.
	 */
	public void afterPropertiesSet() {
		weatherToWeatherForecastTableMapper = TableMapperFactory.getTableMapper("Weather_Weather_WeatherForecast",
				"companyId", "weatherId", "weatherForecastId", this,
				weatherForecastPersistence);
	}

	public void destroy() {
		entityCache.removeCache(WeatherImpl.class.getName());
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
	@BeanReference(type = WeatherForecastPersistence.class)
	protected WeatherForecastPersistence weatherForecastPersistence;
	protected TableMapper<Weather, edu.weather.servicebuilder.model.WeatherForecast> weatherToWeatherForecastTableMapper;
	private static final String _SQL_SELECT_WEATHER = "SELECT weather FROM Weather weather";
	private static final String _SQL_SELECT_WEATHER_WHERE_PKS_IN = "SELECT weather FROM Weather weather WHERE weatherId IN (";
	private static final String _SQL_COUNT_WEATHER = "SELECT COUNT(weather) FROM Weather weather";
	private static final String _ORDER_BY_ENTITY_ALIAS = "weather.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Weather exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(WeatherPersistenceImpl.class);
}