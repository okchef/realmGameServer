package realm.state;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
    The state of one game of Realm. Do not expose the state outside of this package, so that we can control where it is updated.
    The state should only be accessed outside this package via IRealmStateFragment.
 */
public class RealmState implements Serializable {
    public String gameId;

    public List<PlayerState> playerStates = new CopyOnWriteArrayList<>();
}
