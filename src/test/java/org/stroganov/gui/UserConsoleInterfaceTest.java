package org.stroganov.gui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserConsoleInterfaceTest {

    public static final String TEST_STRING = "Результат поиска файлов";
    public static final String INPUT_TEST_STRING = "Введите в терминале";
    public static final String ERROR_TEST_STRING = "Произошла ошибка";

    @InjectMocks
    UserInterface userInterface;


    @Test
    void When_get_notNumber_fromUser_ThenError() {

    }

    @Test
    void showMessage() {
    }


    @Test
    void getStringFromUser_Return_TestString() throws IOException {
        // GIVEN
        String expectedString = "test";
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("test");
        UserInterface userTestInterface = new UserConsoleInterface(reader);
        // WHEN
        String actualString = userInterface.getStringFromUser();
        // THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    void showInputMessage_System_out_PrintLn_Used() {
        // GIVEN
        byte[] expected = INPUT_TEST_STRING.getBytes();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos, true);
        PrintStream oldStream = System.out;
        System.setOut(printStream);
        // WHEN
        userInterface.showInputMessage("");
        byte[] actualString = INPUT_TEST_STRING.getBytes();
        // THEN
        assertArrayEquals(expected, actualString);
        System.setOut(oldStream);

    }

    @Test
    void showOutputMessage_System_out_PrintLn_Used() {
        // GIVEN
        byte[] expected = TEST_STRING.getBytes();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos, true);
        PrintStream oldStream = System.out;
        System.setOut(printStream);
        // WHEN
        userInterface.showOutputMessage("");
        byte[] actualString = TEST_STRING.getBytes();
        // THEN
        assertArrayEquals(expected, actualString);
        System.setOut(oldStream);
    }


}