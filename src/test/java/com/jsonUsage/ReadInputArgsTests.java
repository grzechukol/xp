package com.jsonUsage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadInputArgsTests {

    @Test
    void showSetDefaultPath() {
        ReadInputArgs testReadInputArgs = new ReadInputArgs();
        String testPath = testReadInputArgs.setDefaultPath();
        String os = System.getProperty("os.name");
        String subOS = os.substring(0,7);

        if (subOS.equals("Windows")) {
            assertEquals("\\src\\main\\resources\\simplePath.json", testPath);
        }else {
            assertEquals("/src/main/resources/simplePath.json", testPath);
        }
    }
}
