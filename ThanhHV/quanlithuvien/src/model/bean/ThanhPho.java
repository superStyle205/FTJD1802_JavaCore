package model.bean;

public class ThanhPho {
	private String id;
	private String nameThanhPho;

	public ThanhPho(String id, String nameThanhPho) {
		super();
		this.id = id;
		this.nameThanhPho = nameThanhPho;
	}

	public ThanhPho() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameThanhPho() {
		return nameThanhPho;
	}

	public void setNameThanhPho(String nameThanhPho) {
		this.nameThanhPho = nameThanhPho;
	}

}
