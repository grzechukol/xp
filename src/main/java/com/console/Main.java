package com.console;

import com.jsonUsage.ReadInputArgs;

public class Main {
    public static void main(String[] args) {
        try {
            ReadInputArgs readInputArgs = new ReadInputArgs();
            if (args.length > 0){
                readInputArgs.saveInputArgs(args);
            }else {
                readInputArgs.setDefaultPath();
            }

            ConsoleView consoleView = new ConsoleView();
            consoleView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
