package com.transactions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.lang.*;

public class TransactionSaver {
    private String fileName;
    public String getFileName() {
        return fileName;
    }

    private List<String> columnNames;
    public List<String> getColumnNames() {
        return columnNames;
    }

    public TransactionSaver() {
        fileName = "./src/main/resources/transactions.csv";
        columnNames = this.createColumnNames();
    }

    public TransactionSaver(String fileName, List<String> columnNames) {
        this.fileName = fileName + ".csv";
        this.columnNames = columnNames;
    }

    private List<String> createColumnNames() {
        return Arrays.asList(
                "ID", "Name", "Description", "Date", "Currency"
        );
    }

    public void appendDataToFile(List<String> data) throws IncorrectDataException, IOException {
        if(data.size() != this.columnNames.size()) {
            throw new IncorrectDataException("Number of fields doesn't match number of labels");
        }

        Writer csvWriter = new FileWriter(this.fileName, true);
        csvWriter.append(String.join(";", data));
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();
    }
}
