package model.bean;

public class User {
	private String username;
	private String password;
	private int type;
	private int deleteValue;
	
	public User() {
		
	}
	
	public User(String username, String password, int type, int deleteValue) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.deleteValue = deleteValue;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(int deleteValue) {
		this.deleteValue = deleteValue;
	}
	
	
}
