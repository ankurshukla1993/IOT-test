package io.realm;

import io.realm.internal.ObserverPairList;
import io.realm.internal.ObserverPairList.Callback;
import io.realm.internal.OsObject;
import io.realm.internal.OsObject.ObjectObserverPair;
import io.realm.internal.PendingRow;
import io.realm.internal.PendingRow.FrontEnd;
import io.realm.internal.Row;
import io.realm.internal.UncheckedRow;
import java.util.List;
import javax.annotation.Nullable;

public final class ProxyState<E extends RealmModel> implements FrontEnd {
    private static QueryCallback queryCallback = new QueryCallback();
    private boolean acceptDefaultValue;
    private List<String> excludeFields;
    private E model;
    private ObserverPairList<ObjectObserverPair> observerPairs = new ObserverPairList();
    private OsObject osObject;
    private BaseRealm realm;
    private Row row;
    private boolean underConstruction = true;

    private static class QueryCallback implements Callback<ObjectObserverPair> {
        private QueryCallback() {
        }

        public void onCalled(ObjectObserverPair pair, Object observer) {
            pair.onChange((RealmModel) observer, null);
        }
    }

    static class RealmChangeListenerWrapper<T extends RealmModel> implements RealmObjectChangeListener<T> {
        private final RealmChangeListener<T> listener;

        RealmChangeListenerWrapper(RealmChangeListener<T> listener) {
            if (listener == null) {
                throw new IllegalArgumentException("Listener should not be null");
            }
            this.listener = listener;
        }

        public void onChange(T object, @Nullable ObjectChangeSet changes) {
            this.listener.onChange(object);
        }

        public boolean equals(Object obj) {
            return (obj instanceof RealmChangeListenerWrapper) && this.listener == ((RealmChangeListenerWrapper) obj).listener;
        }

        public int hashCode() {
            return this.listener.hashCode();
        }
    }

    public ProxyState(E model) {
        this.model = model;
    }

    public BaseRealm getRealm$realm() {
        return this.realm;
    }

    public void setRealm$realm(BaseRealm realm) {
        this.realm = realm;
    }

    public Row getRow$realm() {
        return this.row;
    }

    public void setRow$realm(Row row) {
        this.row = row;
    }

    public boolean getAcceptDefaultValue$realm() {
        return this.acceptDefaultValue;
    }

    public void setAcceptDefaultValue$realm(boolean acceptDefaultValue) {
        this.acceptDefaultValue = acceptDefaultValue;
    }

    public List<String> getExcludeFields$realm() {
        return this.excludeFields;
    }

    public void setExcludeFields$realm(List<String> excludeFields) {
        this.excludeFields = excludeFields;
    }

    private void notifyQueryFinished() {
        this.observerPairs.foreach(queryCallback);
    }

    public void addChangeListener(RealmObjectChangeListener<E> listener) {
        if (this.row instanceof PendingRow) {
            this.observerPairs.add(new ObjectObserverPair(this.model, listener));
        } else if (this.row instanceof UncheckedRow) {
            registerToObjectNotifier();
            if (this.osObject != null) {
                this.osObject.addListener(this.model, listener);
            }
        }
    }

    public void removeChangeListener(RealmObjectChangeListener<E> listener) {
        if (this.osObject != null) {
            this.osObject.removeListener(this.model, listener);
        } else {
            this.observerPairs.remove(this.model, listener);
        }
    }

    public void removeAllChangeListeners() {
        if (this.osObject != null) {
            this.osObject.removeListener(this.model);
        } else {
            this.observerPairs.clear();
        }
    }

    public boolean isUnderConstruction() {
        return this.underConstruction;
    }

    public void setConstructionFinished() {
        this.underConstruction = false;
        this.excludeFields = null;
    }

    private void registerToObjectNotifier() {
        if (this.realm.sharedRealm != null && !this.realm.sharedRealm.isClosed() && this.row.isAttached() && this.osObject == null) {
            this.osObject = new OsObject(this.realm.sharedRealm, (UncheckedRow) this.row);
            this.osObject.setObserverPairs(this.observerPairs);
            this.observerPairs = null;
        }
    }

    public boolean isLoaded() {
        return !(this.row instanceof PendingRow);
    }

    public void load() {
        if (this.row instanceof PendingRow) {
            ((PendingRow) this.row).executeQuery();
        }
    }

    public void onQueryFinished(Row row) {
        this.row = row;
        notifyQueryFinished();
        if (row.isAttached()) {
            registerToObjectNotifier();
        }
    }
}
