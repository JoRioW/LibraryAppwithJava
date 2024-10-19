import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book{
	private String Title;
	private String Author;
	
	public Book(String Title, String Author) {
		this.Title = Title;
		this.Author = Author;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String toString() {
		return "Book Title " + Title + " by " + Author;
	}
}

class Library {
	private List<Book> Books = new ArrayList<>();
	
	public void addBook(Book Book) {
		Books.add(Book);
	}
	
	public List<Book> getBooks(){
		return Books;
	}
	
	public boolean removeBook(String title) {
        for (Book book : Books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                Books.remove(book);
                return true;
            }
        }
        return false; 
    }
}

public class Main {
	
	private static void displayBooks(Library library) {
		List<Book> books = library.getBooks();
		for(Book book : books) {
			System.out.println(book);
		}
		System.out.print("\n");
	}
	
	private static void addBook(Scanner sc, Library library) {
		System.out.print("Enter Books Title : ");
		String Title = sc.nextLine();
		
		System.out.print("Enter Author Name : ");
		String Author = sc.nextLine();
		
		Book newBook = new Book(Title, Author);
		library.addBook(newBook);
		
		System.out.println("Book added : " + newBook + "\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library library = new Library();
		Scanner sc = new Scanner(System.in);
		
		Thread thread = new Thread(() ->{
			List<Book> books = library.getBooks();
			for(Book book : books) {
				System.out.println("Thread : " + Thread.currentThread().getId() + " " + book);
				
			}
		});
		
		thread.start();
		
		int choice;
		do {
			System.out.println("Menu");
			System.out.println("1. Display Books");
			System.out.println("2. Add Book");
			System.out.println("3. Remove Book");
			System.out.println("4. Exit");
			System.out.print("Enter your Choice : ");
			System.out.print("\n");
			
			choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case 1:
				displayBooks(library);
				break;
				
			case 2:
				addBook(sc, library);
				break;
				
			case 3:
				System.out.println("Enter Title of the Book to Remove : ");
				String titleToRemove = sc.nextLine();
				boolean removed = library.removeBook(titleToRemove);
				if (removed) {
					System.out.println("Book removed\n");
				} else {
					System.out.println("Book not found\n");
				}
				break;
				
			case 4:
				System.out.println("Exiting the application.");
			    break;
				
			default:
				System.out.println("invalid choice. please enter a valid option");
				break;
			}
		}while (choice != 4);
		
		sc.close();
	}

}
