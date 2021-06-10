package com.jsonUsage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConfigTests {

    @Test
    void getBasePath_ShouldReturnRootPath() {
        // arrange
        var expectedValue = Paths.get(System.getProperty("user.dir"));
        // act
        var result = JsonConfig.getBasePath().toString();
        // assert
        assertEquals(expectedValue.toString(), result);
    }

    @Test
    void saveInputArgs_ShouldSetJsonPath_WhenArgsProvided(){
        // arrange
        var sut = new JsonConfig();
        var testPath = "testPath";
        var testArgs = new String[] {testPath};
        // act
        sut.saveInputArgs(testArgs);
        // assert
        assertEquals(testPath, JsonConfig.PATH_TO_JSON);
    }

    @Test
    void getTransactionsPath_ShouldReturnDefaultPath_WhenNoArgsProvided(){
        // arrange
        var expectedValue = Paths.get(System.getProperty("user.dir"), JsonConfig.TRANSACTIONS_FILENAME).toString();
        // act
        var result = JsonConfig.getTransactionsPath();
        // assert
        assertEquals(expectedValue, result);
    }
}
