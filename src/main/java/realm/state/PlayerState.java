package realm.state;

import java.io.Serializable;

public class PlayerState implements Serializable {
    // For now, add new player to state when connecting. Later, we will allow selecting an
    // existing player and changing their state to connected.
    private boolean connected = true;

    private HexMapCoordinates position = new HexMapCoordinates(0,0);

    private String playerSessionId;

    private final String playerId;

    private String playerName;

    public PlayerState(String playerId, String playerSessionId) {
        this.playerId = playerId;
        this.playerSessionId = playerSessionId;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public HexMapCoordinates getPosition() {
        return position;
    }

    public void setPosition(HexMapCoordinates position) {
        this.position = position;
    }

    public String getPlayerSessionId() {
        return playerSessionId;
    }

    public void setPlayerSessionId(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
