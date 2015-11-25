package com.android.kechong.lib.db;

import java.util.Date;

class ColumnEntity {

	private String name;
	private String type;
	private Object value;
	private boolean isNull;
	private boolean isPrimaryKey;

	public ColumnEntity() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 根据属性的类型，来定义数据库类型。
	 * 
	 * @param type
	 * @return
	 */
	public String type2type(String type) {
		if (type.equals(String.class.getName())) {
			return "varchar";
		}
		if (type.equals(Integer.class.getName())) {
			return "integer";
		}
		if (type.equals(Boolean.class.getName())) {
			return "boolean";
		}
		if (type.equals(Long.class.getName())) {
			return "integer";
		}
		if (type.equals(Float.class.getName())) {
			return "double";
		}
		if (type.equals(Double.class.getName())) {
			return "double";
		}
		if (type.equals(Date.class.getName())) {
			return "time";
		}
		return "varchar";
	}
}
