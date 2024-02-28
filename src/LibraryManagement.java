//CODE CASE - TASK 2 - JAVA Development
import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private List<Book> books;
    private Map<String, Book> bookMap;

    public Library() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getTitle().toLowerCase(), book);
    }

    public void removeBook(Book book) {
        books.remove(book);
        bookMap.remove(book.getTitle().toLowerCase());
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class LibraryManagementSystem {
    private Library library;
    private List<User> users;

    public LibraryManagementSystem() {
        library = new Library();
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void issueBook(User user, String bookTitle) {
        Book book = library.findBook(bookTitle);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book '" + bookTitle + "' issued to " + user.getName());
        } else {
            System.out.println("Book '" + bookTitle + "' is not available");
        }
    }

    public void returnBook(User user, String bookTitle) {
        Book book = library.findBook(bookTitle);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book '" + bookTitle + "' returned by " + user.getName());
        } else {
            System.out.println("Book '" + bookTitle + "' was not issued to " + user.getName());
        }
    }

    public List<Book> browseBooks() {
        return library.getAvailableBooks();
    }

    public void addSelfHelpBooks() {
        String[][] selfHelpBooks = {
                {"The 7 Habits of Highly Effective People", "Stephen R. Covey"},
                {"How to Win Friends and Influence People", "Dale Carnegie"},
                {"Atomic Habits", "James Clear"},
                {"Think and Grow Rich", "Napoleon Hill"},
                {"The Power of Now", "Eckhart Tolle"},
                {"You Are a Badass", "Jen Sincero"},
                {"Mindset: The New Psychology of Success", "Carol S. Dweck"},
                {"The Subtle Art of Not Giving a F*ck", "Mark Manson"},
                {"Girl, Wash Your Face", "Rachel Hollis"},
                {"Daring Greatly", "Brené Brown"},
                {"The Four Agreements", "Don Miguel Ruiz"},
                {"The Gifts of Imperfection", "Brené Brown"},
                {"Unfu*k Yourself", "Gary John Bishop"},
                {"The Miracle Morning", "Hal Elrod"},
                {"Grit: The Power of Passion and Perseverance", "Angela Duckworth"},
                {"Becoming", "Michelle Obama"},
                {"Man's Search for Meaning", "Viktor E. Frankl"},
                {"The Life-Changing Magic of Tidying Up", "Marie Kondo"},
                {"The Power of Habit", "Charles Duhigg"},
                {"Year of Yes", "Shonda Rhimes"},
                {"The Artist's Way", "Julia Cameron"},
                {"Everything Is Figureoutable", "Marie Forleo"},
                {"The Compound Effect", "Darren Hardy"},
                {"Big Magic: Creative Living Beyond Fear", "Elizabeth Gilbert"},
                {"The Happiness Project", "Gretchen Rubin"}
        };

        for (String[] bookInfo : selfHelpBooks) {
            Book book = new Book(bookInfo[0], bookInfo[1]);
            library.addBook(book);
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.addSelfHelpBooks();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System!");

        while (true) {
            System.out.println("\n1. Issue a book");
            System.out.println("2. Return a book");
            System.out.println("3. Browse available self-help books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String userEmail = scanner.nextLine();
                    User user = new User(userName, userEmail);
                    System.out.print("Enter the title of the book you want to issue: ");
                    String issueBookTitle = scanner.nextLine();
                    libraryManagementSystem.issueBook(user, issueBookTitle);
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String returnUserName = scanner.nextLine();
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnBookTitle = scanner.nextLine();
                    User returnUser = new User(returnUserName, "");
                    libraryManagementSystem.returnBook(returnUser, returnBookTitle);
                    break;
                case 3:
                    List<Book> availableBooks = libraryManagementSystem.browseBooks();
                    System.out.println("Available self-help books:");
                    if (availableBooks.isEmpty()) {
                        System.out.println("No self-help books available.");
                    } else {
                        for (Book book : availableBooks) {
                            System.out.println(book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
