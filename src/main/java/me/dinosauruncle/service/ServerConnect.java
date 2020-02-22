package me.dinosauruncle.service;

import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.common.PropertiesManager;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
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
        try {
            socket.connect(new InetSocketAddress(
                    PropertiesManager.getResourceString("SERVER_IP"), PropertiesManager.getResourceInteger("SERVER_PORT")));
            IOStreamUtils ioStreamUtils = null;
            ioStreamUtils = new IOStreamUtils(socket);
            ioStreamUtils.outputStreamExecute(jsonObject);
            JSONObject inputJsonObject = ioStreamUtils.inputStreamExecute();

            return DataStructureConvert.jsonObjectConvertMap(inputJsonObject);


        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        return null;
    }
}
