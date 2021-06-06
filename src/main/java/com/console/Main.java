package com.console;

import com.transactions.TransactionSaver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleView consoleView = new ConsoleView();
            consoleView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
