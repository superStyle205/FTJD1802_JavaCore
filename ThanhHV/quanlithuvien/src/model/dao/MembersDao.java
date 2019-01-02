package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Members;

public class MembersDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	private String id;
	private String fullName;
	private String phoneNumber;
	private String email;
	private String homeNumber;
	private String streetName;
	private String tenPhuong;
	private String tenQuan;
	private String tenThanhPho;
	private int numberOfBooks;
	private String idXa;
	private XaDao xaDao = new XaDao();

	public Members getMembersById(String idMember) {
		String sql = "SELECT * FROM bandoc WHERE mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, idMember);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getString("mabandoc");
				fullName = resultSet.getString("hoten");
				phoneNumber = resultSet.getString("dienthoai");
				email = resultSet.getString("email");
				homeNumber = resultSet.getString("sonha");
				streetName = resultSet.getString("tenduong");
				idXa = resultSet.getString("maxa");
				tenPhuong = xaDao.getNameByIdXa(idXa);
				tenQuan = xaDao.getNameQuanByIdXa(idXa);
				tenThanhPho = xaDao.getNameThanhPhoByIdXa(idXa);
				numberOfBooks = resultSet.getInt("sosachdangmuon");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return new Members(id, fullName, phoneNumber, email, homeNumber, streetName, tenPhuong, tenQuan,
					tenThanhPho, numberOfBooks);
		}
	}

	public List<String> getAllMembersId() {
		String sql = "SELECT * FROM bandoc WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listIdMembers = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listIdMembers.add(resultSet.getString("mabandoc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listIdMembers;
		}
	}

	public boolean addMembers(Members members) {
		String sql = "INSERT INTO bandoc VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, members.getId());
			statement.setString(2, members.getFullName());
			statement.setString(3, members.getPhoneNumber());
			statement.setString(4, members.getEmail());
			statement.setString(5, members.getHomeNumber());
			statement.setString(6, members.getStreetName());
			statement.setString(7, xaDao.getIdXaByNameXa(members.getTenPhuong()));
			statement.setInt(8, 0);
			statement.setInt(9, 0);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean removeMembersById(String id) {
		MuonTraDao muonTraDao = new MuonTraDao();

		String sql = "UPDATE bandoc SET deleted = ? WHERE  mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, id);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		muonTraDao.removeMaMuonTraByMaBanDoc(id);
		return status > 0 ? true : false;

	}

	public boolean updateMembers(Members members) {
		String sql = "UPDATE bandoc SET hoten = ? , dienthoai = ?, email = ?, sonha = ?, tenduong = ?, maxa = ?, sosachdangmuon = ? WHERE  mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, members.getFullName());
			statement.setString(2, members.getPhoneNumber());
			statement.setString(3, members.getEmail());
			statement.setString(4, members.getHomeNumber());
			statement.setString(5, members.getStreetName());
			statement.setString(6, xaDao.getIdXaByNameXa(members.getTenPhuong()));
			statement.setInt(7, members.getNumberOfBooks());
			statement.setString(8, members.getId());
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean checkIdExisted(String id) {
		String sql = "SELECT * FROM bandoc WHERE mabandoc = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean result = true;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			result = resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return result;
		}
	}
}
