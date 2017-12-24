package io.realm;

import io.realm.exceptions.RealmException;
import io.realm.internal.CheckedRow;
import io.realm.internal.OsObject;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.log.RealmLog;
import java.io.File;
import java.util.Locale;
import rx.Observable;

public class DynamicRealm extends BaseRealm {
    private final RealmSchema schema = new MutableRealmSchema(this);

    public static abstract class Callback extends BaseRealm$InstanceCallback<DynamicRealm> {
        public abstract void onSuccess(DynamicRealm dynamicRealm);

        public void onError(Throwable exception) {
            super.onError(exception);
        }
    }

    public interface Transaction {
        void execute(DynamicRealm dynamicRealm);
    }

    public /* bridge */ /* synthetic */ void beginTransaction() {
        super.beginTransaction();
    }

    public /* bridge */ /* synthetic */ void cancelTransaction() {
        super.cancelTransaction();
    }

    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    public /* bridge */ /* synthetic */ void commitTransaction() {
        super.commitTransaction();
    }

    public /* bridge */ /* synthetic */ void deleteAll() {
        super.deleteAll();
    }

    public /* bridge */ /* synthetic */ RealmConfiguration getConfiguration() {
        return super.getConfiguration();
    }

    public /* bridge */ /* synthetic */ String getPath() {
        return super.getPath();
    }

    public /* bridge */ /* synthetic */ long getVersion() {
        return super.getVersion();
    }

    public /* bridge */ /* synthetic */ boolean isAutoRefresh() {
        return super.isAutoRefresh();
    }

    public /* bridge */ /* synthetic */ boolean isClosed() {
        return super.isClosed();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ boolean isInTransaction() {
        return super.isInTransaction();
    }

    public /* bridge */ /* synthetic */ void refresh() {
        super.refresh();
    }

    public /* bridge */ /* synthetic */ void setAutoRefresh(boolean z) {
        super.setAutoRefresh(z);
    }

    public /* bridge */ /* synthetic */ void stopWaitForChange() {
        super.stopWaitForChange();
    }

    public /* bridge */ /* synthetic */ boolean waitForChange() {
        return super.waitForChange();
    }

    public /* bridge */ /* synthetic */ void writeCopyTo(File file) {
        super.writeCopyTo(file);
    }

    public /* bridge */ /* synthetic */ void writeEncryptedCopyTo(File file, byte[] bArr) {
        super.writeEncryptedCopyTo(file, bArr);
    }

    private DynamicRealm(RealmCache cache) {
        super(cache, null);
    }

    private DynamicRealm(SharedRealm sharedRealm) {
        super(sharedRealm);
    }

    public static DynamicRealm getInstance(RealmConfiguration configuration) {
        if (configuration != null) {
            return (DynamicRealm) RealmCache.createRealmOrGetFromCache(configuration, DynamicRealm.class);
        }
        throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
    }

    public static RealmAsyncTask getInstanceAsync(RealmConfiguration configuration, Callback callback) {
        if (configuration != null) {
            return RealmCache.createRealmOrGetFromCacheAsync(configuration, callback, DynamicRealm.class);
        }
        throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
    }

    public DynamicRealmObject createObject(String className) {
        checkIfValid();
        Table table = this.schema.getTable(className);
        if (!table.hasPrimaryKey()) {
            return new DynamicRealmObject(this, CheckedRow.getFromRow(OsObject.create(table)));
        }
        throw new RealmException(String.format(Locale.US, "'%s' has a primary key, use 'createObject(String, Object)' instead.", new Object[]{className}));
    }

    public DynamicRealmObject createObject(String className, Object primaryKeyValue) {
        return new DynamicRealmObject(this, CheckedRow.getFromRow(OsObject.createWithPrimaryKey(this.schema.getTable(className), primaryKeyValue)));
    }

    public RealmQuery<DynamicRealmObject> where(String className) {
        checkIfValid();
        if (this.sharedRealm.hasTable(Table.getTableNameForClass(className))) {
            return RealmQuery.createDynamicQuery(this, className);
        }
        throw new IllegalArgumentException("Class does not exist in the Realm and cannot be queried: " + className);
    }

    public void addChangeListener(RealmChangeListener<DynamicRealm> listener) {
        addListener(listener);
    }

    public void removeChangeListener(RealmChangeListener<DynamicRealm> listener) {
        removeListener(listener);
    }

    public void removeAllChangeListeners() {
        removeAllListeners();
    }

    public void delete(String className) {
        checkIfValid();
        checkIfInTransaction();
        this.schema.getTable(className).clear();
    }

    public void executeTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }
        beginTransaction();
        try {
            transaction.execute(this);
            commitTransaction();
        } catch (RuntimeException e) {
            if (isInTransaction()) {
                cancelTransaction();
            } else {
                RealmLog.warn("Could not cancel transaction, not currently in a transaction.", new Object[0]);
            }
            throw e;
        }
    }

    static DynamicRealm createInstance(RealmCache cache) {
        return new DynamicRealm(cache);
    }

    static DynamicRealm createInstance(SharedRealm sharedRealm) {
        return new DynamicRealm(sharedRealm);
    }

    public Observable<DynamicRealm> asObservable() {
        return this.configuration.getRxFactory().from(this);
    }

    public RealmSchema getSchema() {
        return this.schema;
    }
}
