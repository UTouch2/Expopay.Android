package com.android.kechong.lib.cache.manager;

import android.support.v4.util.LruCache;

import com.android.kechong.lib.cache.CacheEntity;
import com.android.kechong.lib.cache.ConfigCacheModel;
import com.android.kechong.lib.cache.ConfigCacheTime;

public abstract class AbsMemoryCacheManager implements ICacheManager {

	// 硬缓存
	protected LruCache<String, CacheEntity> sHardCache;
	boolean isNetWork;

	protected AbsMemoryCacheManager() {
		super();
	}

	protected boolean putData(String key, Object item) {
		if (item != null) {
			synchronized (sHardCache) {
				CacheEntity entity = new CacheEntity();
				entity.setTime(System.currentTimeMillis());
				entity.setContent(item);
				sHardCache.put(key, entity);
			}
			return true;
		}
		return false;
	}

	// 从缓存中获取数据
	@SuppressWarnings("unused")
	protected CacheEntity getData(String key, int model) {
		synchronized (sHardCache) {
			CacheEntity item = sHardCache.get(key);
			final Object content = item.getContent();
			final Long time = item.getTime();
			// 当网络是无效的,你只能读缓存
			if (isNetWork) {
				if (time == null) {
					return null;
				}
				long expiredTime = System.currentTimeMillis()
						- Long.parseLong(time.toString());
				do {
					if (expiredTime < 0) {
						return null;
					}
					if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_SHORT) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_SHORT_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_MEDIUM) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MEDIUM_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_ML) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_ML_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_LONG) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MEDIUM_TIMEOUT) {
							return null;
						}
					} else {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MAX_TIMEOUT) {
							return null;
						}
					}
				} while (false);
			}
			if (item != null) {
				return item;
			}
		}
		return null;
	}
}