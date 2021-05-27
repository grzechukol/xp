package com.actions;

import com.ExitAssertions;
import com.views.NotFoundView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundActionTests {
    @Test
    public void Inovke_ShouldReturnFalse_WhenActionIsNotFound() {
        var sut = new NotFoundAction();
        NotFoundView notFoundView = Mockito.spy(new NotFoundView());
        var result = sut.invoke("other");
        assertEquals(false, result);
    }
}
