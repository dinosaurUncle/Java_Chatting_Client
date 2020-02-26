package me.dinosauruncle.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        rootJsonObject.put(type, jsonObject);
        return rootJsonObject;
    }

    public static Map<String, String> jsonObjectConvertMap(JSONObject jsonObject) {
        responseMap = new HashMap<String, String>();
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            responseMap.put(key, String.valueOf(jsonObject.get(key)));
        }
        return responseMap;
    }
}
