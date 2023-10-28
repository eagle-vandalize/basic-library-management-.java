import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        checkedOut = true;
    }

    public void checkIn() {
        checkedOut = false;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Checked Out: " + (checkedOut ? "Yes" : "No");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                break;
            }
        }
    }

    public Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int bookId = 1;

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Check out a book");
            System.out.println("4. Check in a book");
            System.out.println("5. Display all books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    Book newBook = new Book(bookId, title, author);
                    library.addBook(newBook);
                    System.out.println("Book added with ID " + bookId);
                    bookId++;
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int removeId = scanner.nextInt();
                    Book removedBook = library.findBook(removeId);
                    if (removedBook != null) {
                        library.removeBook(removeId);
                        System.out.println("Book removed: " + removedBook.getTitle());
                    } else {
                        System.out.println("Book not found with ID " + removeId);
                    }
                    break;
                case 3:
                    System.out.print("Enter book ID to check out: ");
                    int checkOutId = scanner.nextInt();
                    Book checkOutBook = library.findBook(checkOutId);
                    if (checkOutBook != null) {
                        if (!checkOutBook.isCheckedOut()) {
                            checkOutBook.checkOut();
                            System.out.println("Checked out: " + checkOutBook.getTitle());
                        } else {
                            System.out.println("Book is already checked out.");
                        }
                    } else {
                        System.out.println("Book not found with ID " + checkOutId);
                    }
                    break;
                case 4:
                    System.out.print("Enter book ID to check in: ");
                    int checkInId = scanner.nextInt();
                    Book checkInBook = library.findBook(checkInId);
                    if (checkInBook != null) {
                        if (checkInBook.isCheckedOut()) {
                            checkInBook.checkIn();
                            System.out.println("Checked in: " + checkInBook.getTitle());
                        } else {
                            System.out.println("Book is already checked in.");
                        }
                    } else {
                        System.out.println("Book not found with ID " + checkInId);
                    }
                    break;
                case 5:
                    System.out.println("\nBooks in the library:");
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Exiting the Library Management System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
