package android.arch.persistence.room;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class InvalidationTracker {
    @VisibleForTesting
    static final String CLEANUP_SQL = "DELETE FROM room_table_modification_log WHERE version NOT IN( SELECT MAX(version) FROM room_table_modification_log GROUP BY table_id)";
    private static final String CREATE_VERSION_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log(version INTEGER PRIMARY KEY AUTOINCREMENT, table_id INTEGER)";
    @VisibleForTesting
    static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE version  > ? ORDER BY version ASC;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String[] TRIGGERS = new String[]{"UPDATE", HttpRequest.METHOD_DELETE, "INSERT"};
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private static final String VERSION_COLUMN_NAME = "version";
    private volatile SupportSQLiteStatement mCleanupStatement;
    private final RoomDatabase mDatabase;
    private volatile boolean mInitialized = false;
    private long mMaxVersion = 0;
    private ObservedTableTracker mObservedTableTracker;
    @VisibleForTesting
    final SafeIterableMap<Observer, ObserverWrapper> mObserverMap = new SafeIterableMap();
    AtomicBoolean mPendingRefresh = new AtomicBoolean(false);
    private Object[] mQueryArgs = new Object[1];
    @VisibleForTesting
    Runnable mRefreshRunnable = new C00262();
    private Runnable mSyncTriggers = new C00251();
    @VisibleForTesting
    @NonNull
    ArrayMap<String, Integer> mTableIdLookup;
    private String[] mTableNames;
    @VisibleForTesting
    @NonNull
    long[] mTableVersions;

    class C00251 implements Runnable {
        C00251() {
        }

        public void run() {
            RuntimeException exception;
            if (!InvalidationTracker.this.mDatabase.inTransaction() && InvalidationTracker.this.ensureInitialization()) {
                while (true) {
                    SupportSQLiteDatabase writableDatabase;
                    try {
                        int[] tablesToSync = InvalidationTracker.this.mObservedTableTracker.getTablesToSync();
                        if (tablesToSync != null) {
                            int limit = tablesToSync.length;
                            writableDatabase = InvalidationTracker.this.mDatabase.getOpenHelper().getWritableDatabase();
                            writableDatabase.beginTransaction();
                            for (int tableId = 0; tableId < limit; tableId++) {
                                switch (tablesToSync[tableId]) {
                                    case 1:
                                        InvalidationTracker.this.startTrackingTable(writableDatabase, tableId);
                                        break;
                                    case 2:
                                        InvalidationTracker.this.stopTrackingTable(writableDatabase, tableId);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            InvalidationTracker.this.mObservedTableTracker.onSyncCompleted();
                        } else {
                            return;
                        }
                    } catch (IllegalStateException e) {
                        exception = e;
                    } catch (SQLiteException e2) {
                        exception = e2;
                    } catch (Throwable th) {
                        writableDatabase.endTransaction();
                    }
                }
            } else {
                return;
            }
            Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
        }
    }

    class C00262 implements Runnable {
        C00262() {
        }

        public void run() {
            RuntimeException exception;
            Lock closeLock = InvalidationTracker.this.mDatabase.getCloseLock();
            boolean hasUpdatedTable = false;
            Cursor cursor;
            Iterator it;
            try {
                closeLock.lock();
                if (!InvalidationTracker.this.ensureInitialization()) {
                    closeLock.unlock();
                } else if (InvalidationTracker.this.mDatabase.inTransaction() || !InvalidationTracker.this.mPendingRefresh.compareAndSet(true, false)) {
                    closeLock.unlock();
                } else {
                    InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
                    InvalidationTracker.this.mQueryArgs[0] = Long.valueOf(InvalidationTracker.this.mMaxVersion);
                    cursor = InvalidationTracker.this.mDatabase.query(InvalidationTracker.SELECT_UPDATED_TABLES_SQL, InvalidationTracker.this.mQueryArgs);
                    while (cursor.moveToNext()) {
                        long version = cursor.getLong(0);
                        InvalidationTracker.this.mTableVersions[cursor.getInt(1)] = version;
                        hasUpdatedTable = true;
                        InvalidationTracker.this.mMaxVersion = version;
                    }
                    cursor.close();
                    closeLock.unlock();
                    if (!hasUpdatedTable) {
                        synchronized (InvalidationTracker.this.mObserverMap) {
                            it = InvalidationTracker.this.mObserverMap.iterator();
                            while (it.hasNext()) {
                                ((ObserverWrapper) ((Entry) it.next()).getValue()).checkForInvalidation(InvalidationTracker.this.mTableVersions);
                            }
                        }
                    }
                }
            } catch (RuntimeException e) {
                exception = e;
                try {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                    if (!hasUpdatedTable) {
                        synchronized (InvalidationTracker.this.mObserverMap) {
                            it = InvalidationTracker.this.mObserverMap.iterator();
                            while (it.hasNext()) {
                                ((ObserverWrapper) ((Entry) it.next()).getValue()).checkForInvalidation(InvalidationTracker.this.mTableVersions);
                            }
                        }
                    }
                } finally {
                    closeLock.unlock();
                }
            } catch (RuntimeException e2) {
                exception = e2;
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", exception);
                if (!hasUpdatedTable) {
                    synchronized (InvalidationTracker.this.mObserverMap) {
                        it = InvalidationTracker.this.mObserverMap.iterator();
                        while (it.hasNext()) {
                            ((ObserverWrapper) ((Entry) it.next()).getValue()).checkForInvalidation(InvalidationTracker.this.mTableVersions);
                        }
                    }
                }
            } catch (Throwable th) {
                cursor.close();
            }
        }
    }

    static class ObservedTableTracker {
        static final int ADD = 1;
        static final int NO_OP = 0;
        static final int REMOVE = 2;
        boolean mNeedsSync;
        boolean mPendingSync;
        final long[] mTableObservers;
        final int[] mTriggerStateChanges;
        final boolean[] mTriggerStates;

        ObservedTableTracker(int tableCount) {
            this.mTableObservers = new long[tableCount];
            this.mTriggerStates = new boolean[tableCount];
            this.mTriggerStateChanges = new int[tableCount];
            Arrays.fill(this.mTableObservers, 0);
            Arrays.fill(this.mTriggerStates, false);
        }

        boolean onAdded(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long prevObserverCount = this.mTableObservers[tableId];
                    this.mTableObservers[tableId] = 1 + prevObserverCount;
                    if (prevObserverCount == 0) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        boolean onRemoved(int... tableIds) {
            boolean needTriggerSync = false;
            synchronized (this) {
                for (int tableId : tableIds) {
                    long prevObserverCount = this.mTableObservers[tableId];
                    this.mTableObservers[tableId] = prevObserverCount - 1;
                    if (prevObserverCount == 1) {
                        this.mNeedsSync = true;
                        needTriggerSync = true;
                    }
                }
            }
            return needTriggerSync;
        }

        @Nullable
        int[] getTablesToSync() {
            int[] iArr;
            synchronized (this) {
                if (!this.mNeedsSync || this.mPendingSync) {
                    iArr = null;
                } else {
                    int tableCount = this.mTableObservers.length;
                    for (int i = 0; i < tableCount; i++) {
                        boolean newState;
                        if (this.mTableObservers[i] > 0) {
                            newState = true;
                        } else {
                            newState = false;
                        }
                        if (newState != this.mTriggerStates[i]) {
                            this.mTriggerStateChanges[i] = newState ? 1 : 2;
                        } else {
                            this.mTriggerStateChanges[i] = 0;
                        }
                        this.mTriggerStates[i] = newState;
                    }
                    this.mPendingSync = true;
                    this.mNeedsSync = false;
                    iArr = this.mTriggerStateChanges;
                }
            }
            return iArr;
        }

        void onSyncCompleted() {
            synchronized (this) {
                this.mPendingSync = false;
            }
        }
    }

    public static abstract class Observer {
        final String[] mTables;

        public abstract void onInvalidated(@NonNull Set<String> set);

        protected Observer(@NonNull String firstTable, String... rest) {
            this.mTables = (String[]) Arrays.copyOf(rest, rest.length + 1);
            this.mTables[rest.length] = firstTable;
        }

        public Observer(@NonNull String[] tables) {
            this.mTables = (String[]) Arrays.copyOf(tables, tables.length);
        }
    }

    static class ObserverWrapper {
        final Observer mObserver;
        private final Set<String> mSingleTableSet;
        final int[] mTableIds;
        private final String[] mTableNames;
        private final long[] mVersions;

        ObserverWrapper(Observer observer, int[] tableIds, String[] tableNames, long[] versions) {
            this.mObserver = observer;
            this.mTableIds = tableIds;
            this.mTableNames = tableNames;
            this.mVersions = versions;
            if (tableIds.length == 1) {
                ArraySet<String> set = new ArraySet();
                set.add(this.mTableNames[0]);
                this.mSingleTableSet = Collections.unmodifiableSet(set);
                return;
            }
            this.mSingleTableSet = null;
        }

        void checkForInvalidation(long[] versions) {
            Set<String> invalidatedTables = null;
            int size = this.mTableIds.length;
            for (int index = 0; index < size; index++) {
                long newVersion = versions[this.mTableIds[index]];
                if (this.mVersions[index] < newVersion) {
                    this.mVersions[index] = newVersion;
                    if (size == 1) {
                        invalidatedTables = this.mSingleTableSet;
                    } else {
                        if (invalidatedTables == null) {
                            invalidatedTables = new ArraySet(size);
                        }
                        invalidatedTables.add(this.mTableNames[index]);
                    }
                }
            }
            if (invalidatedTables != null) {
                this.mObserver.onInvalidated(invalidatedTables);
            }
        }
    }

    static class WeakObserver extends Observer {
        final WeakReference<Observer> mDelegateRef;
        final InvalidationTracker mTracker;

        WeakObserver(InvalidationTracker tracker, Observer delegate) {
            super(delegate.mTables);
            this.mTracker = tracker;
            this.mDelegateRef = new WeakReference(delegate);
        }

        public void onInvalidated(@NonNull Set<String> tables) {
            Observer observer = (Observer) this.mDelegateRef.get();
            if (observer == null) {
                this.mTracker.removeObserver(this);
            } else {
                observer.onInvalidated(tables);
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public InvalidationTracker(RoomDatabase database, String... tableNames) {
        this.mDatabase = database;
        this.mObservedTableTracker = new ObservedTableTracker(tableNames.length);
        this.mTableIdLookup = new ArrayMap();
        int size = tableNames.length;
        this.mTableNames = new String[size];
        for (int id = 0; id < size; id++) {
            String tableName = tableNames[id].toLowerCase(Locale.US);
            this.mTableIdLookup.put(tableName, Integer.valueOf(id));
            this.mTableNames[id] = tableName;
        }
        this.mTableVersions = new long[tableNames.length];
        Arrays.fill(this.mTableVersions, 0);
    }

    void internalInit(SupportSQLiteDatabase database) {
        synchronized (this) {
            if (this.mInitialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            database.beginTransaction();
            try {
                database.execSQL("PRAGMA temp_store = MEMORY;");
                database.execSQL("PRAGMA recursive_triggers='ON';");
                database.execSQL(CREATE_VERSION_TABLE_SQL);
                database.setTransactionSuccessful();
                this.mCleanupStatement = database.compileStatement(CLEANUP_SQL);
                this.mInitialized = true;
            } finally {
                database.endTransaction();
            }
        }
    }

    private static void appendTriggerName(StringBuilder builder, String tableName, String triggerType) {
        builder.append("room_table_modification_trigger_").append(tableName).append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).append(triggerType);
    }

    private void stopTrackingTable(SupportSQLiteDatabase writableDb, int tableId) {
        String tableName = this.mTableNames[tableId];
        StringBuilder stringBuilder = new StringBuilder();
        for (String trigger : TRIGGERS) {
            stringBuilder.setLength(0);
            stringBuilder.append("DROP TRIGGER IF EXISTS ");
            appendTriggerName(stringBuilder, tableName, trigger);
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    private void startTrackingTable(SupportSQLiteDatabase writableDb, int tableId) {
        String tableName = this.mTableNames[tableId];
        StringBuilder stringBuilder = new StringBuilder();
        for (String trigger : TRIGGERS) {
            stringBuilder.setLength(0);
            stringBuilder.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            appendTriggerName(stringBuilder, tableName, trigger);
            stringBuilder.append(" AFTER ").append(trigger).append(" ON ").append(tableName).append(" BEGIN INSERT OR REPLACE INTO ").append(UPDATE_TABLE_NAME).append(" VALUES(null, ").append(tableId).append("); END");
            writableDb.execSQL(stringBuilder.toString());
        }
    }

    public void addObserver(Observer observer) {
        String[] tableNames = observer.mTables;
        int[] tableIds = new int[tableNames.length];
        int size = tableNames.length;
        long[] versions = new long[tableNames.length];
        for (int i = 0; i < size; i++) {
            Integer tableId = (Integer) this.mTableIdLookup.get(tableNames[i].toLowerCase(Locale.US));
            if (tableId == null) {
                throw new IllegalArgumentException("There is no table with name " + tableNames[i]);
            }
            tableIds[i] = tableId.intValue();
            versions[i] = this.mMaxVersion;
        }
        ObserverWrapper wrapper = new ObserverWrapper(observer, tableIds, tableNames, versions);
        synchronized (this.mObserverMap) {
            ObserverWrapper currentObserver = (ObserverWrapper) this.mObserverMap.putIfAbsent(observer, wrapper);
        }
        if (currentObserver == null && this.mObservedTableTracker.onAdded(tableIds)) {
            AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mSyncTriggers);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void addWeakObserver(Observer observer) {
        addObserver(new WeakObserver(this, observer));
    }

    public void removeObserver(Observer observer) {
        synchronized (this.mObserverMap) {
            ObserverWrapper wrapper = (ObserverWrapper) this.mObserverMap.remove(observer);
        }
        if (wrapper != null && this.mObservedTableTracker.onRemoved(wrapper.mTableIds)) {
            AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mSyncTriggers);
        }
    }

    private boolean ensureInitialization() {
        if (!this.mDatabase.isOpen()) {
            return false;
        }
        if (!this.mInitialized) {
            this.mDatabase.getOpenHelper().getWritableDatabase();
        }
        if (this.mInitialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.mPendingRefresh.compareAndSet(false, true)) {
            AppToolkitTaskExecutor.getInstance().executeOnDiskIO(this.mRefreshRunnable);
        }
    }

    @WorkerThread
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void refreshVersionsSync() {
        syncTriggers();
        this.mRefreshRunnable.run();
    }

    void syncTriggers() {
        this.mSyncTriggers.run();
    }
}
