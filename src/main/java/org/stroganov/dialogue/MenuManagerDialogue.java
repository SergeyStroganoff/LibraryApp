package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceException;
import org.stroganov.exceptions.DBExceptions;
import org.stroganov.gui.UserInterface;
import org.stroganov.history.HistoryManager;
import org.stroganov.util.BookBuilder;
import org.stroganov.util.StringValidator;

import java.io.IOException;
import java.util.List;

public class MenuManagerDialogue {

    public static final String ADDED_SUCCESSFUL_MESSAGE = "Book was added successfully";
    public static final String NO_BOOK_WITH_SUCH_DATA_MESSAGE = "No book with such data. Operation rejected";
    public static final String AUTHOR_WAS_ADDED_SUCCESSFULLY = "New Author was added successfully";
    public static final String USER = "User ";
    public static final String NOT_A_NUMBER_OF_YEAR_MESSAGE = "You entered not a number of year, try again";
    public static final String AN_ERROR_HAPPEN = "An error happen: ";
    private final LibraryDAO libraryDAO;
    private final HistoryManager historyManager;
    private final UserInterface userInterface;
    private final User currentUser;
    private final BookGetterDialogue bookGetterDialogue = new BookGetterDialogue();
    private final BookMarkGetterDialogue bookMarkGetterDialogue = new BookMarkGetterDialogue();
    private final UserGetterDialogue userGetterDialogue = new UserGetterDialogue();
    private final Logger logger = Logger.getLogger(MenuManagerDialogue.class);

    public MenuManagerDialogue(LibraryDAO libraryDAO, HistoryManager historyManager, UserInterface userInterface, User currentUser) {
        this.libraryDAO = libraryDAO;
        this.historyManager = historyManager;
        this.userInterface = userInterface;
        this.currentUser = currentUser;
    }

    public void runDialogue() {
        String rights = currentUser.isAdmin() ? "admin" : "user";
        String welcomeMessage = "You have successfully logged in with " + rights + " rights \n" +
                "The following actions are available to you: \n" +
                "Enter number of menu";
        userInterface.showMessage(welcomeMessage);
        String command;
        String menu = currentUser.isAdmin() ? returnAdminMenu().toString() : returnUserMenu().toString();
        do {
            userInterface.showMessage(menu);
            command = userInterface.getStringFromUser();
            switch (command) {
                case "1": {
                    addNewBook();
                    break;
                }
                case "2": {
                    deleteBook();
                    break;
                }
                case "3": {
                    addNewAuthor();
                    break;
                }
                case "4": {
                    deleteAuthor();
                    break;
                }
                case "5": {
                    addBooksFromFile();
                    break;
                }
                case "6": {
                    addBookMark();
                    break;
                }
                case "7": {
                    deleteBookMark();
                    break;
                }
                case "8": {
                    findBooksByPartName();
                    break;
                }
                case "9": {
                    findBooksByPartAuthorName();
                    break;
                }
                case "10": {
                    findBookByISBN();
                    break;
                }
                case "11": {
                    findBookByYearsRange();
                    break;
                }
                case "12": {
                    findBookByParameters();
                    break;
                }
                case "13": {
                    findBookWithBookmarks();
                    break;
                }
                case "14": {
                    if (currentUser.isAdmin()) {
                        addNewUser();
                    }
                    break;
                }
                case "15":
                    if (currentUser.isAdmin()) {
                        changeUserStatus(true);
                    }
                    break;
                case "16":
                    if (currentUser.isAdmin()) {
                        changeUserStatus(false);
                    }
                    break;
                case "17":
                    if (currentUser.isAdmin()) {
                        getHistory();
                    }
                    break;
                default: {
                    userInterface.showMessage("Incorrect command");
                    break;
                }
            }
        } while (!"q".equals(command));
    }

    private void getHistory() {
        try {
            historyManager.getHistoryEventsList().forEach(historyEvent -> userInterface.showMessage(historyEvent.toString()));
            historyManager.saveAction(USER + currentUser.getLogin() + "have got a list of history events");
        } catch (IOException e) {
            logger.error(e.getMessage());
            userInterface.showMessage("Unable to get history events, see logfile");
        }
    }

