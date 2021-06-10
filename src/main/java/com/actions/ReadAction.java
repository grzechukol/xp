package com.actions;

import com.presenter.ConsolePresenter;
import com.transactions.TransactionReader;
import com.views.ReadView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadAction extends BaseAction {
    private TransactionReader transactionReader;
    private ConsolePresenter presenter;

    public ReadAction() throws IOException {
        this.transactionReader = new TransactionReader();
        this.presenter = new ConsolePresenter(new ReadView());
    }

    public void setTransactionReader(TransactionReader transactionReader) {
        this.transactionReader = transactionReader;
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("read")) {
            presenter.getShow();
            this.readTransactions();
            return true;
        }
        return invokeNext(actionName);
    }

    private void readTransactions() {
        try {
            for (String[] transaction : this.transactionReader.readFile()) {
                System.out.println(Arrays.toString(transaction));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("First you have to add transactions!");
        }
    }
}
