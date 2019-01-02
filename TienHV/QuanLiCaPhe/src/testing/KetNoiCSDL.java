package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KetNoiCSDL {

	private static final String HOST_NAME = "localhost";
	private static final String PORT = "3306";
	private static final String DATABASE_NAME = "quanliquancaphe";
	private static final String URL_CONNECT = "jdbc:mysql://" + HOST_NAME + ":" + PORT + "/" + DATABASE_NAME;
	private static final String USER = "root";
	private static final String PASS = "";
	private static Properties properties = new Properties();
	private Connection conn;
	private Statement st;

	static {
		properties.setProperty("user", USER);
		properties.setProperty("password", PASS);
		properties.setProperty("characterEncoding", "UTF-8");
	}

	public Connection getConnection() {
		conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL_CONNECT, properties);
			System.out.println("Kết nối thành công");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public ResultSet getAllTableFood() throws SQLException {
		ResultSet kq = null;
		String sql = "select * from tablefood";
		Statement st = conn.createStatement();
		kq = st.executeQuery(sql);
		return kq;

	}
	
	public void insertTableFood(int id, int name, String status) throws SQLException {
		Statement st = conn.createStatement();
		String sql = "Insert into tablefood values(" + id + ",'" + name + "','" + status + "')";
		JOptionPane.showMessageDialog(null, "Thêm thành công");
		st.executeUpdate(sql);
	}
	
	public void updateTableFood(int id, int name, String status) throws SQLException {
		Statement st = conn.createStatement();
		String sql = "Update tablefood set idTableFood = '" + id  + "', tableName = '" + name + "', tableStatus = '" + status + "' where idTableFood = '" + id + "'";
		JOptionPane.showMessageDialog(null, "Sửa thành công");
		st.executeUpdate(sql);
	}
	
	public void deleteTableFood(int id) throws SQLException {
		Statement st = conn.createStatement();
		String sql = "Delete from tablefood where idTableFood = " + id + "";
		JOptionPane.showMessageDialog(null, "Xóa thành công");
		st.executeUpdate(sql);
	}
	
	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
