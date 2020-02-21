package me.dinosauruncle.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import me.dinosauruncle.main.MainApp;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class PropertiesManager {

    public static String RESOUCE_PATH;
    private static String PROPERTY_FILE_NAME = "application.properties";

    public static void initResouceRecode(){
        String identifier = "/me/dinosauruncle";

        String path = PropertiesManager.class.getResource("").
                getPath().replace("classes", "resources");
        RESOUCE_PATH = path;
        File propertyFile = new File(RESOUCE_PATH + PROPERTY_FILE_NAME);
        int index = path.indexOf(identifier);
        path = path.substring(0, index+ identifier.length());
        JSONObject jsonObject = new JSONObject();
        URL url = MainApp.class.getClassLoader().getResource("me/dinosauruncle/login/login.fxml");
        jsonObject.put("resourcePath", path);
        jsonObject.put("login.fxml", MainApp.class.getClassLoader().getResource("me/dinosauruncle/login/login.fxml").getPath());
        jsonObject.put("styles.css", MainApp.class.getClassLoader().getResource("me/dinosauruncle/style/styles.css").getPath());
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(propertyFile);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static URL getClassLoaderResource(String key){
        String result = null;
        URL resultUrl = null;
        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(RESOUCE_PATH + PROPERTY_FILE_NAME));
            JSONObject jsonObject = (JSONObject) obj;
            result = (String) jsonObject.get(key);
            resultUrl = new URL("file:" +result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return resultUrl;
    }
}
