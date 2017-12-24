package io.realm.internal;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmChangeListener;
import io.realm.internal.ObserverPairList.ObserverPair;
import java.util.Date;
import javax.annotation.Nullable;

@Keep
public class Collection implements NativeObject {
    public static final byte AGGREGATE_FUNCTION_AVERAGE = (byte) 3;
    public static final byte AGGREGATE_FUNCTION_MAXIMUM = (byte) 2;
    public static final byte AGGREGATE_FUNCTION_MINIMUM = (byte) 1;
    public static final byte AGGREGATE_FUNCTION_SUM = (byte) 4;
    private static final String CLOSED_REALM_MESSAGE = "This Realm instance has already been closed, making it unusable.";
    public static final byte MODE_EMPTY = (byte) 0;
    public static final byte MODE_LINKVIEW = (byte) 3;
    public static final byte MODE_QUERY = (byte) 2;
    public static final byte MODE_TABLE = (byte) 1;
    public static final byte MODE_TABLEVIEW = (byte) 4;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final NativeContext context;
    private boolean isSnapshot;
    private boolean loaded;
    private final long nativePtr;
    private final ObserverPairList<CollectionObserverPair> observerPairs;
    private final SharedRealm sharedRealm;
    private final Table table;

    public enum Aggregate {
        MINIMUM((byte) 1),
        MAXIMUM((byte) 2),
        AVERAGE((byte) 3),
        SUM((byte) 4);
        
        private final byte value;

        private Aggregate(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return this.value;
        }
    }

    private static class Callback implements io.realm.internal.ObserverPairList.Callback<CollectionObserverPair> {
        private final OrderedCollectionChangeSet changeSet;

        Callback(OrderedCollectionChangeSet changeSet) {
            this.changeSet = changeSet;
        }

        public void onCalled(CollectionObserverPair pair, Object observer) {
            pair.onChange(observer, this.changeSet);
        }
    }

    private static class CollectionObserverPair<T> extends ObserverPair<T, Object> {
        public CollectionObserverPair(T observer, Object listener) {
            super(observer, listener);
        }

        public void onChange(T observer, OrderedCollectionChangeSet changes) {
            if (this.listener instanceof OrderedRealmCollectionChangeListener) {
                ((OrderedRealmCollectionChangeListener) this.listener).onChange(observer, changes);
            } else if (this.listener instanceof RealmChangeListener) {
                ((RealmChangeListener) this.listener).onChange(observer);
            } else {
                throw new RuntimeException("Unsupported listener type: " + this.listener);
            }
        }
    }

    public enum Mode {
        EMPTY,
        TABLE,
        QUERY,
        LINKVIEW,
        TABLEVIEW;

        static Mode getByValue(byte value) {
            switch (value) {
                case (byte) 0:
                    return EMPTY;
                case (byte) 1:
                    return TABLE;
                case (byte) 2:
                    return QUERY;
                case (byte) 3:
                    return LINKVIEW;
                case (byte) 4:
                    return TABLEVIEW;
                default:
                    throw new IllegalArgumentException("Invalid value: " + value);
            }
        }
    }

    private static class RealmChangeListenerWrapper<T> implements OrderedRealmCollectionChangeListener<T> {
        private final RealmChangeListener<T> listener;

        RealmChangeListenerWrapper(RealmChangeListener<T> listener) {
            this.listener = listener;
        }

        public void onChange(T collection, OrderedCollectionChangeSet changes) {
            this.listener.onChange(collection);
        }

        public boolean equals(Object obj) {
            return (obj instanceof RealmChangeListenerWrapper) && this.listener == ((RealmChangeListenerWrapper) obj).listener;
        }

        public int hashCode() {
            return this.listener.hashCode();
        }
    }

    private static native Object nativeAggregate(long j, long j2, byte b);

    private static native void nativeClear(long j);

    private static native boolean nativeContains(long j, long j2);

