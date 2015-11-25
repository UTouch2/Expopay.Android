package com.android.kechong.lib.util;

import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class ApkUtil {
	/**
	 * 根据包名查找，如果返回空，怎说明该apk没有安装
	 * 
	 * @param context
	 *            当前APP环境
	 * @param name
	 * @return
	 */
	public static PackageInfo findPackageInfoByName(Context context, String name) {
		PackageManager packManager = context.getPackageManager();
		List<PackageInfo> packageInfo = packManager.getInstalledPackages(0);
		for (int i = 0; i < packageInfo.size(); i++) {
			PackageInfo pi = packageInfo.get(i);
			if (pi.packageName.equalsIgnoreCase(name)) {
				return pi;
			}
		}
		return null;
	}

	/**
	 * 获取未安装的APK信息
	 * 
	 * @param context
	 * @param archiveFilePath
	 */
	public static PackageInfo getPackageInfoByApkInfo(Context context,
			String archiveFilePath) {
		PackageManager pm = context.getPackageManager();
		PackageInfo apkInfo = pm.getPackageArchiveInfo(archiveFilePath,
				PackageManager.GET_META_DATA);
		return apkInfo;
	}

	/**
	 * 根据包名查找版本名，如果包不存在，则返回null
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String findVersionNameByName(Context context, String name) {
		PackageInfo packageInfo = findPackageInfoByName(context, name);
		if (packageInfo == null) {
			return null;
		}
		return findVersionNamePackageInfo(packageInfo);
	}

	/**
	 * 根据包查找versionName
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String findVersionNamePackageInfo(PackageInfo packageInfo) {
		if (packageInfo == null) {
			return null;
		}
		return packageInfo.versionName;
	}

	/**
	 * 根据包查找versionCode
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static int findVersionCodePackageInfo(PackageInfo packageInfo) {
		if (packageInfo == null) {
			return -1;
		}
		return packageInfo.versionCode;
	}

	/**
	 * 根据包名查找版本号，如果包不存在，则返回null
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static int findVersionCodeByName(Context context, String name) {
		PackageInfo packageInfo = findPackageInfoByName(context, name);

		return findVersionCodePackageInfo(packageInfo);
	}

	/** 
	 * @param context
	 * @param className
	 * @return
	 */
	public static ActivityManager.RunningServiceInfo getRunningServiceInfoByClassName(
			Context context, String className) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		if (!(serviceList.size() > 0)) {
			return null;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				return serviceList.get(i);
			}
		}
		return null;
	}

	/**
	 * 检查服务是否已经运行
	 * 
	 * @param context
	 * @param className
	 * @return
	 */
	public static boolean isServiceRunning(ActivityManager.RunningServiceInfo serviceInfo) {
		if (serviceInfo!= null) {
			return true;
		}
		return false;
	}
	/**
	 * 检查服务是否已经运行
	 * 
	 * @param context
	 * @param className
	 * @return
	 */
	public static boolean isServiceRunning(Context context, String className) {
		return isServiceRunning(getRunningServiceInfoByClassName(context, className));
	}

	/**
	 * 安装apk
	 * 
	 * @param apkPath
	 * @param context
	 * @throws IOException
	 */
	public static void installApk(String apkPath, Context context)
			throws IOException {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + apkPath),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
