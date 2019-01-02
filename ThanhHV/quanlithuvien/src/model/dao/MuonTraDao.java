package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Members;

public class MuonTraDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	public MuonTraDao() {

	}

	public List<String> getAllMaMuonTraByMaBanDoc(String idMember) {
		String sql = "SELECT * FROM muontra WHERE mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listMaMuonTra = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idMember);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listMaMuonTra.add(resultSet.getString("mamuontra"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listMaMuonTra;
		}
	}

	public boolean removeMaMuonTraByMaBanDoc(String idMember) {
		String sql = "UPDATE muontra SET deleted = ? WHERE  mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, idMember);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean borrowBooks(String idBanDoc, String idSach, String ngayMuon) {
		String sql = "INSERT INTO muontra (mabandoc, masach, ngaymuon, ngaytra, datra, deleted ) VALUES(?, ?, ?, ?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idBanDoc);
			statement.setString(2, idSach);
			statement.setString(3, ngayMuon);
			statement.setString(4, null);
			statement.setInt(5, 0);
			statement.setInt(6, 0);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean returnBooks(String idBanDoc, String idSach, String ngayTra) {
		String sql = "UPDATE muontra SET ngaytra = ?, datra = ?  WHERE  mabandoc = ? and  masach = ? and deleted = 0 and datra = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, ngayTra);
			statement.setInt(2, 1);
			statement.setString(3, idBanDoc);
			statement.setString(4, idSach);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean returnable(String idMember) {
		String sql = "SELECT * FROM muontra WHERE mabandoc = ? and datra = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idMember);
			resultSet = statement.executeQuery();
			result = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return (!result);
		}
	}

	public boolean canDeletedBooks(String idBook) {
		String sql = "SELECT * FROM muontra WHERE masach = ? and datra = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idBook);
			resultSet = statement.executeQuery();
			result = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return (!result);
		}
	}
	public boolean isBorrowed(String idMember, String idBook) {
		String sql = "SELECT * FROM muontra WHERE masach = ? and mabandoc = ? and datra = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idBook);
			statement.setString(2, idMember);
			resultSet = statement.executeQuery();
			result = resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return (!result);
		}
	}
	public boolean isReturned(String idMember, String idBook) {
		String sql = "SELECT * FROM muontra WHERE masach = ? and mabandoc = ? and datra = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idBook);
			statement.setString(2, idMember);
			resultSet = statement.executeQuery();
			result = resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return (result);
		}
	}

}
