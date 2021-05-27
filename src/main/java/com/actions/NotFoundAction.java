package com.actions;

import com.views.BaseView;
import com.views.NotFoundView;
import com.views.ReadView;

public class NotFoundAction extends BaseAction {
    private BaseView view;

    public NotFoundAction() {
        this.view = new NotFoundView();
    }

    @Override
    public boolean invoke(String actionName) {
        view.show();
        return false;
    }
}
