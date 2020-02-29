//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.configuration.ScreensConfiguration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class ServerConnect {
    private Socket socket;
    @Autowired
    Environment env;
    IOStreamUtils ioStreamUtils;
    DataStructureConvert dataStructureConvert;

    public ServerConnect() {
    }

    public Map<String, String> connectAfterResponse(JSONObject jsonObject) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{ScreensConfiguration.class});
        this.ioStreamUtils = (IOStreamUtils)context.getBean("ioStreamUtils");
        this.dataStructureConvert = (DataStructureConvert)context.getBean("dataStructureConvert");
        Map<String, String> resultMap = null;
        if (this.socket == null) {
            this.socket = new Socket();
        } else if (this.socket.isClosed()) {
            this.socket = new Socket();
        }

        JSONObject inputJsonObject = null;

        try {
            this.socket.connect(new InetSocketAddress(String.valueOf(this.env.getProperty("chat.server.ip")), Integer.valueOf(this.env.getProperty("chat.server.ip"))));
            this.ioStreamUtils.setSocket(this.socket);
            this.ioStreamUtils.outputStreamExecute(jsonObject);
            inputJsonObject = this.ioStreamUtils.inputStreamExecute();
            resultMap = this.dataStructureConvert.jsonObjectConvertMap(inputJsonObject);
        } catch (IOException var6) {
        }

        return resultMap;
    }
}
