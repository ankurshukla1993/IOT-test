package io.realm;

import io.realm.internal.Collection;
import io.realm.internal.Collection.Aggregate;
import io.realm.internal.InvalidRow;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.SortDescriptor;
import io.realm.internal.Table;
import io.realm.internal.UncheckedRow;
import java.util.AbstractList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Locale;
import javax.annotation.Nullable;

abstract class OrderedRealmCollectionImpl<E extends RealmModel> extends AbstractList<E> implements OrderedRealmCollection<E> {
    private static final String NOT_SUPPORTED_MESSAGE = "This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.";
    @Nullable
    final String className;
    @Nullable
    final Class<E> classSpec;
    final Collection collection;
    final BaseRealm realm;

    OrderedRealmCollectionImpl(BaseRealm realm, Collection collection, Class<E> clazz) {
        this(realm, collection, clazz, null);
    }

    OrderedRealmCollectionImpl(BaseRealm realm, Collection collection, String className) {
        this(realm, collection, null, className);
    }

    private OrderedRealmCollectionImpl(BaseRealm realm, Collection collection, @Nullable Class<E> clazz, @Nullable String className) {
        this.realm = realm;
        this.collection = collection;
        this.classSpec = clazz;
        this.className = className;
    }

    Table getTable() {
        return this.collection.getTable();
    }

    Collection getCollection() {
        return this.collection;
    }

    public boolean isValid() {
        return this.collection.isValid();
    }

    public boolean isManaged() {
        return true;
    }

    public boolean contains(@Nullable Object object) {
        if (!isLoaded()) {
            return false;
        }
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

    public E get(int location) {
        this.realm.checkIfValid();
        return this.realm.get(this.classSpec, this.className, this.collection.getUncheckedRow(location));
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
        UncheckedRow row = this.collection.firstUncheckedRow();
        if (row != null) {
            return this.realm.get(this.classSpec, this.className, row);
        }
        if (!shouldThrow) {
            return defaultValue;
        }
        throw new IndexOutOfBoundsException("No results were found.");
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
        UncheckedRow row = this.collection.lastUncheckedRow();
        if (row != null) {
            return this.realm.get(this.classSpec, this.className, row);
        }
        if (!shouldThrow) {
            return defaultValue;
        }
        throw new IndexOutOfBoundsException("No results were found.");
    }

    public void deleteFromRealm(int location) {
        this.realm.checkIfValidAndInTransaction();
        this.collection.delete((long) location);
    }

    public boolean deleteAllFromRealm() {
        this.realm.checkIfValid();
        if (size() <= 0) {
            return false;
        }
        this.collection.clear();
        return true;
    }

    public Iterator<E> iterator() {
        return new RealmCollectionIterator(this);
    }

    public ListIterator<E> listIterator() {
        return new RealmCollectionListIterator(this, 0);
    }

    public ListIterator<E> listIterator(int location) {
        return new RealmCollectionListIterator(this, location);
    }

    private long getColumnIndexForSort(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Non-empty field name required.");
        } else if (fieldName.contains(".")) {
            throw new IllegalArgumentException("Aggregates on child object fields are not supported: " + fieldName);
        } else {
            long columnIndex = this.collection.getTable().getColumnIndex(fieldName);
            if (columnIndex >= 0) {
                return columnIndex;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Field '%s' does not exist.", new Object[]{fieldName}));
        }
    }

    public RealmResults<E> sort(String fieldName) {
        return createLoadedResults(this.collection.sort(SortDescriptor.getInstanceForSort(getSchemaConnector(), this.collection.getTable(), fieldName, Sort.ASCENDING)));
    }

    public RealmResults<E> sort(String fieldName, Sort sortOrder) {
        return createLoadedResults(this.collection.sort(SortDescriptor.getInstanceForSort(getSchemaConnector(), this.collection.getTable(), fieldName, sortOrder)));
    }

    public RealmResults<E> sort(String[] fieldNames, Sort[] sortOrders) {
        return createLoadedResults(this.collection.sort(SortDescriptor.getInstanceForSort(getSchemaConnector(), this.collection.getTable(), fieldNames, sortOrders)));
    }

    public RealmResults<E> sort(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2) {
        return sort(new String[]{fieldName1, fieldName2}, new Sort[]{sortOrder1, sortOrder2});
    }

    public int size() {
        if (!isLoaded()) {
            return 0;
        }
        long size = this.collection.size();
        return size > 2147483647L ? Integer.MAX_VALUE : (int) size;
    }

    public Number min(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateNumber(Aggregate.MINIMUM, getColumnIndexForSort(fieldName));
    }

    public Date minDate(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateDate(Aggregate.MINIMUM, getColumnIndexForSort(fieldName));
    }

    public Number max(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateNumber(Aggregate.MAXIMUM, getColumnIndexForSort(fieldName));
    }

    @Nullable
    public Date maxDate(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateDate(Aggregate.MAXIMUM, getColumnIndexForSort(fieldName));
    }

    public Number sum(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateNumber(Aggregate.SUM, getColumnIndexForSort(fieldName));
    }

    public double average(String fieldName) {
        this.realm.checkIfValid();
        return this.collection.aggregateNumber(Aggregate.AVERAGE, getColumnIndexForSort(fieldName)).doubleValue();
    }

    @Deprecated
    public E remove(int index) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean remove(Object object) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean removeAll(java.util.Collection<?> collection) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public E set(int location, E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean retainAll(java.util.Collection<?> collection) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public boolean deleteLastFromRealm() {
        this.realm.checkIfValidAndInTransaction();
        return this.collection.deleteLast();
    }

    public boolean deleteFirstFromRealm() {
        this.realm.checkIfValidAndInTransaction();
        return this.collection.deleteFirst();
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean add(E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public void add(int index, E e) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean addAll(int location, java.util.Collection<? extends E> collection) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    @Deprecated
    public boolean addAll(java.util.Collection<? extends E> collection) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public OrderedRealmCollectionSnapshot<E> createSnapshot() {
        if (this.className != null) {
            return new OrderedRealmCollectionSnapshot(this.realm, this.collection, this.className);
        }
        return new OrderedRealmCollectionSnapshot(this.realm, this.collection, this.classSpec);
    }

    RealmResults<E> createLoadedResults(Collection newCollection) {
        RealmResults<E> results;
        if (this.className != null) {
            results = new RealmResults(this.realm, newCollection, this.className);
        } else {
            results = new RealmResults(this.realm, newCollection, this.classSpec);
        }
        results.load();
        return results;
    }

    private SchemaConnector getSchemaConnector() {
        return new SchemaConnector(this.realm.getSchema());
    }
}
