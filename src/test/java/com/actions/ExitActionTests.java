//package com.actions;
//
//import org.junit.Rule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.contrib.java.lang.system.ExpectedSystemExit;
//
//public class ExitActionTests {
//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
//
//    @BeforeEach
//    public void Setup() {
//
//    }
//
//    @Test
//    public void Invoke_Should() {
//        var sut = new ExitAction();
//        exit.expectSystemExitWithStatus(1);
//        sut.invoke("exit");
//    }
//}