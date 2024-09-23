package cn.wolfcode.mq;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/{token}")
@Component
public class wsserver {

    public static ConcurrentHashMap<String,Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session){
        clients.put(token,session);
    }

    @OnMessage
    public void onMessage(){

    }

    @OnError
    public void onError(){

    }

    @OnClose
    public void onClose(@PathParam("token") String token){
        clients.remove(token);
    }
}
