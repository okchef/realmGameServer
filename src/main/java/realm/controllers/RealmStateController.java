package realm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import realm.state.IRealmStateFragment;
import realm.state.RealmStateManager;
import realm.state.selectors.FullStateSelector;

@RestController
public class RealmStateController {

    @Autowired
    RealmStateManager realmStateManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, path = "/state")
    public String getState(@RequestParam(value = "gameId", defaultValue = "null") String gameId) {
        IRealmStateFragment fullStateFragment = realmStateManager.getStateFragment(new FullStateSelector());
        return fullStateFragment.toJson();
    }
}
