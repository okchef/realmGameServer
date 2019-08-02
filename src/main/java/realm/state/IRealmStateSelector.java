package realm.state;

public interface IRealmStateSelector {
    IRealmStateFragment getStateFragment(RealmState realmState);
}
