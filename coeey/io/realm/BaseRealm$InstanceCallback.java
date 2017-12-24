package io.realm;

import io.realm.exceptions.RealmException;

public abstract class BaseRealm$InstanceCallback<T extends BaseRealm> {
    public abstract void onSuccess(T t);

    public void onError(Throwable exception) {
        throw new RealmException("Exception happens when initializing Realm in the background thread.", exception);
    }
}
