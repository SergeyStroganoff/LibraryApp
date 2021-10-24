package org.stroganov.gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stroganov.exceptions.ConsoleInterfaceException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserConsoleInterfaceTest {

    String OUTPUT_MESSAGE = "Test string";


    @ParameterizedTest
    @ValueSource(strings = {"fgh12", "67!12", "dfgdfguu"})
    void When_get_notNumber_fromUser_ThenError(String argument) throws IOException {
        // GIVEN
        String testString = argument;
        //WHEN
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn(testString);
        UserInterface userTestInterface = new UserConsoleInterface(reader);
        // THEN
        Assertions.assertThrows(ConsoleInterfaceException.class, userTestInterface::getIntFromUser);
    }

    @Test
    void getIntFromUser_Return_ValidInt() throws ConsoleInterfaceException, IOException {
        // GIVEN
        int expectedIntValue = 123;
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("123");
        UserInterface userTestInterface = new UserConsoleInterface(reader);
        // WHEN
        int actualValue = userTestInterface.getIntFromUser();
        // THEN
        assertEquals(expectedIntValue, actualValue);
    }


    @Test
    void getStringFromUser_Return_TestString() throws IOException {
        // GIVEN
        String expectedString = "test";
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("test");
        UserInterface userTestInterface = new UserConsoleInterface(reader);
        // WHEN
        String actualString = userTestInterface.getStringFromUser();
        // THEN
        assertEquals(expectedString, actualString);
    }

    @Test
    void showInputMessage_System_out_PrintLn_Used() {
        // GIVEN
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        UserInterface userTestInterface = new UserConsoleInterface(reader);
        String expected = OUTPUT_MESSAGE + "\r\n";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos, true);
        PrintStream oldStream = System.out;
        System.setOut(printStream);
        // WHEN
        userTestInterface.showMessage(OUTPUT_MESSAGE);
        String actual = bos.toString(StandardCharsets.UTF_8);
        System.setOut(oldStream);
        // THEN
        assertEquals(expected, actual);

    }
}