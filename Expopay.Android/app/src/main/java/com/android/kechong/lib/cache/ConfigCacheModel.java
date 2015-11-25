package com.android.kechong.lib.cache;

/**
 * CONFIG_CACHE_MODEL_LONG : 长时间(7天)缓存模式 CONFIG_CACHE_MODEL_ML : 中长时间(12小时)缓存模式
 * CONFIG_CACHE_MODEL_MEDIUM: 中等时间(2小时)缓存模式 CONFIG_CACHE_MODEL_SHORT :
 * 短时间(5分钟)缓存模式
 */
public interface ConfigCacheModel {
	/**
	 * 短时间(5分钟)
	 */
	public static final int CONFIG_CACHE_MODEL_SHORT = 0;
	/**
	 * 中等时间(2小时)
	 */
	public static final int CONFIG_CACHE_MODEL_MEDIUM = 1;
	/**
	 * 中长时间(12小时)
	 */
	public static final int CONFIG_CACHE_MODEL_ML = 2;
	/**
	 * 长时间(7天)
	 */
	public static final int CONFIG_CACHE_MODEL_LONG = 3;
}