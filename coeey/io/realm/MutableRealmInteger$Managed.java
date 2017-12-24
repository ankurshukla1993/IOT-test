package io.realm;

import io.realm.internal.Row;
import io.realm.internal.Table;
import javax.annotation.Nullable;

abstract class MutableRealmInteger$Managed<T extends RealmModel> extends MutableRealmInteger {
    protected abstract long getColumnIndex();

    protected abstract ProxyState<T> getProxyState();

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return super.compareTo((MutableRealmInteger) obj);
    }

    MutableRealmInteger$Managed() {
    }

    public final boolean isManaged() {
        return true;
    }

    public final boolean isValid() {
        return !getRealm().isClosed() && getRow().isAttached();
    }

    public final Long get() {
        Row row = getRow();
        row.checkIfAttached();
        long columnIndex = getColumnIndex();
        return row.isNull(columnIndex) ? null : Long.valueOf(row.getLong(columnIndex));
    }

    public final void set(@Nullable Long value) {
        ProxyState proxyState = getProxyState();
        proxyState.getRealm$realm().checkIfValidAndInTransaction();
        if (!proxyState.isUnderConstruction()) {
            setValue(value, false);
        } else if (proxyState.getAcceptDefaultValue$realm()) {
            setValue(value, true);
        }
    }

    public final void increment(long inc) {
        getRealm().checkIfValidAndInTransaction();
        Row row = getRow();
        row.getTable().incrementLong(getColumnIndex(), row.getIndex(), inc);
    }

    public final void decrement(long dec) {
        increment(-dec);
    }

    private BaseRealm getRealm() {
        return getProxyState().getRealm$realm();
    }

    private Row getRow() {
        return getProxyState().getRow$realm();
    }

    private void setValue(@Nullable Long value, boolean isDefault) {
        Row row = getRow();
        Table table = row.getTable();
        long rowIndex = row.getIndex();
        long columnIndex = getColumnIndex();
        if (value == null) {
            table.setNull(columnIndex, rowIndex, isDefault);
        } else {
            table.setLong(columnIndex, rowIndex, value.longValue(), isDefault);
        }
    }
}
