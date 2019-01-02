package model.bean;

public class Books {
	private String id;
	private String title;
	private String author;
	private String genre;
	private String publisher;
	private String year;
	private int numberOfBooks;
	private int soLuongHienCo;

	public Books() {

	}

	public Books(String id, String title, String author, String genre, String publisher, String year, int numberOfBooks,
			int soLuongHienCo) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.year = year;
		this.numberOfBooks = numberOfBooks;
		this.soLuongHienCo = soLuongHienCo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public void setSoLuongHienCo(int soLuongHienCo) {
		this.soLuongHienCo = soLuongHienCo;
	}

	public int getSoLuongHienCo() {
		return soLuongHienCo;
	}

}
