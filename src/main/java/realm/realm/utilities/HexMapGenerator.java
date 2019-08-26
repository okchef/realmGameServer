package realm.realm.utilities;

import realm.realm.HexMapCoordinates;
import realm.realm.TerrainType;
import realm.state.HexState;
import realm.state.MapState;

import java.util.Map;

public class HexMapGenerator {
    public static void generateMap(MapState mapState, int width, int height) {
        Map<HexMapCoordinates, HexState> hexes = mapState.getHexes();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                HexState hexState;
                HexMapCoordinates position = new HexMapCoordinates(i,j);
                if (Math.random() < 0.2) {
                    hexState = new HexState(position, TerrainType.WATER);
                } else {
                    hexState = new HexState(position, TerrainType.GRASS);
                }
                hexes.put(position, hexState);
            }
        }

        mapState.setWidth(width);
        mapState.setHeight(height);

        HexMapCoordinates spawnPosition = new HexMapCoordinates(width/2, height/2);
        hexes.get(spawnPosition).setVisible(true);
        mapState.setSpawnPosition(spawnPosition);
    }
}
