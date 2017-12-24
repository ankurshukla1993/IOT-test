package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.ArrayList;
import javax.annotation.Nullable;

@ReactModule(name = "NativeAnimatedModule")
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener {
    @Nullable
    private GuardedChoreographerFrameCallback mAnimatedFrameCallback;
    private ArrayList<UIThreadOperation> mOperations = new ArrayList();
    private final Object mOperationsCopyLock = new Object();
    @Nullable
    private ReactChoreographer mReactChoreographer;
    @Nullable
    private volatile ArrayList<UIThreadOperation> mReadyOperations = null;

    public NativeAnimatedModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        ReactApplicationContext reactCtx = getReactApplicationContext();
        this.mAnimatedFrameCallback = new 1(this, reactCtx, new NativeAnimatedNodesManager((UIManagerModule) reactCtx.getNativeModule(UIManagerModule.class)));
        reactCtx.addLifecycleEventListener(this);
    }

    public void onBatchComplete() {
        ArrayList<UIThreadOperation> operations = this.mOperations.isEmpty() ? null : this.mOperations;
        if (operations != null) {
            this.mOperations = new ArrayList();
            synchronized (this.mOperationsCopyLock) {
                if (this.mReadyOperations == null) {
                    this.mReadyOperations = operations;
                } else {
                    this.mReadyOperations.addAll(operations);
                }
            }
        }
    }

    public void onHostResume() {
        enqueueFrameCallback();
    }

    public void onHostPause() {
        clearFrameCallback();
    }

    public void onHostDestroy() {
    }

    public String getName() {
        return "NativeAnimatedModule";
    }

    private void clearFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    @ReactMethod
    public void createAnimatedNode(int tag, ReadableMap config) {
        this.mOperations.add(new 2(this, tag, config));
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(int tag) {
        this.mOperations.add(new 4(this, tag, new 3(this, tag)));
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(int tag) {
        this.mOperations.add(new 5(this, tag));
    }

    @ReactMethod
    public void dropAnimatedNode(int tag) {
        this.mOperations.add(new 6(this, tag));
    }

    @ReactMethod
    public void setAnimatedNodeValue(int tag, double value) {
        this.mOperations.add(new 7(this, tag, value));
    }

    @ReactMethod
    public void setAnimatedNodeOffset(int tag, double value) {
        this.mOperations.add(new 8(this, tag, value));
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(int tag) {
        this.mOperations.add(new 9(this, tag));
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(int tag) {
        this.mOperations.add(new 10(this, tag));
    }

    @ReactMethod
    public void startAnimatingNode(int animationId, int animatedNodeTag, ReadableMap animationConfig, Callback endCallback) {
        this.mOperations.add(new 11(this, animationId, animatedNodeTag, animationConfig, endCallback));
    }

    @ReactMethod
    public void stopAnimation(int animationId) {
        this.mOperations.add(new 12(this, animationId));
    }

    @ReactMethod
    public void connectAnimatedNodes(int parentNodeTag, int childNodeTag) {
        this.mOperations.add(new 13(this, parentNodeTag, childNodeTag));
    }

    @ReactMethod
    public void disconnectAnimatedNodes(int parentNodeTag, int childNodeTag) {
        this.mOperations.add(new 14(this, parentNodeTag, childNodeTag));
    }

    @ReactMethod
    public void connectAnimatedNodeToView(int animatedNodeTag, int viewTag) {
        this.mOperations.add(new 15(this, animatedNodeTag, viewTag));
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(int animatedNodeTag, int viewTag) {
        this.mOperations.add(new 16(this, animatedNodeTag, viewTag));
    }

    @ReactMethod
    public void addAnimatedEventToView(int viewTag, String eventName, ReadableMap eventMapping) {
        this.mOperations.add(new 17(this, viewTag, eventName, eventMapping));
    }

    @ReactMethod
    public void removeAnimatedEventFromView(int viewTag, String eventName) {
        this.mOperations.add(new 18(this, viewTag, eventName));
    }
}
