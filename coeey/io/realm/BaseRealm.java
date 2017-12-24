package io.realm;

import android.content.Context;
import android.os.Looper;
import io.realm.Realm.Transaction;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.CheckedRow;
import io.realm.internal.InvalidRow;
import io.realm.internal.OsRealmConfig.Builder;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.SharedRealm;
import io.realm.internal.SharedRealm.InitializationCallback;
import io.realm.internal.SharedRealm.MigrationCallback;
import io.realm.internal.SharedRealm.SchemaChangedCallback;
import io.realm.internal.Table;
import io.realm.internal.UncheckedRow;
import io.realm.internal.async.RealmThreadPoolExecutor;
import io.realm.log.RealmLog;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import rx.Observable;

abstract class BaseRealm implements Closeable {
    private static final String CLOSED_REALM_MESSAGE = "This Realm instance has already been closed, making it unusable.";
    private static final String INCORRECT_THREAD_CLOSE_MESSAGE = "Realm access from incorrect thread. Realm instance can only be closed on the thread it was created.";
    private static final String INCORRECT_THREAD_MESSAGE = "Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.";
    static final String LISTENER_NOT_ALLOWED_MESSAGE = "Listeners cannot be used on current thread.";
    private static final String NOT_IN_TRANSACTION_MESSAGE = "Changing Realm data can only be done from inside a transaction.";
    static volatile Context applicationContext;
    static final RealmThreadPoolExecutor asyncTaskExecutor = RealmThreadPoolExecutor.newDefaultExecutor();
    public static final ThreadLocalRealmObjectContext objectContext = new ThreadLocalRealmObjectContext();
    protected final RealmConfiguration configuration;
    private RealmCache realmCache;
    private SchemaChangedCallback schemaChangedCallback;
    protected SharedRealm sharedRealm;
    private boolean shouldCloseSharedRealm;
    final long threadId;

    public abstract Observable asObservable();

    public abstract RealmSchema getSchema();

    BaseRealm(RealmCache cache, @Nullable OsSchemaInfo schemaInfo) {
        this(cache.getConfiguration(), schemaInfo);
        this.realmCache = cache;
    }

    BaseRealm(RealmConfiguration configuration, @Nullable OsSchemaInfo schemaInfo) {
        this.schemaChangedCallback = new 1(this);
        this.threadId = Thread.currentThread().getId();
        this.configuration = configuration;
        this.realmCache = null;
        MigrationCallback migrationCallback = null;
        if (!(schemaInfo == null || configuration.getMigration() == null)) {
            migrationCallback = createMigrationCallback(configuration.getMigration());
        }
        InitializationCallback initializationCallback = null;
        Transaction initialDataTransaction = configuration.getInitialDataTransaction();
        if (initialDataTransaction != null) {
            initializationCallback = new 2(this, initialDataTransaction);
        }
        this.sharedRealm = SharedRealm.getInstance(new Builder(configuration).autoUpdateNotification(true).migrationCallback(migrationCallback).schemaInfo(schemaInfo).initializationCallback(initializationCallback));
        this.shouldCloseSharedRealm = true;
        this.sharedRealm.registerSchemaChangedCallback(this.schemaChangedCallback);
    }

    BaseRealm(SharedRealm sharedRealm) {
        this.schemaChangedCallback = new 1(this);
        this.threadId = Thread.currentThread().getId();
        this.configuration = sharedRealm.getConfiguration();
        this.realmCache = null;
        this.sharedRealm = sharedRealm;
        this.shouldCloseSharedRealm = false;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        checkIfValid();
        this.sharedRealm.setAutoRefresh(autoRefresh);
    }

    public boolean isAutoRefresh() {
        return this.sharedRealm.isAutoRefresh();
    }

    public void refresh() {
        checkIfValid();
        if (isInTransaction()) {
            throw new IllegalStateException("Cannot refresh a Realm instance inside a transaction.");
        }
        this.sharedRealm.refresh();
    }

    public boolean isInTransaction() {
        checkIfValid();
        return this.sharedRealm.isInTransaction();
    }

    protected <T extends BaseRealm> void addListener(RealmChangeListener<T> listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        checkIfValid();
        this.sharedRealm.capabilities.checkCanDeliverNotification(LISTENER_NOT_ALLOWED_MESSAGE);
        this.sharedRealm.realmNotifier.addChangeListener(this, listener);
    }

