package com.transactions;

import com.jsonUsage.JsonConfig;
import com.jsonUsage.WrongJsonPathException;

import java.io.*;
import java.util.*;
import java.lang.*;

public class TransactionSaver {
    static String finalTransactionFilePath;

    static {
        finalTransactionFilePath = JsonConfig.getTransactionsPath();
    }

    public static String DEFAULT_FILE = finalTransactionFilePath;
    private String fileName;
    private List<String> allColumnNames;
    private List<String> editableColumnNames;

    public TransactionSaver() {
        this.fileName = DEFAULT_FILE;
        this.allColumnNames = this.createAllColumnNames();
        this.editableColumnNames = this.createEditableColumnNames();
    }

    public TransactionSaver(String fileName, List<String> allColumnNames,  List<String> editableColumnNames) {
        this.fileName = fileName + ".csv";
        this.allColumnNames = allColumnNames;
        this.editableColumnNames = editableColumnNames;
    }

    public int getCurrentId() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.fileName));
            int id = 0;
            String row;
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                id = Integer.parseInt(row.split(";")[0]);
            }
            return id;
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFileName() {
        return fileName;
    }

    private List<String> createAllColumnNames() {
        return Arrays.asList(
                "ID", "Name", "Description", "Date", "Currency", "Price"
        );
    }

    private List<String> createEditableColumnNames() {
        return Arrays.asList(
                "Name", "Description", "Price"
        );
    }

    public List<String> getAllColumnNames() {
        return allColumnNames;
    }

    public List<String> getEditableColumnNames() {
        return editableColumnNames;
    }

    public void appendDataToFile(List<String> data) throws IncorrectDataException, IOException {
        if(data.size() != this.getAllColumnNames().size()) {
            throw new IncorrectDataException("Number of fields doesn't match number of labels");
        }

        Writer csvWriter = new FileWriter(this.fileName, true);
        csvWriter.append(String.join(";", data));
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();
    }
}
