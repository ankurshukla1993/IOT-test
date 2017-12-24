package io.realm;

import io.realm.internal.Collection;
import io.realm.internal.InvalidRow;
import io.realm.internal.OsList;
import io.realm.internal.RealmObjectProxy;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import rx.Observable;

public class RealmList<E extends RealmModel> extends AbstractList<E> implements OrderedRealmCollection<E> {
    private static final String NULL_OBJECTS_NOT_ALLOWED_MESSAGE = "RealmList does not accept null values";
    private static final String ONLY_IN_MANAGED_MODE_MESSAGE = "This method is only available in managed mode";
    public static final String REMOVE_OUTSIDE_TRANSACTION_ERROR = "Objects can only be removed from inside a write transaction";
    @Nullable
    protected String className;
    @Nullable
    protected Class<E> clazz;
    private final Collection collection;
    final OsList osList;
    protected final BaseRealm realm;
    private List<E> unmanagedList;

    public RealmList() {
        this.collection = null;
        this.osList = null;
        this.realm = null;
        this.unmanagedList = new ArrayList();
    }

    public RealmList(E... objects) {
        if (objects == null) {
            throw new IllegalArgumentException("The objects argument cannot be null");
        }
        this.collection = null;
        this.osList = null;
        this.realm = null;
        this.unmanagedList = new ArrayList(objects.length);
        Collections.addAll(this.unmanagedList, objects);
    }

    RealmList(Class<E> clazz, OsList osList, BaseRealm realm) {
        this.collection = new Collection(realm.sharedRealm, osList, null);
        this.clazz = clazz;
        this.osList = osList;
        this.realm = realm;
    }

    RealmList(String className, OsList osList, BaseRealm realm) {
        this.collection = new Collection(realm.sharedRealm, osList, null);
        this.osList = osList;
        this.realm = realm;
        this.className = className;
    }

    public boolean isValid() {
        if (this.realm == null) {
            return true;
        }
        if (this.realm.isClosed()) {
            return false;
        }
        return isAttached();
    }

    public boolean isManaged() {
        return this.realm != null;
    }

    private boolean isAttached() {
        return this.osList != null && this.osList.isValid();
    }

    public void add(int location, E object) {
        checkValidObject(object);
        if (isManaged()) {
            checkValidRealm();
            if (location < 0 || location > size()) {
                throw new IndexOutOfBoundsException("Invalid index " + location + ", size is " + size());
            }
            this.osList.insertRow((long) location, ((RealmObjectProxy) copyToRealmIfNeeded(object)).realmGet$proxyState().getRow$realm().getIndex());
        } else {
            this.unmanagedList.add(location, object);
        }
        this.modCount++;
    }

    public boolean add(E object) {
        checkValidObject(object);
        if (isManaged()) {
            checkValidRealm();
            this.osList.addRow(((RealmObjectProxy) copyToRealmIfNeeded(object)).realmGet$proxyState().getRow$realm().getIndex());
        } else {
            this.unmanagedList.add(object);
        }
        this.modCount++;
        return true;
    }

    public E set(int location, E object) {
        checkValidObject(object);
        if (!isManaged()) {
            return (RealmModel) this.unmanagedList.set(location, object);
        }
        checkValidRealm();
        RealmObjectProxy proxy = (RealmObjectProxy) copyToRealmIfNeeded(object);
        E oldObject = get(location);
        this.osList.setRow((long) location, proxy.realmGet$proxyState().getRow$realm().getIndex());
        return oldObject;
    }

