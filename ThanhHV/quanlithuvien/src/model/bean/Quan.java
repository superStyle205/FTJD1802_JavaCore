package model.bean;

public class Quan {
	private String id;
	private String nameQuan;
	private String nameThanhPho;

	public Quan(String id, String nameQuan, String nameThanhPho) {
		super();
		this.id = id;
		this.nameQuan = nameQuan;
		this.nameThanhPho = nameThanhPho;
	}

	public Quan() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameQuan() {
		return nameQuan;
	}

	public void setNameQuan(String nameQuan) {
		this.nameQuan = nameQuan;
	}

	public String getNameThanhPho() {
		return nameThanhPho;
	}

	public void setNameThanhPho(String nameThanhPho) {
		this.nameThanhPho = nameThanhPho;
	}

}
