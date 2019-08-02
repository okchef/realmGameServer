package realm.state;

import java.io.Serializable;
import java.util.UUID;

public class PlayerState implements Serializable {

    // For now, add new player to state when connecting. Later, we will allow selecting an
    // existing player and changing their state to connected. Currently, it is assumed that
    // all players in the state will be in a connected state.
    public boolean isConnected = true;

    public int xPos = 0;

    public int yPos = 0;

    public String playerSessionId;

    public String playerId;

    public String playerName;

    public PlayerState(String playerSessionId) {
        this.playerSessionId = playerSessionId;
        this.playerId = UUID.randomUUID().toString();
    }
}
