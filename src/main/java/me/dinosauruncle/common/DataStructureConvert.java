package me.dinosauruncle.common;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStructureConvert {
    private static Map<String, String> responseMap;
    public DataStructureConvert(){
        responseMap = new HashMap<String, String>();
    }

    public static JSONObject mapConvertJsonObject(String type, Map<String, String> map){
        JSONObject jsonObject = null;
        if (map.size() == 0) throw new NullPointerException("parameter map is Null~!");
        JSONObject rootJsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        Set<String> keyList = map.keySet();
        for (String key : keyList){
            jsonObject.put(key, map.get(key));
        }
        jsonArray.add(jsonObject);
        if (StringUtils.isNotEmpty(type)){
            rootJsonObject.put(type, jsonObject);
            return  rootJsonObject;
        } else {
            return jsonObject;
        }
    }

    public static Map<String, String> jsonObjectConvertMap(JSONObject jsonObject) {
        responseMap = new HashMap<String, String>();
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            responseMap.put(key, String.valueOf(jsonObject.get(key)));
        }
        return responseMap;
    }

    public static JSONObject stringToJsonObject(String serializationString){
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(serializationString);
        } catch (ParseException e){

        }
        return jsonObject;
    }
}
