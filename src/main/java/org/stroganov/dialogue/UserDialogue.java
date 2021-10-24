package org.stroganov.dialogue;

import org.apache.log4j.Logger;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.exceptions.ConsoleInterfaceError;
import org.stroganov.gui.UserInterface;
import org.stroganov.history.HistoryManager;

public class UserDialogue {
    public static final String NOT_A_NUMBER_MESSAGE = "Error. You entered not a number";
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

    public void runDialogue() {
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
                    Book newBook = bookGetterDialogue.getBookFromUser(userInterface);
                    if (libraryDAO.addBook(newBook)) {
                        historyManager.saveAction("User added book" + newBook.getName() + " ISBN " + newBook.getNumberISBN());
                    }
                    break;
                }

                case "2": {
                    userInterface.showMessage("Enter book's ISBN:");
                    try {
                        int numberISBN = userInterface.getIntFromUser();
                        Book oldBook = libraryDAO.findBook(numberISBN);
                        libraryDAO.deleteBook(oldBook);
                    } catch (ConsoleInterfaceError e) {
                        logger.error(e.getMessage());
                        userInterface.showMessage(NOT_A_NUMBER_MESSAGE);
                    }
                }

                case "3":
                    System.out.println();
                case "4":
                    System.out.println();
                case "5":
                    System.out.println();
                case "6":
                    System.out.println();
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

    public StringBuilder returnUserMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1. Add new books to library \n"). //TODO
                append("2. Delete book \n").//TODO
                append("3. Add new authors to library \n").//TODO
                append("4. Delete author from home library with all books by this author \n").//TODO
                append("5. Add books to the home library with a list from the catalog file (CSV and JSON) \n").//TODO
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
