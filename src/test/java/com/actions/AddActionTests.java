package com.actions;

import com.ExitAssertions;
import com.transactions.IncorrectDataException;
import com.transactions.TransactionSaver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddActionTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void Invoke_ShouldAdd() throws IncorrectDataException, IOException {
        String input = "1\n" + "1\n" + "1\n" +"1\n" + "1" ;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        var sut = new AddAction();
        sut.invoke("add");
        assertEquals("Adding new transaction...\n" + "ID: Name: Description: Date: Currency:",
                outputStreamCaptor.toString().trim());
    }
}
