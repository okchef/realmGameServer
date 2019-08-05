package realm.events;

import realm.mutators.IRealmStateMutator;
import realm.state.PlayerState;
import realm.state.RealmState;
import realm.state.selectors.IRealmStateSelector;
import realm.state.selectors.PlayerStateSelector;

@RealmEvent(EventType.PLAYER_DISCONNECTED)
public class PlayerDisconnectedEvent  extends AbstractRealmEvent {
    private final String playerSessionId;

    public PlayerDisconnectedEvent(String gameId, String playerId, String playerSessionId) {
        super(gameId, playerId);
        this.playerSessionId = playerSessionId;
    }

    @Override
    public EventTargetType getEventTarget() {
        return EventTargetType.All;
    }

    @Override
    public IRealmStateMutator getStateMutator() {
        return (RealmState realmState) -> {
            PlayerState playerState = realmState.getPlayerById(getPlayerId());
            playerState.setConnected(false);
            playerState.setPlayerSessionId(null);
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return new PlayerStateSelector(getPlayerId());
    }
}