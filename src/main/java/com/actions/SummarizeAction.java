package com.actions;

import com.presenter.ConsolePresenter;
import com.transactions.TransactionReader;
import com.views.SummarizeView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class SummarizeAction extends BaseAction {
    private ConsolePresenter presenter;
    private TransactionReader transactionReader;

    public SummarizeAction() throws IOException {
        this.presenter = new ConsolePresenter(new SummarizeView());
        this.transactionReader = new TransactionReader();
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("summarize")) {
            presenter.getShow();
            this.displaySummary();
            return true;
        }
        return invokeNext(actionName);
    }

    private void displaySummary() {
        try {
            var transactions =  this.transactionReader.readFile();
            System.out.println("tests");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("First you have to add transactions!");
        }
    }
}
