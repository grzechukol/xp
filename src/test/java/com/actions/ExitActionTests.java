package com.actions;

import com.ExitAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;

import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ExitActionTests {
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
    public void Inovke_ShouldExitApp_WhenActionIsExit() {
        var sut = new ExitAction();
        ExitAssertions.assertExits(0, () -> sut.invoke("exit"));
        assertEquals("Exiting application...", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void Inovke_ShouldCallNextAction_WhenActionIsNotExit() {
        ExitAction exitActionMock = Mockito.spy(new ExitAction());
        exitActionMock.invoke("other");
        verify(exitActionMock, times(1)).invokeNext("other");
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}
