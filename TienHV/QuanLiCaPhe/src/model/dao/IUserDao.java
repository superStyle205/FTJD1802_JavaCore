package model.dao;

import java.sql.ResultSet;

public interface IUserDao {
	ResultSet getAllUser();
	
	boolean insertUser(String username, int type);
	
	boolean updateUser(String username, int type);
	
	boolean  deleteUser(String username);
	
	boolean resetPassword(String username);
}
