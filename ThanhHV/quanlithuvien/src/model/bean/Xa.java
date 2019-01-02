package model.bean;

public class Xa {
	private String id;
	private String nameXa;
	private String nameQuan;
	private String namePhuong;

	public Xa(String id, String nameXa, String nameQuan, String namePhuong) {
		super();
		this.id = id;
		this.nameXa = nameXa;
		this.nameQuan = nameQuan;
		this.namePhuong = namePhuong;
	}

	public Xa() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameXa() {
		return nameXa;
	}

	public void setNameXa(String nameXa) {
		this.nameXa = nameXa;
	}

	public String getNameQuan() {
		return nameQuan;
	}

	public void setNameQuan(String nameQuan) {
		this.nameQuan = nameQuan;
	}

	public String getNamePhuong() {
		return namePhuong;
	}

	public void setNamePhuong(String namePhuong) {
		this.namePhuong = namePhuong;
	}

}
