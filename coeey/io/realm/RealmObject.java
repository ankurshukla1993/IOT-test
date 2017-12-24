package io.realm;

import io.realm.ProxyState.RealmChangeListenerWrapper;
import io.realm.annotations.RealmClass;
import io.realm.internal.InvalidRow;
import io.realm.internal.ManagableObject;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import rx.Observable;

@RealmClass
public abstract class RealmObject implements RealmModel, ManagableObject {
    public final void deleteFromRealm() {
        deleteFromRealm(this);
    }

    public static <E extends RealmModel> void deleteFromRealm(E object) {
        if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            if (proxy.realmGet$proxyState().getRow$realm() == null) {
                throw new IllegalStateException("Object malformed: missing object in Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
            } else if (proxy.realmGet$proxyState().getRealm$realm() == null) {
                throw new IllegalStateException("Object malformed: missing Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
            } else {
                proxy.realmGet$proxyState().getRealm$realm().checkIfValid();
                Row row = proxy.realmGet$proxyState().getRow$realm();
                row.getTable().moveLastOver(row.getIndex());
                proxy.realmGet$proxyState().setRow$realm(InvalidRow.INSTANCE);
                return;
            }
        }
        throw new IllegalArgumentException("Object not managed by Realm, so it cannot be removed.");
    }

    public final boolean isValid() {
        return isValid(this);
    }

    public static <E extends RealmModel> boolean isValid(E object) {
        if (!(object instanceof RealmObjectProxy)) {
            return true;
        }
        Row row = ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm();
        if (row == null || !row.isAttached()) {
            return false;
        }
        return true;
    }

    public final boolean isLoaded() {
        return isLoaded(this);
    }

    public static <E extends RealmModel> boolean isLoaded(E object) {
        if (!(object instanceof RealmObjectProxy)) {
            return true;
        }
        RealmObjectProxy proxy = (RealmObjectProxy) object;
        proxy.realmGet$proxyState().getRealm$realm().checkIfValid();
        return proxy.realmGet$proxyState().isLoaded();
    }

    public boolean isManaged() {
        return isManaged(this);
    }

    public static <E extends RealmModel> boolean isManaged(E object) {
        return object instanceof RealmObjectProxy;
    }

    public final boolean load() {
        return load(this);
    }

    public static <E extends RealmModel> boolean load(E object) {
        if (isLoaded(object)) {
            return true;
        }
        if (!(object instanceof RealmObjectProxy)) {
            return false;
        }
        ((RealmObjectProxy) object).realmGet$proxyState().load();
        return true;
    }

    public final <E extends RealmModel> void addChangeListener(RealmObjectChangeListener<E> listener) {
        addChangeListener((RealmModel) this, (RealmObjectChangeListener) listener);
    }

    public final <E extends RealmModel> void addChangeListener(RealmChangeListener<E> listener) {
        addChangeListener((RealmModel) this, (RealmChangeListener) listener);
    }

    public static <E extends RealmModel> void addChangeListener(E object, RealmObjectChangeListener<E> listener) {
        if (object == null) {
            throw new IllegalArgumentException("Object should not be null");
        } else if (listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        } else if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            BaseRealm realm = proxy.realmGet$proxyState().getRealm$realm();
            realm.checkIfValid();
            realm.sharedRealm.capabilities.checkCanDeliverNotification("Listeners cannot be used on current thread.");
            proxy.realmGet$proxyState().addChangeListener(listener);
        } else {
            throw new IllegalArgumentException("Cannot add listener from this unmanaged RealmObject (created outside of Realm)");
        }
    }

    public static <E extends RealmModel> void addChangeListener(E object, RealmChangeListener<E> listener) {
        addChangeListener((RealmModel) object, new RealmChangeListenerWrapper(listener));
    }

    public final void removeChangeListener(RealmObjectChangeListener listener) {
        removeChangeListener((RealmModel) this, listener);
    }

    public final void removeChangeListener(RealmChangeListener listener) {
        removeChangeListener((RealmModel) this, listener);
    }

    public static <E extends RealmModel> void removeChangeListener(E object, RealmObjectChangeListener listener) {
        if (object == null) {
            throw new IllegalArgumentException("Object should not be null");
        } else if (listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        } else if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            BaseRealm realm = proxy.realmGet$proxyState().getRealm$realm();
            realm.checkIfValid();
            realm.sharedRealm.capabilities.checkCanDeliverNotification("Listeners cannot be used on current thread.");
            proxy.realmGet$proxyState().removeChangeListener(listener);
        } else {
            throw new IllegalArgumentException("Cannot remove listener from this unmanaged RealmObject (created outside of Realm)");
        }
    }

    public static <E extends RealmModel> void removeChangeListener(E object, RealmChangeListener<E> listener) {
        removeChangeListener((RealmModel) object, new RealmChangeListenerWrapper(listener));
    }

    @Deprecated
    public final void removeChangeListeners() {
        removeChangeListeners(this);
    }

    public final void removeAllChangeListeners() {
        removeAllChangeListeners(this);
    }

    @Deprecated
    public static <E extends RealmModel> void removeChangeListeners(E object) {
        removeAllChangeListeners(object);
    }

    public static <E extends RealmModel> void removeAllChangeListeners(E object) {
        if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            BaseRealm realm = proxy.realmGet$proxyState().getRealm$realm();
            realm.checkIfValid();
            realm.sharedRealm.capabilities.checkCanDeliverNotification("Listeners cannot be used on current thread.");
            proxy.realmGet$proxyState().removeAllChangeListeners();
            return;
        }
        throw new IllegalArgumentException("Cannot remove listeners from this unmanaged RealmObject (created outside of Realm)");
    }

    public final <E extends RealmObject> Observable<E> asObservable() {
        return asObservable(this);
    }

    public static <E extends RealmModel> Observable<E> asObservable(E object) {
        if (object instanceof RealmObjectProxy) {
            BaseRealm realm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (realm instanceof Realm) {
                return realm.configuration.getRxFactory().from((Realm) realm, object);
            }
            if (realm instanceof DynamicRealm) {
                return realm.configuration.getRxFactory().from((DynamicRealm) realm, (DynamicRealmObject) object);
            }
            throw new UnsupportedOperationException(realm.getClass() + " does not support RxJava. See https://realm.io/docs/java/latest/#rxjava for more details.");
        }
        throw new IllegalArgumentException("Cannot create Observables from unmanaged RealmObjects");
    }
}
