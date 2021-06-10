package com.actions;

import com.presenter.ConsolePresenter;
import com.views.ExitView;

public class ExitAction extends BaseAction {
    private ConsolePresenter presenter;

    public ExitAction() {
        this.presenter = new ConsolePresenter(new ExitView());
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("exit")) {
            presenter.getShow();
            System.exit(0);
        }
        return invokeNext(actionName);
    }
}
