package com.actions;

import com.views.BaseView;
import com.views.ExitView;

public class ExitAction extends BaseAction {
    private BaseView view;

    public ExitAction() {
        this.view = new ExitView();
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("exit")) {
            view.show();
            System.exit(0);
        }
        return invokeNext(actionName);
    }
}