    protected <T extends BaseRealm> void removeListener(RealmChangeListener<T> listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        checkIfValid();
        this.sharedRealm.capabilities.checkCanDeliverNotification(LISTENER_NOT_ALLOWED_MESSAGE);
        this.sharedRealm.realmNotifier.removeChangeListener(this, listener);
    }

    protected void removeAllListeners() {
        checkIfValid();
        this.sharedRealm.capabilities.checkCanDeliverNotification("removeListener cannot be called on current thread.");
        this.sharedRealm.realmNotifier.removeChangeListeners(this);
    }

    public void writeCopyTo(File destination) {
        if (destination == null) {
            throw new IllegalArgumentException("The destination argument cannot be null");
        }
        checkIfValid();
        this.sharedRealm.writeCopy(destination, null);
    }

    public void writeEncryptedCopyTo(File destination, byte[] key) {
        if (destination == null) {
            throw new IllegalArgumentException("The destination argument cannot be null");
        }
        checkIfValid();
        this.sharedRealm.writeCopy(destination, key);
    }

    public boolean waitForChange() {
        checkIfValid();
        if (isInTransaction()) {
            throw new IllegalStateException("Cannot wait for changes inside of a transaction.");
        } else if (Looper.myLooper() != null) {
            throw new IllegalStateException("Cannot wait for changes inside a Looper thread. Use RealmChangeListeners instead.");
        } else {
            boolean hasChanged = this.sharedRealm.waitForChange();
            if (hasChanged) {
                this.sharedRealm.refresh();
            }
            return hasChanged;
        }
    }

    public void stopWaitForChange() {
        if (this.realmCache != null) {
            this.realmCache.invokeWithLock(new 3(this));
            return;
        }
        throw new IllegalStateException(CLOSED_REALM_MESSAGE);
    }

    public void beginTransaction() {
        checkIfValid();
        this.sharedRealm.beginTransaction();
    }

    public void commitTransaction() {
        checkIfValid();
        this.sharedRealm.commitTransaction();
    }

    public void cancelTransaction() {
        checkIfValid();
        this.sharedRealm.cancelTransaction();
    }

    protected void checkIfValid() {
        if (this.sharedRealm == null || this.sharedRealm.isClosed()) {
            throw new IllegalStateException(CLOSED_REALM_MESSAGE);
        } else if (this.threadId != Thread.currentThread().getId()) {
            throw new IllegalStateException(INCORRECT_THREAD_MESSAGE);
        }
    }

    protected void checkIfInTransaction() {
        if (!this.sharedRealm.isInTransaction()) {
            throw new IllegalStateException(NOT_IN_TRANSACTION_MESSAGE);
        }
    }

    protected void checkIfValidAndInTransaction() {
        if (!isInTransaction()) {
            throw new IllegalStateException(NOT_IN_TRANSACTION_MESSAGE);
        }
    }

    void checkNotInSync() {
        if (this.configuration.isSyncConfiguration()) {
            throw new IllegalArgumentException("You cannot perform changes to a schema. Please update app and restart.");
        }
    }

    public String getPath() {
        return this.configuration.getPath();
    }

    public RealmConfiguration getConfiguration() {
        return this.configuration;
    }

    public long getVersion() {
        return this.sharedRealm.getSchemaVersion();
    }

    public void close() {
        if (this.threadId != Thread.currentThread().getId()) {
            throw new IllegalStateException(INCORRECT_THREAD_CLOSE_MESSAGE);
        } else if (this.realmCache != null) {
            this.realmCache.release(this);
        } else {
            doClose();
        }
    }

    void doClose() {
        this.realmCache = null;
        if (this.sharedRealm != null && this.shouldCloseSharedRealm) {
            this.sharedRealm.close();
            this.sharedRealm = null;
        }
    }

    public boolean isClosed() {
        if (this.threadId == Thread.currentThread().getId()) {
            return this.sharedRealm == null || this.sharedRealm.isClosed();
        } else {
            throw new IllegalStateException(INCORRECT_THREAD_MESSAGE);
        }
    }

    public boolean isEmpty() {
        checkIfValid();
        return this.sharedRealm.isEmpty();
    }

