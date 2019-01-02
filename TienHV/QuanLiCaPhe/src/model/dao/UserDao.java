package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class UserDao implements IUserDao {
	
	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public ResultSet getAllUser() {
		conn = cn.getConnection();
		try {
			String sql = "select * from user";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean insertUser(String username, int type) {
		conn = cn.getConnection();
		try {
			String sql = "Insert into user values('" + username +  "','" + type +"')";
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
	public boolean updateUser(String username, int type) {
		conn = cn.getConnection();
		try {	
			String sql = "Update user set username = '" + username + "', type = '" + type + "' where username = '" + username + "'";
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
	public boolean deleteUser(String username) {
		conn = cn.getConnection();
		try {
			String sql = "Delete from user where username = '" + username + 	"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Xóa thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean resetPassword(String username) {
		conn = cn.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String sql = "UPDATE user SET user.password = 123456 WHERE username = '"+ username +"'";
			JOptionPane.showMessageDialog(null, "Đặt lại mật khẩu thành công");
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
