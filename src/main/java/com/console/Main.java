package com.console;

import com.jsonUsage.ReadInputArgs;

import java.net.JarURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            ReadInputArgs readInputArgs = new ReadInputArgs();
            readInputArgs.saveInputArgs(args);

            ConsoleView consoleView = new ConsoleView();
            consoleView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
