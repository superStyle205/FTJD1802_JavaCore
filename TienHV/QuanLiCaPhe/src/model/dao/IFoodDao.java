package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.bean.Food;

public interface IFoodDao {
	
	List<Food> getAllFood();
	
	boolean  deleteFood(Food food);

	boolean insertFood(Food food);

	boolean updateFood(Food food);
	
	List<Food> searchFood(String searchFood);
}
