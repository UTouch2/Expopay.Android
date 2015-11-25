package com.android.kechong.lib.cache.manager;

import android.app.Activity;

import com.android.kechong.lib.BaseApplication;

public class StringMemoryCacheManager extends AbsMemoryCacheManager {
	protected static StringMemoryCacheManager instance;

	public StringMemoryCacheManager(Activity context) {
		super();
		this.sHardCache = ((BaseApplication) context.getApplication()).getStringHardCache();
	}

	@Override
	public Object load(String url, int model) throws Exception {
		return this.getData(String.valueOf(url.hashCode()), model).getStringContent();
	}

	@Override
	public void save(String url, Object data) throws Exception {
		this.putData(String.valueOf(url.hashCode()), data.toString());
	}
}
