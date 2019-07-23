package realm.ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import realm.PlayerMessageBroker;
import realm.SessionManager;

import java.io.IOException;

@Component
public class PlayerSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PlayerMessageBroker playerMessageBroker;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        System.out.println("POOP: " + message.getPayload());

        JsonObject convertedObject = new Gson().fromJson(message.getPayload(), JsonObject.class);
        playerMessageBroker.brokerMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionManager.addPlayerSession(session);
        System.out.println("Player Joined (Session ID " + session.getId() + ")");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Player Left (Session ID " + session.getId() + ")");
    }

}