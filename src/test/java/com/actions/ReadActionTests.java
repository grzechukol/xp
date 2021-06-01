package com.actions;

import com.views.NotFoundView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReadActionTests {
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
    public void Inovke_ShouldCallNextAction_WhenActionIsNotRead() throws IOException {
        ReadAction readActionMock = Mockito.spy(new ReadAction());
        readActionMock.invoke("other");
        verify(readActionMock, times(1)).invokeNext("other");
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}
