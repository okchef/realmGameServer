package realm.events;

import com.google.gson.Gson;
import realm.mutaters.IRealmStateMutator;
import realm.state.IRealmStateSelector;

import java.io.Serializable;

public abstract class AbstractRealmEvent implements Serializable {
    public String playerId;

    public String gameId;

    public abstract EventTargetType getEventTarget();

    public abstract IRealmStateMutator getStateMutator();

    public abstract IRealmStateSelector getStateSelector();

    public final String toString() {
        return new Gson().toJson(this);
    }
}
