package realm.events;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import realm.mutators.IRealmStateMutator;
import realm.realm.HexMapCoordinates;
import realm.state.HexState;
import realm.state.PlayerState;
import realm.state.RealmState;
import realm.state.RealmStateFragment;
import realm.state.selectors.IRealmStateSelector;
import realm.state.selectors.PlayerStateSelector;
import realm.realm.utilities.HexMapUtilities;

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

            HexMapCoordinates targetPosition = HexMapUtilities.move(playerState.getPosition(), direction);
            realmState.getMap().getHexes().get(targetPosition).setVisible(true);
            playerState.setPosition(targetPosition);
        };
    }

    @Override
    public IRealmStateSelector getStateSelector() {
        return (RealmState realmState) -> {
            JsonObject realmStateJson = new JsonObject();

            JsonObject players = new JsonObject();
            String playerId = this.getPlayerId();
            players.add(playerId, new Gson().toJsonTree(realmState.getPlayerById(playerId)));

            realmStateJson.add("players", players);

            JsonObject map = new JsonObject();
            JsonObject hexes = new JsonObject();

            HexState destinationHex = realmState.getMap().getHexes().get(realmState.getPlayerById(playerId).getPosition());
            JsonElement hex = new Gson().toJsonTree(destinationHex);

            hexes.add(destinationHex.getPosition().toString(), hex);
            map.add("hexes", hexes);
            realmStateJson.add("map", map);

            return new RealmStateFragment(realmStateJson);
        };
    }
}
