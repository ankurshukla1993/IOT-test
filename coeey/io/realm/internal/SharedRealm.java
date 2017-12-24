package io.realm.internal;

import io.realm.RealmConfiguration;
import io.realm.internal.Collection.Iterator;
import io.realm.internal.OsRealmConfig.Builder;
import io.realm.internal.android.AndroidCapabilities;
import io.realm.internal.android.AndroidRealmNotifier;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;

@Keep
public final class SharedRealm implements Closeable, NativeObject {
    public static final byte FILE_EXCEPTION_KIND_ACCESS_ERROR = (byte) 0;
    public static final byte FILE_EXCEPTION_KIND_BAD_HISTORY = (byte) 1;
    public static final byte FILE_EXCEPTION_KIND_EXISTS = (byte) 3;
    public static final byte FILE_EXCEPTION_KIND_FORMAT_UPGRADE_REQUIRED = (byte) 6;
    public static final byte FILE_EXCEPTION_KIND_INCOMPATIBLE_LOCK_FILE = (byte) 5;
    public static final byte FILE_EXCEPTION_KIND_NOT_FOUND = (byte) 4;
    public static final byte FILE_EXCEPTION_KIND_PERMISSION_DENIED = (byte) 2;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private static volatile File temporaryDirectory;
    public final Capabilities capabilities;
    public final List<WeakReference<Collection>> collections = new CopyOnWriteArrayList();
    final NativeContext context;
    public final List<WeakReference<Iterator>> iterators = new ArrayList();
    private final long nativePtr;
    private final OsRealmConfig osRealmConfig;
    private final List<WeakReference<PendingRow>> pendingRows = new CopyOnWriteArrayList();
    public final RealmNotifier realmNotifier;
    private final OsSchemaInfo schemaInfo;

    @Keep
    public interface SchemaChangedCallback {
        void onSchemaChanged();
    }

    @Keep
    public interface InitializationCallback {
        void onInit(SharedRealm sharedRealm);
    }

    @Keep
    public interface MigrationCallback {
        void onMigrationNeeded(SharedRealm sharedRealm, long j, long j2);
    }

    public static class VersionID implements Comparable<VersionID> {
        public final long index;
        public final long version;

        VersionID(long version, long index) {
            this.version = version;
            this.index = index;
        }

        public int compareTo(VersionID another) {
            if (another == null) {
                throw new IllegalArgumentException("Version cannot be compared to a null value.");
            } else if (this.version > another.version) {
                return 1;
            } else {
                if (this.version < another.version) {
                    return -1;
                }
                return 0;
            }
        }

