package com.presenter;

import com.views.BaseView;

public class ConsolePresenter extends BasePresenter<BaseView> implements Presenter {
    private BaseView view;

    public ConsolePresenter(BaseView view) {
        this.view = view;
    }

    @Override
    public void getShow() {
        this.view.show();
    }
}
