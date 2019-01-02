package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class TableFoodDao implements ITableFoodDao {
	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public ResultSet getAllTableFood() {
		conn = cn.getConnection();
		try {
			String sql = "select * from tablefood";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean insertTableFood(int idTableFood, int tableFoodName, String tableStatus) {
		conn = cn.getConnection();
		try {
			String sql = "Insert into tablefood values(" + idTableFood + ",'" + tableFoodName + "','" + tableStatus + "')";
			st = conn.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateTableFood(int idTableFood, int tableFoodName, String tableStatus) {
		conn = cn.getConnection();
		try {
			String sql = "Update tablefood set idTableFood = '" + idTableFood  + "', tableName = '" + tableFoodName + "', tableStatus = '" + tableStatus + "' where idTableFood = '" + idTableFood + "'";
			st = conn.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTableFood(int idTableFood) {
		conn = cn.getConnection();
		try {

			String sql = "Delete from tablefood where idTableFood = " + idTableFood + "";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Xóa thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
