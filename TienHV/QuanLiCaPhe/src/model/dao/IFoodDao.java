package model.dao;

import java.sql.ResultSet;

public interface IFoodDao {
	
	ResultSet getAllFood();
	
	boolean  deleteFood(int idFood);

	boolean insertFood(int idFood, String foodName, int idFoodCategory, double price);

	boolean updateFood(int idFood, String foodName, int idFoodCategory, double price);
	
	ResultSet searchFood(String food);
}
