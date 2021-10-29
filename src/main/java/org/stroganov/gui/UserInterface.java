package org.stroganov.gui;

import org.stroganov.exceptions.ConsoleInterfaceException;

public interface UserInterface {

    String getStringFromUser();

    int getIntFromUser() throws ConsoleInterfaceException;

    void showMessage(String inputMessage);
}
