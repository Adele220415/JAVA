// Package declaration for organizing classes
package library;
// Importing utility classes (Scanner, Map, HashMap)
import java.util.*;

// Book class represents a book in the library
 class Book {
    // Unique identifier for the book
    private int id;
    // Title of the book
    private String title;
    // Author of the book
    private String author;
    // Availability status
    private boolean isAvailable;

    // Constructor to initialize book details
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Book is available by default
    }

    // Getter for book ID
    public int getId() {
        return id;
    }

    // Getter for book title
    public String getTitle() {
        return title;
    }

    // Getter for book author
    public String getAuthor() {
        return author;
    }

    // Check if the book is available
    public boolean isAvailable() {
        return isAvailable;
    }

    // Mark the book as borrowed
    public void borrowBook() {
        isAvailable = false;
    }

    // Mark the book as returned
    public void returnBook() {
        isAvailable = true;
    }

    // String representation of the book
    @Override
    public String toString() {
        return "Book ID: " + id +
               ", Title: '" + title + '\'' +
               ", Author: '" + author + '\'' +
               ", Status: " + (isAvailable ? "Available" : "Borrowed");
    }
}

// Main class for the Library Management System
public class LibraryManagementSystem {
    // Map to store books with their ID as key
    private static Map<Integer, Book> books = new HashMap<>();
    // Next available book ID
    private static int nextBookId = 1;
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    // Entry point of the program
    public static void main(String[] args) {
        boolean running = true; // Flag to keep the program running

        // Main loop for the menu
        while (running) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine(); // Read user choice

            // Handle user choice
            switch (choice) {
                case "1":
                    addBook(); // Add a new book
                    break;
                case "2":
                    viewBooks(); // View all books
                    break;
                case "3":
                    borrowBook(); // Borrow a book
                    break;
                case "4":
                    returnBook(); // Return a book
                    break;
                case "5":
                    running = false; // Exit the program
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Method to add a new book to the library
    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim(); // Get book title

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim(); // Get author name

        Book book = new Book(nextBookId, title, author); // Create new book
        books.put(nextBookId, book); // Add book to map
        System.out.println("Book added with ID: " + nextBookId);
        nextBookId++; // Increment book ID for next book
    }

    // Method to display all books in the library
    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (Book book : books.values()) {
            System.out.println(book); // Print book details
        }
    }

    // Method to borrow a book by ID
    private static void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int id = Integer.parseInt(scanner.nextLine()); // Read book ID

        Book book = books.get(id); // Get book from map
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.borrowBook(); // Mark book as borrowed
            System.out.println("You borrowed the book: " + book.getTitle());
        }
    }

    // Method to return a borrowed book by ID
    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = Integer.parseInt(scanner.nextLine()); // Read book ID

        Book book = books.get(id); // Get book from map
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("This book was not borrowed.");
        } else {
            book.returnBook(); // Mark book as returned
            System.out.println("Book returned: " + book.getTitle());
        }
    }
}
