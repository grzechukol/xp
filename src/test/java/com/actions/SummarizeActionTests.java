package com.actions;

import com.transactions.TransactionReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummarizeActionTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        File testDir = new File("./test");
        testDir.mkdir();

        var fileName = "./test/file";
        var expected = new ArrayList<String[]>();
        // "ID", "Name", "Description", "Date", "Currency", "Price"
        expected.add(new String[]{ "1", "Transaction1","Transaction1 Description","10-05-2021","EUR", "10" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD", "-3" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD", "5" });

        try {
            File file = new File(fileName + ".csv");
            file.createNewFile();
            Writer fileWriter = new FileWriter(fileName + ".csv", false);
            var columns = Arrays.asList(
                    "ID", "Name", "Description", "Date", "Currency", "Price"
            );
            fileWriter.append(String.join(";", columns));
            fileWriter.append("\n");
            fileWriter.append(String.join(";", expected.get(0)));
            fileWriter.append("\n");
            fileWriter.append(String.join(";", expected.get(1)));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        try {
            var dir = new File("./test");
            for (File file : dir.listFiles())
                file.delete();
            dir.delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Inovke_ShouldShowProperSummary_WhenActionRead() throws IOException {
        var sut = new SummarizeAction();
        var fileName = "./test/file";
        TransactionReader transactionReader = new TransactionReader(fileName);
        sut.setTransactionReader(transactionReader);
        sut.invoke("summarize");
        var expected = "Summarizing transactions...\n" +
                "Transaction1 owes you 10.0 EUR\n" +
                "You owe Transaction2 3.0 USD";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }
}

