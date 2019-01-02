package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;
import model.bean.Food;
import model.bean.FoodCategory;

public class FoodCategoryDao implements IFoodCategoryDao{

	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	FoodCategory foodCategory;
	List<FoodCategory> listFoodCategory;
	int status = 0;
	boolean statusExecute = false;
	
	@Override
	public List<FoodCategory> getAllFoodCategory() {
		conn = con.getConnection();
		listFoodCategory = new ArrayList<FoodCategory>();
		try {
			String sql = "select * from foodcategory";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				foodCategory = new FoodCategory(rs.getInt("idFoodCategory"), 
						rs.getString("foodCategoryName"),
						rs.getInt("deleteValue"));
				listFoodCategory.add(foodCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listFoodCategory;
	}

	public boolean insertFoodCategory (FoodCategory foodCategory) {
		conn = con.getConnection();
		try {
			String sql = "Insert into foodcategory values(?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, foodCategory.getIdFoodCategory());
			st.setString(2, foodCategory.getFoodCategoryName());
			st.setInt(3, 1);
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
	public boolean updateFoodCategory (FoodCategory foodCategory) {
		conn = con.getConnection();
		try {
			String sql = "Update foodcategory set foodCategoryName = ?,  deleteValue = ? where idFoodCategory = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, foodCategory.getFoodCategoryName());
			st.setInt(2, 1);
			st.setInt(3, foodCategory.getIdFoodCategory());
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
	public boolean deleteFoodCategory (FoodCategory foodCategory) {
		conn = con.getConnection();
		try {
			String sql = "Update foodcategory set foodCategoryName = ?,  deleteValue = ? where idFoodCategory = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, foodCategory.getFoodCategoryName());
			st.setInt(2, 0);
			st.setInt(3, foodCategory.getIdFoodCategory());
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
	
}
