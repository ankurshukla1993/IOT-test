package io.realm;

final class RealmObjectSchema$FieldMetaData {
    final boolean defaultNullable;
    final RealmFieldType realmType;

    RealmObjectSchema$FieldMetaData(RealmFieldType realmType, boolean defaultNullable) {
        this.realmType = realmType;
        this.defaultNullable = defaultNullable;
    }
}
