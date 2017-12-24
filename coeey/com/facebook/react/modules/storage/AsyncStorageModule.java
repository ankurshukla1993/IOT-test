package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner.Cleanable;

@ReactModule(name = "AsyncSQLiteDBStorage")
public final class AsyncStorageModule extends ReactContextBaseJavaModule implements Cleanable {
    private static final int MAX_SQL_KEYS = 999;
    protected static final String NAME = "AsyncSQLiteDBStorage";
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown = false;

    public AsyncStorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance(reactContext);
    }

    public String getName() {
        return NAME;
    }

    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.clearAndCloseDatabase();
    }

    @ReactMethod
    public void multiGet(ReadableArray keys, Callback callback) {
        if (keys == null) {
            callback.invoke(new Object[]{AsyncStorageErrorUtil.getInvalidKeyError(null), null});
            return;
        }
        new 1(this, getReactApplicationContext(), callback, keys).execute(new Void[0]);
    }

    @ReactMethod
    public void multiSet(ReadableArray keyValueArray, Callback callback) {
        if (keyValueArray.size() == 0) {
            callback.invoke(new Object[]{AsyncStorageErrorUtil.getInvalidKeyError(null)});
            return;
        }
        new 2(this, getReactApplicationContext(), callback, keyValueArray).execute(new Void[0]);
    }

    @ReactMethod
    public void multiRemove(ReadableArray keys, Callback callback) {
        if (keys.size() == 0) {
            callback.invoke(new Object[]{AsyncStorageErrorUtil.getInvalidKeyError(null)});
            return;
        }
        new 3(this, getReactApplicationContext(), callback, keys).execute(new Void[0]);
    }

    @ReactMethod
    public void multiMerge(ReadableArray keyValueArray, Callback callback) {
        new 4(this, getReactApplicationContext(), callback, keyValueArray).execute(new Void[0]);
    }

    @ReactMethod
    public void clear(Callback callback) {
        new 5(this, getReactApplicationContext(), callback).execute(new Void[0]);
    }

    @ReactMethod
    public void getAllKeys(Callback callback) {
        new 6(this, getReactApplicationContext(), callback).execute(new Void[0]);
    }

    private boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }
}
