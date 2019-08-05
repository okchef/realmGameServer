package realm.mutators;

import realm.events.AbstractRealmEvent;

// TODO: Assess any performance improvements that might exist by using this in lieu of IRealmStateMutator directly.
// Not sure what the performance impact is of referencing realmEvent here versus having anonymous inner class access event directly.

public abstract class RealmStateMutator<T extends AbstractRealmEvent> implements IRealmStateMutator {
    protected T realmEvent;

    protected RealmStateMutator(T realmEvent) {
        this.realmEvent = realmEvent;
    }
}
