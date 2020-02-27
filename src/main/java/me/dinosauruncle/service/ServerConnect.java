package me.dinosauruncle.service;

import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.common.PropertiesManager;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.Map;

public class ServerConnect {

    private static ServerConnect instance;
    private ServerConnect(){}

    public static synchronized  ServerConnect getInstance(){
        if (instance == null) instance = new ServerConnect();
        return instance;
    }


    public Map<String, String> connectAfterResponse (JSONObject jsonObject){
        Socket socket = new Socket();
        JSONObject inputJsonObject = null;
            try{
                socket.connect(new InetSocketAddress(
                        PropertiesManager.getResourceString("SERVER_IP"), PropertiesManager.getResourceInteger("SERVER_PORT")));
                IOStreamUtils ioStreamUtils = null;
                ioStreamUtils = new IOStreamUtils(socket);
                ioStreamUtils.outputStreamExecute(jsonObject);
                inputJsonObject = ioStreamUtils.inputStreamExecute();
            } catch (IOException e){

            }

        return DataStructureConvert.jsonObjectConvertMap(inputJsonObject);
    }
}
