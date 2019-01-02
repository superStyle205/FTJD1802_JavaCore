package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.database.ConnectionUtil;
import model.bean.Books;
import model.bean.Members;

public class BooksDao {
	private ConnectionUtil connectionUtil = new ConnectionUtil();

	private String id;
	private String title;
	private String author;
	private String genre;
	private String publishers;
	private String year;
	private int numberOfBooks;
	private int soLuongHienCo;
	private AuthorsDao authorsDao = new AuthorsDao();
	private GenresDao genresDao = new GenresDao();
	private PublishersDao publishersDao = new PublishersDao();

	public Books getBooksById(String id) {
		String sql = "SELECT * FROM sach WHERE masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				this.id = resultSet.getString("masach");
				title = resultSet.getString("tensach");
				author = authorsDao.getNameById(resultSet.getString("matacgia"));
				genre = genresDao.getNameGenresById(resultSet.getString("matheloai"));
				publishers = publishersDao.getNamePublishersById(resultSet.getString("manxb"));
				year = resultSet.getString("namxuatban");
				numberOfBooks = resultSet.getInt("soluong");
				soLuongHienCo = resultSet.getInt("soluonghienco");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return new Books(id, title, author, genre, publishers, year, numberOfBooks, soLuongHienCo);
		}
	}

	public boolean addBooks(Books books) {
		String sql = "INSERT INTO sach VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, books.getId());
			statement.setString(2, books.getTitle());
			statement.setString(3, authorsDao.getIdAuthorsByName(books.getAuthor()));
			statement.setString(4, genresDao.getIdGenresByName(books.getGenre()));
			statement.setString(5, publishersDao.getIdPublishersByName(books.getPublisher()));
			statement.setString(6, books.getYear());
			statement.setInt(7, books.getNumberOfBooks());
			statement.setInt(8, books.getSoLuongHienCo());
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

	public boolean removeBooksById(String id) {
		String sql = "UPDATE sach SET deleted = ? WHERE  masach = ?";
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
		return status > 0 ? true : false;
	}

	public List<String> getAllBooksId() {
		String sql = "SELECT * FROM sach WHERE deleted = 0";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> listId = new ArrayList<String>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				listId.add(resultSet.getString("masach"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return listId;
		}
	}

	public String getNameBooksById(String id) {
		String sql = "SELECT * FROM sach WHERE masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				title = resultSet.getString("tensach");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return title;
		}
	}

	public int getSoLuongHienCoById(String id) {
		String sql = "SELECT * FROM sach WHERE masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				soLuongHienCo = resultSet.getInt("soluonghienco");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeConnection(connection);
			connectionUtil.closeResultSet(resultSet);
			connectionUtil.closeStatement(statement);
			return soLuongHienCo;
		}
	}

	public boolean updateBooks(Books books) {
		String sql = "UPDATE sach SET tensach = ? , matacgia = ?, matheloai = ?, manxb = ?, namxuatban = ?, soluong = ?, soluonghienco = ? WHERE  masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, books.getTitle());
			statement.setString(2, authorsDao.getIdAuthorsByName(books.getAuthor()));
			statement.setString(3, genresDao.getIdGenresByName(books.getGenre()));
			statement.setString(4, publishersDao.getIdPublishersByName(books.getPublisher()));
			statement.setString(5, books.getYear());
			statement.setInt(6, books.getNumberOfBooks());
			statement.setInt(7, books.getSoLuongHienCo());
			statement.setString(8, books.getId());
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean borrowBooks(String idSach) {
		String sql = "UPDATE sach SET  soluonghienco = ? WHERE  masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, getSoLuongHienCoById(idSach) - 1);
			statement.setString(2, idSach);
			status = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionUtil.closeStatement(statement);
			connectionUtil.closeConnection(connection);
		}
		return status > 0 ? true : false;
	}

	public boolean returnBooks(String idSach) {
		String sql = "UPDATE sach SET  soluonghienco = ? WHERE  masach = ?";
		Connection connection = connectionUtil.getConnection();
		PreparedStatement statement = null;
		int status = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, getSoLuongHienCoById(idSach) + 1);
			statement.setString(2, idSach);
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
		String sql = "SELECT * FROM sach WHERE masach = ?";
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
