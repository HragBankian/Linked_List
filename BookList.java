//-----------------------------------------------------------
//Assignment 4
//Part: BookList Class
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-----------------------------------------------------------

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * BookList class (singular circular LinkedList)
 * @author hrag_gregory
 *
 */
public class BookList {

	/**
	 * private inner Node class
	 * @author hrag_gregory
	 *
	 */
	private class Node {

		/**
		 * Node attributes
		 */
		private Book b;
		private Node next;
		
		/**
		 * parameterized constructor for Node
		 * @param book
		 * @param n
		 */
		public Node(Book book, Node n) {
			b = book;
			next = n;
		}
		
	}
	
	/**
	 * BookList attribute
	 */
	private Node head;
	
	/**
	 * default constructor for BookList
	 */
	public BookList() {
		head = null;
	}
	
	/**
	 * adds a Node to the beginning of a BookList
	 * @param b
	 */
	public void addToStart(Book b) {
		if (head == null) {
			head = new Node(b, head);
			head.next = head;
		}
		else {
			Node temp = head;
			head = new Node (b, temp);
			Node t = head;
			do {
				t = t.next;
			} while (t.next != temp);
			t.next = head;
		}
	}
	
	/**
	 * adds a Node to the end of a BookList
	 * @param b
	 */
	public void addToEnd(Book b) {
		if (head == null) {
			head = new Node(b, head);
			head.next = head;
		}
		else {
			Node t = head;
			do { 
				t = t.next;
			} while (t.next != head);
			Node endNode = new Node (b, head);
			t.next = endNode;
		}
	}
	
	/**
	 * creates a .txt file with all records from a given year. File is named after the given year.
	 * @param yr
	 */
	public void storeRecordsByYear(int yr) {
		Node t = head;
		PrintWriter pw = null;
		String year = Integer.toString(yr);
		boolean validYear = false;
		do {
			if (t.b.getYear() == yr) {
				validYear = true;
				try {
					pw = new PrintWriter(new FileOutputStream(year + ".txt", true));
					pw.println(t.b);
					pw.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("File not found!");
				}
			}
			t = t.next;
		} while (t != head);
		if (validYear) {
			System.out.println("File successfully created!");
		}
		else {
			System.out.println("Matching year was not found! File was not created!");
		}
	}
	
	/**
	 * inserts a Node before a given Node containing the matching ISBN
	 * @param isbn
	 * @param b
	 * @return
	 */
	public boolean insertBefore(long isbn, Book b) {
		if (head == null) {
			return false;
		}
		Node t = head;
		Node temp = null;
		boolean isbnFound = false;
		if (head.b.getISBN() == isbn) {
			addToStart(b);
			return true;
		}
		else do {
				if (t.b.getISBN() == isbn) {
					temp = t;
					isbnFound = true;
					break;
				}
				t = t.next;
			} while (t != head);
			if (isbnFound) {
				t = head;
				do {
					t = t.next;
				} while (t.next != temp);
					Node add = new Node(b, temp);
					t.next = add;
				return true;
			}
			else return false;
		}
	
	/**
	 * inserts a Node between two consecutive Nodes, each with a given ISBN
	 * @param isbn1
	 * @param isbn2
	 * @param b
	 * @return
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		if (head == null || head.next == head) {
			return false;
		}
		Node t = head;
		Node temp1 = null;
		Node temp2 = null;
		boolean isbnFound = false;
		do {
			if (t.b.getISBN() == isbn1 && t.next.b.getISBN() == isbn2) {
				temp1 = t;
				temp2 = t.next;
				isbnFound = true;
				break;
			}
			t = t.next;
		} while (t != head);
		if (isbnFound) {
			Node add = new Node(b, temp2);
			temp1.next = add;
			return true;
		}
		else return false;
	}
	
	/**
	 * prints out all the records currently found in BookList
	 */
	public void displayContent() {
		System.out.println();
		if (head == null) {
			System.out.println("List is empty!");
		}
		Node t = head;
		do {
			System.out.println(t.b + " ==>");
			t = t.next;
		} while(t != head);
		System.out.println(" ==> head\n");
	}
	
