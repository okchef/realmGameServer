package realm.utilities;

import realm.state.HexMapCoordinates;

public class HexMapUtilities {
    public static HexMapCoordinates move(HexMapCoordinates start, String direction) {
        int x, y;

        switch(direction) {
            case "1":
                x = start.x + 1;
                y = start.y;
                break;
            case "2":
                if (start.y % 2 == 0) {
                    x = start.x;
                } else {
                    x = start.x + 1;
                }
                y = start.y - 1;
                break;
            case "3":
                if (start.y % 2 == 0) {
                    x = start.x - 1;
                } else {
                    x = start.x;
                }
                y = start.y - 1;
                break;
            case "4":
                x = start.x - 1;
                y = start.y;
                break;
            case "5":
                if (start.y % 2 == 0) {
                    x = start.x - 1;
                } else {
                    x = start.x;
                }
                y = start.y + 1;
                break;
            case "6":
                if (start.y % 2 == 0) {
                    x = start.x;
                } else {
                    x = start.x + 1;
                }
                y = start.y + 1;
                break;
            default:
                x = start.x;
                y = start.y;
        }

        return new HexMapCoordinates(x, y);
    }
}
