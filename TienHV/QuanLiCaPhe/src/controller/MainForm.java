package controller;

import model.dao.FormLoginDao;
import view.FormAdminView;
import view.FormLoginView;
import view.FormManagerMainView;

public class MainForm {

	public static void main(String[] args) {
		FormLoginView lg = new FormLoginView();
		lg.display();
//		FormManagerMainView fm = new FormManagerMainView();
//		fm.display();

	}

}
