package com.android.kechong.lib.cache.manager;

public interface ICacheManager {

	/**
	 * 加载缓存数据
	 * 
	 * @param url
	 * @return
	 */
	Object load(String url, int model) throws Exception;

	/**
	 * 保存缓存数据
	 * 
	 * @param url
	 * @param data
	 */
	void save(String url, Object data) throws Exception;
}