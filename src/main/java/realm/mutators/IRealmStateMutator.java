package realm.mutators;

import realm.state.RealmState;

public interface IRealmStateMutator {
    void mutateState(RealmState realmState);
}
