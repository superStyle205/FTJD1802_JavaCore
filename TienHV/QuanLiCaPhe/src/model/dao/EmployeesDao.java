package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;
import model.bean.Employees;


public class EmployeesDao implements IEmployeesDao {
	private ConnectionUntil con = new ConnectionUntil();
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	Employees employees;
	List<Employees> listEmployees;
	int status = 0;
	boolean statusExecute = false;
	
	@Override
	public List<Employees> getAllEmployees() {
		conn = con.getConnection();
		listEmployees = new ArrayList<Employees>();
		try {
			String sql = "select * from employees";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				employees = new Employees(rs.getInt("idEmployees"), 
						rs.getString("username"),
						rs.getString("fullName"), 
						rs.getInt("age"), 
						rs.getString("address"),
						rs.getInt("phoneNumber"),
						rs.getString("homeTown"),
						rs.getInt("identityCard"),
						rs.getInt("deleteValue"));
				listEmployees.add(employees);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listEmployees;
	}

	@Override
	public boolean deleteEmployees(Employees employees) {
		conn = con.getConnection();
		try {
			String sql = "Update employees set username = ?, fullName = ?, age = ?, address = ?, phoneNumber = ?, homeTown = ?, identityCard = ?, deleteValue = ? where idEmployees = ?";
			st = conn.prepareStatement(sql);
			st = conn.prepareStatement(sql);
			st.setString(1, employees.getUsername());
			st.setString(2, employees.getFullName());
			st.setInt(3, employees.getAge());
			st.setString(4, employees.getAddress());
			st.setInt(5, employees.getPhoneNumber());
			st.setString(6, employees.getHomeTown());
			st.setInt(7, employees.getIdentityCard());
			st.setInt(8, 0);
			st.setInt(9, employees.getIdEmployees());
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
	public boolean insertEmployees(Employees employees) {
		conn = con.getConnection();
		try {
			String sql = "Insert into employees values(?,?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, employees.getIdEmployees());
			st.setString(2, employees.getUsername());
			st.setString(3, employees.getFullName());
			st.setInt(4, employees.getAge());
			st.setString(5, employees.getAddress());
			st.setInt(6, employees.getPhoneNumber());
			st.setString(7, employees.getHomeTown());
			st.setInt(8, employees.getIdentityCard());
			st.setInt(9, 1);
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
	public boolean updateEmployees(Employees employees) {
		conn = con.getConnection();
		try {
			String sql = "Update employees set username = ?, fullName = ?, age = ?, address = ?, phoneNumber = ?, homeTown = ?, identityCard = ?, deleteValue = ? where idEmployees = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, employees.getUsername());
			st.setString(2, employees.getFullName());
			st.setInt(3, employees.getAge());
			st.setString(4, employees.getAddress());
			st.setInt(5, employees.getPhoneNumber());
			st.setString(6, employees.getHomeTown());
			st.setInt(7, employees.getIdentityCard());
			st.setInt(8, 1);
			st.setInt(9, employees.getIdEmployees());
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
	public List<Employees> getAllInfoEmployees() {
		conn = con.getConnection();
		listEmployees = new ArrayList<Employees>();
		try {
			String sql = "select * from employees";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				employees = new Employees(rs.getInt("idEmployees"), 
						rs.getString("username"),
						rs.getString("fullName"), 
						rs.getInt("age"), 
						rs.getString("address"),
						rs.getInt("phoneNumber"),
						rs.getString("homeTown"),
						rs.getInt("identityCard"),
						rs.getInt("deleteValue"));
				listEmployees.add(employees);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeResultSet(rs);
			con.closeStatement(st);
			con.closeConnection(conn);
		} 
		return listEmployees;
	}

}
