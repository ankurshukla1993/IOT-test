package io.realm.internal;

import io.realm.RealmFieldType;

public interface TableSchema {
    long addColumn(RealmFieldType realmFieldType, String str);

    void removeColumn(long j);

    void renameColumn(long j, String str);
}
