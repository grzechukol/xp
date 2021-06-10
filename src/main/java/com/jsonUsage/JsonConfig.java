package com.jsonUsage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonConfig {
    public static String TRANSACTIONS_FILENAME = "transactions.csv";

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

    public static String getJsonProperty(String key) throws Exception{
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(PATH_TO_JSON));
        JSONObject jsonObject = (JSONObject) obj;
        var property = (String) jsonObject.get(key);

        if (property == null || property.isEmpty()){
            throw new NoPropertyFoundException();
        }

        return property;
    }

    public static String getTransactionsPath() {
        try {
            return getJsonProperty("FilePathCustom");
        } catch (Exception e) {
            var basePath = getBasePath();
            return Paths.get(basePath.toString(), TRANSACTIONS_FILENAME).toString();
        }
    }

    public static String getCurrency() {
        try {
            return getJsonProperty("Currency");
        } catch (Exception e) {
            return "USD";
        }
    }

}
