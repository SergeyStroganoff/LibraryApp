package org.stroganov;

import org.stroganov.dialogue.InitialDialogue;
import org.stroganov.gui.UserInterface;
import org.stroganov.gui.UserInterfaceFactory;

/**
 * Home library App
 * JSON data used as Data Base!
 *
 * @author Sergey Stroganov
 */
public class App {

    public static void main(String[] args) {

        UserInterface userInterface = UserInterfaceFactory.getUserInterface("ConsoleInterface");
        InitialDialogue initialDialogue = new InitialDialogue(userInterface);
        initialDialogue.runDialogue();
    }
}
