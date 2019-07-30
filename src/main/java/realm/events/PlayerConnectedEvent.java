package realm.events;

@RealmEvent(EventType.PLAYER_CONNECTED)
public class PlayerConnectedEvent extends AbstractRealmEvent {
    public String playerSessionId;

    public String gameSessionId;

    public PlayerConnectedEvent(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }
}
