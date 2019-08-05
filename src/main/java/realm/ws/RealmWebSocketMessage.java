package realm.ws;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import org.springframework.web.socket.TextMessage;
import realm.events.AbstractRealmEvent;
import realm.events.RealmEvent;
import realm.state.IRealmStateFragment;
import realm.state.RealmStateFragmentAdapter;

import java.io.Serializable;

public class RealmWebSocketMessage implements Serializable {

    private String tid;

    private String gameSessionId;

    private String playerSessionId;

    private AbstractRealmEvent realmEvent;

    private String realmEventType;

    @JsonAdapter(RealmStateFragmentAdapter.class)
    private IRealmStateFragment realmStateFragment;

    public RealmWebSocketMessage(String tid, String gameSessionId, String playerSessionId, AbstractRealmEvent realmEvent) {
        this.tid = tid;
        this.gameSessionId = gameSessionId;
        this.playerSessionId = playerSessionId;
        this.realmEvent = realmEvent;
        this.realmEventType = realmEvent.getClass().getAnnotation(RealmEvent.class).value();
    }

    public RealmWebSocketMessage(String tid, String gameSessionId, String playerSessionId, AbstractRealmEvent realmEvent, IRealmStateFragment realmStateFragment) {
        this(tid, gameSessionId, playerSessionId, realmEvent);
        this.realmStateFragment = realmStateFragment;
    }

    public TextMessage getMessage() {
        return new TextMessage(new Gson().toJson(this));
    }
}
