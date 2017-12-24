package io.realm;

import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException.Kind;
import io.realm.internal.Capabilities;
import io.realm.internal.ObjectServerFacade;
import io.realm.internal.RealmNotifier;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.Util;
import io.realm.internal.android.AndroidCapabilities;
import io.realm.internal.android.AndroidRealmNotifier;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.log.RealmLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

final class RealmCache {
    private static final String ASYNC_CALLBACK_NULL_MSG = "The callback cannot be null.";
    private static final String ASYNC_NOT_ALLOWED_MSG = "Realm instances cannot be loaded asynchronously on a non-looper thread.";
    private static final String DIFFERENT_KEY_MESSAGE = "Wrong key used to decrypt Realm.";
    private static final String WRONG_REALM_CLASS_MESSAGE = "The type of Realm class must be Realm or DynamicRealm.";
    private static final List<WeakReference<RealmCache>> cachesList = new LinkedList();
    private static final Collection<RealmCache> leakedCaches = new ConcurrentLinkedQueue();
    private RealmConfiguration configuration;
    private final AtomicBoolean isLeaked = new AtomicBoolean(false);
    private final String realmPath;
    private final EnumMap<RealmCacheType, RefAndCount> refAndCountMap;

    interface Callback0 {
        void onCall();
    }

    interface Callback {
        void onResult(int i);
    }

    private static class CreateRealmRunnable<T extends BaseRealm> implements Runnable {
        private final BaseRealm$InstanceCallback<T> callback;
        private final CountDownLatch canReleaseBackgroundInstanceLatch = new CountDownLatch(1);
        private final RealmConfiguration configuration;
        private Future future;
        private final RealmNotifier notifier;
        private final Class<T> realmClass;

        class C24341 implements Runnable {
            C24341() {
            }

            public void run() {
                if (CreateRealmRunnable.this.future == null || CreateRealmRunnable.this.future.isCancelled()) {
                    CreateRealmRunnable.this.canReleaseBackgroundInstanceLatch.countDown();
                    return;
                }
                T instanceToReturn = null;
                Throwable throwable = null;
                try {
                    instanceToReturn = RealmCache.createRealmOrGetFromCache(CreateRealmRunnable.this.configuration, CreateRealmRunnable.this.realmClass);
                } catch (Throwable e) {
                    throwable = e;
                } finally {
                    CreateRealmRunnable.this.canReleaseBackgroundInstanceLatch.countDown();
                }
                if (instanceToReturn != null) {
                    CreateRealmRunnable.this.callback.onSuccess(instanceToReturn);
                } else {
                    CreateRealmRunnable.this.callback.onError(throwable);
                }
            }
        }

        class C24352 implements Runnable {
            final /* synthetic */ Throwable val$e;

            C24352(Throwable th) {
                this.val$e = th;
            }

            public void run() {
                CreateRealmRunnable.this.callback.onError(this.val$e);
            }
        }

