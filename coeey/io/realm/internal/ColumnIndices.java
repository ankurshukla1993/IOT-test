package io.realm.internal;

import io.realm.RealmModel;
import io.realm.exceptions.RealmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public final class ColumnIndices {
    private final Map<Class<? extends RealmModel>, ColumnInfo> classToColumnInfoMap = new HashMap();
    private final RealmProxyMediator mediator;
    private final OsSchemaInfo osSchemaInfo;
    private final Map<String, ColumnInfo> simpleClassNameToColumnInfoMap = new HashMap();

    public ColumnIndices(RealmProxyMediator mediator, OsSchemaInfo osSchemaInfo) {
        this.mediator = mediator;
        this.osSchemaInfo = osSchemaInfo;
    }

    @Nonnull
    public ColumnInfo getColumnInfo(Class<? extends RealmModel> clazz) {
        ColumnInfo columnInfo = (ColumnInfo) this.classToColumnInfoMap.get(clazz);
        if (columnInfo != null) {
            return columnInfo;
        }
        columnInfo = this.mediator.createColumnInfo(clazz, this.osSchemaInfo);
        this.classToColumnInfoMap.put(clazz, columnInfo);
        return columnInfo;
    }

    @Nonnull
    public ColumnInfo getColumnInfo(String simpleClassName) {
        ColumnInfo columnInfo = (ColumnInfo) this.simpleClassNameToColumnInfoMap.get(simpleClassName);
        if (columnInfo == null) {
            for (Class modelClass : this.mediator.getModelClasses()) {
                if (Table.getClassNameForTable(this.mediator.getTableName(modelClass)).equals(simpleClassName)) {
                    columnInfo = getColumnInfo(modelClass);
                    this.simpleClassNameToColumnInfoMap.put(simpleClassName, columnInfo);
                    break;
                }
            }
        }
        if (columnInfo != null) {
            return columnInfo;
        }
        throw new RealmException(String.format(Locale.US, "'%s' doesn't exist in current schema.", new Object[]{simpleClassName}));
    }

    public void refresh() {
        for (Entry<Class<? extends RealmModel>, ColumnInfo> entry : this.classToColumnInfoMap.entrySet()) {
            ((ColumnInfo) entry.getValue()).copyFrom(this.mediator.createColumnInfo((Class) entry.getKey(), this.osSchemaInfo));
        }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("ColumnIndices[");
        boolean commaNeeded = false;
        for (Entry<Class<? extends RealmModel>, ColumnInfo> entry : this.classToColumnInfoMap.entrySet()) {
            if (commaNeeded) {
                buf.append(",");
            }
            buf.append(((Class) entry.getKey()).getSimpleName()).append("->").append(entry.getValue());
            commaNeeded = true;
        }
        return buf.append("]").toString();
    }
}
