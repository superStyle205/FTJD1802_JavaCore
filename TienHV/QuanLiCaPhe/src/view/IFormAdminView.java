package view;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public interface IFormAdminView {
	public void display();
	
	JTabbedPane managerTab();
	
	JPanel managerRevenue();
	
	JPanel managerFoodAreaLeft();

	JPanel managerFoodAreaRight();
	
	JSplitPane managerFoodArea();
	
	JPanel managerFoodCategoryAreaLeft();
	
	JPanel managerFoodCategoryAreaRight();
	
	JSplitPane managerFoodCategoryArea();
	
	JPanel managerTableFoodAreaLeft();
	
	JPanel managerTableFoodAreaRight();
	
	JSplitPane managerTableFoodArea();
	
	JPanel managerUserAreaLeft();
	
	JPanel managerUserAreaRight();
	
	JSplitPane managerUserArea();
	
	public void loadDatabaseFood();
	
	public void loadDatabaseFoodCategory();
	
	public void loadDatabaseTableFood();
	
	public void loadDatabaseUser();
}
