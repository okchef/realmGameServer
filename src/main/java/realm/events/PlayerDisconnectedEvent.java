package realm.events;

@RealmEvent(EventType.PLAYER_DISCONNECTED)
public class PlayerDisconnectedEvent  extends AbstractRealmEvent {
    public String playerSessionId;

    public String gameSessionId;

    public PlayerDisconnectedEvent(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }
}