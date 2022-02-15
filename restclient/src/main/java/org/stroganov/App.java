package org.stroganov;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.stroganov.dialogue.InitialDialogue;

/**
 * Home library App
 * JSON data used as Data Base!
 *
 * @author Sergey Stroganov
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        InitialDialogue initialDialogue = context.getBean("initialDialogue", InitialDialogue.class);
        initialDialogue.runDialogue();
        context.close();
    }
}
