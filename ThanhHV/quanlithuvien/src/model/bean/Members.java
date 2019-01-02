package model.bean;

public class Members {
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

	public Members() {

	}

	public Members(String id, String fullName, String phoneNumber, String email, String homeNumber, String streetName,
			String tenPhuong, String tenQuan, String tenThanhPho, int numberOfBooks) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.homeNumber = homeNumber;
		this.streetName = streetName;
		this.tenPhuong = tenPhuong;
		this.tenQuan = tenQuan;
		this.tenThanhPho = tenThanhPho;
		this.numberOfBooks = numberOfBooks;
	}

	public Members(String id, String fullName, String phoneNumber, String email, String homeNumber, String streetName,
			String tenPhuong, String tenQuan, String tenThanhPho) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.homeNumber = homeNumber;
		this.streetName = streetName;
		this.tenPhuong = tenPhuong;
		this.tenQuan = tenQuan;
		this.tenThanhPho = tenThanhPho;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getTenPhuong() {
		return tenPhuong;
	}

	public void setTenPhuong(String tenPhuong) {
		this.tenPhuong = tenPhuong;
	}

	public String getTenQuan() {
		return tenQuan;
	}

	public void setTenQuan(String tenQuan) {
		this.tenQuan = tenQuan;
	}

	public String getTenThanhPho() {
		return tenThanhPho;
	}

	public void setTenThanhPho(String tenThanhPho) {
		this.tenThanhPho = tenThanhPho;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

}
