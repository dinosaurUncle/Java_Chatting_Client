package me.dinosauruncle.common;

import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;

public class DataStructureConvert {

    public static JSONObject mapConvertJsonObject(Map<String, String> map){
        JSONObject jsonObject = null;
        if (map.size() == 0) throw new NullPointerException("parameter map is Null~!");

        jsonObject = new JSONObject();
        Set<String> keyList = map.keySet();
        for (String key : keyList){
            jsonObject.put(key, map.get(key));
        }
        return jsonObject;
    }
}
