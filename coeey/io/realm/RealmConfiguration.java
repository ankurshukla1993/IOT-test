package io.realm;

import android.content.Context;
import io.realm.Realm.Transaction;
import io.realm.annotations.RealmModule;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException.Kind;
import io.realm.internal.OsRealmConfig.Durability;
import io.realm.internal.RealmCore;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Util;
import io.realm.internal.modules.CompositeMediator;
import io.realm.internal.modules.FilterableMediator;
import io.realm.rx.RealmObservableFactory;
import io.realm.rx.RxObservableFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;

public class RealmConfiguration {
    private static final Object DEFAULT_MODULE = Realm.getDefaultModule();
    protected static final RealmProxyMediator DEFAULT_MODULE_MEDIATOR;
    public static final String DEFAULT_REALM_NAME = "default.realm";
    public static final int KEY_LENGTH = 64;
    private static Boolean rxJavaAvailable;
    private final String assetFilePath;
    private final String canonicalPath;
    private final CompactOnLaunchCallback compactOnLaunch;
    private final boolean deleteRealmIfMigrationNeeded;
    private final Durability durability;
    private final Transaction initialDataTransaction;
    private final byte[] key;
    private final RealmMigration migration;
    private final boolean readOnly;
    private final File realmDirectory;
    private final String realmFileName;
    private final RxObservableFactory rxObservableFactory;
    private final RealmProxyMediator schemaMediator;
    private final long schemaVersion;

    public static class Builder {
        private String assetFilePath;
        private CompactOnLaunchCallback compactOnLaunch;
        private HashSet<Class<? extends RealmModel>> debugSchema;
        private boolean deleteRealmIfMigrationNeeded;
        private File directory;
        private Durability durability;
        private String fileName;
        private Transaction initialDataTransaction;
        private byte[] key;
        private RealmMigration migration;
        private HashSet<Object> modules;
        private boolean readOnly;
        private RxObservableFactory rxFactory;
        private long schemaVersion;

        public Builder() {
            this(BaseRealm.applicationContext);
        }

        Builder(Context context) {
            this.modules = new HashSet();
            this.debugSchema = new HashSet();
            if (context == null) {
                throw new IllegalStateException("Call `Realm.init(Context)` before creating a RealmConfiguration");
            }
            RealmCore.loadLibrary(context);
            initializeBuilder(context);
        }

        private void initializeBuilder(Context context) {
            this.directory = context.getFilesDir();
            this.fileName = "default.realm";
            this.key = null;
            this.schemaVersion = 0;
            this.migration = null;
            this.deleteRealmIfMigrationNeeded = false;
            this.durability = Durability.FULL;
            this.readOnly = false;
            this.compactOnLaunch = null;
            if (RealmConfiguration.DEFAULT_MODULE != null) {
                this.modules.add(RealmConfiguration.DEFAULT_MODULE);
            }
        }

        public Builder name(String filename) {
            if (filename == null || filename.isEmpty()) {
                throw new IllegalArgumentException("A non-empty filename must be provided");
            }
            this.fileName = filename;
            return this;
        }

        public Builder directory(File directory) {
            if (directory == null) {
                throw new IllegalArgumentException("Non-null 'dir' required.");
            } else if (directory.isFile()) {
                throw new IllegalArgumentException("'dir' is a file, not a directory: " + directory.getAbsolutePath() + ".");
            } else if (!directory.exists() && !directory.mkdirs()) {
                throw new IllegalArgumentException("Could not create the specified directory: " + directory.getAbsolutePath() + ".");
            } else if (directory.canWrite()) {
                this.directory = directory;
                return this;
            } else {
                throw new IllegalArgumentException("Realm directory is not writable: " + directory.getAbsolutePath() + ".");
            }
        }

        public Builder encryptionKey(byte[] key) {
            if (key == null) {
                throw new IllegalArgumentException("A non-null key must be provided");
            } else if (key.length != 64) {
                throw new IllegalArgumentException(String.format(Locale.US, "The provided key must be %s bytes. Yours was: %s", new Object[]{Integer.valueOf(64), Integer.valueOf(key.length)}));
            } else {
                this.key = Arrays.copyOf(key, key.length);
                return this;
            }
        }

        public Builder schemaVersion(long schemaVersion) {
            if (schemaVersion < 0) {
                throw new IllegalArgumentException("Realm schema version numbers must be 0 (zero) or higher. Yours was: " + schemaVersion);
            }
            this.schemaVersion = schemaVersion;
            return this;
        }

        public Builder migration(RealmMigration migration) {
            if (migration == null) {
                throw new IllegalArgumentException("A non-null migration must be provided");
            }
            this.migration = migration;
            return this;
        }

