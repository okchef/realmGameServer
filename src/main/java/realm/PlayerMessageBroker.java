package realm;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import realm.events.AbstractRealmEvent;
import realm.state.IRealmStateFragment;
import realm.ws.RealmWebSocketMessage;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class PlayerMessageBroker extends AbstractMessageBroker {

    @Override
    public void brokerMessage(WebSocketSession session, TextMessage inMessage) throws IOException {
        WebSocketSession gameSession = sessionManager.getGameSession();
        if (gameSession != null) {
            System.out.println("Player Message: " + inMessage.getPayload());
            AbstractRealmEvent realmEvent = realmEventFactory.getRealmEvent(null, realmStateManager.getPlayerIdFromPlayerSessionId(session.getId()), inMessage.getPayload());

            if (realmEvent != null) {
                handleEvent(gameSession, session, realmEvent);
            }
        }
    }

    public void handleEvent(WebSocketSession gameSession, WebSocketSession playerSession, AbstractRealmEvent realmEvent) throws IOException {
        // Mutate the game state based on the incoming event.
        realmStateManager.mutateState(realmEvent);

        // Grab the relevant state fragment for outMessage.
        IRealmStateFragment realmStateFragment = realmStateManager.getStateFragment(realmEvent);

        // Forward appropriate message details to all required parties.
        // In the future, explore the possibility of only communicating the relevant
        // portion of the updated state, rather than the original action/event payload. This could
        // cut down on duplicated event-processing logic between server/playerClient/gameClient.
        RealmWebSocketMessage realmWebSocketMessage = new RealmWebSocketMessage(null, gameSession.getId(), playerSession.getId(), realmEvent, realmStateFragment);

        ArrayList<WebSocketSession> targetSessions = new ArrayList<>();
        switch(realmEvent.getEventTarget()) {
            case All:
                targetSessions.addAll(sessionManager.getPlayerSessions());
                targetSessions.add(gameSession);
                break;
            case AllPlayers:
                targetSessions.addAll(sessionManager.getPlayerSessions());
                break;
            case GameServer:
                targetSessions.add(gameSession);
                break;
        }

        TextMessage outMessage = realmWebSocketMessage.getMessage();
        for (WebSocketSession targetSession : targetSessions) {
            if (targetSession.isOpen()) {
                targetSession.sendMessage(outMessage);
            }
        }
    }
}
