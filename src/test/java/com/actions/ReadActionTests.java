package com.actions;

import com.transactions.TransactionReader;
import com.views.NotFoundView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReadActionTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        File testDir = new File("./test");
        testDir.mkdir();

        var fileName = "./test/file";
        var expected = new ArrayList<String[]>();
        expected.add(new String[]{ "1","Transaction1","Transaction1 Description","10-05-2021","EUR" });
        expected.add(new String[]{ "2", "Transaction2", "Transaction2 Description", "11-05-2021","USD" });

        try {
            File file = new File(fileName + ".csv");
            file.createNewFile();
            Writer fileWriter = new FileWriter(fileName + ".csv", false);
            var columns = Arrays.asList(
                    "ID", "Name", "Description", "Date", "Currency"
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
    public void Inovke_ShouldCallReadTransactions_WhenActionRead() throws IOException {
        var sut = new ReadAction();
        var fileName = "./test/file";
        TransactionReader transactionReader = new TransactionReader(fileName);
        sut.setTransactionReader(transactionReader);
        sut.invoke("read");
        var expected = "Reading transactions...\r\n" +
                "[1, Transaction1, Transaction1 Description, 10-05-2021, EUR]\r\n" +
                "[2, Transaction2, Transaction2 Description, 11-05-2021, USD]";
        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void Inovke_ShouldCallNextAction_WhenActionIsNotRead() throws IOException {
        ReadAction readActionMock = Mockito.spy(new ReadAction());
        readActionMock.invoke("other");
        verify(readActionMock, times(1)).invokeNext("other");
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}
