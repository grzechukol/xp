package com.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleViewTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = null;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inContent);
    }

    @AfterEach
    public void restoreState() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);

        try {
            var dir = new File("./test");
            for (File file : dir.listFiles())
                file.delete();
        } catch(Exception e) {

        }
    }

    @Test
    public void MenuShouldReturnProperText() throws Exception {
        ConsoleView consoleView = new ConsoleView();

        String menuMessage = "\nChoose action:\nadd - add transaction\nread - read transactions\nexit - exit console\nACTION: ";
        String actualMessage = consoleView.menu();

        assertEquals(menuMessage, actualMessage);
    }
}
