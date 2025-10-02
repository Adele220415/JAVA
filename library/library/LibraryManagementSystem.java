
// // Package declaration for organizing classes
// package library;


// // Importing necessary Swing and AWT classes for GUI
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.io.*;
// // Importing utility classes for data structures
// import java.util.*;

// public class LibraryManagementSystem {
//     // Map to store books with their ID as key
//     private static java.util.Map<Integer, Book> books = new java.util.HashMap<>();
//     // Next available book ID
//     private static int nextBookId = 1;
//     private static final String FILE_NAME = "books.dat";

//     /**
//      * Main entry point. Launches the GUI on the Event Dispatch Thread.
//      */
//     public static void main(String[] args) {
//         loadBooksFromFile(); // Load at startup
//         // Ensures thread safety for Swing components
//         SwingUtilities.invokeLater(() -> {
//             new LibraryGUI();

//             // Save automatically when closing
//             gui.addWindowListener(new java.awt.event.WindowAdapter() {
//                 @Override
//                 public void windowClosing(java.awt.event.WindowEvent e) {
//                     saveBooksToFile();
//                     System.exit(0);
//                 }
//             });
//         });
//     }

//     // Save books to file
//     private static void saveBooksToFile() {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
//             oos.writeObject(books);
//             oos.writeInt(nextBookId);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Load books from file
//     @SuppressWarnings("unchecked")
//     private static void loadBooksFromFile() {
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
//             books = (Map<Integer, Book>) ois.readObject();
//             nextBookId = ois.readInt();
//         } catch (Exception e) {
//             books = new HashMap<>();
//             nextBookId = 1;
//         }
//     }

//     /**
//      * Inner class for the main application window (GUI).
//      * Extends JFrame to create the main window.
//      */
//     static class LibraryGUI extends JFrame {
//         // Table model for managing book data in the table
//         private DefaultTableModel tableModel;
//         // Table to display books
//         private JTable bookTable;

//         /**
//          * Constructor sets up the GUI components and layout.
//          */

//         public LibraryGUI() {
//             // Set window title
//             setTitle("Library Management System");
//             // Set window size
//             setSize(600, 400);
//             // Close the application when the window is closed
//             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             // Center the window on the screen
//             setLocationRelativeTo(null);

//             // Define table columns
//             String[] columns = {"ID", "Title", "Author", "Status"};
//             // Create a table model with columns and no rows
//             tableModel = new DefaultTableModel(columns, 0) {
//                 // Make all cells non-editable
//                 @Override
//                 public boolean isCellEditable(int row, int column) {
//                     return false;
//                 }
//             };
//             // Create the table and wrap it in a scroll pane
//             bookTable = new JTable(tableModel);
//             JScrollPane scrollPane = new JScrollPane(bookTable);

//             // Create buttons for actions
//             JButton addButton = new JButton("Add Book");
//             JButton borrowButton = new JButton("Borrow Book");
//             JButton returnButton = new JButton("Return Book");
//             JButton refreshButton = new JButton("Refresh List");

//             // Panel to hold the buttons
//             JPanel buttonPanel = new JPanel();
//             buttonPanel.add(addButton);
//             buttonPanel.add(borrowButton);
//             buttonPanel.add(returnButton);
//             buttonPanel.add(refreshButton);

//             // Set the layout of the window
//             setLayout(new BorderLayout());
//             // Add the table (center) and buttons (bottom) to the window
//             add(scrollPane, BorderLayout.CENTER);
//             add(buttonPanel, BorderLayout.SOUTH);

//             // Add event listeners to buttons
//             addButton.addActionListener(e -> addBookDialog());
//             borrowButton.addActionListener(e -> borrowBookDialog());
//             returnButton.addActionListener(e -> returnBookDialog());
//             refreshButton.addActionListener(e -> refreshTable());

//             // Populate the table with current books
//             refreshTable();
//             // Make the window visible
//             setVisible(true);
//         }

//         /**
//          * Show a dialog to add a new book to the library.
//          * Prompts for title and author, validates input, and updates the table.
//          */
//         private void addBookDialog() {
//             JTextField titleField = new JTextField();
//             JTextField authorField = new JTextField();
//             Object[] message = {
//                 "Title:", titleField,
//                 "Author:", authorField
//             };
//             int option = JOptionPane.showConfirmDialog(this, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
//             if (option == JOptionPane.OK_OPTION) {
//                 String title = titleField.getText().trim();
//                 String author = authorField.getText().trim();
//                 if (!title.isEmpty() && !author.isEmpty()) {
//                     // Create and add the new book
//                     Book book = new Book(nextBookId, title, author);
//                     books.put(nextBookId, book);
//                     nextBookId++;
//                     refreshTable();
//                 } else {
//                     // Show error if fields are empty
//                     JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Input Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         }

