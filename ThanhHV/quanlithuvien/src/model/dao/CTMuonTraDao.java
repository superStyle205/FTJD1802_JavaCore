package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Members;

public class CTMuonTraDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	public CTMuonTraDao() {

	}

	public List<String> getAllMaCTMuonTraByMaMuonTra(String maMuonTra) {
		String sql = "SELECT * FROM chitietmuontra WHERE mamuontra = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listMaCTMuonTra = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, maMuonTra);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listMaCTMuonTra.add(resultSet.getString("mactmuontra"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listMaCTMuonTra;
		}
	}

	public boolean removeMaCTMuonTraByMaMuonTra(String maMuonTra) {
		String sql = "UPDATE chitietmuontra SET deleted = ? WHERE  mamuontra = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, maMuonTra);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}

		return status > 0 ? true : false;

	}
}
