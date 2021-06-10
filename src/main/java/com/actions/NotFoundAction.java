package com.actions;

import com.presenter.ConsolePresenter;
import com.views.BaseView;
import com.views.NotFoundView;
import com.views.ReadView;

public class NotFoundAction extends BaseAction {
    private ConsolePresenter presenter;

    public NotFoundAction() {
        this.presenter = new ConsolePresenter(new NotFoundView());
    }

    @Override
    public boolean invoke(String actionName) {
        presenter.getShow();
        return false;
    }
}
