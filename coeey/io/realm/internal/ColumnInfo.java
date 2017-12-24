package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public abstract class ColumnInfo {
    private final Map<String, ColumnDetails> indicesMap;
    private final boolean mutable;

    protected abstract ColumnInfo copy(boolean z);

    protected abstract void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2);

    protected ColumnInfo(int mapSize) {
        this(mapSize, true);
    }

    protected ColumnInfo(@Nullable ColumnInfo src, boolean mutable) {
        this(src == null ? 0 : src.indicesMap.size(), mutable);
        if (src != null) {
            this.indicesMap.putAll(src.indicesMap);
        }
    }

    private ColumnInfo(int mapSize, boolean mutable) {
        this.indicesMap = new HashMap(mapSize);
        this.mutable = mutable;
    }

    public final boolean isMutable() {
        return this.mutable;
    }

    public long getColumnIndex(String columnName) {
        ColumnDetails details = (ColumnDetails) this.indicesMap.get(columnName);
        return details == null ? -1 : details.columnIndex;
    }

    @Nullable
    public ColumnDetails getColumnDetails(String columnName) {
        return (ColumnDetails) this.indicesMap.get(columnName);
    }

    public void copyFrom(ColumnInfo src) {
        if (!this.mutable) {
            throw new UnsupportedOperationException("Attempt to modify an immutable ColumnInfo");
        } else if (src == null) {
            throw new NullPointerException("Attempt to copy null ColumnInfo");
        } else {
            this.indicesMap.clear();
            this.indicesMap.putAll(src.indicesMap);
            copy(src, this);
        }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("ColumnInfo[");
        buf.append(this.mutable).append(",");
        if (this.indicesMap != null) {
            boolean commaNeeded = false;
            for (Entry<String, ColumnDetails> entry : this.indicesMap.entrySet()) {
                if (commaNeeded) {
                    buf.append(",");
                }
                buf.append((String) entry.getKey()).append("->").append(entry.getValue());
                commaNeeded = true;
            }
        }
        return buf.append("]").toString();
    }

    protected final long addColumnDetails(String columnName, OsObjectSchemaInfo objectSchemaInfo) {
        Property property = objectSchemaInfo.getProperty(columnName);
        this.indicesMap.put(columnName, new ColumnDetails(property));
        return property.getColumnIndex();
    }

    protected final void addBacklinkDetails(OsSchemaInfo schemaInfo, String columnName, String sourceTableName, String sourceColumnName) {
        this.indicesMap.put(columnName, new ColumnDetails(schemaInfo.getObjectSchemaInfo(sourceTableName).getProperty(sourceColumnName).getColumnIndex(), RealmFieldType.LINKING_OBJECTS, sourceTableName, null));
    }

    public Map<String, ColumnDetails> getIndicesMap() {
        return this.indicesMap;
    }
}
