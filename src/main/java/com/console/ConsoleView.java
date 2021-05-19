package com.console;

import com.transactions.IncorrectDataException;
import com.transactions.TransactionReader;
import com.transactions.TransactionSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private TransactionSaver transactionSaver;
    private TransactionReader transactionReader;
    private Scanner scanner;

    public ConsoleView() {
        this.transactionSaver = new TransactionSaver();
        this.scanner = new Scanner(System.in);
    }

    public String menu() {
        return "Choose action:\nadd - add transaction\nread - read transactions\nexit - exit console\nACTION: ";
    }

    public List<String> addTransaction() {
        List<String> transactionDetails = new ArrayList<>();

        System.out.println("Adding new transaction...");

        for (String column : transactionSaver.getColumnNames()) {
            System.out.print(column + ": ");
            transactionDetails.add(scanner.nextLine());
        }

        return transactionDetails;
    }

    public void readTransactions() {
        try {
            this.transactionReader = new TransactionReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Reading transactions...");
            for (String[] transaction : this.transactionReader.readFile()) {
                System.out.println(Arrays.toString(transaction));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void consoleLoop() {
        while(true){
            System.out.print(menu());
            switch (scanner.nextLine()) {
                case "add":
                    try {
                        transactionSaver.appendDataToFile(addTransaction());
                    } catch (IncorrectDataException e) {
                        System.out.println("Exception occurred" + e.toString());

                    } catch (IOException e) {
                        System.out.println("Failed write to file" + e.toString());
                    }
                    break;
                case "read":
                    readTransactions();
                    break;
                case "exit":
                    System.out.println("Program exited");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No match for command, try again...");
                    break;
            }
        }
    }
}
