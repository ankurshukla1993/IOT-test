package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.Row;
import java.util.List;

public final class BaseRealm$RealmObjectContext {
    private boolean acceptDefaultValue;
    private ColumnInfo columnInfo;
    private List<String> excludeFields;
    private BaseRealm realm;
    private Row row;

    public void set(BaseRealm realm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        this.realm = realm;
        this.row = row;
        this.columnInfo = columnInfo;
        this.acceptDefaultValue = acceptDefaultValue;
        this.excludeFields = excludeFields;
    }

    BaseRealm getRealm() {
        return this.realm;
    }

    public Row getRow() {
        return this.row;
    }

    public ColumnInfo getColumnInfo() {
        return this.columnInfo;
    }

    public boolean getAcceptDefaultValue() {
        return this.acceptDefaultValue;
    }

    public List<String> getExcludeFields() {
        return this.excludeFields;
    }

    public void clear() {
        this.realm = null;
        this.row = null;
        this.columnInfo = null;
        this.acceptDefaultValue = false;
        this.excludeFields = null;
    }
}
