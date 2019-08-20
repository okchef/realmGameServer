package realm.ws;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import realm.PlayerMessageBroker;
import realm.SessionManager;
import realm.events.PlayerConnectedEvent;
import realm.events.PlayerDisconnectedEvent;
import realm.state.RealmStateManager;

import java.io.IOException;
import java.util.UUID;

@Component
public class PlayerSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PlayerMessageBroker playerMessageBroker;

    @Autowired
    private RealmStateManager realmStateManager;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        playerMessageBroker.brokerMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessionManager.addPlayerSession(session);

        String playerId = UUID.randomUUID().toString();

        // TODO: Find a better way to get the session ID to the player.
        JsonObject connectionAck = new JsonObject();
        connectionAck.add("playerSessionId", new JsonPrimitive(session.getId()));
        connectionAck.add("playerId", new JsonPrimitive(playerId));
        session.sendMessage(new TextMessage(new Gson().toJson(connectionAck)));

        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            PlayerConnectedEvent playerConnectedEvent = new PlayerConnectedEvent(null, playerId, session.getId());
            playerMessageBroker.handleEvent(gameSession, session, playerConnectedEvent);
        } else {
            session.close(CloseStatus.NO_STATUS_CODE);
        }

        System.out.println("Player Joined (Session ID " + session.getId() + ")");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            PlayerDisconnectedEvent playerDisconnectedEvent = new PlayerDisconnectedEvent(null, realmStateManager.getPlayerIdFromPlayerSessionId(session.getId()), session.getId());
            playerMessageBroker.handleEvent(gameSession, session, playerDisconnectedEvent);
        }
        sessionManager.removePlayerSession(session);
        System.out.println("Player Left (Session ID " + session.getId() + ")");
    }

}