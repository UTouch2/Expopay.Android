package com.android.kechong.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedRefUtil {
	private static final String SHARE_NAME = "cuoyibang";
	/**
	 * @param key
	 * @param value
	 */
	public static void setSharedPreference(Context context, String k, String v) {

		// 实例化SharedPreferences.Editor对象
		SharedPreferences.Editor editor = context.getSharedPreferences(
				SHARE_NAME, Activity.MODE_PRIVATE).edit();
		// 用putString的方法保存数据
		editor.putString(k, v);
		// 提交当前数据
		editor.commit();
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void removeSharedPreference(Context context, String k) {

		// 实例化SharedPreferences.Editor对象
		SharedPreferences.Editor editor = context.getSharedPreferences(
				SHARE_NAME, Activity.MODE_PRIVATE).edit();
		// 用putString的方法保存数据
		editor.remove(k);
		// 提交当前数据
		editor.commit();
	}

	/**
	 * @param key
	 * @param defaultValue
	 *            当key不存在的时候的默认值
	 * @return
	 */
	public static String getSharedPreference(Context context, String k,
			String defaultV) {
		// 使用getString方法获得value，注意第2个参数是value的默认值
		SharedPreferences f = context.getSharedPreferences(SHARE_NAME,
				Activity.MODE_PRIVATE);
		return f.getString(k, defaultV);
	}

	/**
	 * 清理数据
	 * 
	 * @param context
	 */
	public static void clearSharedPreference(Context context) {
		context.getSharedPreferences(SHARE_NAME, Activity.MODE_PRIVATE).edit()
				.clear().commit();
	}

	public static String getOpenId(Context context) {
		return getSharedPreference(context, "openId", "");
	}

	public static String getMobile(Context context) {
		return getSharedPreference(context, "mobile", "");
	}

	public static void removeOpenId(Context context) {
		removeSharedPreference(context, "openId");
	}

	public static void removeMobile(Context context) {
		removeSharedPreference(context, "mobile");
	}

}
