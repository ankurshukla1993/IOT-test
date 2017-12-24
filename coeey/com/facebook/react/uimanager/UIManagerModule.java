package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModuleLogger;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.PerformanceCounter;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "UIManager")
public class UIManagerModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener, PerformanceCounter, NativeModuleLogger {
    private static final boolean DEBUG = false;
    protected static final String NAME = "UIManager";
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private int mBatchId = 0;
    private final EventDispatcher mEventDispatcher;
    private final MemoryTrimCallback mMemoryTrimCallback = new MemoryTrimCallback(this, null);
    private final Map<String, Object> mModuleConstants;
    private int mNextRootViewTag = 1;
    private final UIImplementation mUIImplementation;

    public UIManagerModule(ReactApplicationContext reactContext, List<ViewManager> viewManagerList, UIImplementationProvider uiImplementationProvider, boolean lazyViewManagersEnabled) {
        super(reactContext);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactContext);
        this.mEventDispatcher = new EventDispatcher(reactContext);
        this.mModuleConstants = createConstants(viewManagerList, lazyViewManagersEnabled);
        this.mUIImplementation = uiImplementationProvider.createUIImplementation(reactContext, viewManagerList, this.mEventDispatcher);
        reactContext.addLifecycleEventListener(this);
    }

    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
    }

    public void onHostResume() {
        this.mUIImplementation.onHostResume();
    }

    public void onHostPause() {
        this.mUIImplementation.onHostPause();
    }

    public void onHostDestroy() {
        this.mUIImplementation.onHostDestroy();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.onCatalystInstanceDestroyed();
        getReactApplicationContext().unregisterComponentCallbacks(this.mMemoryTrimCallback);
        YogaNodePool.get().clear();
    }

    private static Map<String, Object> createConstants(List<ViewManager> viewManagerList, boolean lazyViewManagersEnabled) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        Systrace.beginSection(0, "CreateUIManagerConstants");
        try {
            Map<String, Object> createConstants = UIManagerModuleConstantsHelper.createConstants(viewManagerList, lazyViewManagersEnabled);
            return createConstants;
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    public Map<String, Double> getPerformanceCounters() {
        Map<String, Double> perfMap = new HashMap();
        perfMap.put("LayoutCount", Double.valueOf(this.mUIImplementation.getLayoutCount()));
        perfMap.put("LayoutTimer", Double.valueOf(this.mUIImplementation.getLayoutTimer()));
        return perfMap;
    }

    public int addMeasuredRootView(SizeMonitoringFrameLayout rootView) {
        int width;
        int height;
        int tag = this.mNextRootViewTag;
        this.mNextRootViewTag += 10;
        if (rootView.getLayoutParams() == null || rootView.getLayoutParams().width <= 0 || rootView.getLayoutParams().height <= 0) {
            width = rootView.getWidth();
            height = rootView.getHeight();
        } else {
            width = rootView.getLayoutParams().width;
            height = rootView.getLayoutParams().height;
        }
        this.mUIImplementation.registerRootView(rootView, tag, width, height, new ThemedReactContext(getReactApplicationContext(), rootView.getContext()));
        rootView.setOnSizeChangedListener(new 1(this, tag));
        return tag;
    }

    @ReactMethod
    public void removeRootView(int rootViewTag) {
        this.mUIImplementation.removeRootView(rootViewTag);
    }

    public void updateNodeSize(int nodeViewTag, int newWidth, int newHeight) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.updateNodeSize(nodeViewTag, newWidth, newHeight);
    }

    @ReactMethod
    public void createView(int tag, String className, int rootViewTag, ReadableMap props) {
        this.mUIImplementation.createView(tag, className, rootViewTag, props);
    }

    @ReactMethod
    public void updateView(int tag, String className, ReadableMap props) {
        this.mUIImplementation.updateView(tag, className, props);
    }

    @ReactMethod
    public void manageChildren(int viewTag, @Nullable ReadableArray moveFrom, @Nullable ReadableArray moveTo, @Nullable ReadableArray addChildTags, @Nullable ReadableArray addAtIndices, @Nullable ReadableArray removeFrom) {
        this.mUIImplementation.manageChildren(viewTag, moveFrom, moveTo, addChildTags, addAtIndices, removeFrom);
    }

    @ReactMethod
    public void setChildren(int viewTag, ReadableArray childrenTags) {
        this.mUIImplementation.setChildren(viewTag, childrenTags);
    }

    @ReactMethod
    public void replaceExistingNonRootView(int oldTag, int newTag) {
        this.mUIImplementation.replaceExistingNonRootView(oldTag, newTag);
    }

    @ReactMethod
    public void removeSubviewsFromContainerWithID(int containerTag) {
        this.mUIImplementation.removeSubviewsFromContainerWithID(containerTag);
    }

    @ReactMethod
    public void measure(int reactTag, Callback callback) {
        this.mUIImplementation.measure(reactTag, callback);
    }

    @ReactMethod
    public void measureInWindow(int reactTag, Callback callback) {
        this.mUIImplementation.measureInWindow(reactTag, callback);
    }

    @ReactMethod
    public void measureLayout(int tag, int ancestorTag, Callback errorCallback, Callback successCallback) {
        this.mUIImplementation.measureLayout(tag, ancestorTag, errorCallback, successCallback);
    }

    @ReactMethod
    public void measureLayoutRelativeToParent(int tag, Callback errorCallback, Callback successCallback) {
        this.mUIImplementation.measureLayoutRelativeToParent(tag, errorCallback, successCallback);
    }

    @ReactMethod
    public void findSubviewIn(int reactTag, ReadableArray point, Callback callback) {
        this.mUIImplementation.findSubviewIn(reactTag, (float) Math.round(PixelUtil.toPixelFromDIP(point.getDouble(0))), (float) Math.round(PixelUtil.toPixelFromDIP(point.getDouble(1))), callback);
    }

    public void registerAnimation(Animation animation) {
        this.mUIImplementation.registerAnimation(animation);
    }

    public void addAnimation(int reactTag, int animationID, Callback onSuccess) {
        this.mUIImplementation.addAnimation(reactTag, animationID, onSuccess);
    }

    public void removeAnimation(int reactTag, int animationID) {
        this.mUIImplementation.removeAnimation(reactTag, animationID);
    }

    @ReactMethod
    public void setJSResponder(int reactTag, boolean blockNativeResponder) {
        this.mUIImplementation.setJSResponder(reactTag, blockNativeResponder);
    }

    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.clearJSResponder();
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int reactTag, int commandId, ReadableArray commandArgs) {
        this.mUIImplementation.dispatchViewManagerCommand(reactTag, commandId, commandArgs);
    }

    @ReactMethod
    public void showPopupMenu(int reactTag, ReadableArray items, Callback error, Callback success) {
        this.mUIImplementation.showPopupMenu(reactTag, items, error, success);
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean enabled) {
        this.mUIImplementation.setLayoutAnimationEnabledExperimental(enabled);
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap config, Callback success, Callback error) {
        this.mUIImplementation.configureNextLayoutAnimation(config, success, error);
    }

    public void onBatchComplete() {
        int batchId = this.mBatchId;
        this.mBatchId++;
        SystraceMessage.beginSection(0, "onBatchCompleteUI").arg("BatchId", batchId).flush();
        try {
            this.mUIImplementation.dispatchViewUpdates(batchId);
        } finally {
            Systrace.endSection(0);
        }
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener listener) {
        this.mUIImplementation.setViewHierarchyUpdateDebugListener(listener);
    }

    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @ReactMethod
    public void sendAccessibilityEvent(int tag, int eventType) {
        this.mUIImplementation.sendAccessibilityEvent(tag, eventType);
    }

    public void addUIBlock(UIBlock block) {
        this.mUIImplementation.addUIBlock(block);
    }

    public int resolveRootTagFromReactTag(int reactTag) {
        return this.mUIImplementation.resolveRootTagFromReactTag(reactTag);
    }

    public void startConstantsMapConversion() {
        ReactMarker.logMarker(ReactMarkerConstants.UI_MANAGER_MODULE_CONSTANTS_CONVERT_START);
    }

    public void endConstantsMapConversion() {
        ReactMarker.logMarker(ReactMarkerConstants.UI_MANAGER_MODULE_CONSTANTS_CONVERT_END);
    }
}
