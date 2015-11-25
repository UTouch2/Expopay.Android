package com.android.kechong.lib.cache;
public interface ConfigCacheTime {
		/** 5分钟超时时间 */
		public static final int CONFIG_CACHE_SHORT_TIMEOUT = 1000 * 60 * 5; // 5
																			// 分钟
		/** 2小时超时时间 */
		public static final int CONFIG_CACHE_MEDIUM_TIMEOUT = 1000 * 3600 * 2; // 2小时
		/** 中长缓存时间 */
		public static final int CONFIG_CACHE_ML_TIMEOUT = 1000 * 60 * 60 * 24
				* 1; // 1天
		/** 最大缓存时间 */
		public static final int CONFIG_CACHE_MAX_TIMEOUT = 1000 * 60 * 60 * 24
				* 7; // 7天
	}