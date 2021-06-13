package com.actions;

import com.ExitAssertions;
import com.transactions.IncorrectDataException;
import com.transactions.TransactionSaver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AddActionTests {
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
    public void Invoke_ShouldInvokeNext_WhenAddArgumentPassed() throws IncorrectDataException, IOException {
        String input = "1\n" + "1\n" + "1\n" +"1\n" + "1" ;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        var sut = new AddAction();
        sut.invoke("add");
        assertEquals("Adding new transaction...\r\n" + "Name: Description: Price: Transaction added succesfully!",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void Invoke_ShouldInvokeNext_WhenOtherArgumentPassed() throws IncorrectDataException, IOException {
        AddAction addActionMock = Mockito.spy(new AddAction());
        addActionMock.invoke("other");
        verify(addActionMock, times(1)).invokeNext("other");
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}
