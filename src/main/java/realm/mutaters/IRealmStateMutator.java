package realm.mutaters;

import realm.state.RealmState;

public interface IRealmStateMutator {
    void mutateState(RealmState realmState);
}
