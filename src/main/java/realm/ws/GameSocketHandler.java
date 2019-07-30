package realm.ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import realm.GameMessageBroker;
import realm.SessionManager;

import java.io.IOException;

@Component
public class GameSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private GameMessageBroker gameMessageBroker;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        JsonObject convertedObject = new Gson().fromJson(message.getPayload(), JsonObject.class);
        gameMessageBroker.brokerMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionManager.setGameSession(session);
        System.out.println("Game Established (Session ID " + session.getId() + ")");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        sessionManager.removeGameSession();
        System.out.println("Game Closed (Session ID " + session.getId() + ")");

        for (WebSocketSession playerSession : sessionManager.getPlayerSessions()) {
            playerSession.close(CloseStatus.NO_STATUS_CODE);
        }
    }

}