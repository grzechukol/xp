package com.jsonUsage;

public class ReadInputArgs {
    public static String PATH_TO_JSON = "./src/main/resources/simplePath.json";

    public void saveInputArgs(String[] args){
        PATH_TO_JSON = args[0];
    }
}
