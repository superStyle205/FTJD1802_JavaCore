package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;
import model.bean.Food;
import model.bean.TableFood;

public class TableFoodDao implements ITableFoodDao {
	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	List<TableFood> listTableFood;
	TableFood tableFood;
	int status = 0;
	boolean statusExecute = false;
	
	@Override
	public List<TableFood> getAllTableFood() {
		conn = con.getConnection();
		listTableFood = new ArrayList<TableFood>();
		try {
			String sql = "select * from tablefood";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				tableFood = new TableFood(rs.getInt("idTableFood"), 
						rs.getInt("tableName"), 
						rs.getString("tableStatus"), 
						rs.getInt("deleteValue"));
				listTableFood.add(tableFood);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listTableFood;
	}

	@Override
	public boolean insertTableFood(TableFood tableFood) {
		conn = con.getConnection();
		try {
			String sql = "Insert into tablefood values(?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, tableFood.getIdTableFood());
			st.setInt(2, tableFood.getTableName());
			st.setString(3, tableFood.getTableStatus());
			st.setInt(4, 1);
			status = st.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Thêm thất bại");
			e.printStackTrace();
		}
		return status > 0 ? true : false;
	}

	@Override
	public boolean updateTableFood(TableFood tableFood) {
		conn = con.getConnection();
		try {
			String sql = "Update tablefood set tableName = ?, tableStatus = ?, deleteValue = ? where idTableFood = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, tableFood.getTableName());
			st.setString(2, tableFood.getTableStatus());
			st.setInt(3, 1);
			st.setInt(4, tableFood.getIdTableFood());
			status = st.executeUpdate();
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
	public boolean deleteTableFood(TableFood tableFood) {
		conn = con.getConnection();
		try {
			String sql = "Update tablefood set tableName = ?, tableStatus = ?, deleteValue = ? where idTableFood = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, tableFood.getTableName());
			st.setString(2, tableFood.getTableStatus());
			st.setInt(3, 0);
			st.setInt(4, tableFood.getIdTableFood());
			status = st.executeUpdate();
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
