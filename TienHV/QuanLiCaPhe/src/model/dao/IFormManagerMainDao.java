package model.dao;

import java.sql.ResultSet;

public interface IFormManagerMainDao {
	ResultSet getAllTableBill(String table_Name);
	
	ResultSet getAllTable();
	
	boolean insertFood(String tableName);
	
	boolean viewPay();
	
	boolean checkType();
	
	ResultSet getAllListFoodCategory();
	
	ResultSet getAllListFood();
	
	ResultSet getAllId_FoodCategory_Food(int idFoodCotegory);
	
	
}
