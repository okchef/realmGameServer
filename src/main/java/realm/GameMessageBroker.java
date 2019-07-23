package realm;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class GameMessageBroker extends AbstractMessageBroker {

    @Override
    public void brokerMessage(WebSocketSession session, TextMessage message) {

    }
}
