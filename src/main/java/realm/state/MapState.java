package realm.state;

import realm.realm.HexMapCoordinates;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class MapState implements Serializable {

    public final ConcurrentHashMap<HexMapCoordinates, HexState> hexes = new ConcurrentHashMap<>();

}
