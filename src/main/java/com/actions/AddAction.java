package com.actions;

import com.transactions.TransactionSaver;
import com.views.AddView;
import com.views.BaseView;

import java.util.*;

public class AddAction extends BaseAction {
    private TransactionSaver transactionSaver;
    private Scanner scanner;
    private BaseView view;

    public AddAction() {
        this.transactionSaver = new TransactionSaver();
        this.view = new AddView();
    }

    public void setTransactionSaver(TransactionSaver transactionSaver) {
        this.transactionSaver = transactionSaver;
    }

    @Override
    public boolean invoke(String actionName) {
        if (actionName.equals("add")) {
            view.show();
            try {
                this.transactionSaver.appendDataToFile(this.addTransaction());
                System.out.println("Transaction added succesfully!");
                return true;
            } catch (Exception e) {
                System.out.println("Please provide price as integer!");
                return true;
            }
        }
        return invokeNext(actionName);
    }

    private List<String> addTransaction() throws InputMismatchException {
        this.scanner = new Scanner(System.in);
        List<String> transactionDetails = new ArrayList<>();
        transactionDetails.add(String.valueOf(this.transactionSaver.getCurrentId() + 1));
        for (String column : transactionSaver.getAllColumnNames()) {
            if(transactionSaver.getEditableColumnNames().contains(column)) {
                System.out.print(column + ": ");
                if(column.equals("Price")) {
                    var line = scanner.nextDouble();
                    transactionDetails.add(Double.toString(line));
                } else {
                    transactionDetails.add(scanner.nextLine());
                }
            } else {
                if(column.equals("Date")) {
                    transactionDetails.add(new Date().toString());
                } else if (column.equals("Currency")) {
                    transactionDetails.add("$");
                }
            }
        }
        return transactionDetails;
    }
}
