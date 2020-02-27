package me.dinosauruncle.common;

import me.dinosauruncle.main.MainApp;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PropertiesManager {

    public static String RESOUCE_PATH;
    private static String PROPERTY_FILE_NAME = "application.properties";

    public static void initResouceRecode(){
        String identifier = "/me/dinosauruncle";
        System.out.println(PropertiesManager.class.getResource("/").getPath());
        String path = PropertiesManager.class.getResource("").
                getPath().replace("classes", "resources").replace("java/main", "main");
        RESOUCE_PATH = path;
        File propertyFile = new File(RESOUCE_PATH + PROPERTY_FILE_NAME);
        int index = path.indexOf(identifier);
        path = path.substring(0, index+ identifier.length());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resourcePath", path);
        jsonObject.put("login.fxml", MainApp.class.getClassLoader().getResource("me/dinosauruncle/view/login/login.fxml").getPath());
        jsonObject.put("signup.fxml", MainApp.class.getClassLoader().getResource("me/dinosauruncle/view/signup/signup.fxml").getPath());
        jsonObject.put("styles.css", MainApp.class.getClassLoader().getResource("me/dinosauruncle/style/styles.css").getPath());
        jsonObject.put("SERVER_IP", "127.0.0.1");
        jsonObject.put("SERVER_PORT", 5000);
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

    public static JSONObject readPropertyFileGetJsonObject(){
        JSONObject jsonObject = null;
        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(RESOUCE_PATH + PROPERTY_FILE_NAME));
            jsonObject = (JSONObject) obj;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static URL getClassLoaderResource(String key){
        String result = null;
        URL resultUrl = null;
        JSONObject jsonObject = readPropertyFileGetJsonObject();
        result = (String) jsonObject.get(key);
        try {
            resultUrl = new URL("file:" + result);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return resultUrl;
    }



    public static String getResourceString(String key){
        JSONObject jsonObject = readPropertyFileGetJsonObject();
        return (String)jsonObject.get(key);
    }

    public static int getResourceInteger(String key){
        JSONObject jsonObject = readPropertyFileGetJsonObject();
        return Integer.parseInt(String.valueOf(jsonObject.get(key)));
    }
}
