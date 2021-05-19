package com.transactions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader {
    private String fileName;
    private String[] columnNames;
    private List<String[]> fileContent = new ArrayList<String[]>();

    public TransactionReader() throws IOException {
        this.fileName = TransactionSaver.DEFAULT_FILE;
        readColumnNames();
    }

    public TransactionReader(String fileName) throws IOException {
        this.fileName = fileName + ".csv";
        readColumnNames();
    }

    private void readColumnNames() throws FileNotFoundException {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.fileName));
            String columnsLine = csvReader.readLine();
            csvReader.close();

            var columns = columnsLine.split(";");
            columnNames = columns;
        }
        catch (IOException e){
            throw new FileNotFoundException("File" + fileName + " couldn't be found!");
        }
    }

    public List<String[]> readFile() throws FileNotFoundException {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.fileName));
            String row;

            // ominięcie nazw kolumn
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                fileContent.add(row.split(";"));
            }
            return fileContent;
        }
        catch (IOException e) {
            throw new FileNotFoundException("File" + fileName + " couldn't be found!");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

}
