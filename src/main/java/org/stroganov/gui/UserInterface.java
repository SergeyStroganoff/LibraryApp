package org.stroganov.gui;

import org.stroganov.exceptions.ConsoleInterfaceError;

public interface UserInterface {

    String getStringFromUser();

    int getIntFromUser() throws ConsoleInterfaceError;

    void showMessage(String inputMessage);
}
