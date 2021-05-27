package com.actions;

public abstract class BaseAction {
    private BaseAction next;

    public BaseAction linkWith(BaseAction next) {
        this.next = next;
        return next;
    }

    public abstract boolean invoke(String actionName);

    protected boolean invokeNext(String actionName) {
        if (next == null) {
            return true;
        }
        return next.invoke(actionName);
    }
}
