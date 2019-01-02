package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class FoodDao implements IFoodDao {
	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public ResultSet getAllFood() {
		conn = con.getConnection();
		try {
			String sql = "select * from food";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}

	public boolean insertFood(int idFood, String foodName, int idFoodCategory, double price) {
		conn = con.getConnection();
		try {
			st = conn.createStatement();
			String sql = "Insert into food values(" + idFood + ",'" + foodName + "','" + idFoodCategory + "','" + price + "')";
			JOptionPane.showMessageDialog(null, "Thêm thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean updateFood(int idFood, String foodName, int idFoodCategory, double price) {
		conn = con.getConnection();
		try {
			st = conn.createStatement();
			String sql = "Update food set idFood = '" + idFood  + "', foodName = '" + foodName + "', idFoodCategory = '" + idFoodCategory +  "', price = '" + price + "' where idFood = '" + idFood + "'";
			JOptionPane.showMessageDialog(null, "Sửa thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteFood(int idFood) {
		conn = con.getConnection();
		try {
			st = conn.createStatement();
			String sql = "Delete from food where idFood = " + idFood + "";
			JOptionPane.showMessageDialog(null, "Xóa thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	@Override
	public ResultSet searchFood(String food) {
		conn = con.getConnection();
		try {
			String sql = "SELECT * FROM food WHERE foodName LIKE '%" + food + "%'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
}
