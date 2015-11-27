package com.android.kechong.lib;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.v4.util.LruCache;

import com.android.kechong.lib.cache.CacheEntity;

@SuppressLint("NewApi")
public class BaseApplication extends Application {

	public static Context context;
	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}
}
