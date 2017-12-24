package io.realm.internal;

import io.realm.CompactOnLaunchCallback;
import io.realm.RealmConfiguration;
import io.realm.internal.SharedRealm.InitializationCallback;
import io.realm.internal.SharedRealm.MigrationCallback;
import javax.annotation.Nullable;

public class OsRealmConfig implements NativeObject {
    private static final byte SCHEMA_MODE_VALUE_ADDITIVE = (byte) 4;
    private static final byte SCHEMA_MODE_VALUE_AUTOMATIC = (byte) 0;
    private static final byte SCHEMA_MODE_VALUE_IMMUTABLE = (byte) 1;
    private static final byte SCHEMA_MODE_VALUE_MANUAL = (byte) 5;
    private static final byte SCHEMA_MODE_VALUE_READONLY = (byte) 2;
    private static final byte SCHEMA_MODE_VALUE_RESET_FILE = (byte) 3;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final CompactOnLaunchCallback compactOnLaunchCallback;
    private final NativeContext context;
    private final InitializationCallback initializationCallback;
    private final MigrationCallback migrationCallback;
    private final long nativePtr;
    private final RealmConfiguration realmConfiguration;

    public static class Builder {
        private boolean autoUpdateNotification = false;
        private RealmConfiguration configuration;
        private InitializationCallback initializationCallback = null;
        private MigrationCallback migrationCallback = null;
        private OsSchemaInfo schemaInfo = null;

        public Builder(RealmConfiguration configuration) {
            this.configuration = configuration;
        }

        public Builder schemaInfo(@Nullable OsSchemaInfo schemaInfo) {
            this.schemaInfo = schemaInfo;
            return this;
        }

        public Builder migrationCallback(@Nullable MigrationCallback migrationCallback) {
            this.migrationCallback = migrationCallback;
            return this;
        }

        public Builder initializationCallback(@Nullable InitializationCallback initializationCallback) {
            this.initializationCallback = initializationCallback;
            return this;
        }

        public Builder autoUpdateNotification(boolean autoUpdateNotification) {
            this.autoUpdateNotification = autoUpdateNotification;
            return this;
        }

        OsRealmConfig build() {
            return new OsRealmConfig(this.configuration, this.autoUpdateNotification, this.schemaInfo, this.migrationCallback, this.initializationCallback);
        }
    }

    public enum Durability {
        FULL(0),
        MEM_ONLY(1);
        
        final int value;

        private Durability(int value) {
            this.value = value;
        }
    }

    public enum SchemaMode {
        SCHEMA_MODE_AUTOMATIC((byte) 0),
        SCHEMA_MODE_IMMUTABLE((byte) 1),
        SCHEMA_MODE_READONLY((byte) 2),
        SCHEMA_MODE_RESET_FILE((byte) 3),
        SCHEMA_MODE_ADDITIVE((byte) 4),
        SCHEMA_MODE_MANUAL((byte) 5);
        
        final byte value;

        private SchemaMode(byte value) {
            this.value = value;
        }

        public byte getNativeValue() {
            return this.value;
        }
    }

    private static native long nativeCreate(String str, boolean z, boolean z2);

    private static native void nativeCreateAndSetSyncConfig(long j, String str, String str2, String str3, String str4);

    private static native void nativeEnableChangeNotification(long j, boolean z);

    private static native long nativeGetFinalizerPtr();

    private static native void nativeSetCompactOnLaunchCallback(long j, CompactOnLaunchCallback compactOnLaunchCallback);

    private static native void nativeSetEncryptionKey(long j, byte[] bArr);

    private static native void nativeSetInMemory(long j, boolean z);

    private native void nativeSetInitializationCallback(long j, InitializationCallback initializationCallback);

    private native void nativeSetSchemaConfig(long j, byte b, long j2, long j3, @Nullable MigrationCallback migrationCallback);

    private static native void nativeSetSyncConfigSslSettings(long j, boolean z, String str);

    private OsRealmConfig(RealmConfiguration config, boolean autoUpdateNotification, @Nullable OsSchemaInfo schemaInfo, @Nullable MigrationCallback migrationCallback, @Nullable InitializationCallback initializationCallback) {
        this.context = new NativeContext();
        this.realmConfiguration = config;
        this.nativePtr = nativeCreate(config.getPath(), false, true);
        NativeContext.dummyContext.addReference(this);
        Object[] syncUserConf = ObjectServerFacade.getSyncFacadeIfPossible().getUserAndServerUrl(this.realmConfiguration);
        String syncUserIdentifier = syncUserConf[0];
        String syncRealmUrl = syncUserConf[1];
        String syncRealmAuthUrl = syncUserConf[2];
        String syncRefreshToken = syncUserConf[3];
        boolean syncClientValidateSsl = Boolean.TRUE.equals(syncUserConf[4]);
        String syncSslTrustCertificatePath = syncUserConf[5];
        byte[] key = config.getEncryptionKey();
        if (key != null) {
            nativeSetEncryptionKey(this.nativePtr, key);
        }
        nativeSetInMemory(this.nativePtr, config.getDurability() == Durability.MEM_ONLY);
        nativeEnableChangeNotification(this.nativePtr, autoUpdateNotification);
        SchemaMode schemaMode = SchemaMode.SCHEMA_MODE_MANUAL;
        if (config.isReadOnly()) {
            schemaMode = SchemaMode.SCHEMA_MODE_READONLY;
        } else if (syncRealmUrl != null) {
            schemaMode = SchemaMode.SCHEMA_MODE_ADDITIVE;
        } else if (config.shouldDeleteRealmIfMigrationNeeded()) {
            schemaMode = SchemaMode.SCHEMA_MODE_RESET_FILE;
        }
        long schemaVersion = config.getSchemaVersion();
        long nativeSchemaPtr = schemaInfo == null ? 0 : schemaInfo.getNativePtr();
        this.migrationCallback = migrationCallback;
        nativeSetSchemaConfig(this.nativePtr, schemaMode.getNativeValue(), schemaVersion, nativeSchemaPtr, migrationCallback);
        this.compactOnLaunchCallback = config.getCompactOnLaunchCallback();
        if (this.compactOnLaunchCallback != null) {
            nativeSetCompactOnLaunchCallback(this.nativePtr, this.compactOnLaunchCallback);
        }
        this.initializationCallback = initializationCallback;
        if (initializationCallback != null) {
            nativeSetInitializationCallback(this.nativePtr, initializationCallback);
        }
        if (syncRealmUrl != null) {
            nativeCreateAndSetSyncConfig(this.nativePtr, syncRealmUrl, syncRealmAuthUrl, syncUserIdentifier, syncRefreshToken);
            nativeSetSyncConfigSslSettings(this.nativePtr, syncClientValidateSsl, syncSslTrustCertificatePath);
        }
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public RealmConfiguration getRealmConfiguration() {
        return this.realmConfiguration;
    }

    NativeContext getContext() {
        return this.context;
    }
}
