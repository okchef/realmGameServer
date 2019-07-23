package realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public abstract class AbstractMessageBroker {

    @Autowired
    protected SessionManager sessionManager;

    public abstract void brokerMessage(WebSocketSession session, TextMessage message) throws IOException;
}
