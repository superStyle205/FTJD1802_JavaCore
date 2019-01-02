package model.dao;

import java.sql.ResultSet;

public interface ITableFoodDao {
	
	ResultSet getAllTableFood();
	
	boolean insertTableFood(int idTableFood, int tableFoodName, String tableStatus);
	
	boolean updateTableFood(int idTableFood, int tableFoodName, String tableStatus);
	
	boolean  deleteTableFood(int idTableFood);
}