	/**
	 * deletes duplicate consecutive records
	 * @return
	 */
	public boolean delConsecutiveRepeatedRecords() {
		if (head == null) {
			return false;
		}
		Node t = head;
		boolean found = false;
		do {
			if ((t.b).equals((t.next).b)) {
				t.next = (t.next).next;
				found = true;
			}
			t = t.next;
		} while (t.next != head);
		if (t.b.equals(t.next.b)) {
			t.next = t.next.next;
			head = t.next;
			found = true;
		}
		if (found) {
			return true;
		}
		else return false;
	}
	
	/**
	 * returns a new BookList with all records from a given author
	 * @param aut
	 * @return
	 */
	public BookList extractAuthList(String aut) {
		if (head == null) {
			return new BookList();
		}
		Node t = head;
		BookList authorList = new BookList();
		do {
			if (t.b.getAuthor().equals(aut)) {
				authorList.addToEnd(t.b);
			}
			t = t.next;
		} while (t != head);
		return authorList;
	}
	
	/**
	 * swaps two Nodes, each with a given ISBN
	 * @param isbn1
	 * @param isbn2
	 * @return
	 */
	public boolean swap(long isbn1, long isbn2) {
		if (head == null) {
			return false;
		}
		Node t = head;
		Node temp1 = null;
		Node temp1Previous = null;
		Node temp1Next = null;
		Node temp2 = null;
		Node temp2Previous = null;
		Node temp2Next = null;
		boolean isbn1Found = false;
		boolean isbn2Found = false;
		do {
			if (t.b.getISBN() == isbn1) { //looking for Node with matching ISBN1
				temp1 = t;
				temp1Next = t.next;
				isbn1Found = true;
				break;
			}
			t = t.next;
		} while (t != head);
		t= head;
		if (isbn1Found) {
			while (t.next != temp1) {
				t = t.next;
			}
			temp1Previous = t;
		}
		t = head;
		do {
			if (t.b.getISBN() == isbn2) { //looking for Node with matching ISBN2
				temp2 = t;
				temp2Next = t.next;
				isbn2Found = true;
				break;
			}
			t = t.next;
		} while (t != head);
		if (isbn1Found && isbn2Found) { //if both ISBN's exist
			t = head;
			while (t.next != temp2) {
				t = t.next;
			}
			temp2Previous = t;
			if (temp1.next == temp2 || temp2.next == temp1) { //if Nodes that need to be swapped are consecutive
				if (temp1 == head) { //if both Nodes are consecutive, and first Node is head
					temp2Previous.next = temp1;
					temp2.next = temp1Next;
					temp1.next = temp2;
					head = temp2;
					return true;
				}
				if (temp2 == head) { //if both Nodes are consecutive, and second Node is head
					temp1Previous.next = temp2;
					temp1.next = temp2Next;
					temp2.next = temp1;
					head = temp1;
					return true;
				}
				temp1Previous.next = temp2;
				temp2.next = temp1;
				temp1.next = temp2Next;
				return true;
			}
			temp2Previous.next = temp1;
			temp1.next = temp2Next;
			temp1Previous.next = temp2;
			temp2.next = temp1Next;
			return true;
		}
		else return false;
	}
	
	/**
	 * prints all current records of BookList in a .txt file
	 */
	public void commit() {
		if (head == null) {
			System.out.println("Cannot commit! BookList is empty!");
		}
		else {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileOutputStream("Update_Books.txt"));
				Node t = head;
				do {
					pw.println(t.b);
					t = t.next;
				} while (t != head);
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}
			pw.close();
			System.out.println("Update_Books.txt has successfully been created!");
		}
	}
	}
