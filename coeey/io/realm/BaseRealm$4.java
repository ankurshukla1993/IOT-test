package io.realm;

import io.realm.internal.Util;
import java.util.concurrent.atomic.AtomicBoolean;

class BaseRealm$4 implements Callback {
    final /* synthetic */ RealmConfiguration val$configuration;
    final /* synthetic */ AtomicBoolean val$realmDeleted;

    BaseRealm$4(RealmConfiguration realmConfiguration, AtomicBoolean atomicBoolean) {
        this.val$configuration = realmConfiguration;
        this.val$realmDeleted = atomicBoolean;
    }

    public void onResult(int count) {
        if (count != 0) {
            throw new IllegalStateException("It's not allowed to delete the file associated with an open Realm. Remember to close() all the instances of the Realm before deleting its file: " + this.val$configuration.getPath());
        }
        this.val$realmDeleted.set(Util.deleteRealm(this.val$configuration.getPath(), this.val$configuration.getRealmDirectory(), this.val$configuration.getRealmFileName()));
    }
}