    private static native long nativeCreateResults(long j, long j2, @Nullable SortDescriptor sortDescriptor, @Nullable SortDescriptor sortDescriptor2);

    private static native long nativeCreateResultsFromBacklinks(long j, long j2, long j3, long j4);

    private static native long nativeCreateResultsFromList(long j, long j2, @Nullable SortDescriptor sortDescriptor);

    private static native long nativeCreateSnapshot(long j);

    private static native void nativeDelete(long j, long j2);

    private static native boolean nativeDeleteFirst(long j);

    private static native boolean nativeDeleteLast(long j);

    private static native long nativeDistinct(long j, SortDescriptor sortDescriptor);

    private static native long nativeFirstRow(long j);

    private static native long nativeGetFinalizerPtr();

    private static native byte nativeGetMode(long j);

    private static native long nativeGetRow(long j, int i);

    private static native long nativeIndexOf(long j, long j2);

    private static native boolean nativeIsValid(long j);

    private static native long nativeLastRow(long j);

    private static native long nativeSize(long j);

    private static native long nativeSort(long j, SortDescriptor sortDescriptor);

    private native void nativeStartListening(long j);

    private native void nativeStopListening(long j);

    private static native long nativeWhere(long j);

    public static Collection createBacklinksCollection(SharedRealm realm, UncheckedRow row, Table srcTable, String srcFieldName) {
        return new Collection(realm, srcTable, nativeCreateResultsFromBacklinks(realm.getNativePtr(), row.getNativePtr(), srcTable.getNativePtr(), srcTable.getColumnIndex(srcFieldName)), true);
    }

    public Collection(SharedRealm sharedRealm, TableQuery query, @Nullable SortDescriptor sortDescriptor, @Nullable SortDescriptor distinctDescriptor) {
        this.isSnapshot = false;
        this.observerPairs = new ObserverPairList();
        query.validateQuery();
        this.nativePtr = nativeCreateResults(sharedRealm.getNativePtr(), query.getNativePtr(), sortDescriptor, distinctDescriptor);
        this.sharedRealm = sharedRealm;
        this.context = sharedRealm.context;
        this.table = query.getTable();
        this.context.addReference(this);
        this.loaded = false;
    }

    public Collection(SharedRealm sharedRealm, TableQuery query, @Nullable SortDescriptor sortDescriptor) {
        this(sharedRealm, query, sortDescriptor, null);
    }

    public Collection(SharedRealm sharedRealm, TableQuery query) {
        this(sharedRealm, query, null, null);
    }

    public Collection(SharedRealm sharedRealm, OsList osList, @Nullable SortDescriptor sortDescriptor) {
        this.isSnapshot = false;
        this.observerPairs = new ObserverPairList();
        this.nativePtr = nativeCreateResultsFromList(sharedRealm.getNativePtr(), osList.getNativePtr(), sortDescriptor);
        this.sharedRealm = sharedRealm;
        this.context = sharedRealm.context;
        this.table = osList.getTargetTable();
        this.context.addReference(this);
        this.loaded = true;
    }

    private Collection(SharedRealm sharedRealm, Table table, long nativePtr) {
        this(sharedRealm, table, nativePtr, false);
    }

    Collection(SharedRealm sharedRealm, Table table, long nativePtr, boolean loaded) {
        this.isSnapshot = false;
        this.observerPairs = new ObserverPairList();
        this.sharedRealm = sharedRealm;
        this.context = sharedRealm.context;
        this.table = table;
        this.nativePtr = nativePtr;
        this.context.addReference(this);
        this.loaded = loaded;
    }

