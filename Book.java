//-----------------------------------------------------------
//Assignment 4
//Part: Book Class
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-----------------------------------------------------------

/**
 * Book class
 * @author hrag_gregory
 *
 */
public class Book {

	/**
	 * Book attributes
	 */
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;
	
	/**
	 * parameterized constructor for Book
	 * @param t
	 * @param a
	 * @param p
	 * @param i
	 * @param g
	 * @param y
	 */
	public Book(String t, String a, double p, long i, String g, int y) {
		title = t;
		author = a;
		price = p;
		ISBN = i;
		genre = g;
		year = y;
	}
	
	/**
	 * title accessor
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * title mutator
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * author accessor
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * author mutator
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * price accessor
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * price mutator
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * ISBN accessor
	 * @return
	 */
	public long getISBN() {
		return ISBN;
	}

	/**
	 * ISBN mutator
	 * @param iSBN
	 */
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	/**
	 * genre accessor
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * genre mutator
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * year accessor
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * year mutator
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * overriding toString for Book
	 */
	public String toString() {
		return(title + ", " + author + ", " + price + ", " + ISBN + ", " + genre + ", " + year);
	}
	
	/**
	 * overriding equals method for Book
	 */
	public boolean equals(Object o) {
		if (o instanceof Book) {
			return (title.equals(((Book)o).title) && author.equals(((Book)o).author) && price == ((Book)o).price && ISBN == ((Book)o).ISBN && genre.equals(((Book)o).genre) && year == ((Book)o).year);
		}
		else return false;
	}
}
