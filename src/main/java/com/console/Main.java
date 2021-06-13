package com.console;

import com.jsonUsage.JsonConfig;

public class Main {
    public static void main(String[] args) {
        try {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.saveInputArgs(args);

            ConsoleView consoleView = new ConsoleView();
            consoleView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
