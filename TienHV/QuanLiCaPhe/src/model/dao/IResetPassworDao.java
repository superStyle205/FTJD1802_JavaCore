package model.dao;

public interface IResetPassworDao {
	boolean updatePassword(String username, String txtNewPassword);
}
