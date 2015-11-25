package com.android.kechong.lib.db;

import java.util.List;

public class TableEntity {

	private String tableName;
	private List<ColumnEntity> columns;

	public TableEntity() {
		super();
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<ColumnEntity> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}
}
