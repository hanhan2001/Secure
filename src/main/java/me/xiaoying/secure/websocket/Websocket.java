package me.xiaoying.secure.websocket;

import com.alibaba.fastjson.JSONObject;
import me.xiaoying.secure.utils.StringUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.servlet.http.Cookie;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Websocket extends WebSocketServer {
    public Websocket(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);

        // cookie处理
        List<Cookie> list = new ArrayList<>();
        String cookieString = jsonObject.getString("Cookies");
        cookieString = StringUtil.removeString(cookieString, " ");
        for (String s1 : cookieString.split(";")) {
            String[] spl = s1.split("=");
            list.add(new Cookie(spl[0], spl[1]));
        }
        Cookie[] cookies = list.toArray(new Cookie[0]);

        // 消息类型区分
        int code = jsonObject.getIntValue("code");
        if (code == 100) {
            String user = jsonObject.getString("user");
            String password = jsonObject.getString("password");
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}