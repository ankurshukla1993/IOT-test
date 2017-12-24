package io.realm;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.SystemClock;
import android.util.JsonReader;
import io.realm.RealmConfiguration.Builder;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnIndices;
import io.realm.internal.ObjectServerFacade;
import io.realm.internal.OsObject;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmCore;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmObjectProxy.CacheData;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.log.RealmLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;

public class Realm extends BaseRealm {
    public static final String DEFAULT_REALM_NAME = "default.realm";
    private static final String NULL_CONFIG_MSG = "A non-null RealmConfiguration must be provided";
    private static RealmConfiguration defaultConfiguration;
    private static final Object defaultConfigurationLock = new Object();
    private final RealmSchema schema;

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

    private Realm(RealmCache cache) {
        super(cache, createExpectedSchemaInfo(cache.getConfiguration().getSchemaMediator()));
        this.schema = new ImmutableRealmSchema(this, new ColumnIndices(this.configuration.getSchemaMediator(), this.sharedRealm.getSchemaInfo()));
        if (this.configuration.isReadOnly()) {
            RealmProxyMediator mediator = this.configuration.getSchemaMediator();
            for (Class<? extends RealmModel> clazz : mediator.getModelClasses()) {
                if (!this.sharedRealm.hasTable(mediator.getTableName(clazz))) {
                    this.sharedRealm.close();
                    throw new RealmMigrationNeededException(this.configuration.getPath(), String.format(Locale.US, "Cannot open the read only Realm. '%s' is missing.", new Object[]{Table.getClassNameForTable(tableName)}));
                }
            }
        }
    }

    private Realm(SharedRealm sharedRealm) {
        super(sharedRealm);
        this.schema = new ImmutableRealmSchema(this, new ColumnIndices(this.configuration.getSchemaMediator(), sharedRealm.getSchemaInfo()));
    }

    private static OsSchemaInfo createExpectedSchemaInfo(RealmProxyMediator mediator) {
        return new OsSchemaInfo(mediator.getExpectedObjectSchemaInfoMap().values());
    }

    public Observable<Realm> asObservable() {
        return this.configuration.getRxFactory().from(this);
    }

    public RealmSchema getSchema() {
        return this.schema;
    }

    public static synchronized void init(Context context) {
        synchronized (Realm.class) {
            if (BaseRealm.applicationContext == null) {
                if (context == null) {
                    throw new IllegalArgumentException("Non-null context required.");
                }
                checkFilesDirAvailable(context);
                RealmCore.loadLibrary(context);
                setDefaultConfiguration(new Builder(context).build());
                ObjectServerFacade.getSyncFacadeIfPossible().init(context);
                BaseRealm.applicationContext = context.getApplicationContext();
                SharedRealm.initialize(new File(context.getFilesDir(), ".realm.temp"));
            }
        }
    }

