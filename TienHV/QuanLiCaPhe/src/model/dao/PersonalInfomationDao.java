package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class PersonalInfomationDao implements IPersonalInformationDao {
	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public ResultSet getAllEmployees() {
		conn = cn.getConnection();
		try {
			String sql = "select * from employees";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}


}
