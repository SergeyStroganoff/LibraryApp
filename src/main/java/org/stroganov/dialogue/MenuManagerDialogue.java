package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceException;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
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
    private final LibraryDAO libraryDAO;
    private final HistoryManager historyManager;
    private final UserInterface userInterface;
    private final User currentUser;
    private final BookGetterDialogue bookGetterDialogue = new BookGetterDialogue();
    private final BookMarkGetterDialogue bookMarkGetterDialogue = new BookMarkGetterDialogue();
    private final UserGetterDialogue userGetterDialogue = new UserGetterDialogue();
    Logger logger = Logger.getLogger(MenuManagerDialogue.class);

    public MenuManagerDialogue(LibraryDAO libraryDAO, HistoryManager historyManager, UserInterface userInterface, User currentUser) {
        this.libraryDAO = libraryDAO;
        this.historyManager = historyManager;
        this.userInterface = userInterface;
        this.currentUser = currentUser;
    }

    public void runDialogue() throws UnrealizedFunctionalityException {
        String rights = currentUser.isAdmin() ? "admin" : "user";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have successfully logged in with " + rights + " rights \n").
                append("The following actions are available to you: \n").
                append("Enter number of menu");
        userInterface.showMessage(stringBuilder.toString());
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
                        historyManager.getHistoryList().forEach(userInterface::showMessage);
                    }
                    break;
                default: {
                    userInterface.showMessage("Incorrect command");
                }
            }
        } while ("q".equals(command));
    }

    private boolean changeUserStatus(boolean blockAction) {
        userInterface.showMessage("Enter user login");
        String userLogin = userInterface.getStringFromUser();
        User user = libraryDAO.findUser(userLogin);
        if (user != null) {
            if (blockAction) {
                if (libraryDAO.blockUser(user)) {
                    historyManager.saveAction(USER + currentUser.getLogin() + "blocked user: " + user.getLogin());
                    return true;
                }
            } else {
                if (libraryDAO.unblockUser(user)) {
                    historyManager.saveAction(USER + currentUser.getLogin() + "blocked user: " + user.getLogin());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean addNewUser() {
        User user = userGetterDialogue.getUserFromDialogue(userInterface);
        if (libraryDAO.addUser(user)) {
            historyManager.saveAction(USER + currentUser.getLogin() + " added a new user: " + user.getLogin());
            return true;
        } else {
            userInterface.showMessage("User wasn't added");
        }
        return false;
    }

    private void findBookWithBookmarks() {
        libraryDAO.findBooksWithUserBookMarks(currentUser).forEach(x -> userInterface.showMessage(x.toString()));
        historyManager.saveAction(USER + currentUser.getLogin() + " have got a list of books with his bookmarks");
    }

    private boolean findBookByParameters() {
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

    private boolean findBookByYearsRange() {
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

    private boolean findBookByISBN() {
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

    private void findBooksByPartAuthorName() {
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
        if (libraryDAO.deleteBookMark(bookMark)) {
            historyManager.saveAction(USER + currentUser.getLogin() + " deleted booksMark from book " + bookMark.getBook().getNumberISBN());
            userInterface.showMessage("You successfully deleted this bookmark");
            return true;
        } else {
            userInterface.showMessage("Bookmark was not deleted");
        }
        return false;
    }

    public boolean addBookMark() {

        BookMark bookMark = bookMarkGetterDialogue.getBookMarkFromUser(userInterface, libraryDAO, currentUser);
        if (bookMark == null) {
            return false;
        }
        if (libraryDAO.addBookMark(bookMark)) {
            historyManager.saveAction(USER + currentUser.getLogin() + "added booksMark to book " + bookMark.getBook().getNumberISBN());
            userInterface.showMessage("You successfully added this bookmark");
            return true;
        } else {
            userInterface.showMessage("Bookmark wasn't added");
        }
        return false;
    }

    public boolean addBooksFromFile() throws UnrealizedFunctionalityException {
        userInterface.showMessage("Enter path of file with books list");
        String filePath = userInterface.getStringFromUser();
        if (StringValidator.isStringFilePath(filePath)) {
            if (filePath.endsWith(".csv")) {
                BookBuilder bookBuilder = new BookBuilder(libraryDAO);
                try {
                    List<Book> bookList = bookBuilder.getBookListFromTXTFile(filePath);
                    bookList.forEach(libraryDAO::addBook);
                } catch (IOException e) {
                    String errorMessage = "Error IO with file" + filePath + "happen: " + e.getMessage();
                    logger.error(errorMessage);
                    userInterface.showMessage(errorMessage);
                }
                historyManager.saveAction(USER + currentUser.getLogin() + "added books from file");
                return true;
            }
            if (filePath.endsWith(".json")) {
                //TODO
                throw new UnrealizedFunctionalityException("filePath.endsWith(\".json\" not realized");
            }
        } else {
            userInterface.showMessage("You enter wrong path format");
        }
        return false;
    }

    public boolean deleteAuthor() {
        userInterface.showMessage("Enter the author's name");
        String authorName = userInterface.getStringFromUser();
        Author author = libraryDAO.findAuthor(authorName);
        if (author != null) {
            libraryDAO.deleteAuthorWithAllHisBooks(author);
            String successMessage = "Author " + authorName + "was successfully deleted with all his books";
            historyManager.saveAction(successMessage + " by User " + currentUser.getLogin());
            userInterface.showMessage(successMessage);
            return true;
        } else {
            userInterface.showMessage("Author " + authorName + "was not found");
        }
        return false;
    }

    public void addNewAuthor() {
        if (libraryDAO.addAuthor(bookGetterDialogue.getAuthorGetterDialogue().getAuthorFromUser(userInterface))) {
            historyManager.saveAction(AUTHOR_WAS_ADDED_SUCCESSFULLY + " by user:" + currentUser.getLogin());
            userInterface.showMessage(AUTHOR_WAS_ADDED_SUCCESSFULLY);
        } else {
            userInterface.showMessage("Unable to add new author");
        }
    }

    public boolean deleteBook() {
        userInterface.showMessage("Enter book's ISBN:");
        Book oldBook;
        String numberISBN = userInterface.getStringFromUser();
        oldBook = libraryDAO.findBook(numberISBN);
        if (oldBook != null) {
            if (libraryDAO.deleteBook(oldBook)) {
                historyManager.saveAction(USER + currentUser.getLogin() + " deleted book " + oldBook.getNumberISBN());
                userInterface.showMessage("You successfully deleted book");
                return true;
            }
        } else {
            userInterface.showMessage(NO_BOOK_WITH_SUCH_DATA_MESSAGE);
        }
        return false;
    }

    public boolean addNewBook() {
        Book newBook = bookGetterDialogue.getBookFromUser(userInterface);
        if (libraryDAO.addBook(newBook)) {
            historyManager.saveAction("User added book" + newBook.getName() + " ISBN " + newBook.getNumberISBN());
            userInterface.showMessage(ADDED_SUCCESSFUL_MESSAGE);
            return true;
        } else {
            userInterface.showMessage("Book was not added");
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
