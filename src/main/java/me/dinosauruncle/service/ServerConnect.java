//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

import me.dinosauruncle.ChattingClientApplication;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.configuration.ScreensConfiguration;
import me.dinosauruncle.model.User;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerConnect {
    private Socket socket;

    IOStreamUtils ioStreamUtils;
    DataStructureConvert dataStructureConvert;
    private static User session;
    private static boolean connecting;

    public ServerConnect() {
        connecting = false;
        session = new User();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{ScreensConfiguration.class});
        this.ioStreamUtils = (IOStreamUtils)context.getBean("ioStreamUtils");
        this.dataStructureConvert = (DataStructureConvert)context.getBean("dataStructureConvert");
    }

    public Map<String, String> connectChatServer(JSONObject jsonObject) {
        JSONObject inputJsonObject = null;
            try {
                if (!connecting) {
                    socket = new Socket();
                    this.socket.connect
                            (new InetSocketAddress
                                    (String.valueOf(ChattingClientApplication.propertyMap.get("chat.server.ip")),
                                            Integer.valueOf(String.valueOf(ChattingClientApplication.propertyMap.get("chat.server.port")))
                                    )
                            );
                }

                this.ioStreamUtils.setSocket(this.socket);
                this.ioStreamUtils.outputStreamExecute(jsonObject);
                inputJsonObject = this.ioStreamUtils.inputStreamExecute();

                if (inputJsonObject.get("connecting") != null){
                    if (StringUtils.isNotEmpty(String.valueOf(inputJsonObject.get("connecting")))){
                        if (String.valueOf(inputJsonObject.get("connecting")).equals("true")) connecting = true;
                    }
                }
                if (!connecting) socket.close();
        } catch (IOException var6) {
        }

        return dataStructureConvert.jsonObjectConvertMap(inputJsonObject);
    }

    public void setSession(String jsonString){
        if (StringUtils.isNotEmpty(jsonString)) session.setUser(dataStructureConvert.stringToJsonObject(jsonString));
    }

    public static User getSession() {
        return session;
    }
}
