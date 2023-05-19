//-----------------------------------------------------------
//Assignment 4
//Part: BookDriver
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-----------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//This program reads the records from "Books.txt", stores records with invalid years in an ArrayList, then prints those faulty records in "YearErr.txt".
//Valid records are stored in a LinkedList. An interactive menu allows to manipulate the LinkedList through method calls.

/**
 * BookDriver
 * @author hrag_gregory
 *
 */
public class BookDriver {

	/**
	 * Main method. Interactive menu to manipulate BookList
	 * @param args
	 */
	public static void main(String[] args) {
		
	ArrayList arrLst = new ArrayList<Book>();
	BookList bkLst = new BookList();
	
	//reading records from Books.txt
	Scanner sc = null;
	try {
		sc = new Scanner(new FileInputStream("Books.txt")); 
	}
	catch (FileNotFoundException e) {
		System.out.println("File not found!");
	}
	
	//processing records into Book objects
	while (sc.hasNextLine()) {
		String line = sc.nextLine();
		String[] fields = line.split(",");
		String title = fields[0];
		String author = fields[1];
		double price = Double.parseDouble(fields[2]);
		long ISBN = Long.parseLong(fields[3]);
		String genre = fields[4];
		int year = Integer.parseInt(fields[5]);
		Book book = new Book(title, author, price, ISBN, genre, year);
		//if invalid year, add Book to ArrayList
		if (year > 2023) {
			arrLst.add(book);
		}
		//if valid year, add Book to BookList
		else if (year <= 2023) {
			bkLst.addToEnd(book);
		}
	}
	sc.close();
	//printing all faulty records (contained in the ArrayList) into a .txt file
	if (arrLst.size() != 0) {
		PrintWriter pw = null;
		//writing to a .txt file
		try {
			pw = new PrintWriter(new FileOutputStream("YearErr.txt", true));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		for (int i = 0; i < arrLst.size(); i++) {
			pw.println(arrLst.get(i));
			}
		pw.close();
	}
	System.out.println("YearErr.txt file successfully created!");
	Scanner input = new Scanner(System.in);
	int select = 0;
	bkLst.displayContent(); //prints the current records found in BookList
	//interactive BookList menu
	do {
		displayMenu(); //prompts menu
		System.out.print("\nEnter your selection: ");
		
		//user input
		if(input.hasNextInt()) {
			select = input.nextInt();
		}
		
		//select 1: calls storeRecordsByYear()
		if (select == 1) {
			int year = 0;
				System.out.print("Enter a year: ");
				if (input.hasNextInt()) {
					year = input.nextInt();
				}
				if (year > 0 && year < 2024) {
					bkLst.storeRecordsByYear(year);
				}	
				else {
					System.out.println("Invalid year! Returning to menu!");
				}
			bkLst.displayContent();
		}
		
		//select 2: calls delConsecutiveRepeatedRecords()
		else if (select == 2) {
			if (bkLst.delConsecutiveRepeatedRecords()) {
				System.out.println("Here are the contents of the list after deleting all consecutive repeated records.");
				System.out.println("=========================================================================");
				bkLst.displayContent();
			}
			else {
				System.out.println("No repeated records found! Returning to main menu.");
			}
		}
		
		//select 3: calls extractAuthList(String aut)
		else if (select == 3) {
			System.out.print("Write the author name you are looking for: ");
			String trash = input.nextLine();
			String author = input.nextLine();
			bkLst.extractAuthList(author).displayContent();
		}
		
		//select 4: calls insertBefore(long isbn, Book b)
		else if (select == 4) {
			long isbn1 = 0;
			String title = "";
			String author = "";
			double price = 0;
			long isbn2 = 0;
			String genre = "";
			int year = 0;
			String trash;
			//validate inputs
			try {
				System.out.print("Insert ISBN of book you are looking for: ");
				isbn1 = input.nextLong();
				trash = input.nextLine();
				System.out.print("Insert title: ");
				title = input.nextLine();
				System.out.print("Insert author: ");
				author = input.nextLine();
				System.out.print("Insert price: ");
				price = input.nextDouble();
				if (price < 0) {
					throw new InputMismatchException(); 
				}
				System.out.print("Insert ISBN: ");
				isbn2 = input.nextLong();
				trash = input.nextLine();
				System.out.print("Insert genre: ");
				genre = input.next();
				System.out.print("Insert year: ");
				year = input.nextInt();
				if (year < 0 || year > 2023) {
					throw new InputMismatchException();
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Returning to main menu.");
				input = new Scanner(System.in);
			}
			Book book = new Book (title, author, price, isbn2, genre, year); //create Book object with user inputs
			if (bkLst.insertBefore(isbn1, book)) {
				System.out.println("Here are the contents of the list after inserting the new inputed record.");
				System.out.println("=========================================================================");
				bkLst.displayContent();
			}
			else {
				System.out.println("No matching ISBN found! Book has not been added! Returning to main menu.");
			}
		}
		
		//select 5: calls insertBetween(long isbn1, long isbn2, Book b)
		else if (select == 5) {
			long isbn1 = 0;
			long isbn2 = 0;
			String title = "";
			String author = "";
			double price = 0;
			long isbn3 = 0;
			String genre = "";
			int year = 0;
			String trash;
			//validating user inputs
			try {
				System.out.print("Insert ISBN of first book you are looking for: ");
				isbn1 = input.nextLong();
				System.out.print("Insert ISBN of second book you are looking for: ");
				isbn2 = input.nextLong();
				trash = input.nextLine();
				System.out.print("Insert title: ");
				title = input.nextLine();
				System.out.print("Insert author: ");
				author = input.nextLine();
				System.out.print("Insert price: ");
				price = input.nextDouble();
				if (price < 0) {
					throw new InputMismatchException(); 
				}
				System.out.print("Insert ISBN: ");
				isbn3 = input.nextLong();
				trash = input.nextLine();
				System.out.print("Insert genre: ");
				genre = input.next();
				System.out.print("Insert year: ");
				year = input.nextInt();
				if (year < 0 || year > 2023) {
					throw new InputMismatchException();
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Returning to main menu.");
				input = new Scanner(System.in);
			}
			Book book = new Book (title, author, price, isbn3, genre, year); //creating Book object with user inputs
			if (bkLst.insertBetween(isbn1, isbn2, book)) {
				System.out.println("Here are the contents of the list after inserting the new inputed record.");
				System.out.println("=========================================================================");
				bkLst.displayContent();
			}
			else {
				System.out.println("No matching ISBN pair found! Book has not been added! Returning to main menu.");
			}
		}
		
		//select 6: calls swap(long isbn1, long isbn2)
		else if (select == 6) {
			long isbn1 = 0;
			long isbn2 = 0;
			//validating inputs
			try {
				System.out.print("Insert ISBN of first book you are looking for: ");
				isbn1 = input.nextLong();
				System.out.print("Insert ISBN of second book you are looking for: ");
				isbn2 = input.nextLong();
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Returning to main menu.");
				input = new Scanner(System.in);
			}
			if (bkLst.swap(isbn1, isbn2)) {
				System.out.println("Here are the contents of the list after the given swap.");
				System.out.println("=========================================================================");
				bkLst.displayContent();
			}
			else {
				System.out.println("No matching ISBN pair found! Books were not swapped! Returning to main menu.");
			}
		}
		
		//select 7: calls commit()
		else if (select == 7) {
			bkLst.commit();
		}
		
		//select 8: terminates program
		else if (select == 8) {
			System.out.println("Terminating program!");
		}
		else
			System.out.println("Invalid input. Please try again!");
	} while (select != 8); //loops menu
	input.close();
	}
	
	/**
	 * static method for displaying menu
	 */
	public static void displayMenu() {
		System.out.println("\nTell me what you want to do? Let's Chat since this is trending now! Here are the options:");
		System.out.println("\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
		System.out.println("\t2) Ask me to delete all consecutive repeated records;");
		System.out.println("\t3) Give me an author name and I will create a new list with the records of this author and display them;");
		System.out.println("\t4) Give me an ISBN number and a Book object, and I will insert a Node with the book before the record with this ISBN;");
		System.out.println("\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!");
		System.out.println("\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
		System.out.println("\t7) Tell me to COMMIT! Your wish is my command. I will commit your list to a file called Updated_Books;");
		System.out.println("\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
	}
}


