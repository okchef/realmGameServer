package realm.ws;

import com.google.gson.Gson;
import org.springframework.web.socket.TextMessage;
import realm.events.AbstractRealmEvent;
import realm.events.RealmEvent;

import java.io.Serializable;

public class RealmWebSocketMessage implements Serializable {

    public String tid;

    public String gameSessionId;

    public String playerSessionId;

    public AbstractRealmEvent realmEvent;

    public String realmEventType;

    public RealmWebSocketMessage(String tid, String gameSessionId, String playerSessionId, AbstractRealmEvent realmEvent) {
        this.tid = tid;
        this.gameSessionId = gameSessionId;
        this.playerSessionId = playerSessionId;
        this.realmEvent = realmEvent;
        this.realmEventType = realmEvent.getClass().getAnnotation(RealmEvent.class).value();
    }

    public TextMessage getMessage() {
        return new TextMessage(new Gson().toJson(this));
    }
}
