package realm.state;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
    The state of one game of Realm. Do not expose the state outside of this package, so that we can control where it is updated.
    The state should only be accessed outside this package via IRealmStateFragment.
 */
public class RealmState implements Serializable {
    private final String gameId;

    // Maps playerId => playerState
    private final ConcurrentHashMap<String, PlayerState> players = new ConcurrentHashMap();

    public RealmState() {
        gameId = UUID.randomUUID().toString();
    }

    public String getGameId() {
        return gameId;
    }

    public Map<String, PlayerState> getPlayers() {
        return players;
    }

    public PlayerState getPlayerById(String playerId) {
        return players.get(playerId);
    }

    public PlayerState getPlayerBySessionId(String playerSessionId) {
        for (PlayerState playerState : players.values()) {
            if (playerSessionId.equals(playerState.getPlayerSessionId())) {
                return playerState;
            }
        }
        return null;
    }

}
