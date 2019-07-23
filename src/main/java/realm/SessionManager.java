package realm;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SessionManager {
    private WebSocketSession gameSession;

    private CopyOnWriteArrayList<WebSocketSession> playerSessions = new CopyOnWriteArrayList<>();

    public void setGameSession(WebSocketSession session) {
        gameSession = session;
    }

    public void removeGameSession() {
        gameSession = null;
    }

    public WebSocketSession getGameSession() {
        return (gameSession != null && gameSession.isOpen()) ? gameSession : null;
    }

    public void addPlayerSession(WebSocketSession session) {
        playerSessions.add(session);
    }

    public List<WebSocketSession> getPlayerSessions() {
        return new LinkedList<>(playerSessions);
    }

    public List<WebSocketSession> getOtherPlayerSessions(WebSocketSession playerSession) {
        List<WebSocketSession> otherPlayerSessions = new LinkedList<>();
        for (WebSocketSession s : playerSessions) {
            if (!s.getId().equals(playerSession.getId())) {
                otherPlayerSessions.add(s);
            }
        }
        return otherPlayerSessions;
    }

    public void removePlayerSession(WebSocketSession session) {
        for (WebSocketSession s : playerSessions) {
            if (s.getId().equals(session.getId())) {
                playerSessions.remove(s);
                break;
            }
        }
    }


}
