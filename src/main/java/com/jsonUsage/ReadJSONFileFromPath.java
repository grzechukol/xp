package com.jsonUsage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJSONFileFromPath {
    public String filePath = "";

    public String getJsonFilePath(String pathToFile) throws WrongJsonPathException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(pathToFile));

            JSONObject jsonObject = (JSONObject) obj;
            filePath = (String) jsonObject.get("FilePath");
            System.out.println("Sciezka do zapisu i odczytu tranzakcji-" + filePath);

        } catch (Exception e) {
            throw new WrongJsonPathException("Path" + pathToFile + " couldn't be found!");
        }

        return filePath;
    }
}