//         /**
//          * Show a dialog to borrow a book by ID.
//          * Prompts for ID, checks availability, and updates the table.
//          */
//         private void borrowBookDialog() {
//             String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to borrow:");
//             if (idStr == null) return; // Cancelled
//             try {
//                 int id = Integer.parseInt(idStr.trim());
//                 Book book = books.get(id);
//                 if (book == null) {
//                     // Book not found
//                     JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else if (!book.isAvailable()) {
//                     // Book already borrowed
//                     JOptionPane.showMessageDialog(this, "Book is already borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     // Borrow the book
//                     book.borrowBook();
//                     JOptionPane.showMessageDialog(this, "You borrowed the book: " + book.getTitle());
//                     refreshTable();
//                 }
//             } catch (NumberFormatException ex) {
//                 // Invalid ID entered
//                 JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }

//         /**
//          * Show a dialog to return a borrowed book by ID.
//          * Prompts for ID, checks if borrowed, and updates the table.
//          */
//         private void returnBookDialog() {
//             String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to return:");
//             if (idStr == null) return; // Cancelled
//             try {
//                 int id = Integer.parseInt(idStr.trim());
//                 Book book = books.get(id);
//                 if (book == null) {
//                     // Book not found
//                     JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else if (book.isAvailable()) {
//                     // Book was not borrowed
//                     JOptionPane.showMessageDialog(this, "This book was not borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     // Return the book
//                     book.returnBook();
//                     JOptionPane.showMessageDialog(this, "Book returned: " + book.getTitle());
//                     refreshTable();
//                 }
//             } catch (NumberFormatException ex) {
//                 // Invalid ID entered
//                 JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }

//         /**
//          * Refresh the table to show the current list of books.
//          * Clears and repopulates the table model.
//          */
//         private void refreshTable() {
//             tableModel.setRowCount(0); // Clear table
//             for (Book book : books.values()) {
//                 Object[] row = {
//                     book.getId(),
//                     book.getTitle(),
//                     book.getAuthor(),
//                     book.isAvailable() ? "Available" : "Borrowed"
//                 };
//                 tableModel.addRow(row);
//             }
//         }
//     }
// }
// File: LibraryManagementSystem.java
package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    // Persistent storage
    private static Map<Integer, Book> books = new HashMap<>();
    private static int nextBookId = 1;
    private static final String FILE_NAME = "books.dat";

    public static void main(String[] args) {
        loadBooksFromFile(); // Load at startup

        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();

            // Save automatically when closing
            gui.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    saveBooksToFile();
                    System.exit(0);
                }
            });
        });
    }

    // Save books to file
    private static void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            oos.writeInt(nextBookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load books from file
    @SuppressWarnings("unchecked")
    private static void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (Map<Integer, Book>) ois.readObject();
            nextBookId = ois.readInt();
        } catch (Exception e) {
            books = new HashMap<>();
            nextBookId = 1;
        }
    }

    // GUI class
    static class LibraryGUI extends JFrame {
        private DefaultTableModel tableModel;
        private JTable bookTable;

        public LibraryGUI() {
            setTitle("Library Management System");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            String[] columns = {"ID", "Title", "Author", "Status"};
            tableModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            bookTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(bookTable);

            JButton addButton = new JButton("Add Book");
            JButton borrowButton = new JButton("Borrow Book");
            JButton returnButton = new JButton("Return Book");
            JButton refreshButton = new JButton("Refresh List");

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addButton);
            buttonPanel.add(borrowButton);
            buttonPanel.add(returnButton);
            buttonPanel.add(refreshButton);

            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            addButton.addActionListener(e -> addBookDialog());
            borrowButton.addActionListener(e -> borrowBookDialog());
            returnButton.addActionListener(e -> returnBookDialog());
            refreshButton.addActionListener(e -> refreshTable());

            refreshTable();
            setVisible(true);
        }

        private void addBookDialog() {
            JTextField titleField = new JTextField();
            JTextField authorField = new JTextField();
            Object[] message = {"Title:", titleField, "Author:", authorField};
            int option = JOptionPane.showConfirmDialog(this, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                if (!title.isEmpty() && !author.isEmpty()) {
                    Book book = new Book(nextBookId, title, author);
                    books.put(nextBookId, book);
                    nextBookId++;
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void borrowBookDialog() {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to borrow:");
            if (idStr == null) return;
            try {
                int id = Integer.parseInt(idStr.trim());
                Book book = books.get(id);
                if (book == null) {
                    JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!book.isAvailable()) {
                    JOptionPane.showMessageDialog(this, "Book is already borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    book.borrowBook();
                    JOptionPane.showMessageDialog(this, "You borrowed the book: " + book.getTitle());
                    refreshTable();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void returnBookDialog() {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to return:");
            if (idStr == null) return;
            try {
                int id = Integer.parseInt(idStr.trim());
                Book book = books.get(id);
                if (book == null) {
                    JOptionPane.showMessageDialog(this, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (book.isAvailable()) {
                    JOptionPane.showMessageDialog(this, "This book was not borrowed.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    book.returnBook();
                    JOptionPane.showMessageDialog(this, "Book returned: " + book.getTitle());
                    refreshTable();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void refreshTable() {
            tableModel.setRowCount(0);
            for (Book book : books.values()) {
                Object[] row = {book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable() ? "Available" : "Borrowed"};
                tableModel.addRow(row);
            }
        }
    }
}