        public String toString() {
            return "VersionID{version=" + this.version + ", index=" + this.index + '}';
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            VersionID versionID = (VersionID) object;
            if (this.version == versionID.version && this.index == versionID.index) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((super.hashCode() * 31) + ((int) (this.version ^ (this.version >>> 32)))) * 31) + ((int) (this.index ^ (this.index >>> 32)));
        }
    }

    private static native void nativeBeginTransaction(long j);

    private static native void nativeCancelTransaction(long j);

    private static native void nativeCloseSharedRealm(long j);

    private static native void nativeCommitTransaction(long j);

    private static native boolean nativeCompact(long j);

    private static native long nativeCreateTable(long j, String str);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetSchemaInfo(long j);

    private static native long nativeGetSharedRealm(long j, RealmNotifier realmNotifier);

    private static native long nativeGetTable(long j, String str);

    private static native String nativeGetTableName(long j, int i);

    private static native long nativeGetVersion(long j);

    private static native long[] nativeGetVersionID(long j);

    private static native boolean nativeHasTable(long j, String str);

    private static native void nativeInit(String str);

    private static native boolean nativeIsAutoRefresh(long j);

    private static native boolean nativeIsClosed(long j);

    private static native boolean nativeIsEmpty(long j);

    private static native boolean nativeIsInTransaction(long j);

    private static native long nativeReadGroup(long j);

    private static native void nativeRefresh(long j);

    private static native void nativeRegisterSchemaChangedCallback(long j, SchemaChangedCallback schemaChangedCallback);

    private static native void nativeRemoveTable(long j, String str);

    private static native void nativeRenameTable(long j, String str, String str2);

    private static native void nativeSetAutoRefresh(long j, boolean z);

    private static native void nativeSetVersion(long j, long j2);

    private static native long nativeSize(long j);

    private static native void nativeStopWaitForChange(long j);

    private static native boolean nativeWaitForChange(long j);

    private static native void nativeWriteCopy(long j, String str, @Nullable byte[] bArr);

    public static void initialize(File tempDirectory) {
        if (temporaryDirectory == null) {
            if (tempDirectory == null) {
                throw new IllegalArgumentException("'tempDirectory' must not be null.");
            }
            String temporaryDirectoryPath = tempDirectory.getAbsolutePath();
            if (tempDirectory.isDirectory() || tempDirectory.mkdirs() || tempDirectory.isDirectory()) {
                if (!temporaryDirectoryPath.endsWith("/")) {
                    temporaryDirectoryPath = temporaryDirectoryPath + "/";
                }
                nativeInit(temporaryDirectoryPath);
                temporaryDirectory = tempDirectory;
                return;
            }
            throw new IOException("failed to create temporary directory: " + temporaryDirectoryPath);
        }
    }

    public static File getTemporaryDirectory() {
        return temporaryDirectory;
    }

    private SharedRealm(OsRealmConfig osRealmConfig) {
        Capabilities capabilities = new AndroidCapabilities();
        RealmNotifier realmNotifier = new AndroidRealmNotifier(this, capabilities);
        this.nativePtr = nativeGetSharedRealm(osRealmConfig.getNativePtr(), realmNotifier);
        this.osRealmConfig = osRealmConfig;
        this.schemaInfo = new OsSchemaInfo(nativeGetSchemaInfo(this.nativePtr), this);
        this.context = osRealmConfig.getContext();
        this.context.addReference(this);
        this.capabilities = capabilities;
        this.realmNotifier = realmNotifier;
        nativeSetAutoRefresh(this.nativePtr, capabilities.canDeliverNotification());
    }

    private SharedRealm(long nativeSharedRealmPtr, OsRealmConfig osRealmConfig) {
        this.nativePtr = nativeSharedRealmPtr;
        this.osRealmConfig = osRealmConfig;
        this.schemaInfo = new OsSchemaInfo(nativeGetSchemaInfo(this.nativePtr), this);
        this.context = osRealmConfig.getContext();
        this.context.addReference(this);
        this.capabilities = new AndroidCapabilities();
        this.realmNotifier = null;
        nativeSetAutoRefresh(this.nativePtr, false);
    }

    public static SharedRealm getInstance(RealmConfiguration config) {
        return getInstance(new Builder(config));
    }

    public static SharedRealm getInstance(Builder configBuilder) {
        OsRealmConfig osRealmConfig = configBuilder.build();
        ObjectServerFacade.getSyncFacadeIfPossible().wrapObjectStoreSessionIfRequired(osRealmConfig.getRealmConfiguration());
        return new SharedRealm(osRealmConfig);
    }

    public void beginTransaction() {
        detachIterators();
        executePendingRowQueries();
        nativeBeginTransaction(this.nativePtr);
    }

    public void commitTransaction() {
        nativeCommitTransaction(this.nativePtr);
    }

    public void cancelTransaction() {
        nativeCancelTransaction(this.nativePtr);
    }

    public boolean isInTransaction() {
        return nativeIsInTransaction(this.nativePtr);
    }

    public void setSchemaVersion(long schemaVersion) {
        nativeSetVersion(this.nativePtr, schemaVersion);
    }

    public long getSchemaVersion() {
        return nativeGetVersion(this.nativePtr);
    }

    long getGroupNative() {
        return nativeReadGroup(this.nativePtr);
    }

    public boolean hasTable(String name) {
        return nativeHasTable(this.nativePtr, name);
    }

    public Table getTable(String name) {
        return new Table(this, nativeGetTable(this.nativePtr, name));
    }

    public Table createTable(String name) {
        return new Table(this, nativeCreateTable(this.nativePtr, name));
    }

    public void renameTable(String oldName, String newName) {
        nativeRenameTable(this.nativePtr, oldName, newName);
    }

    public void removeTable(String name) {
        nativeRemoveTable(this.nativePtr, name);
    }

    public String getTableName(int index) {
        return nativeGetTableName(this.nativePtr, index);
    }

    public long size() {
        return nativeSize(this.nativePtr);
    }

    public String getPath() {
        return this.osRealmConfig.getRealmConfiguration().getPath();
    }

    public boolean isEmpty() {
        return nativeIsEmpty(this.nativePtr);
    }

    public void refresh() {
        nativeRefresh(this.nativePtr);
    }

    public VersionID getVersionID() {
        long[] versionId = nativeGetVersionID(this.nativePtr);
        return new VersionID(versionId[0], versionId[1]);
    }

    public boolean isClosed() {
        return nativeIsClosed(this.nativePtr);
    }

    public void writeCopy(File file, @Nullable byte[] key) {
        if (file.isFile() && file.exists()) {
            throw new IllegalArgumentException("The destination file must not exist");
        }
        nativeWriteCopy(this.nativePtr, file.getAbsolutePath(), key);
    }

    public boolean waitForChange() {
        return nativeWaitForChange(this.nativePtr);
    }

    public void stopWaitForChange() {
        nativeStopWaitForChange(this.nativePtr);
    }

    public boolean compact() {
        return nativeCompact(this.nativePtr);
    }

    public void setAutoRefresh(boolean enabled) {
        this.capabilities.checkCanDeliverNotification(null);
        nativeSetAutoRefresh(this.nativePtr, enabled);
    }

    public boolean isAutoRefresh() {
        return nativeIsAutoRefresh(this.nativePtr);
    }

    public RealmConfiguration getConfiguration() {
        return this.osRealmConfig.getRealmConfiguration();
    }

    public void close() {
        if (this.realmNotifier != null) {
            this.realmNotifier.close();
        }
        synchronized (this.context) {
            nativeCloseSharedRealm(this.nativePtr);
        }
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public OsSchemaInfo getSchemaInfo() {
        return this.schemaInfo;
    }

    public void registerSchemaChangedCallback(SchemaChangedCallback callback) {
        nativeRegisterSchemaChangedCallback(this.nativePtr, callback);
    }

    void addIterator(Iterator iterator) {
        this.iterators.add(new WeakReference(iterator));
    }

    void detachIterators() {
        for (WeakReference<Iterator> iteratorRef : this.iterators) {
            Iterator iterator = (Iterator) iteratorRef.get();
            if (iterator != null) {
                iterator.detach();
            }
        }
        this.iterators.clear();
    }

    void invalidateIterators() {
        for (WeakReference<Iterator> iteratorRef : this.iterators) {
            Iterator iterator = (Iterator) iteratorRef.get();
            if (iterator != null) {
                iterator.invalidate();
            }
        }
        this.iterators.clear();
    }

    void addPendingRow(PendingRow pendingRow) {
        this.pendingRows.add(new WeakReference(pendingRow));
    }

    void removePendingRow(PendingRow pendingRow) {
        for (WeakReference<PendingRow> ref : this.pendingRows) {
            PendingRow row = (PendingRow) ref.get();
            if (row == null || row == pendingRow) {
                this.pendingRows.remove(ref);
            }
        }
    }

    private void executePendingRowQueries() {
        for (WeakReference<PendingRow> ref : this.pendingRows) {
            PendingRow row = (PendingRow) ref.get();
            if (row != null) {
                row.executeQuery();
            }
        }
        this.pendingRows.clear();
    }

    private static void runMigrationCallback(long nativeSharedRealmPtr, OsRealmConfig osRealmConfig, MigrationCallback callback, long oldVersion) {
        callback.onMigrationNeeded(new SharedRealm(nativeSharedRealmPtr, osRealmConfig), oldVersion, osRealmConfig.getRealmConfiguration().getSchemaVersion());
    }

    private static void runInitializationCallback(long nativeSharedRealmPtr, OsRealmConfig osRealmConfig, InitializationCallback callback) {
        callback.onInit(new SharedRealm(nativeSharedRealmPtr, osRealmConfig));
    }
}
