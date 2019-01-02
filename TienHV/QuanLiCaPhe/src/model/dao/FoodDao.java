package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;
import model.bean.Food;

public class FoodDao implements IFoodDao {
	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	Food food;
	List<Food> listFood;
	int status = 0;
	boolean statusExecute = false;
	
	@Override
	public List<Food> getAllFood() {
		conn = con.getConnection();
		listFood = new ArrayList<Food>();
		try {
			String sql = "select * from food";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				food = new Food(rs.getInt("idFood"), 
						rs.getString("foodName"), 
						rs.getInt("idFoodCategory"), 
						rs.getDouble("price"),
						rs.getInt("deleteValue"));
				listFood.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listFood;
	}

	public boolean insertFood(Food food) {
		conn = con.getConnection();
		try {
			String sql = "Insert into food values(?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, food.getIdFood());
			st.setString(2, food.getFoodName());
			st.setInt(3, food.getIdFoodCategory());
			st.setDouble(4, food.getPrice());
			st.setInt(5, 1);
			status = st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Thêm thất bại");
		} finally {
			con.closeConnection(conn);
			con.closeStatement(st);
		}
		return status > 0 ? true : false;
	}

	@Override
	public boolean updateFood(Food food) {
		conn = con.getConnection();
		try {
			String sql = "Update food set foodName = ?, idFoodCategory = ?, price = ?, deleteValue = ? where idFood = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, food.getFoodName());
			st.setInt(2, food.getIdFoodCategory());
			st.setDouble(3, food.getPrice());
			st.setInt(4, 1);
			st.setInt(5, food.getIdFood());
			if (st.executeUpdate() > 0) {
				statusExecute = true;
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sửa thất bại");
			e.printStackTrace();
		} 
		return statusExecute;
	}

	@Override
	public boolean deleteFood(Food food) {
		conn = con.getConnection();
		try {
			String sql = "Update food set foodName = ?, idFoodCategory = ?, price = ?, deleteValue = ? where idFood = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, food.getFoodName());
			st.setInt(2, food.getIdFoodCategory());
			st.setDouble(3, food.getPrice());
			st.setInt(4, 0);
			st.setInt(5, food.getIdFood());
			if (st.executeUpdate() > 0) {
				statusExecute = true;
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Xóa thất bại");
			e.printStackTrace();
		} 
		return statusExecute;
	}
	
	@Override
	public List<Food> searchFood(String searchFood) {
		conn = con.getConnection();
		listFood = new ArrayList<Food>();
		try {
			String sql = "SELECT * FROM food WHERE foodName LIKE '%" + searchFood + "%'";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				food = new Food(rs.getInt("idFood"), 
						rs.getString("foodName"), 
						rs.getInt("idFoodCategory"), 
						rs.getDouble("price"),
						rs.getInt("deleteValue"));
				listFood.add(food);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return listFood;
	}
}
