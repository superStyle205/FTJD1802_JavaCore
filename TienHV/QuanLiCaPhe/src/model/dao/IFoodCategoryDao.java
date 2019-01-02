package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.bean.FoodCategory;

public interface IFoodCategoryDao {
	
	List<FoodCategory> getAllFoodCategory();
	
	boolean  deleteFoodCategory (FoodCategory foodCategory);

	boolean insertFoodCategory (FoodCategory foodCategory);

	boolean updateFoodCategory (FoodCategory foodCategory);
}