    void setVersion(long version) {
        this.sharedRealm.setSchemaVersion(version);
    }

    <E extends RealmModel> E get(@Nullable Class<E> clazz, @Nullable String dynamicClassName, UncheckedRow row) {
        boolean isDynamicRealmObject;
        if (dynamicClassName != null) {
            isDynamicRealmObject = true;
        } else {
            isDynamicRealmObject = false;
        }
        if (isDynamicRealmObject) {
            return new DynamicRealmObject(this, CheckedRow.getFromRow(row));
        }
        return this.configuration.getSchemaMediator().newInstance(clazz, this, row, getSchema().getColumnInfo((Class) clazz), false, Collections.emptyList());
    }

    <E extends RealmModel> E get(Class<E> clazz, long rowIndex, boolean acceptDefaultValue, List<String> excludeFields) {
        UncheckedRow row = getSchema().getTable((Class) clazz).getUncheckedRow(rowIndex);
        return this.configuration.getSchemaMediator().newInstance(clazz, this, row, getSchema().getColumnInfo((Class) clazz), acceptDefaultValue, excludeFields);
    }

    <E extends RealmModel> E get(@Nullable Class<E> clazz, @Nullable String dynamicClassName, long rowIndex) {
        boolean isDynamicRealmObject;
        if (dynamicClassName != null) {
            isDynamicRealmObject = true;
        } else {
            isDynamicRealmObject = false;
        }
        Table table = isDynamicRealmObject ? getSchema().getTable(dynamicClassName) : getSchema().getTable((Class) clazz);
        if (isDynamicRealmObject) {
            return new DynamicRealmObject(this, rowIndex != -1 ? table.getCheckedRow(rowIndex) : InvalidRow.INSTANCE);
        }
        return this.configuration.getSchemaMediator().newInstance(clazz, this, rowIndex != -1 ? table.getUncheckedRow(rowIndex) : InvalidRow.INSTANCE, getSchema().getColumnInfo((Class) clazz), false, Collections.emptyList());
    }

    public void deleteAll() {
        checkIfValid();
        for (RealmObjectSchema objectSchema : getSchema().getAll()) {
            getSchema().getTable(objectSchema.getClassName()).clear();
        }
    }

    static boolean deleteRealm(RealmConfiguration configuration) {
        AtomicBoolean realmDeleted = new AtomicBoolean(true);
        RealmCache.invokeWithGlobalRefCount(configuration, new 4(configuration, realmDeleted));
        return realmDeleted.get();
    }

    static boolean compactRealm(RealmConfiguration configuration) {
        SharedRealm sharedRealm = SharedRealm.getInstance(configuration);
        Boolean result = Boolean.valueOf(sharedRealm.compact());
        sharedRealm.close();
        return result.booleanValue();
    }

    protected static void migrateRealm(RealmConfiguration configuration, @Nullable RealmMigration migration) throws FileNotFoundException {
        if (configuration == null) {
            throw new IllegalArgumentException("RealmConfiguration must be provided");
        } else if (configuration.isSyncConfiguration()) {
            throw new IllegalArgumentException("Manual migrations are not supported for synced Realms");
        } else if (migration == null && configuration.getMigration() == null) {
            throw new RealmMigrationNeededException(configuration.getPath(), "RealmMigration must be provided.");
        } else {
            AtomicBoolean fileNotFound = new AtomicBoolean(false);
            RealmCache.invokeWithGlobalRefCount(configuration, new 5(configuration, fileNotFound, migration));
            if (fileNotFound.get()) {
                throw new FileNotFoundException("Cannot migrate a Realm file which doesn't exist: " + configuration.getPath());
            }
        }
    }

    private static MigrationCallback createMigrationCallback(RealmMigration migration) {
        return new 6(migration);
    }

    protected void finalize() throws Throwable {
        if (!(!this.shouldCloseSharedRealm || this.sharedRealm == null || this.sharedRealm.isClosed())) {
            RealmLog.warn("Remember to call close() on all Realm instances. Realm %s is being finalized without being closed, this can lead to running out of native memory.", new Object[]{this.configuration.getPath()});
            if (this.realmCache != null) {
                this.realmCache.leak();
            }
        }
        super.finalize();
    }

    SharedRealm getSharedRealm() {
        return this.sharedRealm;
    }
}
