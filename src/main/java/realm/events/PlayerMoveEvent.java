package realm.events;

import realm.mutaters.IRealmStateMutator;
import realm.state.RealmState;
import realm.state.IRealmStateSelector;

@RealmEvent(EventType.PLAYER_MOVE)
public class PlayerMoveEvent extends AbstractRealmEvent {
    public String playerSessionId;

    public String direction;

    public PlayerMoveEvent(String playerSessionId, String direction) {
        this.playerSessionId = playerSessionId;
        this.direction = direction;
    }

    @Override
    public EventTargetType getEventTarget() {
        return EventTargetType.All;
    }

    @Override
    public IRealmStateMutator getStateMutator() {
        return (RealmState realmState) -> {
            //TODO: Update state
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return (RealmState realmState) -> {
            return null;
        };
    }
}
