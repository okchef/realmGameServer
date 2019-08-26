package realm.state;

import realm.realm.HexMapCoordinates;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapState implements Serializable {

    private final ConcurrentHashMap<HexMapCoordinates, HexState> hexes = new ConcurrentHashMap<>();

    private int height;

    private int width;

    private HexMapCoordinates spawnPosition;

    public Map<HexMapCoordinates, HexState> getHexes() {
        return hexes;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public HexMapCoordinates getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(HexMapCoordinates spawnPosition) {
        this.spawnPosition = spawnPosition;
    }
}
