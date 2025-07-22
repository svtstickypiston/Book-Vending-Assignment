

public class Book {
	private String author;
	private String title;
	private String content;
	private int edition;
	
	public Book (String t, String a, String c, int e) {		
		author = a;
		title = t;
		content = c;
		edition = e;
	}
	
	public int getPages () {
		return (content.length() + 699) / 700;
	}
	
	public String toString () {
		String edStr = Integer.toString(edition);
		return "Title: " + title + "\nAuthor: " + author + "\nEdition: " + edStr;
	}
	
	public String getAuthor () {
		return author;
	}
	
	public String getTitle () {
		return title;
	}
	
	public String getContent () {
		return content;
	}
	
	public int getEdition () {
		return edition;
	}
}