        public void run() {
            /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r7 = this;
            r1 = 0;
            r3 = r7.configuration;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = r7.realmClass;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r1 = io.realm.RealmCache.createRealmOrGetFromCache(r3, r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3 = r7.notifier;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = new io.realm.RealmCache$CreateRealmRunnable$1;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4.<init>();	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r2 = r3.post(r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            if (r2 != 0) goto L_0x001b;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x0016:
            r3 = r7.canReleaseBackgroundInstanceLatch;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3.countDown();	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x001b:
            r3 = r7.canReleaseBackgroundInstanceLatch;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = 2;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3 = r3.await(r4, r6);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            if (r3 != 0) goto L_0x002f;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x0027:
            r3 = "Timeout for creating Realm instance in foreground thread in `CreateRealmRunnable` ";	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = 0;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            io.realm.log.RealmLog.warn(r3, r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x002f:
            if (r1 == 0) goto L_0x0034;
        L_0x0031:
            r1.close();
        L_0x0034:
            return;
        L_0x0035:
            r0 = move-exception;
            r3 = "`CreateRealmRunnable` has been interrupted.";	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = 0;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            io.realm.log.RealmLog.warn(r0, r3, r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            if (r1 == 0) goto L_0x0034;
        L_0x0040:
            r1.close();
            goto L_0x0034;
        L_0x0044:
            r0 = move-exception;
            r3 = io.realm.internal.ObjectServerFacade.getSyncFacadeIfPossible();	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3 = r3.wasDownloadInterrupted(r0);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            if (r3 != 0) goto L_0x0061;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x004f:
            r3 = "`CreateRealmRunnable` failed.";	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = 0;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = new java.lang.Object[r4];	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            io.realm.log.RealmLog.error(r0, r3, r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3 = r7.notifier;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4 = new io.realm.RealmCache$CreateRealmRunnable$2;	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r4.<init>(r0);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
            r3.post(r4);	 Catch:{ InterruptedException -> 0x0035, Throwable -> 0x0044, all -> 0x0067 }
        L_0x0061:
            if (r1 == 0) goto L_0x0034;
        L_0x0063:
            r1.close();
            goto L_0x0034;
        L_0x0067:
            r3 = move-exception;
            if (r1 == 0) goto L_0x006d;
        L_0x006a:
            r1.close();
        L_0x006d:
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.realm.RealmCache.CreateRealmRunnable.run():void");
        }

        CreateRealmRunnable(RealmNotifier notifier, RealmConfiguration configuration, BaseRealm$InstanceCallback<T> callback, Class<T> realmClass) {
            this.configuration = configuration;
            this.realmClass = realmClass;
            this.callback = callback;
            this.notifier = notifier;
        }

        public void setFuture(Future future) {
            this.future = future;
        }
    }

    private enum RealmCacheType {
        TYPED_REALM,
        DYNAMIC_REALM;

        static RealmCacheType valueOf(Class<? extends BaseRealm> clazz) {
            if (clazz == Realm.class) {
                return TYPED_REALM;
            }
            if (clazz == DynamicRealm.class) {
                return DYNAMIC_REALM;
            }
            throw new IllegalArgumentException(RealmCache.WRONG_REALM_CLASS_MESSAGE);
        }
    }

    private static class RefAndCount {
        private int globalCount;
        private final ThreadLocal<Integer> localCount;
        private final ThreadLocal<BaseRealm> localRealm;

        private RefAndCount() {
            this.localRealm = new ThreadLocal();
            this.localCount = new ThreadLocal();
            this.globalCount = 0;
        }
    }

    private RealmCache(String path) {
        int i = 0;
        this.realmPath = path;
        this.refAndCountMap = new EnumMap(RealmCacheType.class);
        RealmCacheType[] values = RealmCacheType.values();
        int length = values.length;
        while (i < length) {
            this.refAndCountMap.put(values[i], new RefAndCount());
            i++;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static io.realm.RealmCache getCache(java.lang.String r7, boolean r8) {
        /*
        r1 = 0;
        r5 = cachesList;
        monitor-enter(r5);
        r4 = cachesList;	 Catch:{ all -> 0x0044 }
        r3 = r4.iterator();	 Catch:{ all -> 0x0044 }
        r2 = r1;
    L_0x000b:
        r4 = r3.hasNext();	 Catch:{ all -> 0x0047 }
        if (r4 == 0) goto L_0x002f;
    L_0x0011:
        r4 = r3.next();	 Catch:{ all -> 0x0047 }
        r4 = (java.lang.ref.WeakReference) r4;	 Catch:{ all -> 0x0047 }
        r0 = r4.get();	 Catch:{ all -> 0x0047 }
        r0 = (io.realm.RealmCache) r0;	 Catch:{ all -> 0x0047 }
        if (r0 != 0) goto L_0x0025;
    L_0x001f:
        r3.remove();	 Catch:{ all -> 0x0047 }
        r1 = r2;
    L_0x0023:
        r2 = r1;
        goto L_0x000b;
    L_0x0025:
        r4 = r0.realmPath;	 Catch:{ all -> 0x0047 }
        r4 = r4.equals(r7);	 Catch:{ all -> 0x0047 }
        if (r4 == 0) goto L_0x004c;
    L_0x002d:
        r1 = r0;
        goto L_0x0023;
    L_0x002f:
        if (r2 != 0) goto L_0x004a;
    L_0x0031:
        if (r8 == 0) goto L_0x004a;
    L_0x0033:
        r1 = new io.realm.RealmCache;	 Catch:{ all -> 0x0047 }
        r1.<init>(r7);	 Catch:{ all -> 0x0047 }
        r4 = cachesList;	 Catch:{ all -> 0x0044 }
        r6 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0044 }
        r6.<init>(r1);	 Catch:{ all -> 0x0044 }
        r4.add(r6);	 Catch:{ all -> 0x0044 }
    L_0x0042:
        monitor-exit(r5);	 Catch:{ all -> 0x0044 }
        return r1;
    L_0x0044:
        r4 = move-exception;
    L_0x0045:
        monitor-exit(r5);	 Catch:{ all -> 0x0044 }
        throw r4;
    L_0x0047:
        r4 = move-exception;
        r1 = r2;
        goto L_0x0045;
    L_0x004a:
        r1 = r2;
        goto L_0x0042;
    L_0x004c:
        r1 = r2;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.RealmCache.getCache(java.lang.String, boolean):io.realm.RealmCache");
    }

    static <T extends BaseRealm> RealmAsyncTask createRealmOrGetFromCacheAsync(RealmConfiguration configuration, BaseRealm$InstanceCallback<T> callback, Class<T> realmClass) {
        return getCache(configuration.getPath(), true).doCreateRealmOrGetFromCacheAsync(configuration, callback, realmClass);
    }

    private synchronized <T extends BaseRealm> RealmAsyncTask doCreateRealmOrGetFromCacheAsync(RealmConfiguration configuration, BaseRealm$InstanceCallback<T> callback, Class<T> realmClass) {
        Future<?> future;
        Capabilities capabilities = new AndroidCapabilities();
        capabilities.checkCanDeliverNotification(ASYNC_NOT_ALLOWED_MSG);
        if (callback == null) {
            throw new IllegalArgumentException(ASYNC_CALLBACK_NULL_MSG);
        }
        CreateRealmRunnable<T> createRealmRunnable = new CreateRealmRunnable(new AndroidRealmNotifier(null, capabilities), configuration, callback, realmClass);
        future = BaseRealm.asyncTaskExecutor.submitTransaction(createRealmRunnable);
        createRealmRunnable.setFuture(future);
        return new RealmAsyncTaskImpl(future, BaseRealm.asyncTaskExecutor);
    }

    static <E extends BaseRealm> E createRealmOrGetFromCache(RealmConfiguration configuration, Class<E> realmClass) {
        return getCache(configuration.getPath(), true).doCreateRealmOrGetFromCache(configuration, realmClass);
    }

    private synchronized <E extends BaseRealm> E doCreateRealmOrGetFromCache(RealmConfiguration configuration, Class<E> realmClass) {
        RefAndCount refAndCount;
        refAndCount = (RefAndCount) this.refAndCountMap.get(RealmCacheType.valueOf((Class) realmClass));
        if (getTotalGlobalRefCount() == 0) {
            copyAssetFileIfNeeded(configuration);
            boolean fileExists = configuration.realmExists();
            SharedRealm sharedRealm = null;
            try {
                if (configuration.isSyncConfiguration()) {
                    if (!fileExists) {
                        sharedRealm = SharedRealm.getInstance(configuration);
                        ObjectServerFacade.getSyncFacadeIfPossible().downloadRemoteChanges(configuration);
                    }
                } else if (fileExists) {
                    sharedRealm = SharedRealm.getInstance(configuration);
                    if (Table.primaryKeyTableNeedsMigration(sharedRealm)) {
                        sharedRealm.beginTransaction();
                        if (Table.migratePrimaryKeyTableIfNeeded(sharedRealm)) {
                            sharedRealm.commitTransaction();
                        } else {
                            sharedRealm.cancelTransaction();
                        }
                    }
                }
                if (sharedRealm != null) {
                    sharedRealm.close();
                }
                this.configuration = configuration;
            } catch (Throwable th) {
                if (sharedRealm != null) {
                    sharedRealm.close();
                }
            }
        } else {
            validateConfiguration(configuration);
        }
        if (refAndCount.localRealm.get() == null) {
            BaseRealm realm;
            if (realmClass == Realm.class) {
                realm = Realm.createInstance(this);
            } else if (realmClass == DynamicRealm.class) {
                realm = DynamicRealm.createInstance(this);
            } else {
                throw new IllegalArgumentException(WRONG_REALM_CLASS_MESSAGE);
            }
            refAndCount.localRealm.set(realm);
            refAndCount.localCount.set(Integer.valueOf(0));
            refAndCount.globalCount = refAndCount.globalCount + 1;
        }
        refAndCount.localCount.set(Integer.valueOf(((Integer) refAndCount.localCount.get()).intValue() + 1));
        return (BaseRealm) refAndCount.localRealm.get();
    }

    synchronized void release(BaseRealm realm) {
        String canonicalPath = realm.getPath();
        RefAndCount refAndCount = (RefAndCount) this.refAndCountMap.get(RealmCacheType.valueOf(realm.getClass()));
        Integer refCount = (Integer) refAndCount.localCount.get();
        if (refCount == null) {
            refCount = Integer.valueOf(0);
        }
        if (refCount.intValue() <= 0) {
            RealmLog.warn("%s has been closed already. refCount is %s", canonicalPath, refCount);
        } else {
            refCount = Integer.valueOf(refCount.intValue() - 1);
            if (refCount.intValue() == 0) {
                refAndCount.localCount.set(null);
                refAndCount.localRealm.set(null);
                refAndCount.globalCount = refAndCount.globalCount - 1;
                if (refAndCount.globalCount < 0) {
                    throw new IllegalStateException("Global reference counter of Realm" + canonicalPath + " got corrupted.");
                }
                realm.doClose();
                if (getTotalGlobalRefCount() == 0) {
                    this.configuration = null;
                    ObjectServerFacade.getFacade(realm.getConfiguration().isSyncConfiguration()).realmClosed(realm.getConfiguration());
                }
            } else {
                refAndCount.localCount.set(refCount);
            }
        }
    }

    private void validateConfiguration(RealmConfiguration newConfiguration) {
        if (!this.configuration.equals(newConfiguration)) {
            if (Arrays.equals(this.configuration.getEncryptionKey(), newConfiguration.getEncryptionKey())) {
                RealmMigration newMigration = newConfiguration.getMigration();
                RealmMigration oldMigration = this.configuration.getMigration();
                if (oldMigration == null || newMigration == null || !oldMigration.getClass().equals(newMigration.getClass()) || newMigration.equals(oldMigration)) {
                    throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. \nCached configuration: \n" + this.configuration + "\n\nNew configuration: \n" + newConfiguration);
                }
                throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. The most likely cause is that equals() and hashCode() are not overridden in the migration class: " + newConfiguration.getMigration().getClass().getCanonicalName());
            }
            throw new IllegalArgumentException(DIFFERENT_KEY_MESSAGE);
        }
    }

    static void invokeWithGlobalRefCount(RealmConfiguration configuration, Callback callback) {
        synchronized (cachesList) {
            RealmCache cache = getCache(configuration.getPath(), false);
            if (cache == null) {
                callback.onResult(0);
                return;
            }
            cache.doInvokeWithGlobalRefCount(callback);
        }
    }

    private synchronized void doInvokeWithGlobalRefCount(Callback callback) {
        callback.onResult(getTotalGlobalRefCount());
    }

    synchronized void invokeWithLock(Callback0 callback) {
        callback.onCall();
    }

    private static void copyAssetFileIfNeeded(RealmConfiguration configuration) {
        if (configuration.hasAssetFile()) {
            copyFileIfNeeded(configuration.getAssetFilePath(), new File(configuration.getRealmDirectory(), configuration.getRealmFileName()));
        }
        String syncServerCertificateAssetName = ObjectServerFacade.getFacade(configuration.isSyncConfiguration()).getSyncServerCertificateAssetName(configuration);
        if (!Util.isEmptyString(syncServerCertificateAssetName)) {
            copyFileIfNeeded(syncServerCertificateAssetName, new File(ObjectServerFacade.getFacade(configuration.isSyncConfiguration()).getSyncServerCertificateFilePath(configuration)));
        }
    }

    private static void copyFileIfNeeded(String assetFileName, File file) {
        IOException e;
        Throwable th;
        IOException exceptionWhenClose;
        if (!file.exists()) {
            Throwable exceptionWhenClose2 = null;
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            try {
                inputStream = BaseRealm.applicationContext.getAssets().open(assetFileName);
                if (inputStream == null) {
                    throw new RealmFileException(Kind.ACCESS_ERROR, "Invalid input stream to the asset file: " + assetFileName);
                }
                FileOutputStream outputStream2 = new FileOutputStream(file);
                try {
                    byte[] buf = new byte[4096];
                    while (true) {
                        int bytesRead = inputStream.read(buf);
                        if (bytesRead <= -1) {
                            break;
                        }
                        outputStream2.write(buf, 0, bytesRead);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e2) {
                            exceptionWhenClose2 = e2;
                        }
                    }
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (Throwable e22) {
                            if (exceptionWhenClose2 == null) {
                                exceptionWhenClose2 = e22;
                            }
                        }
                    }
                    if (exceptionWhenClose2 != null) {
                        throw new RealmFileException(Kind.ACCESS_ERROR, exceptionWhenClose2);
                    }
                } catch (IOException e3) {
                    e = e3;
                    outputStream = outputStream2;
                    try {
                        throw new RealmFileException(Kind.ACCESS_ERROR, "Could not resolve the path to the asset file: " + assetFileName, e);
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                exceptionWhenClose = e4;
                            }
                        }
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e42) {
                                if (exceptionWhenClose == null) {
                                    exceptionWhenClose = e42;
                                }
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = outputStream2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e42 = e5;
                throw new RealmFileException(Kind.ACCESS_ERROR, "Could not resolve the path to the asset file: " + assetFileName, e42);
            }
        }
    }

    static int getLocalThreadCount(RealmConfiguration configuration) {
        RealmCache cache = getCache(configuration.getPath(), false);
        if (cache == null) {
            return 0;
        }
        int totalRefCount = 0;
        for (RefAndCount refAndCount : cache.refAndCountMap.values()) {
            int intValue;
            Integer localCount = (Integer) refAndCount.localCount.get();
            if (localCount != null) {
                intValue = localCount.intValue();
            } else {
                intValue = 0;
            }
            totalRefCount += intValue;
        }
        return totalRefCount;
    }

    public RealmConfiguration getConfiguration() {
        return this.configuration;
    }

    private int getTotalGlobalRefCount() {
        int totalRefCount = 0;
        for (RefAndCount refAndCount : this.refAndCountMap.values()) {
            totalRefCount += refAndCount.globalCount;
        }
        return totalRefCount;
    }

    void leak() {
        if (!this.isLeaked.getAndSet(true)) {
            leakedCaches.add(this);
        }
    }
}
