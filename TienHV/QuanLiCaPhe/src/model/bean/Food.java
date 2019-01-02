package model.bean;

public class Food {
	private int idFood;
	private String foodName;
	private int idFoodCategory;
	private double price;
	private int deleteValue;
	
	public Food() {
		
	}
	
	public Food(int idFood, String foodName, int idFoodCategory, double price, int deleteValue) {
		this.idFood = idFood;
		this.foodName = foodName;
		this.idFoodCategory = idFoodCategory;
		this.price = price;
		this.deleteValue = deleteValue;
	}

	public int getIdFood() {
		return idFood;
	}

	public void setIdFood(int idFood) {
		this.idFood = idFood;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getIdFoodCategory() {
		return idFoodCategory;
	}

	public void setIdFoodCategory(int idFoodCategory) {
		this.idFoodCategory = idFoodCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(int deleteValue) {
		this.deleteValue = deleteValue;
	}

	public String toString() {
		return foodName;
	}
	
}
