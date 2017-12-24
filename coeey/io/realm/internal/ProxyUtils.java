package io.realm.internal;

import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import java.util.Map;

public class ProxyUtils {
    public static void verifyField(SharedRealm sharedRealm, Map<String, RealmFieldType> columnTypes, String fieldName, RealmFieldType fieldType, String fieldSimpleType) {
        if (!columnTypes.containsKey(fieldName)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), String.format("Missing field '%s' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().", new Object[]{fieldName}));
        } else if (columnTypes.get(fieldName) != fieldType) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), String.format("Invalid type '%s' for field '%s' in existing Realm file.", new Object[]{fieldSimpleType, fieldName}));
        }
    }
}
