package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceException;
import org.stroganov.exceptions.UnrealizedFunctionalityException;
import org.stroganov.gui.UserInterface;
import org.stroganov.history.HistoryManager;
import org.stroganov.util.BookBuilder;
import org.stroganov.util.StringValidator;

import java.io.IOException;
import java.util.List;

public class UserDialogue {
    public static final String NOT_A_NUMBER_MESSAGE = "Error. You entered not a number";
    public static final String ADDED_SUCCESSFUL_MESSAGE = "Book was added successfully";
    public static final String NO_BOOK_WITH_SUCH_DATA_MESSAGE = "No book with such data. Operation rejected";
    public static final String AUTHOR_WAS_ADDED_SUCCESSFULLY = "New Author was added successfully";
    private final LibraryDAO libraryDAO;
    private final HistoryManager historyManager;
    private final UserInterface userInterface;
    private final User user;
    private final BookGetterDialogue bookGetterDialogue = new BookGetterDialogue();
    Logger logger = Logger.getLogger(UserDialogue.class);

    public UserDialogue(LibraryDAO libraryDAO, HistoryManager historyManager, UserInterface userInterface, User user) {
        this.libraryDAO = libraryDAO;
        this.historyManager = historyManager;
        this.userInterface = userInterface;
        this.user = user;
    }

    public void runDialogue() throws UnrealizedFunctionalityException {
        String rights = user.isAdmin() ? "admin" : "user";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have successfully logged in with " + rights + " rights \n").
                append("The following actions are available to you: \n").
                append("Enter number of menu");
        userInterface.showMessage(stringBuilder.toString());
        String command;
        String menu = user.isAdmin() ? returnAdminMenu().toString() : returnUserMenu().toString();
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
                    System.out.println();
                }
                case "7":
                    System.out.println();
                case "8":
                    System.out.println();
                case "9":
                    System.out.println();
                case "10":
                    System.out.println();
                case "11":
                    System.out.println();
                case "12":
                    System.out.println();
                case "13":
                    System.out.println();
                case "14":
                    System.out.println();
                case "15":
                    System.out.println();
                case "16":
                    System.out.println();
                case "17":
                    System.out.println();
                default: {
                    userInterface.showMessage("Incorrect command");
                }
            }
        } while ("q".equals(command));
    }

    private boolean addBooksFromFile() throws UnrealizedFunctionalityException {
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
                historyManager.saveAction("User " + user.getLogin() + "added books from file");
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
            historyManager.saveAction(successMessage + " by User " + user.getLogin());
            userInterface.showMessage(successMessage);
            return true;
        }
        return false;
    }

    public void addNewAuthor() {
        if (libraryDAO.addAuthor(bookGetterDialogue.getAuthorGetterDialogue().getAuthorFromUser(userInterface))) {
            historyManager.saveAction(AUTHOR_WAS_ADDED_SUCCESSFULLY + " by user:" + user.getLogin());
            userInterface.showMessage(AUTHOR_WAS_ADDED_SUCCESSFULLY);
        }
    }

    public boolean deleteBook() {
        userInterface.showMessage("Enter book's ISBN:");
        Book oldBook = null;
        try {
            int numberISBN = userInterface.getIntFromUser();
            oldBook = libraryDAO.findBook(numberISBN);
        } catch (ConsoleInterfaceException e) {
            logger.error(e.getMessage());
            userInterface.showMessage(NOT_A_NUMBER_MESSAGE);
        }
        if (oldBook != null) {
            libraryDAO.deleteBook(oldBook);
            return true;
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
        }
        return false;
    }

    public StringBuilder returnUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1. Add new books to library \n").
                append("2. Delete book \n").
                append("3. Add new authors to library \n").
                append("4. Delete author from home library with all books by this author \n").
                append("5. Add books to the home library with a list from the catalog file (CSV and JSON) \n").
                append("6. Add bookmarks to book \n").//TODO
                append("7. Delete bookmarks from books \n").//TODO
                append("8. Search for books by part of the name of the book \n").//TODO
                append("9. Search for books by part of the author's name \n").//TODO
                append("10. Search for books by the unique book identifier (ISBN) \n").//TODO
                append("11. Search for books by the range of years from and to with the occurrence of boundary values \n").//TODO
                append("12. Search for books by year, number of pages and part of the title of the book \n").//TODO
                append("13. See the list of books that have my bookmarks \n");//TODO
        return stringBuilder;
    }

    public StringBuilder returnAdminMenu() {
        return returnUserMenu().
                append("14. Create a new user \n").
                append("15. Block user \n").
                append("16. Unblock user \n").
                append("17. Shock \n");
    }
}
