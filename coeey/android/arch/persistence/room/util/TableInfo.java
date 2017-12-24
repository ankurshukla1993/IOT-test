package android.arch.persistence.room.util;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TableInfo {
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final String name;

    public static class Column {
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String name, String type, boolean notNull, int primaryKeyPosition) {
            this.name = name;
            this.type = type;
            this.notNull = notNull;
            this.primaryKeyPosition = primaryKeyPosition;
        }

        public boolean equals(Object o) {
            boolean z = true;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Column column = (Column) o;
            if (VERSION.SDK_INT >= 20) {
                if (this.primaryKeyPosition != column.primaryKeyPosition) {
                    return false;
                }
            } else if (isPrimaryKey() != column.isPrimaryKey()) {
                return false;
            }
            if (!this.name.equals(column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.type != null) {
                z = this.type.equalsIgnoreCase(column.type);
            } else if (column.type != null) {
                z = false;
            }
            return z;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + (this.type != null ? this.type.hashCode() : 0)) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            return "Column{name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + '}';
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class ForeignKey {
        @NonNull
        public final List<String> columnNames;
        @NonNull
        public final String onDelete;
        @NonNull
        public final String onUpdate;
        @NonNull
        public final List<String> referenceColumnNames;
        @NonNull
        public final String referenceTable;

        public ForeignKey(@NonNull String referenceTable, @NonNull String onDelete, @NonNull String onUpdate, @NonNull List<String> columnNames, @NonNull List<String> referenceColumnNames) {
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = Collections.unmodifiableList(columnNames);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ForeignKey that = (ForeignKey) o;
            if (this.referenceTable.equals(that.referenceTable) && this.onDelete.equals(that.onDelete) && this.onUpdate.equals(that.onUpdate) && this.columnNames.equals(that.columnNames)) {
                return this.referenceColumnNames.equals(that.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + '\'' + ", onDelete='" + this.onDelete + '\'' + ", onUpdate='" + this.onUpdate + '\'' + ", columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id, int sequence, String from, String to) {
            this.mId = id;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        public int compareTo(ForeignKeyWithSequence o) {
            int idCmp = this.mId - o.mId;
            if (idCmp == 0) {
                return this.mSequence - o.mSequence;
            }
            return idCmp;
        }
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys) {
        this.name = name;
        this.columns = Collections.unmodifiableMap(columns);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys);
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName));
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        Set<ForeignKey> foreignKeys = new HashSet();
        Cursor cursor = database.query("PRAGMA foreign_key_list(`" + tableName + "`)");
        try {
            int idColumnIndex = cursor.getColumnIndex(ShareConstants.WEB_DIALOG_PARAM_ID);
            int seqColumnIndex = cursor.getColumnIndex("seq");
            int tableColumnIndex = cursor.getColumnIndex("table");
            int onDeleteColumnIndex = cursor.getColumnIndex("on_delete");
            int onUpdateColumnIndex = cursor.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> ordered = readForeignKeyFieldMappings(cursor);
            int count = cursor.getCount();
            for (int position = 0; position < count; position++) {
                cursor.moveToPosition(position);
                if (cursor.getInt(seqColumnIndex) == 0) {
                    int id = cursor.getInt(idColumnIndex);
                    List<String> myColumns = new ArrayList();
                    List<String> refColumns = new ArrayList();
                    for (ForeignKeyWithSequence key : ordered) {
                        if (key.mId == id) {
                            myColumns.add(key.mFrom);
                            refColumns.add(key.mTo);
                        }
                    }
                    foreignKeys.add(new ForeignKey(cursor.getString(tableColumnIndex), cursor.getString(onDeleteColumnIndex), cursor.getString(onUpdateColumnIndex), myColumns, refColumns));
                }
            }
            return foreignKeys;
        } finally {
            cursor.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex(ShareConstants.WEB_DIALOG_PARAM_ID);
        int seqColumnIndex = cursor.getColumnIndex("seq");
        int fromColumnIndex = cursor.getColumnIndex("from");
        int toColumnIndex = cursor.getColumnIndex(ShareConstants.WEB_DIALOG_PARAM_TO);
        int count = cursor.getCount();
        List<ForeignKeyWithSequence> result = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            result.add(new ForeignKeyWithSequence(cursor.getInt(idColumnIndex), cursor.getInt(seqColumnIndex), cursor.getString(fromColumnIndex), cursor.getString(toColumnIndex)));
        }
        Collections.sort(result);
        return result;
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA table_info(`" + tableName + "`)");
        Map<String, Column> columns = new HashMap();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int notNullIndex = cursor.getColumnIndex("notnull");
                int pkIndex = cursor.getColumnIndex("pk");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    columns.put(name, new Column(name, cursor.getString(typeIndex), cursor.getInt(notNullIndex) != 0, cursor.getInt(pkIndex)));
                }
            }
            cursor.close();
            return columns;
        } catch (Throwable th) {
            cursor.close();
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        if (this.name.equals(tableInfo.name) && this.columns.equals(tableInfo.columns)) {
            return this.foreignKeys.equals(tableInfo.foreignKeys);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.foreignKeys.hashCode();
    }

    public String toString() {
        return "TableInfo{name='" + this.name + '\'' + ", columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + '}';
    }
}
