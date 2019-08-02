package realm.state;

import org.springframework.stereotype.Component;
import realm.events.AbstractRealmEvent;

/*
    Responsible for managing the states of all active games of Realm on this server.

    For now, it only manages a single game at a time.
 */
@Component
public class RealmStateManager {

    //private HashMap<String, RealmState> realmStates;

    private RealmState realmState = new RealmState();

    public void mutateState(AbstractRealmEvent event) {
        // TODO: IMPORTANT! Investigate thread safety of these state updates.
        // For a given game, we should not be running state updates in parallel.

        event.getStateMutator().mutateState(realmState);
    }

    public IRealmStateFragment getStateFragment(IRealmStateSelector realmStateSelector) {
        return realmStateSelector.getStateFragment(realmState);
    }

    public IRealmStateFragment getStateFragment(AbstractRealmEvent realmEvent) {
        return realmEvent.getStateSelector().getStateFragment(realmState);
    }
}
