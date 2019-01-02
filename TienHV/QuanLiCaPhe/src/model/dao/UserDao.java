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
import model.bean.User;

public class UserDao implements IUserDao {
	
	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	User user;
	List<User> listUser;
	int status = 0;
	boolean statusExecute = false;
	
	@Override
	public List<User> getAllUser() {
		conn = con.getConnection();
		listUser = new ArrayList<User>();
		try {
			String sql = "select * from user";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				user = new User(rs.getString("username"), 
						rs.getString("password"), 
						rs.getInt("type"),
						rs.getInt("deleteValue"));
				listUser.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listUser;
	}

	public boolean insertUser(User user) {
		conn = con.getConnection();
		try {
			String sql = "Insert into user values(?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setInt(3, user.getType());
			st.setInt(4, 1);
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
	public boolean updateUser(User user) {
		conn = con.getConnection();
		try {
			String sql = "Update user set password = ?, type = ?,  deleteValue = ? where username = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getPassword());
			st.setInt(2, user.getType());
			st.setInt(3, 1);
			st.setString(4, user.getUsername());
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
	public boolean deleteUser(User user) {
		conn = con.getConnection();
		try {
			String sql = "Update user set password = ?, type = ?,  deleteValue = ? where username = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getPassword());
			st.setInt(2, user.getType());
			st.setInt(3, 0);
			st.setString(4, user.getUsername());
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
	public boolean resetPassword(User user) {
		conn = con.getConnection();
		try {
			String sql = "Update user set password = 123456, deleteValue = ? where username = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, 1);
			st.setString(2, user.getUsername());
			if (st.executeUpdate() > 0) {
				statusExecute = true;
				JOptionPane.showMessageDialog(null, "Đặt lại mật khẩu thành công");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Đặt lại mật khẩu thất bại");
			e.printStackTrace();
		} 
		return statusExecute;
	}

	@Override
	public boolean changePassword(User user) {
		conn = con.getConnection();
		try {
			String sql = "Update user set password = ?, deleteValue = ? where username = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getPassword());
			st.setInt(2, 1);
			st.setString(3, user.getUsername());
			if (st.executeUpdate() > 0) {
				statusExecute = true;
				JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return statusExecute;
	}
}