    public Collection createSnapshot() {
        if (this.isSnapshot) {
            return this;
        }
        Collection collection = new Collection(this.sharedRealm, this.table, nativeCreateSnapshot(this.nativePtr));
        collection.isSnapshot = true;
        return collection;
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public UncheckedRow getUncheckedRow(int index) {
        return this.table.getUncheckedRowByPointer(nativeGetRow(this.nativePtr, index));
    }

    public UncheckedRow firstUncheckedRow() {
        long rowPtr = nativeFirstRow(this.nativePtr);
        if (rowPtr != 0) {
            return this.table.getUncheckedRowByPointer(rowPtr);
        }
        return null;
    }

    public UncheckedRow lastUncheckedRow() {
        long rowPtr = nativeLastRow(this.nativePtr);
        if (rowPtr != 0) {
            return this.table.getUncheckedRowByPointer(rowPtr);
        }
        return null;
    }

    public Table getTable() {
        return this.table;
    }

    public TableQuery where() {
        return new TableQuery(this.context, this.table, nativeWhere(this.nativePtr));
    }

    public Number aggregateNumber(Aggregate aggregateMethod, long columnIndex) {
        return (Number) nativeAggregate(this.nativePtr, columnIndex, aggregateMethod.getValue());
    }

    public Date aggregateDate(Aggregate aggregateMethod, long columnIndex) {
        return (Date) nativeAggregate(this.nativePtr, columnIndex, aggregateMethod.getValue());
    }

    public long size() {
        return nativeSize(this.nativePtr);
    }

    public void clear() {
        nativeClear(this.nativePtr);
    }

    public Collection sort(SortDescriptor sortDescriptor) {
        return new Collection(this.sharedRealm, this.table, nativeSort(this.nativePtr, sortDescriptor));
    }

    public Collection distinct(SortDescriptor distinctDescriptor) {
        return new Collection(this.sharedRealm, this.table, nativeDistinct(this.nativePtr, distinctDescriptor));
    }

    public boolean contains(UncheckedRow row) {
        return nativeContains(this.nativePtr, row.getNativePtr());
    }

    public int indexOf(UncheckedRow row) {
        long index = nativeIndexOf(this.nativePtr, row.getNativePtr());
        return index > 2147483647L ? Integer.MAX_VALUE : (int) index;
    }

    public void delete(long index) {
        nativeDelete(this.nativePtr, index);
    }

    public boolean deleteFirst() {
        return nativeDeleteFirst(this.nativePtr);
    }

    public boolean deleteLast() {
        return nativeDeleteLast(this.nativePtr);
    }

    public <T> void addListener(T observer, OrderedRealmCollectionChangeListener<T> listener) {
        if (this.observerPairs.isEmpty()) {
            nativeStartListening(this.nativePtr);
        }
        this.observerPairs.add(new CollectionObserverPair(observer, listener));
    }

    public <T> void addListener(T observer, RealmChangeListener<T> listener) {
        addListener((Object) observer, new RealmChangeListenerWrapper(listener));
    }

    public <T> void removeListener(T observer, OrderedRealmCollectionChangeListener<T> listener) {
        this.observerPairs.remove(observer, listener);
        if (this.observerPairs.isEmpty()) {
            nativeStopListening(this.nativePtr);
        }
    }

    public <T> void removeListener(T observer, RealmChangeListener<T> listener) {
        removeListener((Object) observer, new RealmChangeListenerWrapper(listener));
    }

    public void removeAllListeners() {
        this.observerPairs.clear();
        nativeStopListening(this.nativePtr);
    }

    public boolean isValid() {
        return nativeIsValid(this.nativePtr);
    }

    private void notifyChangeListeners(long nativeChangeSetPtr) {
        if (nativeChangeSetPtr != 0 || !isLoaded()) {
            boolean wasLoaded = this.loaded;
            this.loaded = true;
            ObserverPairList observerPairList = this.observerPairs;
            OrderedCollectionChangeSet osCollectionChangeSet = (nativeChangeSetPtr == 0 || !wasLoaded) ? null : new OsCollectionChangeSet(nativeChangeSetPtr);
            observerPairList.foreach(new Callback(osCollectionChangeSet));
        }
    }

    public Mode getMode() {
        return Mode.getByValue(nativeGetMode(this.nativePtr));
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void load() {
        if (!this.loaded) {
            notifyChangeListeners(0);
        }
    }
}
