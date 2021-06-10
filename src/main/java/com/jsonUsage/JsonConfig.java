package com.jsonUsage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.JarURLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonConfig {
    public static Path getBasePath() {
        return Paths.get(System.getProperty("user.dir"));
    }

    public static String getDefaultJsonPath() {
        var basePath = getBasePath();
        return Paths.get(basePath.toString(), "configFile.json").toString();
    }

    public static String PATH_TO_JSON = getDefaultJsonPath();

    public void saveInputArgs(String[] args){
        if (args.length > 0){
            PATH_TO_JSON = args[0];
        }
    }

    public String setDefaultPath() {
        PATH_TO_JSON = getDefaultJsonPath();
        return PATH_TO_JSON;
    }

    public static String getTransactionsPath() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(PATH_TO_JSON));
            JSONObject jsonObject = (JSONObject) obj;
            String customFilePath = (String) jsonObject.get("FilePathCustom");
            return customFilePath;
        } catch (Exception e) {
            var basePath = getBasePath();
            return Paths.get(basePath.toString(), "transactions.csv").toString();
        }
    }

}
