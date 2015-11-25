package com.android.kechong.lib.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.android.kechong.lib.db.annotiation.ColumnAnnotation;
import com.android.kechong.lib.db.annotiation.TableAnnotation;

/**
 * 利用反射来解析一个类，获取类的属性和方法。
 * 
 * @author misxu012
 * 
 */
class ClassResolver {
	private com.android.kechong.lib.db.TableEntity tableEntity;
	private Class<?> clazz; // 类型
	private Object obj;// 类型的实例

	ClassResolver(Class<?> clazz) {
		this.tableEntity = new com.android.kechong.lib.db.TableEntity();
		this.clazz = clazz;
		try {
			// 为class创建一个实例
			obj = clazz.newInstance();
			// 获取类的所有属性
			initTable();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	

	<T> ClassResolver(T t) {
		this.tableEntity = new com.android.kechong.lib.db.TableEntity();
		this.clazz = t.getClass();
		// 为class创建一个实例
		obj = t;
		initTable();
	}

	private void initTable() {
		TableAnnotation ann = clazz.getAnnotation(TableAnnotation.class);
		if (ann != null) {
			tableEntity.setTableName(ann.tableName());
		} else {
			tableEntity.setTableName(clazz.getSimpleName());
		}
		// 获取类的所有属性
		initProperties();
	}

	private void initProperties() {
		List<com.android.kechong.lib.db.ColumnEntity> propertys = new ArrayList<com.android.kechong.lib.db.ColumnEntity>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			ColumnAnnotation propertyAnnotation = (ColumnAnnotation) field
					.getAnnotation(ColumnAnnotation.class);
			if (propertyAnnotation != null) {
				com.android.kechong.lib.db.ColumnEntity entity = new com.android.kechong.lib.db.ColumnEntity();
				String name = propertyAnnotation.columnName();
				// 属性名和类型名
				entity.setName(name.equals("") ? field.getName() : name);
				entity.setType(field.getType().getName());
				// 属性值
				entity.setValue(getPerp(field.getName()));
				// 获取
				entity.setNull(propertyAnnotation.isNull());
				entity.setPrimaryKey(propertyAnnotation.primaryKey());
				propertys.add(entity);
			}
		}
		tableEntity.setColumns(propertys);
	}

	public void setPerp(String perpName, Object value) {
		Field field;
		try {
			field = clazz.getDeclaredField(perpName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Object getPerp(String perpName) {
		Field field;
		try {
			field = clazz.getDeclaredField(perpName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TableEntity getTableEntity() {
		return tableEntity;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Object getObj() {
		return obj;
	}
}