    private static void checkFilesDirAvailable(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            if (!filesDir.exists()) {
                try {
                    filesDir.mkdirs();
                } catch (SecurityException e) {
                }
            } else {
                return;
            }
        }
        if (filesDir == null || !filesDir.exists()) {
            long[] timeoutsMs = new long[]{1, 2, 5, 10, 16};
            long currentTotalWaitMs = 0;
            int waitIndex = -1;
            do {
                if (context.getFilesDir() != null && context.getFilesDir().exists()) {
                    break;
                }
                waitIndex++;
                long waitMs = timeoutsMs[Math.min(waitIndex, timeoutsMs.length - 1)];
                SystemClock.sleep(waitMs);
                currentTotalWaitMs += waitMs;
            } while (currentTotalWaitMs <= 200);
        }
        if (context.getFilesDir() == null || !context.getFilesDir().exists()) {
            throw new IllegalStateException("Context.getFilesDir() returns " + context.getFilesDir() + " which is not an existing directory. See https://issuetracker.google.com/issues/36918154");
        }
    }

    public static Realm getDefaultInstance() {
        RealmConfiguration configuration = getDefaultConfiguration();
        if (configuration != null) {
            return (Realm) RealmCache.createRealmOrGetFromCache(configuration, Realm.class);
        }
        if (BaseRealm.applicationContext == null) {
            throw new IllegalStateException("Call `Realm.init(Context)` before calling this method.");
        }
        throw new IllegalStateException("Set default configuration by using `Realm.setDefaultConfiguration(RealmConfiguration)`.");
    }

    public static Realm getInstance(RealmConfiguration configuration) {
        if (configuration != null) {
            return (Realm) RealmCache.createRealmOrGetFromCache(configuration, Realm.class);
        }
        throw new IllegalArgumentException(NULL_CONFIG_MSG);
    }

    public static RealmAsyncTask getInstanceAsync(RealmConfiguration configuration, Callback callback) {
        if (configuration != null) {
            return RealmCache.createRealmOrGetFromCacheAsync(configuration, callback, Realm.class);
        }
        throw new IllegalArgumentException(NULL_CONFIG_MSG);
    }

    public static void setDefaultConfiguration(RealmConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException(NULL_CONFIG_MSG);
        }
        synchronized (defaultConfigurationLock) {
            defaultConfiguration = configuration;
        }
    }

    @Nullable
    public static RealmConfiguration getDefaultConfiguration() {
        RealmConfiguration realmConfiguration;
        synchronized (defaultConfigurationLock) {
            realmConfiguration = defaultConfiguration;
        }
        return realmConfiguration;
    }

    public static void removeDefaultConfiguration() {
        synchronized (defaultConfigurationLock) {
            defaultConfiguration = null;
        }
    }

    static Realm createInstance(RealmCache cache) {
        return new Realm(cache);
    }

    static Realm createInstance(SharedRealm sharedRealm) {
        return new Realm(sharedRealm);
    }

    public <E extends RealmModel> void createAllFromJson(Class<E> clazz, JSONArray json) {
        if (clazz != null && json != null) {
            checkIfValid();
            int i = 0;
            while (i < json.length()) {
                try {
                    this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, json.getJSONObject(i), false);
                    i++;
                } catch (JSONException e) {
                    throw new RealmException("Could not map JSON", e);
                }
            }
        }
    }

    public <E extends RealmModel> void createOrUpdateAllFromJson(Class<E> clazz, JSONArray json) {
        if (clazz != null && json != null) {
            checkIfValid();
            checkHasPrimaryKey(clazz);
            int i = 0;
            while (i < json.length()) {
                try {
                    this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, json.getJSONObject(i), true);
                    i++;
                } catch (JSONException e) {
                    throw new RealmException("Could not map JSON", e);
                }
            }
        }
    }

    public <E extends RealmModel> void createAllFromJson(Class<E> clazz, String json) {
        if (clazz != null && json != null && json.length() != 0) {
            try {
                createAllFromJson((Class) clazz, new JSONArray(json));
            } catch (JSONException e) {
                throw new RealmException("Could not create JSON array from string", e);
            }
        }
    }

    public <E extends RealmModel> void createOrUpdateAllFromJson(Class<E> clazz, String json) {
        if (clazz != null && json != null && json.length() != 0) {
            checkIfValid();
            checkHasPrimaryKey(clazz);
            try {
                createOrUpdateAllFromJson((Class) clazz, new JSONArray(json));
            } catch (JSONException e) {
                throw new RealmException("Could not create JSON array from string", e);
            }
        }
    }

    @TargetApi(11)
    public <E extends RealmModel> void createAllFromJson(Class<E> clazz, InputStream inputStream) throws IOException {
        if (clazz != null && inputStream != null) {
            checkIfValid();
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                reader.beginArray();
                while (reader.hasNext()) {
                    this.configuration.getSchemaMediator().createUsingJsonStream(clazz, this, reader);
                }
                reader.endArray();
            } finally {
                reader.close();
            }
        }
    }

    @TargetApi(11)
    public <E extends RealmModel> void createOrUpdateAllFromJson(Class<E> clazz, InputStream in) {
        if (clazz != null && in != null) {
            checkIfValid();
            checkHasPrimaryKey(clazz);
            Scanner scanner = null;
            try {
                scanner = getFullStringScanner(in);
                JSONArray json = new JSONArray(scanner.next());
                for (int i = 0; i < json.length(); i++) {
                    this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, json.getJSONObject(i), true);
                }
                if (scanner != null) {
                    scanner.close();
                }
            } catch (JSONException e) {
                throw new RealmException("Failed to read JSON", e);
            } catch (Throwable th) {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
    }

    @Nullable
    public <E extends RealmModel> E createObjectFromJson(Class<E> clazz, JSONObject json) {
        if (clazz == null || json == null) {
            return null;
        }
        checkIfValid();
        try {
            return this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, json, false);
        } catch (JSONException e) {
            throw new RealmException("Could not map JSON", e);
        }
    }

    public <E extends RealmModel> E createOrUpdateObjectFromJson(Class<E> clazz, JSONObject json) {
        if (clazz == null || json == null) {
            return null;
        }
        checkIfValid();
        checkHasPrimaryKey(clazz);
        try {
            return this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, json, true);
        } catch (JSONException e) {
            throw new RealmException("Could not map JSON", e);
        }
    }

    @Nullable
    public <E extends RealmModel> E createObjectFromJson(Class<E> clazz, String json) {
        if (clazz == null || json == null || json.length() == 0) {
            return null;
        }
        try {
            return createObjectFromJson((Class) clazz, new JSONObject(json));
        } catch (JSONException e) {
            throw new RealmException("Could not create Json object from string", e);
        }
    }

    public <E extends RealmModel> E createOrUpdateObjectFromJson(Class<E> clazz, String json) {
        if (clazz == null || json == null || json.length() == 0) {
            return null;
        }
        checkIfValid();
        checkHasPrimaryKey(clazz);
        try {
            return createOrUpdateObjectFromJson((Class) clazz, new JSONObject(json));
        } catch (JSONException e) {
            throw new RealmException("Could not create Json object from string", e);
        }
    }

    @TargetApi(11)
    @Nullable
    public <E extends RealmModel> E createObjectFromJson(Class<E> clazz, InputStream inputStream) throws IOException {
        if (clazz == null || inputStream == null) {
            return null;
        }
        checkIfValid();
        E realmObject;
        if (this.schema.getTable((Class) clazz).hasPrimaryKey()) {
            Scanner scanner = null;
            try {
                scanner = getFullStringScanner(inputStream);
                realmObject = this.configuration.getSchemaMediator().createOrUpdateUsingJsonObject(clazz, this, new JSONObject(scanner.next()), false);
                if (scanner == null) {
                    return realmObject;
                }
                scanner.close();
                return realmObject;
            } catch (JSONException e) {
                throw new RealmException("Failed to read JSON", e);
            } catch (Throwable th) {
                if (scanner != null) {
                    scanner.close();
                }
            }
        } else {
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                realmObject = this.configuration.getSchemaMediator().createUsingJsonStream(clazz, this, reader);
                return realmObject;
            } finally {
                reader.close();
            }
        }
    }

    @TargetApi(11)
    public <E extends RealmModel> E createOrUpdateObjectFromJson(Class<E> clazz, InputStream in) {
        if (clazz == null || in == null) {
            return null;
        }
        checkIfValid();
        checkHasPrimaryKey(clazz);
        Scanner scanner = null;
        try {
            scanner = getFullStringScanner(in);
            E createOrUpdateObjectFromJson = createOrUpdateObjectFromJson((Class) clazz, new JSONObject(scanner.next()));
            if (scanner == null) {
                return createOrUpdateObjectFromJson;
            }
            scanner.close();
            return createOrUpdateObjectFromJson;
        } catch (JSONException e) {
            throw new RealmException("Failed to read JSON", e);
        } catch (Throwable th) {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private Scanner getFullStringScanner(InputStream in) {
        return new Scanner(in, "UTF-8").useDelimiter("\\A");
    }

    public <E extends RealmModel> E createObject(Class<E> clazz) {
        checkIfValid();
        return createObjectInternal(clazz, true, Collections.emptyList());
    }

    <E extends RealmModel> E createObjectInternal(Class<E> clazz, boolean acceptDefaultValue, List<String> excludeFields) {
        Table table = this.schema.getTable((Class) clazz);
        if (table.hasPrimaryKey()) {
            throw new RealmException(String.format(Locale.US, "'%s' has a primary key, use 'createObject(Class<E>, Object)' instead.", new Object[]{table.getClassName()}));
        }
        return this.configuration.getSchemaMediator().newInstance(clazz, this, OsObject.create(table), this.schema.getColumnInfo((Class) clazz), acceptDefaultValue, excludeFields);
    }

    public <E extends RealmModel> E createObject(Class<E> clazz, @Nullable Object primaryKeyValue) {
        checkIfValid();
        return createObjectInternal(clazz, primaryKeyValue, true, Collections.emptyList());
    }

    <E extends RealmModel> E createObjectInternal(Class<E> clazz, @Nullable Object primaryKeyValue, boolean acceptDefaultValue, List<String> excludeFields) {
        Table table = this.schema.getTable((Class) clazz);
        return this.configuration.getSchemaMediator().newInstance(clazz, this, OsObject.createWithPrimaryKey(table, primaryKeyValue), this.schema.getColumnInfo((Class) clazz), acceptDefaultValue, excludeFields);
    }

    public <E extends RealmModel> E copyToRealm(E object) {
        checkNotNullObject(object);
        return copyOrUpdate(object, false, new HashMap());
    }

    public <E extends RealmModel> E copyToRealmOrUpdate(E object) {
        checkNotNullObject(object);
        checkHasPrimaryKey(object.getClass());
        return copyOrUpdate(object, true, new HashMap());
    }

    public <E extends RealmModel> List<E> copyToRealm(Iterable<E> objects) {
        if (objects == null) {
            return new ArrayList();
        }
        Map<RealmModel, RealmObjectProxy> cache = new HashMap();
        List<E> realmObjects = new ArrayList();
        for (E object : objects) {
            checkNotNullObject(object);
            realmObjects.add(copyOrUpdate(object, false, cache));
        }
        return realmObjects;
    }

    public void insert(Collection<? extends RealmModel> objects) {
        checkIfValidAndInTransaction();
        if (objects == null) {
            throw new IllegalArgumentException("Null objects cannot be inserted into Realm.");
        } else if (!objects.isEmpty()) {
            this.configuration.getSchemaMediator().insert(this, objects);
        }
    }

    public void insert(RealmModel object) {
        checkIfValidAndInTransaction();
        if (object == null) {
            throw new IllegalArgumentException("Null object cannot be inserted into Realm.");
        }
        this.configuration.getSchemaMediator().insert(this, object, new HashMap());
    }

    public void insertOrUpdate(Collection<? extends RealmModel> objects) {
        checkIfValidAndInTransaction();
        if (objects == null) {
            throw new IllegalArgumentException("Null objects cannot be inserted into Realm.");
        } else if (!objects.isEmpty()) {
            this.configuration.getSchemaMediator().insertOrUpdate(this, objects);
        }
    }

    public void insertOrUpdate(RealmModel object) {
        checkIfValidAndInTransaction();
        if (object == null) {
            throw new IllegalArgumentException("Null object cannot be inserted into Realm.");
        }
        this.configuration.getSchemaMediator().insertOrUpdate(this, object, new HashMap());
    }

    public <E extends RealmModel> List<E> copyToRealmOrUpdate(Iterable<E> objects) {
        if (objects == null) {
            return new ArrayList(0);
        }
        Map<RealmModel, RealmObjectProxy> cache = new HashMap();
        List<E> realmObjects = new ArrayList();
        for (E object : objects) {
            checkNotNullObject(object);
            realmObjects.add(copyOrUpdate(object, true, cache));
        }
        return realmObjects;
    }

    public <E extends RealmModel> List<E> copyFromRealm(Iterable<E> realmObjects) {
        return copyFromRealm((Iterable) realmObjects, Integer.MAX_VALUE);
    }

    public <E extends RealmModel> List<E> copyFromRealm(Iterable<E> realmObjects, int maxDepth) {
        checkMaxDepth(maxDepth);
        if (realmObjects == null) {
            return new ArrayList(0);
        }
        List<E> unmanagedObjects = new ArrayList();
        Map<RealmModel, CacheData<RealmModel>> listCache = new HashMap();
        for (E object : realmObjects) {
            checkValidObjectForDetach(object);
            unmanagedObjects.add(createDetachedCopy(object, maxDepth, listCache));
        }
        return unmanagedObjects;
    }

    public <E extends RealmModel> E copyFromRealm(E realmObject) {
        return copyFromRealm((RealmModel) realmObject, Integer.MAX_VALUE);
    }

    public <E extends RealmModel> E copyFromRealm(E realmObject, int maxDepth) {
        checkMaxDepth(maxDepth);
        checkValidObjectForDetach(realmObject);
        return createDetachedCopy(realmObject, maxDepth, new HashMap());
    }

    public <E extends RealmModel> RealmQuery<E> where(Class<E> clazz) {
        checkIfValid();
        return RealmQuery.createQuery(this, clazz);
    }

    public void addChangeListener(RealmChangeListener<Realm> listener) {
        addListener(listener);
    }

    public void removeChangeListener(RealmChangeListener<Realm> listener) {
        removeListener(listener);
    }

    public void removeAllChangeListeners() {
        removeAllListeners();
    }

    public void executeTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }
        beginTransaction();
        try {
            transaction.execute(this);
            commitTransaction();
        } catch (Throwable th) {
            if (isInTransaction()) {
                cancelTransaction();
            } else {
                RealmLog.warn("Could not cancel transaction, not currently in a transaction.", new Object[0]);
            }
        }
    }

    public RealmAsyncTask executeTransactionAsync(Transaction transaction) {
        return executeTransactionAsync(transaction, null, null);
    }

    public RealmAsyncTask executeTransactionAsync(Transaction transaction, OnSuccess onSuccess) {
        if (onSuccess != null) {
            return executeTransactionAsync(transaction, onSuccess, null);
        }
        throw new IllegalArgumentException("onSuccess callback can't be null");
    }

    public RealmAsyncTask executeTransactionAsync(Transaction transaction, OnError onError) {
        if (onError != null) {
            return executeTransactionAsync(transaction, null, onError);
        }
        throw new IllegalArgumentException("onError callback can't be null");
    }

    public RealmAsyncTask executeTransactionAsync(Transaction transaction, @Nullable OnSuccess onSuccess, @Nullable OnError onError) {
        checkIfValid();
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }
        boolean canDeliverNotification = this.sharedRealm.capabilities.canDeliverNotification();
        if (!(onSuccess == null && onError == null)) {
            this.sharedRealm.capabilities.checkCanDeliverNotification("Callback cannot be delivered on current thread.");
        }
        return new RealmAsyncTaskImpl(asyncTaskExecutor.submitTransaction(new 1(this, getConfiguration(), transaction, canDeliverNotification, onSuccess, this.sharedRealm.realmNotifier, onError)), asyncTaskExecutor);
    }

    public void delete(Class<? extends RealmModel> clazz) {
        checkIfValid();
        this.schema.getTable((Class) clazz).clear();
    }

    private <E extends RealmModel> E copyOrUpdate(E object, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        checkIfValid();
        return this.configuration.getSchemaMediator().copyOrUpdate(this, object, update, cache);
    }

    private <E extends RealmModel> E createDetachedCopy(E object, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        checkIfValid();
        return this.configuration.getSchemaMediator().createDetachedCopy(object, maxDepth, cache);
    }

    private <E extends RealmModel> void checkNotNullObject(E object) {
        if (object == null) {
            throw new IllegalArgumentException("Null objects cannot be copied into Realm.");
        }
    }

    private void checkHasPrimaryKey(Class<? extends RealmModel> clazz) {
        if (!this.schema.getTable((Class) clazz).hasPrimaryKey()) {
            throw new IllegalArgumentException("A RealmObject with no @PrimaryKey cannot be updated: " + clazz.toString());
        }
    }

    private void checkMaxDepth(int maxDepth) {
        if (maxDepth < 0) {
            throw new IllegalArgumentException("maxDepth must be > 0. It was: " + maxDepth);
        }
    }

    private <E extends RealmModel> void checkValidObjectForDetach(E realmObject) {
        if (realmObject == null) {
            throw new IllegalArgumentException("Null objects cannot be copied from Realm.");
        } else if (!RealmObject.isManaged(realmObject) || !RealmObject.isValid(realmObject)) {
            throw new IllegalArgumentException("Only valid managed objects can be copied from Realm.");
        } else if (realmObject instanceof DynamicRealmObject) {
            throw new IllegalArgumentException("DynamicRealmObject cannot be copied from Realm.");
        }
    }

    public static void migrateRealm(RealmConfiguration configuration) throws FileNotFoundException {
        migrateRealm(configuration, null);
    }

    public static void migrateRealm(RealmConfiguration configuration, @Nullable RealmMigration migration) throws FileNotFoundException {
        BaseRealm.migrateRealm(configuration, migration);
    }

    public static boolean deleteRealm(RealmConfiguration configuration) {
        return BaseRealm.deleteRealm(configuration);
    }

    public static boolean compactRealm(RealmConfiguration configuration) {
        if (!configuration.isSyncConfiguration()) {
            return BaseRealm.compactRealm(configuration);
        }
        throw new UnsupportedOperationException("Compacting is not supported yet on synced Realms. See https://github.com/realm/realm-core/issues/2345");
    }

    Table getTable(Class<? extends RealmModel> clazz) {
        return this.schema.getTable((Class) clazz);
    }

    @Nullable
    public static Object getDefaultModule() {
        String moduleName = "io.realm.DefaultRealmModule";
        try {
            Constructor<?> constructor = Class.forName(moduleName).getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InvocationTargetException e2) {
            throw new RealmException("Could not create an instance of " + moduleName, e2);
        } catch (InstantiationException e3) {
            throw new RealmException("Could not create an instance of " + moduleName, e3);
        } catch (IllegalAccessException e4) {
            throw new RealmException("Could not create an instance of " + moduleName, e4);
        }
    }

    public static int getGlobalInstanceCount(RealmConfiguration configuration) {
        AtomicInteger globalCount = new AtomicInteger(0);
        RealmCache.invokeWithGlobalRefCount(configuration, new 2(globalCount));
        return globalCount.get();
    }

    public static int getLocalInstanceCount(RealmConfiguration configuration) {
        return RealmCache.getLocalThreadCount(configuration);
    }
}
