package realm.state;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RealmStateFragment implements IRealmStateFragment{

    private final JsonObject jsonObject;

    public RealmStateFragment(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(jsonObject);
    }
}
