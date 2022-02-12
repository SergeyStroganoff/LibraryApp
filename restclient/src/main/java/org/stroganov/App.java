package org.stroganov;

import org.springframework.context.support.ClassPathXmlApplicationContext;
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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // ApplicationContext applicationContext = A

       // UserInterface userInterface = UserInterfaceFactory.getUserInterface("ConsoleInterface");
       // InitialDialogue initialDialogue = new InitialDialogue(userInterface);
        InitialDialogue initialDialogue = context.getBean("initialDialogue",InitialDialogue.class);
        initialDialogue.runDialogue();
        context.close();
    }
}
