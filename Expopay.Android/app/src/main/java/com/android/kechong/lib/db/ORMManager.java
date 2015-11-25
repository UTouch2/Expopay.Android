package com.android.kechong.lib.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.kechong.lib.util.FileUtil;

public class ORMManager {

	private SQLiteDatabase db;

	public ORMManager() {

	}

	public ORMManager(File file) {
		createDB(file);
	}

	public SQLiteDatabase createDB(File file) {
		db = SQLiteDatabase.openOrCreateDatabase(file, null);
		return db;
	}

	public SQLiteDatabase createDB(String fileName, String path) {
		try {
			FileUtil.createDirByPath(path);
			File f = FileUtil.createFileByPath(path, fileName);
			db = SQLiteDatabase.openOrCreateDatabase(f, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return db;
	}

	public void createTable(Class<?> clazz) {
		ClassResolver cr = new ClassResolver(clazz);
		// 检查表是否存在
		if (com.android.kechong.lib.db.DbUtil.tabIsExist(db, cr.getTableEntity().getTableName())) {
			return;
		}
		List<ColumnEntity> propertys = cr.getTableEntity().getColumns();
		StringBuffer sb = new StringBuffer();
		sb.append("create table ");
		sb.append(cr.getTableEntity().getTableName());
		sb.append("(");
		for (int i = 0; i < propertys.size(); i++) {
			ColumnEntity property = propertys.get(i);
			sb.append(property.getName());
			sb.append(" ");
			sb.append(property.type2type(property.getType()));
			sb.append(" ");
			if (property.isPrimaryKey()) {
				sb.append("PRIMARY KEY ");
			}
			if (property.isNull()) {
				sb.append("NOT NULL");
			}
			if (i != propertys.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(");");
		String sql = sb.toString();
		System.out.println(sql);
		db.execSQL(sql);
	}

	public <T> List<?> select(T t) {
		ClassResolver cr = new ClassResolver(t.getClass());
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(cr.getTableEntity().getTableName());
		String sql = sb.toString();
		System.out.println(sql);
		return select(t, sql);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> select(T t, String sql) {
		System.out.println(sql);
		List<T> list = new ArrayList<T>();
		Cursor cursor = db.rawQuery(sql, null);
		for (; cursor.moveToNext();) {
			ClassResolver cr = new ClassResolver(t.getClass());
			List<ColumnEntity> propertys = cr.getTableEntity().getColumns();
			for (int j = 0; j < propertys.size(); j++) {
				ColumnEntity propertyEntity = propertys.get(j);
				int index = cursor.getColumnIndex(propertyEntity.getName());
				if (index < 0) {
					continue;
				}
				String name = cursor.getColumnName(index);
				cr.setPerp(name, get(propertyEntity.getType(), cursor, j));
			}
			T s = (T) cr.getObj();
			list.add(s);
		}
		cursor.close();
		return list;
	}

	public <T> void delete(String sql) {
		db.execSQL(sql);
	}

	public <T> void delete(T t) {
		ClassResolver cr = new ClassResolver(t);
		StringBuffer sb = new StringBuffer();
		sb.append("delete  from ");
		sb.append(cr.getTableEntity().getTableName());
		sb.append(" where ");
		List<ColumnEntity> propertys = cr.getTableEntity().getColumns();
		for (int i = 0; i < propertys.size(); i++) {
			ColumnEntity propertyEntity = propertys.get(i);
			sb.append(propertyEntity.getName());
			sb.append(" = ");
			if (propertyEntity.getType().equals(String.class.getName())) {
				sb.append("'");
				sb.append(propertyEntity.getValue());
				sb.append("'");
			} else {
				sb.append(propertyEntity.getValue());
			}
			if (i < propertys.size() - 1) {
				sb.append(" and ");
			}
		}
		String sql = sb.toString();
		System.out.println(sql);
		delete(sql);
	}

	public <T> void update(T t, String key, String v) {
		ClassResolver cr = new ClassResolver(t);
		StringBuffer sb = new StringBuffer();
		sb.append("update set ");
		sb.append(key);
		sb.append(" = ");
		sb.append(v);
		sb.append(" from ");
		sb.append(cr.getTableEntity().getTableName());
		sb.append(" where ");
		for (ColumnEntity propertyEntity : cr.getTableEntity().getColumns()) {
			if (propertyEntity.isPrimaryKey()) {
				sb.append(propertyEntity.getName());
				sb.append(" = ");
				sb.append(cr.getPerp(propertyEntity.getName()));
			}
		}
		String sql = sb.toString();
		System.out.println(sql);
		update(sql);
	}

	public <T> void update(T t) {
		ClassResolver cr = new ClassResolver(t);
		StringBuffer sb = new StringBuffer();
		sb.append("update set ");
		List<ColumnEntity> propertys = cr.getTableEntity().getColumns();
		for (int i = 0; i < propertys.size(); i++) {
			ColumnEntity propertyEntity = propertys.get(i);
			if (propertyEntity.isPrimaryKey()) {
				continue;
			}
			sb.append(propertyEntity.getName());
			sb.append(" = ");
			sb.append(cr.getPerp(propertyEntity.getName()));
			if (i < propertys.size() - 1) {
				sb.append(" and ");
			}
		}
		sb.append(" from ");
		sb.append(cr.getTableEntity().getTableName());
		sb.append(" where ");
		for (ColumnEntity propertyEntity : propertys) {
			if (propertyEntity.isPrimaryKey()) {
				sb.append(propertyEntity.getName());
				sb.append(" = ");
				sb.append(cr.getPerp(propertyEntity.getName()));
			}
		}
		String sql = sb.toString();
		System.out.println(sql);
		update(sql);
	}

	public <T> void update(String sql) {
		db.execSQL(sql);
	}

	public <T> void insert(T t) {
		ClassResolver cr = new ClassResolver(t);
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(cr.getTableEntity().getTableName());
		sb.append(" (");
		StringBuffer sbv = new StringBuffer();
		sbv.append("values (");
		List<ColumnEntity> propertys = cr.getTableEntity().getColumns();
		for (int j = 0; j < propertys.size(); j++) {
			ColumnEntity propertyEntity = propertys.get(j);
			sb.append(propertyEntity.getName());
			if (propertyEntity.getType().equals(String.class.getName())) {
				sbv.append("'");
				sbv.append(propertyEntity.getValue());
				sbv.append("'");
			} else {
				sbv.append(propertyEntity.getValue());
			}
			if (j < propertys.size() - 1) {
				sb.append(",");
				sbv.append(",");
			}
		}
		sb.append(")");
		sbv.append(");");
		String sql = sb.toString() + sbv.toString();
		db.execSQL(sql);
	}

	public void insert(String sql) {
		db.execSQL(sql);
	}

	private Object get(String type, Cursor cursor, int i) {

		if (type.equals(String.class.getName())) {
			return cursor.getString(i);
		}
		if (type.equals(Integer.class.getName()) || type.equals("int")) {
			return cursor.getInt(i);
		}
		if (type.equals(Long.class.getName()) || type.equals("long")) {
			return cursor.getLong(i);
		}
		if (type.equals(Float.class.getName()) || type.equals("float")) {
			return cursor.getFloat(i);
		}
		if (type.equals(Double.class.getName()) || type.equals("double")) {
			return cursor.getDouble(i);
		}
		return null;
	}
}
