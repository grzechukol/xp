package com.jsonUsage;

import java.nio.file.Paths;

public class ReadInputArgs {
    public static String PATH_TO_JSON = "C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\simplePath.json";

    public void saveInputArgs(String[] args){
        PATH_TO_JSON = args[0];
    }

    public String setDefaultPath() {
        String pathRelative = "";
        String projectDirectory = System.getProperty("user.dir");
        String os = System.getProperty("os.name");
        String subOS = os.substring(0,7);

        if(subOS.equals("Windows")){
            String jsonAbsolutePath = Paths.get("src/main/resources/simplePath.json").toAbsolutePath().toString();
            pathRelative = "\\src\\main\\resources\\simplePath.json";
            PATH_TO_JSON = "C:\\Users\\Lenovo\\IdeaProjects\\xp\\src\\main\\resources\\simplePath.json";
        }else{
            //TODO problem ze scieżkami przy uruchamianiu .jar (\xp\out\artifacts\xp_labs_jar)
            pathRelative = "/src/main/resources/simplePath.json";
            PATH_TO_JSON = projectDirectory + "/src/main/resources/simplePath.json";
        }
        return pathRelative;
    }

}
