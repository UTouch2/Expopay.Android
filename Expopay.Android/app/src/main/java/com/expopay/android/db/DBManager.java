package com.expopay.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CardEntity;
import com.expopay.android.model.CityEntity;
import com.expopay.android.utils.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class DBManager {
    private static SQLiteDatabase db;

    static {
        if (db == null) {
            File file = new File(FileManager.dbPath, MyApplication.DB_NAME);
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
        }
    }

    public static List<CityEntity> getCardList(int level, int parentId) {
        String columnName = "";
        String sql = "";
        if (level == 1) {
            columnName = "province_name";
            sql = "select distinct * from areas where city_id =0 and province_id=0";
        } else if (level == 2) {
            columnName = "city_name";
            sql = "select * from areas where city_id =0 and province_id=" + parentId;
        } else if (level == 3) {
            columnName = "district_name";
            sql = "select * from areas where city_id=" + parentId;
        }
        List<CityEntity> list = new ArrayList<CityEntity>();
        Cursor cursor = db.rawQuery(sql, null);
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex(columnName);
        int pIdIndex = cursor.getColumnIndex("province_id");
        int cIdIndex = cursor.getColumnIndex("city_id");
        int pNameIndex = cursor.getColumnIndex("province_name");
        int cNameIndex = cursor.getColumnIndex("city_name");
        int dNameIndex = cursor.getColumnIndex("district_name");
        for (; cursor.moveToNext(); ) {
            CityEntity e = new CityEntity();
            e.setName(cursor.getString(nameIndex));
            e.setLevel(level);
            e.setId(cursor.getInt(idIndex));
            e.setCityId(cursor.getInt(cIdIndex));
            e.setProvinceId(cursor.getInt(pIdIndex));
            e.setCityName(cursor.getString(cNameIndex));
            e.setProvinceName(cursor.getString(pNameIndex));
            e.setDistrictName(cursor.getString(dNameIndex));
            list.add(e);
        }
        return list;
    }
}
