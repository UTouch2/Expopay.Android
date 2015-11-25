package com.android.kechong.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

	/**
	 * 查网络状
	 * @return
	 */
	public static  boolean checkNetWork(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
		for (int i = 0; i < networkInfo.length; i++) {
			if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
}
