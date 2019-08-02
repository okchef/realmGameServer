package realm.events;

import realm.mutaters.IRealmStateMutator;
import realm.state.RealmState;
import realm.state.IRealmStateSelector;

@RealmEvent(EventType.PLAYER_DISCONNECTED)
public class PlayerDisconnectedEvent  extends AbstractRealmEvent {
    public String playerSessionId;

    public String gameSessionId;

    public PlayerDisconnectedEvent(String playerSessionId) {
        this.playerSessionId = playerSessionId;
    }

    @Override
    public EventTargetType getEventTarget() {
        return EventTargetType.All;
    }

    @Override
    public IRealmStateMutator getStateMutator() {
        return (RealmState realmState) -> {
            // For now, remove player from state when disconnecting. Later, we will allow selecting an
            // existing player and changing their state to connected. Currently, it is assumed that
            // all players in the state will be in a connected state.

            realmState.playerStates.removeIf(playerState -> playerState.playerSessionId.equals(playerSessionId));
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return (RealmState realmState) -> {
            return null;
        };
    }
}