        public Builder deleteRealmIfMigrationNeeded() {
            if (this.assetFilePath == null || this.assetFilePath.length() == 0) {
                this.deleteRealmIfMigrationNeeded = true;
                return this;
            }
            throw new IllegalStateException("Realm cannot clear its schema when previously configured to use an asset file by calling assetFile().");
        }

        public Builder inMemory() {
            if (Util.isEmptyString(this.assetFilePath)) {
                this.durability = Durability.MEM_ONLY;
                return this;
            }
            throw new RealmException("Realm can not use in-memory configuration if asset file is present.");
        }

        public Builder modules(Object baseModule, Object... additionalModules) {
            this.modules.clear();
            addModule(baseModule);
            if (additionalModules != null) {
                for (Object module : additionalModules) {
                    addModule(module);
                }
            }
            return this;
        }

        public Builder rxFactory(RxObservableFactory factory) {
            this.rxFactory = factory;
            return this;
        }

        public Builder initialData(Transaction transaction) {
            this.initialDataTransaction = transaction;
            return this;
        }

        public Builder assetFile(String assetFile) {
            if (Util.isEmptyString(assetFile)) {
                throw new IllegalArgumentException("A non-empty asset file path must be provided");
            } else if (this.durability == Durability.MEM_ONLY) {
                throw new RealmException("Realm can not use in-memory configuration if asset file is present.");
            } else if (this.deleteRealmIfMigrationNeeded) {
                throw new IllegalStateException("Realm cannot use an asset file when previously configured to clear its schema in migration by calling deleteRealmIfMigrationNeeded().");
            } else {
                this.assetFilePath = assetFile;
                return this;
            }
        }

        public Builder readOnly() {
            this.readOnly = true;
            return this;
        }

        public Builder compactOnLaunch() {
            return compactOnLaunch(new DefaultCompactOnLaunchCallback());
        }

        public Builder compactOnLaunch(CompactOnLaunchCallback compactOnLaunch) {
            if (compactOnLaunch == null) {
                throw new IllegalArgumentException("A non-null compactOnLaunch must be provided");
            }
            this.compactOnLaunch = compactOnLaunch;
            return this;
        }

        private void addModule(Object module) {
            if (module != null) {
                checkModule(module);
                this.modules.add(module);
            }
        }

        @SafeVarargs
        final Builder schema(Class<? extends RealmModel> firstClass, Class<? extends RealmModel>... additionalClasses) {
            if (firstClass == null) {
                throw new IllegalArgumentException("A non-null class must be provided");
            }
            this.modules.clear();
            this.modules.add(RealmConfiguration.DEFAULT_MODULE_MEDIATOR);
            this.debugSchema.add(firstClass);
            if (additionalClasses != null) {
                Collections.addAll(this.debugSchema, additionalClasses);
            }
            return this;
        }

        public RealmConfiguration build() {
            if (this.readOnly) {
                if (this.initialDataTransaction != null) {
                    throw new IllegalStateException("This Realm is marked as read-only. Read-only Realms cannot use initialData(Realm.Transaction).");
                } else if (this.assetFilePath == null) {
                    throw new IllegalStateException("Only Realms provided using 'assetFile(path)' can be marked read-only. No such Realm was provided.");
                } else if (this.deleteRealmIfMigrationNeeded) {
                    throw new IllegalStateException("'deleteRealmIfMigrationNeeded()' and read-only Realms cannot be combined");
                } else if (this.compactOnLaunch != null) {
                    throw new IllegalStateException("'compactOnLaunch()' and read-only Realms cannot be combined");
                }
            }
            if (this.rxFactory == null && RealmConfiguration.isRxJavaAvailable()) {
                this.rxFactory = new RealmObservableFactory();
            }
            return new RealmConfiguration(this.directory, this.fileName, RealmConfiguration.getCanonicalPath(new File(this.directory, this.fileName)), this.assetFilePath, this.key, this.schemaVersion, this.migration, this.deleteRealmIfMigrationNeeded, this.durability, RealmConfiguration.createSchemaMediator(this.modules, this.debugSchema), this.rxFactory, this.initialDataTransaction, this.readOnly, this.compactOnLaunch);
        }

        private void checkModule(Object module) {
            if (!module.getClass().isAnnotationPresent(RealmModule.class)) {
                throw new IllegalArgumentException(module.getClass().getCanonicalName() + " is not a RealmModule. Add @RealmModule to the class definition.");
            }
        }
    }

