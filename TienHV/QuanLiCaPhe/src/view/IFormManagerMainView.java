package view;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public interface IFormManagerMainView {
	public void display();
	
	public void menuBar();
	
	JPanel managerMainViewLeft();
	
	JPanel managerMainViewRight();
	
	JSplitPane managerMainViewArea();
	
	public void loadDataListFoodCategory();
	
	public void loadDataListFood();
	
}
