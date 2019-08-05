package realm.state.selectors;

import com.google.gson.Gson;
import realm.state.IRealmStateFragment;
import realm.state.RealmState;
import realm.state.RealmStateFragment;

public class FullStateSelector implements IRealmStateSelector {
    @Override
    public IRealmStateFragment getStateFragment(RealmState realmState) {
        return new RealmStateFragment(new Gson().toJsonTree(realmState).getAsJsonObject());
    }
}
