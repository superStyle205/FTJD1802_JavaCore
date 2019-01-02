package model.dao;

import java.sql.ResultSet;

public interface IFoodCategoryDao {
	
	ResultSet getAllFoodCategory();
	
	boolean insertFoodCategory(int idFoodCategory, String FoodCategoryName);
	
	boolean updateFoodCategory(int idFoodCategory, String FoodCategoryName);
	
	boolean  deleteFoodCategory(int idFoodCategory);
}
