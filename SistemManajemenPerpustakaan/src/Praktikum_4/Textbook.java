package Praktikum_4;

public class Textbook extends Book {
	private String genre;
	
	public Textbook(String title, String author, String genre) {
		super(title, author);
		this.genre = genre;
	}
	
	public String getGenre() { return genre; }
}
