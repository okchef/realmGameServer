package realm.events;

import com.google.gson.Gson;
import realm.mutators.IRealmStateMutator;
import realm.state.selectors.IRealmStateSelector;

import java.io.Serializable;

public abstract class AbstractRealmEvent implements Serializable {
    private String playerId;

    private String gameId;

    public AbstractRealmEvent(String gameId, String playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public abstract EventTargetType getEventTarget();

    public abstract IRealmStateMutator getStateMutator();

    public abstract IRealmStateSelector getStateSelector();

    public final String toString() {
        return new Gson().toJson(this);
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
