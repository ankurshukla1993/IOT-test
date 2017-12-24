package io.realm;

final class BaseRealm$ThreadLocalRealmObjectContext extends ThreadLocal<BaseRealm$RealmObjectContext> {
    BaseRealm$ThreadLocalRealmObjectContext() {
    }

    protected BaseRealm$RealmObjectContext initialValue() {
        return new BaseRealm$RealmObjectContext();
    }
}
