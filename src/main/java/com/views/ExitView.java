package com.views;

public class ExitView extends BaseView {
    private String message = "Exiting application...";

    @Override
    public String show() {
        System.out.println(message);
        return message;
    }
}
