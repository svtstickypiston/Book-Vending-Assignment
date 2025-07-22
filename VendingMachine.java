import java.util.List;
import java.util.ArrayList;


public class VendingMachine {
	private List<Book> shelf = new ArrayList<>();
	private double locationFactor;
	private int cassette;
	private int safe;
	private String password;
	
	public VendingMachine(double l, String p) {
		locationFactor = l;
		password = p;
	}
	
	public int getCassette () {
		return cassette;
	}
	
	public void insertCoin (int coin) {
		if (coin == 1 || coin == 2 || coin == 5 || coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200) {
			cassette = cassette + coin;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public int returnCoins () {
		int value = cassette;
		cassette = 0;
		return value;
	}
	
	public void restock (List<Book> Books, String p) {
		if (p == password) {
			for (int i = 0; i < Books.size(); i++) {
				shelf.add(Books.get(i));
			}
		}
		else {
			throw new InvalidPasswordException("Invalid Password");
		}
	}
	
	public int emptySafe(String p) {
		if (p == password) {
			int value = safe;
			safe = 0;
			return value;
		}
		else {
			throw new InvalidPasswordException("Invalid Password");
		}
	}
	
	public List<String> getCatalogue() {
		List<String> bookstrings = new ArrayList<>();
		for (int i = 0; i < shelf.size(); i++) {
			bookstrings.add((shelf.get(i)).toString());
		}
		return bookstrings;
	}
	
	public int getPrice(int index) {
		Book book = shelf.get(index);
		int price = (int) Math.ceil(book.getPages()*locationFactor);
		return price;
	}
	
	public Book buyBook(int index) {
		if (index >= 0 && index < shelf.size()) {
			if (cassette >= getPrice(index)) {
				Book book = shelf.get(index);
				cassette = cassette - getPrice(index);
				return book;
			}
			else {
				throw new CassetteException();
			}
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
}