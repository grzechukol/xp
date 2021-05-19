package com.console;

import com.transactions.TransactionSaver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File(TransactionSaver.DEFAULT_FILE);
            file.createNewFile();
            ConsoleView consoleView = new ConsoleView();
            consoleView.consoleLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
