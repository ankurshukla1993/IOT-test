package com.facebook.react.uimanager.debug;

import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "DebugComponentOwnershipModule")
public class DebugComponentOwnershipModule extends ReactContextBaseJavaModule {
    private int mNextRequestId = 0;
    @Nullable
    private RCTDebugComponentOwnership mRCTDebugComponentOwnership;
    private final SparseArray<OwnerHierarchyCallback> mRequestIdToCallback = new SparseArray();

    public DebugComponentOwnershipModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public void initialize() {
        super.initialize();
        this.mRCTDebugComponentOwnership = (RCTDebugComponentOwnership) getReactApplicationContext().getJSModule(RCTDebugComponentOwnership.class);
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mRCTDebugComponentOwnership = null;
    }

    @ReactMethod
    public synchronized void receiveOwnershipHierarchy(int requestId, int tag, @Nullable ReadableArray owners) {
        OwnerHierarchyCallback callback = (OwnerHierarchyCallback) this.mRequestIdToCallback.get(requestId);
        if (callback == null) {
            throw new JSApplicationCausedNativeException("Got receiveOwnershipHierarchy for invalid request id: " + requestId);
        }
        this.mRequestIdToCallback.delete(requestId);
        callback.onOwnerHierarchyLoaded(tag, owners);
    }

    public synchronized void loadComponentOwnerHierarchy(int tag, OwnerHierarchyCallback callback) {
        int requestId = this.mNextRequestId;
        this.mNextRequestId++;
        this.mRequestIdToCallback.put(requestId, callback);
        ((RCTDebugComponentOwnership) Assertions.assertNotNull(this.mRCTDebugComponentOwnership)).getOwnerHierarchy(requestId, tag);
    }

    public String getName() {
        return "DebugComponentOwnershipModule";
    }
}
