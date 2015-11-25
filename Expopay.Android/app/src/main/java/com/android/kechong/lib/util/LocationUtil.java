package com.android.kechong.lib.util;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class LocationUtil {
	/**
	 * 查看地理位置
	 * @return 
	 * @return
	 */
	public static Location getLocation(Context context) {

		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getProviders(true);
		Location location = null;
		//优先选择gps获取位置
		if (providers.contains(LocationManager.GPS_PROVIDER)) {
			// 如果是GPS
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);			
		} 
		if (location ==null&&providers.contains(LocationManager.NETWORK_PROVIDER)) {
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);	
		}
		//double lat = location.getLatitude();
		//double lng = location.getLongitude();
		return location;
	}
}
