package io.realm;

import android.annotation.SuppressLint;
import io.realm.internal.CheckedRow;
import io.realm.internal.Row;
import io.realm.internal.SortDescriptor;
import io.realm.internal.Table;
import io.realm.internal.UncheckedRow;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import javax.annotation.Nullable;
import rx.Observable;

public class RealmResults<E extends RealmModel> extends OrderedRealmCollectionImpl<E> {
    @Deprecated
    public /* bridge */ /* synthetic */ void add(int i, RealmModel realmModel) {
        super.add(i, realmModel);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ boolean add(RealmModel realmModel) {
        return super.add(realmModel);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ boolean addAll(int i, Collection collection) {
        return super.addAll(i, collection);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    public /* bridge */ /* synthetic */ double average(String str) {
        return super.average(str);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ boolean contains(@Nullable Object obj) {
        return super.contains(obj);
    }

    public /* bridge */ /* synthetic */ OrderedRealmCollectionSnapshot createSnapshot() {
        return super.createSnapshot();
    }

    public /* bridge */ /* synthetic */ boolean deleteAllFromRealm() {
        return super.deleteAllFromRealm();
    }

    public /* bridge */ /* synthetic */ boolean deleteFirstFromRealm() {
        return super.deleteFirstFromRealm();
    }

    public /* bridge */ /* synthetic */ void deleteFromRealm(int i) {
        super.deleteFromRealm(i);
    }

    public /* bridge */ /* synthetic */ boolean deleteLastFromRealm() {
        return super.deleteLastFromRealm();
    }

    public /* bridge */ /* synthetic */ RealmModel first() {
        return super.first();
    }

    @Nullable
    public /* bridge */ /* synthetic */ RealmModel first(@Nullable RealmModel realmModel) {
        return super.first(realmModel);
    }

    public /* bridge */ /* synthetic */ RealmModel get(int i) {
        return super.get(i);
    }

    public /* bridge */ /* synthetic */ boolean isManaged() {
        return super.isManaged();
    }

    public /* bridge */ /* synthetic */ boolean isValid() {
        return super.isValid();
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ RealmModel last() {
        return super.last();
    }

    @Nullable
    public /* bridge */ /* synthetic */ RealmModel last(@Nullable RealmModel realmModel) {
        return super.last(realmModel);
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    public /* bridge */ /* synthetic */ Number max(String str) {
        return super.max(str);
    }

    @Nullable
    public /* bridge */ /* synthetic */ Date maxDate(String str) {
        return super.maxDate(str);
    }

    public /* bridge */ /* synthetic */ Number min(String str) {
        return super.min(str);
    }

    public /* bridge */ /* synthetic */ Date minDate(String str) {
        return super.minDate(str);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Deprecated
    public /* bridge */ /* synthetic */ RealmModel set(int i, RealmModel realmModel) {
        return super.set(i, realmModel);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ RealmResults sort(String str) {
        return super.sort(str);
    }

    public /* bridge */ /* synthetic */ Number sum(String str) {
        return super.sum(str);
    }

    @SuppressLint({"unused"})
    static <T extends RealmModel> RealmResults<T> createBacklinkResults(BaseRealm realm, Row row, Class<T> srcTableType, String srcFieldName) {
        return new RealmResults(realm, io.realm.internal.Collection.createBacklinksCollection(realm.sharedRealm, (UncheckedRow) row, realm.getSchema().getTable((Class) srcTableType), srcFieldName), (Class) srcTableType);
    }

    static RealmResults<DynamicRealmObject> createDynamicBacklinkResults(DynamicRealm realm, CheckedRow row, Table srcTable, String srcFieldName) {
        return new RealmResults((BaseRealm) realm, io.realm.internal.Collection.createBacklinksCollection(realm.sharedRealm, row, srcTable, srcFieldName), Table.getClassNameForTable(srcTable.getName()));
    }

    RealmResults(BaseRealm realm, io.realm.internal.Collection collection, Class<E> clazz) {
        super(realm, collection, (Class) clazz);
    }

    RealmResults(BaseRealm realm, io.realm.internal.Collection collection, String className) {
        super(realm, collection, className);
    }

    public RealmQuery<E> where() {
        this.realm.checkIfValid();
        return RealmQuery.createQueryFromResult(this);
    }

    public RealmResults<E> sort(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2) {
        return sort(new String[]{fieldName1, fieldName2}, new Sort[]{sortOrder1, sortOrder2});
    }

    public boolean isLoaded() {
        this.realm.checkIfValid();
        return this.collection.isLoaded();
    }

    public boolean load() {
        this.realm.checkIfValid();
        this.collection.load();
        return true;
    }

    public void addChangeListener(RealmChangeListener<RealmResults<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.addListener(this, listener);
    }

    public void addChangeListener(OrderedRealmCollectionChangeListener<RealmResults<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.addListener(this, listener);
    }

    private void checkForAddRemoveListener(@Nullable Object listener, boolean checkListener) {
        if (checkListener && listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        this.realm.checkIfValid();
        this.realm.sharedRealm.capabilities.checkCanDeliverNotification("Listeners cannot be used on current thread.");
    }

    public void removeAllChangeListeners() {
        checkForAddRemoveListener(null, false);
        this.collection.removeAllListeners();
    }

    @Deprecated
    public void removeChangeListeners() {
        removeAllChangeListeners();
    }

    public void removeChangeListener(RealmChangeListener<RealmResults<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.removeListener(this, listener);
    }

    public void removeChangeListener(OrderedRealmCollectionChangeListener<RealmResults<E>> listener) {
        checkForAddRemoveListener(listener, true);
        this.collection.removeListener(this, listener);
    }

    public Observable<RealmResults<E>> asObservable() {
        if (this.realm instanceof Realm) {
            return this.realm.configuration.getRxFactory().from((Realm) this.realm, this);
        }
        if (this.realm instanceof DynamicRealm) {
            return this.realm.configuration.getRxFactory().from(this.realm, this);
        }
        throw new UnsupportedOperationException(this.realm.getClass() + " does not support RxJava.");
    }

    @Deprecated
    public RealmResults<E> distinct(String fieldName) {
        return createLoadedResults(this.collection.distinct(SortDescriptor.getInstanceForDistinct(new SchemaConnector(this.realm.getSchema()), this.collection.getTable(), fieldName)));
    }

    @Deprecated
    public RealmResults<E> distinctAsync(String fieldName) {
        return where().distinctAsync(fieldName);
    }

    @Deprecated
    public RealmResults<E> distinct(String firstFieldName, String... remainingFieldNames) {
        return where().distinct(firstFieldName, remainingFieldNames);
    }
}
