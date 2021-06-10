package com.jsonUsage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConfigTests {

    @Test
    void showSetDefaultPath() {
        JsonConfig testJsonConfig = new JsonConfig();
        String testPath = testJsonConfig.setDefaultPath();
        String os = System.getProperty("os.name");
        String subOS = os.substring(0,7);

        if (subOS.equals("Windows")) {
            assertEquals("\\src\\main\\resources\\configFile.json", testPath);
        }else {
            assertEquals("/src/main/resources/configFile.json", testPath);
        }
    }
}
