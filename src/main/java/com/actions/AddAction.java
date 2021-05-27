package com.actions;

import com.transactions.TransactionSaver;
import com.views.AddView;
import com.views.BaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddAction extends BaseAction {
    private TransactionSaver transactionSaver;
    private Scanner scanner;
    private BaseView view;

    public AddAction() {
        this.transactionSaver = new TransactionSaver();
        this.scanner = new Scanner(System.in);
        this.view = new AddView();
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("add")) {
            view.show();
            try {
                this.transactionSaver.appendDataToFile(this.addTransaction());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invokeNext(actionName);
    }

    private List<String> addTransaction() {
        List<String> transactionDetails = new ArrayList<>();

        for (String column : transactionSaver.getColumnNames()) {
            System.out.print(column + ": ");
            transactionDetails.add(scanner.nextLine());
        }

        return transactionDetails;
    }
}
