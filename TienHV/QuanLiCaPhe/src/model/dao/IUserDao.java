package model.dao;

import java.util.List;
import model.bean.User;

public interface IUserDao {
	List<User> getAllUser();
	
	boolean insertUser(User user);
	
	boolean updateUser(User user);
	
	boolean  deleteUser(User user);
	
	boolean resetPassword(User user);
	
	boolean changePassword(User user);
}
