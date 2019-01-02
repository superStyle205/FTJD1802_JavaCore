package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class FormLoginDao implements IFormLoginDao {

	private ConnectionUntil cn = new ConnectionUntil();
	
	@Override
	public boolean checkLogin(String username, String password) {
		Connection conn = cn.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password= '" + password + "'";
			stmt = conn.createStatement();
			 rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

}
