package com.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddActionTests {
    @BeforeEach
    public void Setup() {

    }

    @Test
    public void Invoke_Should() {
        var sut = new AddAction();
        sut.invoke("add");
    }
}
