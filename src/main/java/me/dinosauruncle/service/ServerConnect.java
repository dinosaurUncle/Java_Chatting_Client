package me.dinosauruncle.service;

import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.common.PropertiesManager;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
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
        JSONObject inputJsonObject = null;
            /*
            socket.connect(new InetSocketAddress(
                    PropertiesManager.getResourceString("SERVER_IP"), PropertiesManager.getResourceInteger("SERVER_PORT")));
            IOStreamUtils ioStreamUtils = null;
            ioStreamUtils = new IOStreamUtils(socket);
            ioStreamUtils.outputStreamExecute(jsonObject);
            inputJsonObject = ioStreamUtils.inputStreamExecute();*/
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toJSONString(), headers);
            ResponseEntity<String> response =
                    restTemplate.postForEntity("http://localhost:8089/account/login", entity, String.class);

            System.out.println(response);

        return null;
    }
}
