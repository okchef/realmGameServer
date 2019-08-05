package realm.events;

import realm.mutators.IRealmStateMutator;
import realm.state.PlayerState;
import realm.state.RealmState;
import realm.state.selectors.IRealmStateSelector;
import realm.state.selectors.PlayerStateSelector;

@RealmEvent(EventType.PLAYER_CONNECTED)
public class PlayerConnectedEvent extends AbstractRealmEvent {
    private final String playerSessionId;

    public PlayerConnectedEvent(String gameId, String playerId, String playerSessionId) {
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
            // For now, add new player to state when connecting. Later, we will allow selecting an
            // existing player and changing their state to connected. Currently, it is assumed that
            // all players in the state will be in a connected state.

            String playerId = getPlayerId();
            PlayerState playerState = new PlayerState(playerId, playerSessionId);

            realmState.getPlayers().put(playerId, playerState);
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return new PlayerStateSelector(getPlayerId());
    }
}
