package com.jsonUsage;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJSONFileFromPath {
    public String filePath = "";
    static String projectDirectory = System.getProperty("user.dir");
    String os = System.getProperty("os.name");
    String subOS = os.substring(0,7);

    public String getJsonFilePath(String pathToFile) throws WrongJsonPathException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(pathToFile));
            JSONObject jsonObject = (JSONObject) obj;
            //TODO do edycji sciezki - usuwa ostatnie litery stringa
            projectDirectory = StringUtils.substring(projectDirectory, 0, projectDirectory.length() - 0);

            String customFilePath = (String) jsonObject.get("FilePathCustom");
            //TODO:Odczytywanie JSON'a działa bez FilePathCustom, gdy uruchamiamy w IDE.
            // Natmoiast przy uruchamianiu .jar, trzeba podać ścieżkę absolutną do .json'a

            if(customFilePath != null){
            filePath = customFilePath;
            }else {
            if (subOS.equals("Windows")) {
                filePath = projectDirectory + (String) jsonObject.get("FilePathWindows");
                System.out.println("Sciezka do zapisu i odczytu tranzakcji-" + filePath);
            } else {
                filePath = projectDirectory + (String) jsonObject.get("FilePathUnix");
                System.out.println("Sciezka do zapisu i odczytu tranzakcji-" + filePath);
            }
        }
        } catch (Exception e) {
            throw new WrongJsonPathException("Path" + pathToFile + " couldn't be found!");
        }

        return filePath;
    }
}
