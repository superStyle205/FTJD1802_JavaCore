package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class FoodCategoryDao implements IFoodCategoryDao{

	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	
	@Override
	public ResultSet getAllFoodCategory() {
		conn = cn.getConnection();
		ResultSet rs = null;
		String sql = "select * from foodcategory";
		Statement st;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean insertFoodCategory(int idFoodCategory, String FoodCategoryName) {
		Statement st;
		conn = cn.getConnection();
		try {
			st = conn.createStatement();
			String sql = "Insert into foodcategory values(" + idFoodCategory + ",'" + FoodCategoryName + "')";
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateFoodCategory(int idFoodCategory, String FoodCategoryName) {
		conn = cn.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String sql = "Update foodcategory set idFoodCategory = '" + idFoodCategory + "', foodCategoryName = '" + FoodCategoryName + "' where idFoodCategory = '" + idFoodCategory + "'";
			JOptionPane.showMessageDialog(null, "Sửa thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteFoodCategory(int idFoodCategory) {
		try {
			conn = cn.getConnection();
			Statement st = conn.createStatement();
			String sql = "Delete from foodcategory where idFoodCategory = " + idFoodCategory + "";
			JOptionPane.showMessageDialog(null, "Xóa thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
