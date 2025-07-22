import java.io.File;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Press {
	private Map<String, List<Book>> shelf;
	private int shelfSize;
	private Map<String, Integer> edition = new HashMap<>();
	
	public Press (String pathToBookDir, int s) {
		shelfSize = s;
		shelf = new HashMap<>();
		edition = new HashMap<>();
		
		File directoryPath = new File(pathToBookDir);
		File filesList[] = directoryPath.listFiles();
		int iterate = 0;
		for (File file : filesList) {
			iterate = iterate + 1;
			if (iterate < shelfSize) {
				shelf.put(file.getName(), new ArrayList<Book>());
				edition.put(file.getName(), 0);
			}
		}
	}
	
	protected Book print(String bookID, int e) throws IOException {
		String title = "";
		String author = "";
		String content = "";
		boolean foundContent = false;
		
		File file = new File(bookID);
		ArrayList<String> yourList;
		try {
			yourList = (ArrayList<String>) Files.readAllLines(file.toPath());
		}
		catch(IOException ioe) {
			throw ioe;
		}
		
		for (int i = 0; i < yourList.size(); i++) {
			String[] stringArray = yourList.get(i).split(" ", 2);
			if (foundContent = true) {
				content = content + yourList.get(i);
			}
			else if (stringArray[0].equals("Title:")) {
				title = stringArray[1];
			}
			else if (stringArray[0].equals("Author:")) {
				author = stringArray[1];
			}
			else if (yourList.get(i).split(" ", 4)[0].equals("***") && yourList.get(i).split(" ", 4)[1].equals("START") && yourList.get(i).split(" ", 4)[1].equals("OF")) {
				foundContent = true;
			}
		}
		
		if (title.equals("") || author.equals("") || content.equals("")) {
			throw new IOException();
		}
		
		Book book = new Book(title, author, content, e);
		return book;
	}
}