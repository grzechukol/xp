package com.views;

public class ReadView extends BaseView {
    private String message = "Reading transactions...";

    @Override
    public String show() {
        System.out.println(message);
        return message;
    }
}
