package com.views;

public class MainView extends BaseView {
    private String message =
            "\n" +
            "Choose action:\n" +
            "add - add transaction\n" +
            "read - read transactions\n" +
            "exit - exit console\n" +
            "ACTION: ";

    @Override
    public String show() {
        System.out.printf(message);
        return message;
    }
}
