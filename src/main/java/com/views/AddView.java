package com.views;

public class AddView extends BaseView {
    private String message = "Adding new transaction...";

    @Override
    public String show() {
        System.out.println(message);
        return message;
    }
}
