package com.views;

public class MainView extends BaseView {
    @Override
    public void show() {
        System.out.printf(
                "\n" +
                "Choose action:\n" +
                "add - add transaction\n" +
                "read - read transactions\n" +
                "summarize - display summarized transactions\n" +
                "exit - exit console\n" +
                "ACTION: "
                );
    }
}
