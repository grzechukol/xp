package com.console;

import com.actions.*;
import com.transactions.IncorrectDataException;
import com.transactions.TransactionReader;
import com.transactions.TransactionSaver;
import com.views.AddView;
import com.views.BaseView;
import com.views.MainView;
import com.views.ReadView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private TransactionSaver transactionSaver;
    private BaseAction action;
    private Scanner scanner;

    public ConsoleView() throws IncorrectDataException, IOException {
        this.transactionSaver = new TransactionSaver();
        File transactionFile = new File(this.transactionSaver.DEFAULT_FILE);
        if(!transactionFile.exists()) {
            File file = new File(TransactionSaver.DEFAULT_FILE);
            file.createNewFile();
            this.transactionSaver.appendDataToFile(this.transactionSaver.getAllColumnNames());
        }
        this.action = new AddAction();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        BaseView view;
        this.action
                .linkWith(new ReadAction())
                .linkWith(new ExitAction())
                .linkWith(new NotFoundAction());

        while(true) {
            view = new MainView();
            view.show();
            var command = scanner.nextLine();
            this.action.invoke(command);
        }
    }
}
