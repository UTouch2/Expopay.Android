package com.android.kechong.lib;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.v4.util.LruCache;

import com.android.kechong.lib.cache.CacheEntity;

@SuppressLint("NewApi")
public class BaseApplication extends Application {

	public static Context context;
	
	protected final int m = 1024 * 1024;
	// 字符串硬缓存
	protected LruCache<String, CacheEntity> stringHardCache;
	// bitmap硬缓存
	protected LruCache<String, CacheEntity> bitmapHardCache;
	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化缓存对象 2m空间
		this.stringHardCache = new LruCache<String, CacheEntity>(2 * m) {
			@Override
			public int sizeOf(String key, CacheEntity value) {
				return value.sizeOf();
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					CacheEntity oldValue, CacheEntity newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
			}
		};
		// 初始化缓存对象 6m空间
		this.bitmapHardCache = new LruCache<String, CacheEntity>(6 * m) {
			@Override
			public int sizeOf(String key, CacheEntity value) {
				return value.sizeOf();
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					CacheEntity oldValue, CacheEntity newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
			}
		};

		context = getApplicationContext();
	}

	public LruCache<String, CacheEntity> getStringHardCache() {
		return stringHardCache;
	}

	public void setStringHardCache(LruCache<String, CacheEntity> stringHardCache) {
		this.stringHardCache = stringHardCache;
	}

	public LruCache<String, CacheEntity> getBitmapHardCache() {
		return bitmapHardCache;
	}

	public void setBitmapHardCache(LruCache<String, CacheEntity> bitmapHardCache) {
		this.bitmapHardCache = bitmapHardCache;
	}
}
