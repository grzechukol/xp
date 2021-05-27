package com.actions;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ExitActionTests {
    @Rule
    public final ClearSystemProperties myPropertyIsCleared
            = new ClearSystemProperties("MyProperty");

    @Test
    public void overrideProperty() {
        assertNull(System.getProperty("MyProperty"));
    }

}