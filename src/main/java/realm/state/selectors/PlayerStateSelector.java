package realm.state.selectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import realm.state.IRealmStateFragment;
import realm.state.RealmState;
import realm.state.RealmStateFragment;

public class PlayerStateSelector implements IRealmStateSelector {
    private final String playerId;

    public PlayerStateSelector(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public IRealmStateFragment getStateFragment(RealmState realmState) {
        JsonObject realmStateJson = new JsonObject();

        JsonObject players = new JsonObject();
        players.add(playerId, new Gson().toJsonTree(realmState.getPlayerById(playerId)));

        realmStateJson.add("players", players);

        return new RealmStateFragment(realmStateJson);
    }
}
