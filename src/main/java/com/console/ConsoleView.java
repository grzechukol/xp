package com.console;

import com.transactions.IncorrectDataException;
import com.transactions.TransactionSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    public void createFile(String fileName) {
        File transactions = new File(fileName);
        try {
            transactions.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public List<String> consoleLoop() {
        List<String> outList = new ArrayList<>();
        createFile("./src/main/resources/transactions.csv");

        List<String> transactionDetails = new ArrayList<>();
        TransactionSaver ts = new TransactionSaver();
        Scanner scan = new Scanner(System.in);

        while(true){
        System.out.print("Choose action:\nadd - add transaction\nexit - exit console\nACTION: ");
        String action = scan.nextLine();
        outList.add(action);

        switch (action) {
            case "add":
                System.out.println("Adding new transaction...");
                for (int i = 0; i < ts.getColumnNames().size(); i++) {
                    System.out.print(ts.getColumnNames().get(i) + ": ");
                    action = scan.nextLine();
                    transactionDetails.add(action);
                    outList.add(action);
                }

                try {
                    ts.appendDataToFile(transactionDetails);
                } catch (IncorrectDataException e) {
                    System.out.println("Exception occurred" + e.toString());

                } catch (IOException e) {
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
            return outList;
        }
    }
}
