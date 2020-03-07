//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataStructureConvert {
    private Map<String, String> responseMap = new HashMap();

    public DataStructureConvert() {
    }

    public JSONObject mapConvertJsonObject(String type, Map<String, String> map) {
        JSONObject jsonObject = null;
        if (map.size() == 0) {
            throw new NullPointerException("parameter map is Null~!");
        } else {
            JSONObject rootJsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject = new JSONObject();
            Set<String> keyList = map.keySet();
            Iterator var7 = keyList.iterator();

            while(var7.hasNext()) {
                String key = (String)var7.next();
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
    }

    public Map<String, String> jsonObjectConvertMap(JSONObject jsonObject) {
        this.responseMap = new HashMap();
        Set<String> keySet = jsonObject.keySet();
        Iterator var3 = keySet.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            this.responseMap.put(key, String.valueOf(jsonObject.get(key)));
        }

        return this.responseMap;
    }

    public JSONObject stringToJsonObject(String serializationString){
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(serializationString);
        } catch (ParseException e){

        }
        return jsonObject;
    }
}
