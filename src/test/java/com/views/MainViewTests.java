package com.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainViewTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void Show_Should_PrintCorrectTextToStdin() {
        var sut = new MainView();
        sut.show();
        assertEquals(
                "\nChoose action:\n" +
                "add - add transaction\n" +
                "read - read transactions\n" +
                "exit - exit console\n" +
                "ACTION: ", outputStreamCaptor.toString());
    }
}
