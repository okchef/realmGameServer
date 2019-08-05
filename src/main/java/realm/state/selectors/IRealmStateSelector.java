package realm.state.selectors;

import realm.state.IRealmStateFragment;
import realm.state.RealmState;

public interface IRealmStateSelector {
    IRealmStateFragment getStateFragment(RealmState realmState);
}
