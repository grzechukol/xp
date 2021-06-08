package com.jsonUsage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadJSONFileFromPathTests {

    @Test
    void showGetJsonFilePath() throws WrongJsonPathException {
        ReadJSONFileFromPath testReadJSONFileFromPath = new ReadJSONFileFromPath();
        String testFilePath = testReadJSONFileFromPath.getJsonFilePath("C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\simplePath.json");
        String os = System.getProperty("os.name");
        String subOS = os.substring(0,7);
        String projectDirectory = System.getProperty("user.dir");

        if (subOS.equals("Windows")) {
            assertEquals("C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\transactions.csv", testFilePath);
        }else {
            assertEquals(projectDirectory + "/src/main/resources/simplePath.json", testFilePath);
        }


    }
}