    static {
        if (DEFAULT_MODULE != null) {
            RealmProxyMediator mediator = getModuleMediator(DEFAULT_MODULE.getClass().getCanonicalName());
            if (mediator.transformerApplied()) {
                DEFAULT_MODULE_MEDIATOR = mediator;
                return;
            }
            throw new ExceptionInInitializerError("RealmTransformer doesn't seem to be applied. Please update the project configuration to use the Realm Gradle plugin. See https://realm.io/news/android-installation-change/");
        }
        DEFAULT_MODULE_MEDIATOR = null;
    }

    protected RealmConfiguration(File realmDirectory, String realmFileName, String canonicalPath, @Nullable String assetFilePath, @Nullable byte[] key, long schemaVersion, @Nullable RealmMigration migration, boolean deleteRealmIfMigrationNeeded, Durability durability, RealmProxyMediator schemaMediator, @Nullable RxObservableFactory rxObservableFactory, @Nullable Transaction initialDataTransaction, boolean readOnly, @Nullable CompactOnLaunchCallback compactOnLaunch) {
        this.realmDirectory = realmDirectory;
        this.realmFileName = realmFileName;
        this.canonicalPath = canonicalPath;
        this.assetFilePath = assetFilePath;
        this.key = key;
        this.schemaVersion = schemaVersion;
        this.migration = migration;
        this.deleteRealmIfMigrationNeeded = deleteRealmIfMigrationNeeded;
        this.durability = durability;
        this.schemaMediator = schemaMediator;
        this.rxObservableFactory = rxObservableFactory;
        this.initialDataTransaction = initialDataTransaction;
        this.readOnly = readOnly;
        this.compactOnLaunch = compactOnLaunch;
    }

    public File getRealmDirectory() {
        return this.realmDirectory;
    }

    public String getRealmFileName() {
        return this.realmFileName;
    }

    public byte[] getEncryptionKey() {
        return this.key == null ? null : Arrays.copyOf(this.key, this.key.length);
    }

    public long getSchemaVersion() {
        return this.schemaVersion;
    }

    public RealmMigration getMigration() {
        return this.migration;
    }

    public boolean shouldDeleteRealmIfMigrationNeeded() {
        return this.deleteRealmIfMigrationNeeded;
    }

    public Durability getDurability() {
        return this.durability;
    }

    protected RealmProxyMediator getSchemaMediator() {
        return this.schemaMediator;
    }

    Transaction getInitialDataTransaction() {
        return this.initialDataTransaction;
    }

    boolean hasAssetFile() {
        return !Util.isEmptyString(this.assetFilePath);
    }

    String getAssetFilePath() {
        return this.assetFilePath;
    }

    public CompactOnLaunchCallback getCompactOnLaunchCallback() {
        return this.compactOnLaunch;
    }

    public Set<Class<? extends RealmModel>> getRealmObjectClasses() {
        return this.schemaMediator.getModelClasses();
    }

    public String getPath() {
        return this.canonicalPath;
    }

    boolean realmExists() {
        return new File(this.canonicalPath).exists();
    }

    public RxObservableFactory getRxFactory() {
        if (this.rxObservableFactory != null) {
            return this.rxObservableFactory;
        }
        throw new UnsupportedOperationException("RxJava seems to be missing from the classpath. Remember to add it as a compile dependency. See https://realm.io/docs/java/latest/#rxjava for more details.");
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RealmConfiguration that = (RealmConfiguration) obj;
        if (this.schemaVersion != that.schemaVersion || this.deleteRealmIfMigrationNeeded != that.deleteRealmIfMigrationNeeded || !this.realmDirectory.equals(that.realmDirectory) || !this.realmFileName.equals(that.realmFileName) || !this.canonicalPath.equals(that.canonicalPath) || !Arrays.equals(this.key, that.key) || !this.durability.equals(that.durability)) {
            return false;
        }
        if (this.migration != null) {
            if (!this.migration.equals(that.migration)) {
                return false;
            }
        } else if (that.migration != null) {
            return false;
        }
        if (this.rxObservableFactory != null) {
            if (!this.rxObservableFactory.equals(that.rxObservableFactory)) {
                return false;
            }
        } else if (that.rxObservableFactory != null) {
            return false;
        }
        if (this.initialDataTransaction != null) {
            if (!this.initialDataTransaction.equals(that.initialDataTransaction)) {
                return false;
            }
        } else if (that.initialDataTransaction != null) {
            return false;
        }
        if (this.readOnly != that.readOnly) {
            return false;
        }
        if (this.compactOnLaunch != null) {
            if (!this.compactOnLaunch.equals(that.compactOnLaunch)) {
                return false;
            }
        } else if (that.compactOnLaunch != null) {
            return false;
        }
        return this.schemaMediator.equals(that.schemaMediator);
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int i2 = 0;
        int hashCode2 = ((((((((this.realmDirectory.hashCode() * 31) + this.realmFileName.hashCode()) * 31) + this.canonicalPath.hashCode()) * 31) + (this.key != null ? Arrays.hashCode(this.key) : 0)) * 31) + ((int) this.schemaVersion)) * 31;
        if (this.migration != null) {
            hashCode = this.migration.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.deleteRealmIfMigrationNeeded) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (((((hashCode2 + hashCode) * 31) + this.schemaMediator.hashCode()) * 31) + this.durability.hashCode()) * 31;
        if (this.rxObservableFactory != null) {
            hashCode = this.rxObservableFactory.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.initialDataTransaction != null) {
            hashCode = this.initialDataTransaction.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode2 + hashCode) * 31;
        if (!this.readOnly) {
            i = 0;
        }
        hashCode = (hashCode + i) * 31;
        if (this.compactOnLaunch != null) {
            i2 = this.compactOnLaunch.hashCode();
        }
        return hashCode + i2;
    }

