//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
            rootJsonObject.put(type, jsonObject);
            return rootJsonObject;
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
}
