package realm.events;

import realm.mutaters.IRealmStateMutator;
import realm.state.PlayerState;
import realm.state.RealmState;
import realm.state.IRealmStateSelector;

@RealmEvent(EventType.PLAYER_CONNECTED)
public class PlayerConnectedEvent extends AbstractRealmEvent {
    public String playerSessionId;

    public String gameSessionId;

    public PlayerConnectedEvent(String playerSessionId) {
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

            PlayerState playerState = new PlayerState(playerSessionId);

            realmState.playerStates.add(playerState);
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return (RealmState realmState) -> {
            return null;
        };
    }
}
