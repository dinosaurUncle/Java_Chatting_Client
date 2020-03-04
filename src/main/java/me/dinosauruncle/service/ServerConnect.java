package me.dinosauruncle.service;

import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.common.PropertiesManager;
import me.dinosauruncle.model.User;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.Map;

public class ServerConnect {

    private static ServerConnect instance;
    private ServerConnect(){}
    private static User session;
    private Socket socket;
    private static boolean connecting;

    public static synchronized  ServerConnect getInstance(){
        connecting = false;
        session = new User();
        if (instance == null) instance = new ServerConnect();
        return instance;
    }


    public Map<String, String> connectChatServer(JSONObject jsonObject){
        JSONObject inputJsonObject = null;
        try {
                if (!connecting) {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(
                            PropertiesManager.getResourceString("SERVER_IP"), PropertiesManager.getResourceInteger("SERVER_PORT")));
                }
                IOStreamUtils ioStreamUtils = null;
                ioStreamUtils = new IOStreamUtils(socket);
                ioStreamUtils.outputStreamExecute(jsonObject);
                inputJsonObject = ioStreamUtils.inputStreamExecute();
                if (inputJsonObject.get("connecting") != null){
                    if (StringUtils.isNotEmpty(String.valueOf(inputJsonObject.get("connecting")))){
                        if (String.valueOf(inputJsonObject.get("connecting")).equals("true")) connecting = true;
                    }
                }
                if (!connecting) socket.close();
            } catch (IOException e){

            }

        return DataStructureConvert.jsonObjectConvertMap(inputJsonObject);
    }

    public void setSession(String jsonString){
        if (StringUtils.isNotEmpty(jsonString)) session.setUser(DataStructureConvert.stringToJsonObject(jsonString));
    }

    public static User getSession() {
        return session;
    }
}
