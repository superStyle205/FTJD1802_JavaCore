package model.bean;

public class Employees {
	private int idEmployees;
	private String username;
	private String fullName;
	private int age;
	private String address;
	private int phoneNumber;
	private String homeTown;
	private int identityCard;
	private int deleteValue;
	
	public Employees() {
		
	}
	
	public Employees(int idEmployees, String username, String fullName, int age, String address, int phoneNumber, String homeTown, int identityCard, int deleteValue) {
		this.idEmployees = idEmployees;
		this.username = username;
		this.fullName = fullName;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.homeTown = homeTown;
		this.identityCard = identityCard;
		this.deleteValue = deleteValue;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdEmployees() {
		return idEmployees;
	}

	public void setIdEmployees(int idEmployees) {
		this.idEmployees = idEmployees;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public int getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(int identityCard) {
		this.identityCard = identityCard;
	}

	public int getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(int deleteValue) {
		this.deleteValue = deleteValue;
	}
}
