package realm.events;

import com.google.gson.Gson;

import java.io.Serializable;

public abstract class AbstractRealmEvent implements Serializable {
    public String playerId;

    public String gameId;

    public final String toString() {
        return new Gson().toJson(this);
    }
}