    public boolean changeUserStatus(boolean blockAction) {
        userInterface.showMessage("Enter user login");
        String userLogin = userInterface.getStringFromUser();
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            String blockingAction = "unblocked user: ";
            try {
                if (blockAction) {
                    blockingAction = "blocked user: ";
                    libraryDAO.blockUser(user);
                } else {
                    libraryDAO.unblockUser(user);
                }
            } catch (IOException e) {
                userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
                return false;
            }
            historyManager.saveAction(USER + currentUser.getLogin() + blockingAction + user.getLogin());
            return true;
        } else {
            userInterface.showMessage("No user with this login was found");
        }
        return false;
    }

    public boolean addNewUser() {
        User user = userGetterDialogue.getUserFromDialogue(userInterface);
        try {
            if (libraryDAO.addUser(user)) {
                historyManager.saveAction(USER + currentUser.getLogin() + " added a new user: " + user.getLogin());
                userInterface.showMessage("User saved successfully");
                return true;
            } else {
                userInterface.showMessage("User already saved");
            }
        } catch (IOException e) {
            userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
        }
        return false;
    }

    public void findBookWithBookmarks() {
        List<Book> bookList = libraryDAO.findBooksWithUserBookMarks(currentUser);
        if (bookList != null && !bookList.isEmpty()) {
            bookList.forEach(System.out::println);
        } else {
            userInterface.showMessage("No books with bookMarks");
        }
        historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books with his bookmarks");
    }

    public boolean findBookByParameters() {
        userInterface.showMessage("Enter book's year publishing");
        int bookYear;
        int bookPages;
        try {
            bookYear = userInterface.getIntFromUser();
            userInterface.showMessage("Enter number of book's pages");
            bookPages = userInterface.getIntFromUser();
        } catch (ConsoleInterfaceException e) {
            logger.error(e.getMessage());
            userInterface.showMessage(NOT_A_NUMBER_OF_YEAR_MESSAGE + "ore not number of book's pages");
            return false;
        }
        userInterface.showMessage("Enter part of book name");
        String partBookName = userInterface.getStringFromUser();
        libraryDAO.findBooksByParameters(bookYear, bookPages, partBookName).forEach(x -> userInterface.showMessage(x.toString()));
        historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books by parameters");
        return true;
    }

    public boolean findBookByYearsRange() {
        try {
            userInterface.showMessage("Enter fist year of range");
            int firstYear = userInterface.getIntFromUser();
            userInterface.showMessage("Enter second year of range");
            int secondYear = userInterface.getIntFromUser();
            libraryDAO.findBooksByYearsRange(firstYear, secondYear).forEach(x -> userInterface.showMessage(x.toString()));
            historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books in years range");
            return true;
        } catch (ConsoleInterfaceException e) {
            logger.error(e.getMessage());
            userInterface.showMessage(NOT_A_NUMBER_OF_YEAR_MESSAGE);
        }
        return false;
    }

    public boolean findBookByISBN() {
        userInterface.showMessage("Enter book ISBN");
        String numberISBN = userInterface.getStringFromUser();
        Book book = libraryDAO.findBook(numberISBN);
        if (book != null) {
            userInterface.showMessage(book.toString());
            historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books by part of author name");
            return true;
        } else {
            userInterface.showMessage("Book wast not found");
        }
        return false;
    }

    public void findBooksByPartAuthorName() {
        userInterface.showMessage("Enter part of author name");
        String partAuthorName = userInterface.getStringFromUser();
        libraryDAO.findBooksByPartAuthorName(partAuthorName).forEach(x -> userInterface.showMessage(x.toString()));
        historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books by part of author name");
    }

    public void findBooksByPartName() {
        userInterface.showMessage("Enter part of book's name");
        String partBookName = userInterface.getStringFromUser();
        libraryDAO.findBooksByPartName(partBookName).forEach(x -> userInterface.showMessage(x.toString()));
        historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books by part of name");
    }

    public boolean deleteBookMark() {
        BookMark bookMark = bookMarkGetterDialogue.getBookMarkFromUser(userInterface, libraryDAO, currentUser);
        if (bookMark == null) {
            return false;
        }
        try {
            if (libraryDAO.deleteBookMark(bookMark)) {
                historyManager.saveAction(USER + currentUser.getLogin() + " deleted booksMark from book " + bookMark.getBook().getNumberISBN());
                userInterface.showMessage("You successfully deleted this bookmark");
                return true;
            } else {
                userInterface.showMessage("This book wasn't found");
            }
        } catch (IOException e) {
            userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
        }
        return false;
    }

    public boolean addBookMark() {
        BookMark bookMark = bookMarkGetterDialogue.getBookMarkFromUser(userInterface, libraryDAO, currentUser);
        if (bookMark == null) {
            return false;
        }
        try {
            if (libraryDAO.addBookMark(bookMark)) {
                historyManager.saveAction(USER + currentUser.getLogin() + "added booksMark to book " + bookMark.getBook().getNumberISBN());
                userInterface.showMessage("You successfully added this bookmark");
                return true;
            } else {
                userInterface.showMessage("Bookmark already preset");
            }
        } catch (IOException e) {
            userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
        }
        return false;
    }

    public boolean addBooksFromFile() {
        userInterface.showMessage("Enter path of file with books list");
        String filePath = userInterface.getStringFromUser();
        List<Book> bookList;
        if (StringValidator.isStringFilePath(filePath)) {
            try {
                if (filePath.endsWith(".csv")) {
                    BookBuilder bookBuilder = new BookBuilder(libraryDAO);
                    bookList = bookBuilder.getBookListFromTXTFile(filePath);
                } else {
                    if (filePath.endsWith(".json")) {
                        bookList = JsonDBLoader.loadEntities(filePath, Book.class);
                    } else {
                        userInterface.showMessage("You enter wrong file format: file can be 'txt' ore 'json'");
                        return false;
                    }
                }
                libraryDAO.addBook(bookList);
            } catch (DBExceptions | IOException e) {
                String errorMessage = "Error IO happen: " + e.getMessage();
                logger.error(errorMessage);
                userInterface.showMessage(errorMessage);
                return false;
            }
            historyManager.saveAction(USER + currentUser.getLogin() + "added books from file");
        }
        return true;
    }

    public boolean deleteAuthor() {
        userInterface.showMessage("Enter the author's name");
        String authorName = userInterface.getStringFromUser();
        Author author = libraryDAO.findAuthor(authorName);
        if (author != null) {
            try {
                libraryDAO.deleteAuthorWithAllHisBooks(author);
            } catch (IOException e) {
                userInterface.showMessage("An error happen:" + e.getMessage());
                return false;
            }
            String successMessage = "Author " + authorName + "was successfully deleted with all his books";
            historyManager.saveAction(successMessage + " by User " + currentUser.getLogin());
            userInterface.showMessage(successMessage);
            return true;
        } else {
            userInterface.showMessage("Author " + authorName + "was not found");
        }
        return false;
    }

    public boolean addNewAuthor() {
        try {
            if (!libraryDAO.addAuthor(bookGetterDialogue.getAuthorGetterDialogue().getAuthorFromUser(userInterface))) {
                userInterface.showMessage("This author is exist already");
                return false;
            }
        } catch (IOException e) {
            userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
            return false;
        }
        historyManager.saveAction(AUTHOR_WAS_ADDED_SUCCESSFULLY + " by user:" + currentUser.getLogin());
        userInterface.showMessage(AUTHOR_WAS_ADDED_SUCCESSFULLY);
        return true;
    }

    public boolean deleteBook() {
        userInterface.showMessage("Enter book's ISBN:");
        Book oldBook;
        String numberISBN = userInterface.getStringFromUser();
        oldBook = libraryDAO.findBook(numberISBN);
        if (oldBook != null) {
            try {
                libraryDAO.deleteBook(oldBook);
            } catch (IOException e) {
                userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
                return false;
            }
            historyManager.saveAction(USER + currentUser.getLogin() + " deleted book " + oldBook.getNumberISBN());
            userInterface.showMessage("You successfully deleted book");
            return true;

        } else {
            userInterface.showMessage(NO_BOOK_WITH_SUCH_DATA_MESSAGE);
        }
        return false;
    }

    public boolean addNewBook() {
        Book newBook = bookGetterDialogue.getBookFromUser(userInterface);
        try {
            if (libraryDAO.addBook(newBook)) {
                historyManager.saveAction("User added book" + newBook.getBookName() + " ISBN " + newBook.getNumberISBN());
                userInterface.showMessage(ADDED_SUCCESSFUL_MESSAGE);
                return true;
            } else {
                userInterface.showMessage("This book already saved in library");
            }
        } catch (IOException e) {
            userInterface.showMessage(AN_ERROR_HAPPEN + e.getMessage());
        }
        return false;
    }

    public StringBuilder returnUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("1. Add new books to library \n")
                .append("2. Delete book \n")
                .append("3. Add new authors to library \n")
                .append("4. Delete author from home library with all books by this author \n")
                .append("5. Add books to the home library with a list from the catalog file (CSV and JSON) \n")
                .append("6. Add bookmarks to book \n")
                .append("7. Delete bookmarks from books \n")
                .append("8. Search for books by part of the name of the book \n")
                .append("9. Search for books by part of the author's name \n")
                .append("10. Search for books by the unique book identifier (ISBN) \n")
                .append("11. Search for books by the range of years \n")
                .append("12. Search for books by year, number of pages and part of the title of the book \n")
                .append("13. See the list of books that have my bookmarks \n");
        return stringBuilder;
    }

    public StringBuilder returnAdminMenu() {
        return returnUserMenu().
                append("14. Create a new user \n").
                append("15. Block user \n").
                append("16. Unblock user \n").
                append("17. Show history all users \n");
    }
}
