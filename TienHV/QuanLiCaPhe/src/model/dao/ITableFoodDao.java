package model.dao;

import java.util.List;
import model.bean.TableFood;

public interface ITableFoodDao {
	
	List<TableFood> getAllTableFood();
	
	boolean insertTableFood(TableFood tableFood);
	
	boolean updateTableFood(TableFood tableFood);
	
	boolean  deleteTableFood(TableFood tableFood);
}
