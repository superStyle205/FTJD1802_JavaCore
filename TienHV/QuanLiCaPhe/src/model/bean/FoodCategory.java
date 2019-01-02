package model.bean;

public class FoodCategory {
	private int idFoodCategory;
	private String foodCategoryName;
	private int deleteValue;
	
	public FoodCategory() {
		
	}
	
	public FoodCategory(int idFoodCategory, String foodCategoryName, int deleteValue) {
		this.idFoodCategory = idFoodCategory;
		this.foodCategoryName = foodCategoryName;
		this.deleteValue = deleteValue;
	}

	public int getIdFoodCategory() {
		return idFoodCategory;
	}

	public void setIdFoodCategory(int idFoodCategory) {
		this.idFoodCategory = idFoodCategory;
	}

	public String getFoodCategoryName() {
		return foodCategoryName;
	}

	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}

	public int getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(int deleteValue) {
		this.deleteValue = deleteValue;
	}
	
}
