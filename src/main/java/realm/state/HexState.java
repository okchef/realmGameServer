package realm.state;

import realm.realm.TerrainType;

import java.io.Serializable;

public class HexState implements Serializable {
    private boolean visible = false;

    private String terrain = TerrainType.GRASS;

    public HexState(String terrain) {
        this.terrain = terrain;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
}
