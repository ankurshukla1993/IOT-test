package io.realm.internal;

import io.realm.RealmChangeListener;
import io.realm.RealmFieldType;
import java.lang.ref.WeakReference;
import java.util.Date;
import javax.annotation.Nullable;

public class PendingRow implements Row {
    private static final String PROXY_NOT_SET_MESSAGE = "The 'frontEnd' has not been set.";
    private static final String QUERY_EXECUTED_MESSAGE = "The query has been executed. This 'PendingRow' is not valid anymore.";
    private static final String QUERY_NOT_RETURNED_MESSAGE = "The pending query has not been executed.";
    private WeakReference<FrontEnd> frontEndRef;
    private RealmChangeListener<PendingRow> listener = new C24391();
    private Collection pendingCollection;
    private boolean returnCheckedRow;
    private SharedRealm sharedRealm;

    public interface FrontEnd {
        void onQueryFinished(Row row);
    }

    class C24391 implements RealmChangeListener<PendingRow> {
        C24391() {
        }

        public void onChange(PendingRow pendingRow) {
            PendingRow.this.notifyFrontEnd();
        }
    }

    public PendingRow(SharedRealm sharedRealm, TableQuery query, @Nullable SortDescriptor sortDescriptor, boolean returnCheckedRow) {
        this.sharedRealm = sharedRealm;
        this.pendingCollection = new Collection(sharedRealm, query, sortDescriptor, null);
        this.pendingCollection.addListener((Object) this, this.listener);
        this.returnCheckedRow = returnCheckedRow;
        sharedRealm.addPendingRow(this);
    }

    public void setFrontEnd(FrontEnd frontEnd) {
        this.frontEndRef = new WeakReference(frontEnd);
    }

    public long getColumnCount() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public String getColumnName(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getColumnIndex(String columnName) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public RealmFieldType getColumnType(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public Table getTable() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getIndex() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getLong(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean getBoolean(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public float getFloat(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public double getDouble(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public Date getDate(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public String getString(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public byte[] getBinaryByteArray(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public long getLink(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean isNullLink(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public OsList getLinkList(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setLong(long columnIndex, long value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setBoolean(long columnIndex, boolean value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setFloat(long columnIndex, float value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setDouble(long columnIndex, double value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setDate(long columnIndex, Date date) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setString(long columnIndex, String value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setBinaryByteArray(long columnIndex, byte[] data) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setLink(long columnIndex, long value) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void nullifyLink(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean isNull(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public void setNull(long columnIndex) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean isAttached() {
        return false;
    }

    public void checkIfAttached() {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    public boolean hasColumn(String fieldName) {
        throw new IllegalStateException(QUERY_NOT_RETURNED_MESSAGE);
    }

    private void clearPendingCollection() {
        this.pendingCollection.removeListener((Object) this, this.listener);
        this.pendingCollection = null;
        this.listener = null;
        this.sharedRealm.removePendingRow(this);
    }

    private void notifyFrontEnd() {
        if (this.frontEndRef == null) {
            throw new IllegalStateException(PROXY_NOT_SET_MESSAGE);
        }
        FrontEnd frontEnd = (FrontEnd) this.frontEndRef.get();
        if (frontEnd == null) {
            clearPendingCollection();
        } else if (this.pendingCollection.isValid()) {
            UncheckedRow uncheckedRow = this.pendingCollection.firstUncheckedRow();
            clearPendingCollection();
            if (uncheckedRow != null) {
                Row row;
                if (this.returnCheckedRow) {
                    row = CheckedRow.getFromRow(uncheckedRow);
                } else {
                    Object row2 = uncheckedRow;
                }
                frontEnd.onQueryFinished(row);
                return;
            }
            frontEnd.onQueryFinished(InvalidRow.INSTANCE);
        } else {
            clearPendingCollection();
        }
    }

    public void executeQuery() {
        if (this.pendingCollection == null) {
            throw new IllegalStateException(QUERY_EXECUTED_MESSAGE);
        }
        notifyFrontEnd();
    }
}
