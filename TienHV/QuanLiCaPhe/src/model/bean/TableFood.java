package model.bean;

public class TableFood {
	private int idTableFood;
	private int tableName;
	private String tableStatus;
	private int deleteValue;
	
	public TableFood() {
		
	}
	
	public TableFood(int idTableFood, int tableName, String tableStatus, int deleteValue) {
		this.idTableFood = idTableFood;
		this.tableName = tableName;
		this.tableStatus = tableStatus;
		this.deleteValue = deleteValue;
	}

	public int getIdTableFood() {
		return idTableFood;
	}

	public void setIdTableFood(int idTableFood) {
		this.idTableFood = idTableFood;
	}

	public int getTableName() {
		return tableName;
	}

	public void setTableName(int tableName) {
		this.tableName = tableName;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	public int getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(int deleteValue) {
		this.deleteValue = deleteValue;
	}
	
	
}
