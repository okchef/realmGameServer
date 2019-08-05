package realm.events;

import realm.mutators.IRealmStateMutator;
import realm.state.PlayerState;
import realm.state.RealmState;
import realm.state.selectors.IRealmStateSelector;
import realm.state.selectors.PlayerStateSelector;
import realm.utilities.HexMapUtilities;

@RealmEvent(EventType.PLAYER_MOVE)
public class PlayerMoveEvent extends AbstractRealmEvent {

    private final String direction;

    public PlayerMoveEvent(String gameId, String playerId, String direction) {
        super(gameId, playerId);
        this.direction = direction;
    }

    @Override
    public EventTargetType getEventTarget() {
        return EventTargetType.All;
    }

    @Override
    public IRealmStateMutator getStateMutator() {
        return (RealmState realmState) -> {
            PlayerState playerState = realmState.getPlayerById(getPlayerId());
            playerState.setPosition(HexMapUtilities.move(playerState.getPosition(), direction));
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return new PlayerStateSelector(getPlayerId());
        /*return (RealmState realmState) -> {
            return null;
        };*/
    }
}
