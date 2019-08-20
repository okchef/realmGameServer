package realm.realm.utilities;

import realm.realm.HexMapCoordinates;
import realm.realm.TerrainType;
import realm.state.HexState;

import java.util.Map;

public class HexMapGenerator {
    public static void generateMap(Map<HexMapCoordinates, HexState> hexes, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                HexState hexState;
                if (Math.random() < 0.2) {
                    hexState = new HexState(TerrainType.WATER);
                } else {
                    hexState = new HexState(TerrainType.GRASS);
                }
                hexes.put(new HexMapCoordinates(i,j), hexState);
            }
        }
    }
}
