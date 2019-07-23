package realm;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class PlayerMessageBroker extends AbstractMessageBroker {

    @Override
    public void brokerMessage(WebSocketSession session, TextMessage message) throws IOException {
        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            gameSession.sendMessage(new TextMessage("Blorp"));
        }
    }
}
