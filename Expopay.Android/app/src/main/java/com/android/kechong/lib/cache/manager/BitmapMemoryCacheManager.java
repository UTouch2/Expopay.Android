package com.android.kechong.lib.cache.manager;

import android.app.Activity;

import com.android.kechong.lib.BaseApplication;

public class BitmapMemoryCacheManager extends AbsMemoryCacheManager {

	public BitmapMemoryCacheManager(Activity context) {
		super();
		this.sHardCache = ((BaseApplication) context.getApplication())
				.getBitmapHardCache();
	}

	@Override
	public Object load(String url, int model) {
		return this.getData(String.valueOf(url.hashCode()), model)
				.getBitmapContent();
	}

	@Override
	public void save(String url, Object data) {
		this.putData(String.valueOf(url.hashCode()), data.toString());
	}
}
