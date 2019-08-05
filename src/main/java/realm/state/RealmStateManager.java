package realm.state;

import org.springframework.stereotype.Component;
import realm.events.AbstractRealmEvent;
import realm.state.selectors.IRealmStateSelector;

/*
    Responsible for managing the states of all active games of Realm on this server.

    For now, it only manages a single game at a time.
 */
@Component
public class RealmStateManager {

    //private HashMap<String, RealmState> realmStates;

    // IMPORTANT! The state should never be made available outside of the state manager.
    // It can only be interacted with via mutators and selectors.
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

    public String getPlayerIdFromPlayerSessionId(String playerSessionId) {
        PlayerState playerState = realmState.getPlayerBySessionId(playerSessionId);
        return playerState != null ? playerState.getPlayerId() : null;
    }
}
