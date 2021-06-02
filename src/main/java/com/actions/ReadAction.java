package com.actions;

import com.transactions.TransactionReader;
import com.views.BaseView;
import com.views.ReadView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadAction extends BaseAction {
    private TransactionReader transactionReader;
    private BaseView view;

    public ReadAction() throws IOException {
        this.transactionReader = new TransactionReader();
        this.view = new ReadView();
    }

    public void setTransactionReader(TransactionReader transactionReader) {
        this.transactionReader = transactionReader;
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("read")) {
            view.show();
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
