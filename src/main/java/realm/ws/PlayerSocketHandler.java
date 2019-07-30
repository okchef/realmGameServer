package realm.ws;

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

import java.io.IOException;

@Component
public class PlayerSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private PlayerMessageBroker playerMessageBroker;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        playerMessageBroker.brokerMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessionManager.addPlayerSession(session);

        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            PlayerConnectedEvent playerConnectedEvent = new PlayerConnectedEvent(session.getId());
            RealmWebSocketMessage playerConnectedMessage = new RealmWebSocketMessage(null, gameSession.getId(), session.getId(), playerConnectedEvent);
            gameSession.sendMessage(playerConnectedMessage.getMessage());
        } else {
            session.close(CloseStatus.NO_STATUS_CODE);
        }

        System.out.println("Player Joined (Session ID " + session.getId() + ")");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            PlayerDisconnectedEvent playerDisconnectedEvent = new PlayerDisconnectedEvent(session.getId());
            RealmWebSocketMessage playerDisconnectedMessage = new RealmWebSocketMessage(null, gameSession.getId(), session.getId(), playerDisconnectedEvent);
            gameSession.sendMessage(playerDisconnectedMessage.getMessage());
        }
        System.out.println("Player Left (Session ID " + session.getId() + ")");
    }

}