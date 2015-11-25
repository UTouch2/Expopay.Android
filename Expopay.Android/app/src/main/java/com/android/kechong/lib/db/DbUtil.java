package com.android.kechong.lib.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbUtil {
	public static boolean tabIsExist(SQLiteDatabase db,String tabName) {
		boolean result = false;
		if (tabName == null) {
			return false;
		}
		Cursor cursor = null;
		try {
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"
					+ tabName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
		} catch (Exception e) { 
		}
		return result;
	}
}
