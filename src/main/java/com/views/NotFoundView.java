package com.views;

import com.views.BaseView;

public class NotFoundView extends BaseView {
    private String message = "No match for command, try again...";

    @Override
    public String show() {
        System.out.println(message);
        return message;
    }
}