    private E copyToRealmIfNeeded(E object) {
        if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            if (proxy instanceof DynamicRealmObject) {
                String listClassName = this.className;
                if (proxy.realmGet$proxyState().getRealm$realm() == this.realm) {
                    if (listClassName.equals(((DynamicRealmObject) object).getType())) {
                        return object;
                    }
                    throw new IllegalArgumentException(String.format(Locale.US, "The object has a different type from list's. Type of the list is '%s', type of object is '%s'.", new Object[]{listClassName, ((DynamicRealmObject) object).getType()}));
                } else if (this.realm.threadId == proxy.realmGet$proxyState().getRealm$realm().threadId) {
                    throw new IllegalArgumentException("Cannot copy DynamicRealmObject between Realm instances.");
                } else {
                    throw new IllegalStateException("Cannot copy an object to a Realm instance created in another thread.");
                }
            } else if (proxy.realmGet$proxyState().getRow$realm() != null && proxy.realmGet$proxyState().getRealm$realm().getPath().equals(this.realm.getPath())) {
                if (this.realm == proxy.realmGet$proxyState().getRealm$realm()) {
                    return object;
                }
                throw new IllegalArgumentException("Cannot copy an object from another Realm instance.");
            }
        }
        Realm realm = this.realm;
        if (realm.getTable(object.getClass()).hasPrimaryKey()) {
            return realm.copyToRealmOrUpdate((RealmModel) object);
        }
        return realm.copyToRealm((RealmModel) object);
    }

    public void move(int oldPos, int newPos) {
        if (isManaged()) {
            checkValidRealm();
            this.osList.move((long) oldPos, (long) newPos);
            return;
        }
        checkIndex(oldPos);
        checkIndex(newPos);
        RealmModel object = (RealmModel) this.unmanagedList.remove(oldPos);
        if (newPos > oldPos) {
            this.unmanagedList.add(newPos - 1, object);
        } else {
            this.unmanagedList.add(newPos, object);
        }
    }

    public void clear() {
        if (isManaged()) {
            checkValidRealm();
            this.osList.removeAll();
        } else {
            this.unmanagedList.clear();
        }
        this.modCount++;
    }

    public E remove(int location) {
        E removedItem;
        if (isManaged()) {
            checkValidRealm();
            removedItem = get(location);
            this.osList.remove((long) location);
        } else {
            RealmModel removedItem2 = (RealmModel) this.unmanagedList.remove(location);
        }
        this.modCount++;
        return removedItem;
    }

    public boolean remove(Object object) {
        if (!isManaged() || this.realm.isInTransaction()) {
            return super.remove(object);
        }
        throw new IllegalStateException(REMOVE_OUTSIDE_TRANSACTION_ERROR);
    }

    public boolean removeAll(java.util.Collection<?> collection) {
        if (!isManaged() || this.realm.isInTransaction()) {
            return super.removeAll(collection);
        }
        throw new IllegalStateException(REMOVE_OUTSIDE_TRANSACTION_ERROR);
    }

    public boolean deleteFirstFromRealm() {
        if (!isManaged()) {
            throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
        } else if (size() <= 0) {
            return false;
        } else {
            deleteFromRealm(0);
            this.modCount++;
            return true;
        }
    }

    public boolean deleteLastFromRealm() {
        if (!isManaged()) {
            throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
        } else if (size() <= 0) {
            return false;
        } else {
            deleteFromRealm(size() - 1);
            this.modCount++;
            return true;
        }
    }

    public E get(int location) {
        if (!isManaged()) {
            return (RealmModel) this.unmanagedList.get(location);
        }
        checkValidRealm();
        return this.realm.get(this.clazz, this.className, this.osList.getUncheckedRow((long) location));
    }

    public E first() {
        return firstImpl(true, null);
    }

    @Nullable
    public E first(@Nullable E defaultValue) {
        return firstImpl(false, defaultValue);
    }

    @Nullable
    private E firstImpl(boolean shouldThrow, @Nullable E defaultValue) {
        if (isManaged()) {
            checkValidRealm();
            if (!this.osList.isEmpty()) {
                return get(0);
            }
        } else if (!(this.unmanagedList == null || this.unmanagedList.isEmpty())) {
            return (RealmModel) this.unmanagedList.get(0);
        }
        if (!shouldThrow) {
            return defaultValue;
        }
        throw new IndexOutOfBoundsException("The list is empty.");
    }

    public E last() {
        return lastImpl(true, null);
    }

    @Nullable
    public E last(@Nullable E defaultValue) {
        return lastImpl(false, defaultValue);
    }

    @Nullable
    private E lastImpl(boolean shouldThrow, @Nullable E defaultValue) {
        if (isManaged()) {
            checkValidRealm();
            if (!this.osList.isEmpty()) {
                return get(((int) this.osList.size()) - 1);
            }
        } else if (!(this.unmanagedList == null || this.unmanagedList.isEmpty())) {
            return (RealmModel) this.unmanagedList.get(this.unmanagedList.size() - 1);
        }
        if (!shouldThrow) {
            return defaultValue;
        }
        throw new IndexOutOfBoundsException("The list is empty.");
    }

    public RealmResults<E> sort(String fieldName) {
        return sort(fieldName, Sort.ASCENDING);
    }

    public RealmResults<E> sort(String fieldName, Sort sortOrder) {
        if (isManaged()) {
            return where().findAllSorted(fieldName, sortOrder);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public RealmResults<E> sort(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2) {
        return sort(new String[]{fieldName1, fieldName2}, new Sort[]{sortOrder1, sortOrder2});
    }

    public RealmResults<E> sort(String[] fieldNames, Sort[] sortOrders) {
        if (isManaged()) {
            return where().findAllSorted(fieldNames, sortOrders);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public void deleteFromRealm(int location) {
        if (isManaged()) {
            checkValidRealm();
            this.osList.delete((long) location);
            this.modCount++;
            return;
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public int size() {
        if (!isManaged()) {
            return this.unmanagedList.size();
        }
        checkValidRealm();
        long size = this.osList.size();
        return size < 2147483647L ? (int) size : Integer.MAX_VALUE;
    }

    public RealmQuery<E> where() {
        if (isManaged()) {
            checkValidRealm();
            return RealmQuery.createQueryFromList(this);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    @Nullable
    public Number min(String fieldName) {
        if (isManaged()) {
            return where().min(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    @Nullable
    public Number max(String fieldName) {
        if (isManaged()) {
            return where().max(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public Number sum(String fieldName) {
        if (isManaged()) {
            return where().sum(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public double average(String fieldName) {
        if (isManaged()) {
            return where().average(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    @Nullable
    public Date maxDate(String fieldName) {
        if (isManaged()) {
            return where().maximumDate(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    @Nullable
    public Date minDate(String fieldName) {
        if (isManaged()) {
            return where().minimumDate(fieldName);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public boolean deleteAllFromRealm() {
        if (isManaged()) {
            checkValidRealm();
            if (size() <= 0) {
                return false;
            }
            this.osList.deleteAll();
            this.modCount++;
            return true;
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public boolean isLoaded() {
        return true;
    }

    public boolean load() {
        return true;
    }

    public boolean contains(@Nullable Object object) {
        if (!isManaged()) {
            return this.unmanagedList.contains(object);
        }
        this.realm.checkIfValid();
        if ((object instanceof RealmObjectProxy) && ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm() == InvalidRow.INSTANCE) {
            return false;
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((RealmModel) it.next()).equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Nonnull
    public Iterator<E> iterator() {
        if (isManaged()) {
            return new RealmItr(this, null);
        }
        return super.iterator();
    }

    @Nonnull
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Nonnull
    public ListIterator<E> listIterator(int location) {
        if (isManaged()) {
            return new RealmListItr(this, location);
        }
        return super.listIterator(location);
    }

    private void checkValidObject(E object) {
        if (object == null) {
            throw new IllegalArgumentException(NULL_OBJECTS_NOT_ALLOWED_MESSAGE);
        }
    }

    private void checkIndex(int location) {
        int size = size();
        if (location < 0 || location >= size) {
            throw new IndexOutOfBoundsException("Invalid index " + location + ", size is " + size);
        }
    }

    private void checkValidRealm() {
        this.realm.checkIfValid();
    }

    public OrderedRealmCollectionSnapshot<E> createSnapshot() {
        if (isManaged()) {
            checkValidRealm();
            if (this.className != null) {
                return new OrderedRealmCollectionSnapshot(this.realm, new Collection(this.realm.sharedRealm, this.osList, null), this.className);
            }
            return new OrderedRealmCollectionSnapshot(this.realm, new Collection(this.realm.sharedRealm, this.osList, null), this.clazz);
        }
        throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isManaged()) {
            sb.append(this.className != null ? this.className : this.realm.getSchema().getSchemaForClass(this.clazz).getClassName());
        } else {
            sb.append(getClass().getSimpleName());
        }
        sb.append("@[");
        if (!isManaged() || isAttached()) {
            for (int i = 0; i < size(); i++) {
                if (isManaged()) {
                    sb.append(((RealmObjectProxy) get(i)).realmGet$proxyState().getRow$realm().getIndex());
                } else {
                    sb.append(System.identityHashCode(get(i)));
                }
                if (i < size() - 1) {
                    sb.append(',');
                }
            }
        } else {
            sb.append("invalid");
        }
        sb.append("]");
        return sb.toString();
    }

    public Observable<RealmList<E>> asObservable() {
        if (this.realm instanceof Realm) {
            return this.realm.configuration.getRxFactory().from((Realm) this.realm, this);
        }
        if (this.realm instanceof DynamicRealm) {
            return this.realm.configuration.getRxFactory().from(this.realm, this);
        }
        throw new UnsupportedOperationException(this.realm.getClass() + " does not support RxJava.");
    }

    private void checkForAddRemoveListener(@Nullable Object listener, boolean checkListener) {
        if (checkListener && listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification("Listeners cannot be used on current thread.");
    }

    public void addChangeListener(OrderedRealmCollectionChangeListener<RealmList<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.addListener(this, listener);
    }

    public void removeChangeListener(OrderedRealmCollectionChangeListener<RealmList<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.removeListener(this, listener);
    }

    public void addChangeListener(RealmChangeListener<RealmList<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.addListener(this, listener);
    }

    public void removeChangeListener(RealmChangeListener<RealmList<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.removeListener(this, listener);
    }

    public void removeAllChangeListeners() {
        checkForAddRemoveListener(null, false);
        this.collection.removeAllListeners();
    }
}
