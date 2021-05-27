package com.views;

import com.views.BaseView;

public class NotFoundView extends BaseView {
    @Override
    public void show() {
        System.out.println("No match for command, try again...");
    }
}
