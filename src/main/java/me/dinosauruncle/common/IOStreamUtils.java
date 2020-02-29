//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IOStreamUtils {
    private Socket socket = null;

    public IOStreamUtils() {
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void outputStreamExecute(JSONObject jsonObject) {
        String serializeJsonObject = jsonObject.toJSONString();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] serializedObject;
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);

                try {
                    oos.writeObject(serializeJsonObject);
                    serializedObject = baos.toByteArray();
                } catch (Throwable var10) {
                    try {
                        oos.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }

                    throw var10;
                }

                oos.close();
            } catch (Throwable var11) {
                try {
                    baos.close();
                } catch (Throwable var8) {
                    var11.addSuppressed(var8);
                }

                throw var11;
            }

            baos.close();
            ObjectOutputStream var13 = new ObjectOutputStream(this.socket.getOutputStream());
            var13.writeObject(Base64.getEncoder().encodeToString(serializedObject));
        } catch (IOException var12) {
            var12.printStackTrace();
        }

    }

    public JSONObject inputStreamExecute() {
        JSONObject result = null;

        try {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
                String base64Member = objectInputStream.readObject().toString();
                byte[] inputSerialzedMember = Base64.getDecoder().decode(base64Member);
                ByteArrayInputStream bais = new ByteArrayInputStream(inputSerialzedMember);

                try {
                    ObjectInputStream ois = new ObjectInputStream(bais);

                    try {
                        JSONParser jsonParser = new JSONParser();
                        result = (JSONObject)jsonParser.parse(String.valueOf(ois.readObject()));
                        System.out.println(result);
                    } catch (Throwable var20) {
                        try {
                            ois.close();
                        } catch (Throwable var19) {
                            var20.addSuppressed(var19);
                        }

                        throw var20;
                    }

                    ois.close();
                } catch (Throwable var21) {
                    try {
                        bais.close();
                    } catch (Throwable var18) {
                        var21.addSuppressed(var18);
                    }

                    throw var21;
                }

                bais.close();
            } catch (IOException var22) {
                var22.printStackTrace();
            } catch (ClassNotFoundException var23) {
                var23.printStackTrace();
            } catch (ParseException var24) {
                var24.printStackTrace();
            }

            return result;
        } finally {
            ;
        }
    }
}
