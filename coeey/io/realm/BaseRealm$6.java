package io.realm;

import io.realm.internal.SharedRealm;
import io.realm.internal.SharedRealm.MigrationCallback;

class BaseRealm$6 implements MigrationCallback {
    final /* synthetic */ RealmMigration val$migration;

    BaseRealm$6(RealmMigration realmMigration) {
        this.val$migration = realmMigration;
    }

    public void onMigrationNeeded(SharedRealm sharedRealm, long oldVersion, long newVersion) {
        this.val$migration.migrate(DynamicRealm.createInstance(sharedRealm), oldVersion, newVersion);
    }
}
