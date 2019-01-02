package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class ResetPasswordDao implements IResetPassworDao {
	
	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public boolean updatePassword(String username, String txtNewPassword) {
		conn = cn.getConnection();
		try {
			String sql = "Update user set password = '" + txtNewPassword +  "' where username = '" + username + "'";
			st = conn.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