    protected static RealmProxyMediator createSchemaMediator(Set<Object> modules, Set<Class<? extends RealmModel>> debugSchema) {
        if (debugSchema.size() > 0) {
            return new FilterableMediator(DEFAULT_MODULE_MEDIATOR, debugSchema);
        }
        if (modules.size() == 1) {
            return getModuleMediator(modules.iterator().next().getClass().getCanonicalName());
        }
        RealmProxyMediator[] mediators = new RealmProxyMediator[modules.size()];
        int i = 0;
        for (Object module : modules) {
            mediators[i] = getModuleMediator(module.getClass().getCanonicalName());
            i++;
        }
        return new CompositeMediator(mediators);
    }

    private static RealmProxyMediator getModuleMediator(String fullyQualifiedModuleClassName) {
        String[] moduleNameParts = fullyQualifiedModuleClassName.split("\\.");
        String moduleSimpleName = moduleNameParts[moduleNameParts.length - 1];
        String mediatorName = String.format(Locale.US, "io.realm.%s%s", new Object[]{moduleSimpleName, "Mediator"});
        try {
            Constructor<?> constructor = Class.forName(mediatorName).getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return (RealmProxyMediator) constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            throw new RealmException("Could not find " + mediatorName, e);
        } catch (InvocationTargetException e2) {
            throw new RealmException("Could not create an instance of " + mediatorName, e2);
        } catch (InstantiationException e3) {
            throw new RealmException("Could not create an instance of " + mediatorName, e3);
        } catch (IllegalAccessException e4) {
            throw new RealmException("Could not create an instance of " + mediatorName, e4);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("realmDirectory: ").append(this.realmDirectory.toString());
        stringBuilder.append("\n");
        stringBuilder.append("realmFileName : ").append(this.realmFileName);
        stringBuilder.append("\n");
        stringBuilder.append("canonicalPath: ").append(this.canonicalPath);
        stringBuilder.append("\n");
        stringBuilder.append("key: ").append("[length: ").append(this.key == null ? 0 : 64).append("]");
        stringBuilder.append("\n");
        stringBuilder.append("schemaVersion: ").append(Long.toString(this.schemaVersion));
        stringBuilder.append("\n");
        stringBuilder.append("migration: ").append(this.migration);
        stringBuilder.append("\n");
        stringBuilder.append("deleteRealmIfMigrationNeeded: ").append(this.deleteRealmIfMigrationNeeded);
        stringBuilder.append("\n");
        stringBuilder.append("durability: ").append(this.durability);
        stringBuilder.append("\n");
        stringBuilder.append("schemaMediator: ").append(this.schemaMediator);
        stringBuilder.append("\n");
        stringBuilder.append("readOnly: ").append(this.readOnly);
        stringBuilder.append("\n");
        stringBuilder.append("compactOnLaunch: ").append(this.compactOnLaunch);
        return stringBuilder.toString();
    }

    static synchronized boolean isRxJavaAvailable() {
        boolean booleanValue;
        synchronized (RealmConfiguration.class) {
            if (rxJavaAvailable == null) {
                try {
                    Class.forName("rx.Observable");
                    rxJavaAvailable = Boolean.valueOf(true);
                } catch (ClassNotFoundException e) {
                    rxJavaAvailable = Boolean.valueOf(false);
                }
            }
            booleanValue = rxJavaAvailable.booleanValue();
        }
        return booleanValue;
    }

    protected static String getCanonicalPath(File realmFile) {
        try {
            return realmFile.getCanonicalPath();
        } catch (IOException e) {
            throw new RealmFileException(Kind.ACCESS_ERROR, "Could not resolve the canonical path to the Realm file: " + realmFile.getAbsolutePath(), e);
        }
    }

    boolean isSyncConfiguration() {
        return false;
    }
}
