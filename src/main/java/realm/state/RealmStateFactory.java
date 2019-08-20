package realm.state;

import realm.realm.utilities.HexMapGenerator;

import java.util.UUID;

public class RealmStateFactory {
    public static RealmState initGameState() {
        String gameId = UUID.randomUUID().toString();
        RealmState realmState = new RealmState(gameId);

        MapState mapState = new MapState();
        HexMapGenerator.generateMap(mapState.hexes, 100, 100);
        realmState.setMap(mapState);

        return realmState;
    }
}
