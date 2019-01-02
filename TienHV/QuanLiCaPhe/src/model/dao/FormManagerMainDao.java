package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import common.database.ConnectionUntil;

public class FormManagerMainDao implements IFormManagerMainDao {

	private ConnectionUntil cn = new ConnectionUntil();
	Connection conn;
	Statement st;
	ResultSet rs;
	
	@Override
	public ResultSet getAllTableBill(String table_Name) {
		conn = cn.getConnection();
		try {
			String sql = "SELECT foodName, count, price, price*count as intoMoney "
	    			+ "FROM billinfo join food on billinfo.idFood = food.idFood "
	    			+ "JOIN bill on billinfo.idBill = bill.idBill "
	    			+ "WHERE idTableFood = "+ table_Name + " AND billStatus = 0";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getAllTable() {
		conn = cn.getConnection();
		String sql = "select * from tablefood";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean insertFood(String tableName) {

		return false;
	}

	@Override
	public boolean viewPay() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkType() {
		conn = cn.getConnection();
		try {
			String sql = "select * from user";
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ResultSet getAllListFoodCategory() {
		conn = cn.getConnection();
		String sql = "select * from foodcategory";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getAllListFood() {
		conn = cn.getConnection();
		String sql = "select * from food";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getAllId_FoodCategory_Food(int idFoodCategory) {
		conn = cn.getConnection();
		try {
			String sql = "SELECT foodCategoryName, foodName FROM food JOIN foodcategory ON food.idFoodCategory = foodcategory.idFoodCategory WHERE foodcategory.idFoodCategory = "+ idFoodCategory +"";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
