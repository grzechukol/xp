package com.jsonUsage;

import java.net.JarURLConnection;
import java.nio.file.Paths;

public class ReadInputArgs {
    public static String getDefaultJsonPath() {
        var basePath =  Paths.get(System.getProperty("user.dir"));
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

}
