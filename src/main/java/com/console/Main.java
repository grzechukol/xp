package com.console;

import com.transactions.IncorrectDataException;
import com.transactions.TransactionSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> transactionDetails = new ArrayList<>();
        TransactionSaver ts = new TransactionSaver();
        Scanner scan = new Scanner(System.in);

        File transactions = new File("./src/main/resources/transactions.csv");
        try {
            transactions.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        while (true) {
            System.out.print("Choose action:\nadd - add transaction\nexit - exit console\nACTION: ");
            String action = scan.nextLine();

            switch (action) {
                case "add":
                    System.out.println("Adding new transaction...");
                    for (int i = 0; i < ts.getColumnNames().size(); i++) {
                        System.out.print(ts.getColumnNames().get(i) + ": ");
                        transactionDetails.add(scan.nextLine());
                    }

                    try {
                        ts.appendDataToFile(transactionDetails);
                    }
                    catch (IncorrectDataException e) {
                        System.out.println("Exception occurred" + e.toString());

                    }
                    catch (IOException e) {
                        System.out.println("Failed write to file" + e.toString());
                    }
                    transactionDetails.clear();
                    break;

                case "exit":
                    System.out.println("Program exited");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Command no match, try again...");
                    break;
            }
        }
    }
}
