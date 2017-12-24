package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.yoga.YogaDirection;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public class UIImplementation {
    protected final EventDispatcher mEventDispatcher;
    private double mLayoutCount;
    private double mLayoutTimer;
    private final int[] mMeasureBuffer;
    private final NativeViewHierarchyOptimizer mNativeViewHierarchyOptimizer;
    private final UIViewOperationQueue mOperationsQueue;
    private final ReactApplicationContext mReactContext;
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final ViewManagerRegistry mViewManagers;

    public UIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext, new ViewManagerRegistry(viewManagers), eventDispatcher);
    }

    private UIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext, viewManagers, new UIViewOperationQueue(reactContext, new NativeViewHierarchyManager(viewManagers)), eventDispatcher);
    }

    protected UIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, UIViewOperationQueue operationsQueue, EventDispatcher eventDispatcher) {
        this.mShadowNodeRegistry = new ShadowNodeRegistry();
        this.mMeasureBuffer = new int[4];
        this.mLayoutCount = 0.0d;
        this.mLayoutTimer = 0.0d;
        this.mReactContext = reactContext;
        this.mViewManagers = viewManagers;
        this.mOperationsQueue = operationsQueue;
        this.mNativeViewHierarchyOptimizer = new NativeViewHierarchyOptimizer(this.mOperationsQueue, this.mShadowNodeRegistry);
        this.mEventDispatcher = eventDispatcher;
    }

    protected ReactShadowNode createRootShadowNode() {
        ReactShadowNode rootCSSNode = new ReactShadowNode();
        if (I18nUtil.getInstance().isRTL(this.mReactContext)) {
            rootCSSNode.setLayoutDirection(YogaDirection.RTL);
        }
        rootCSSNode.setViewClassName("Root");
        return rootCSSNode;
    }

    protected ReactShadowNode createShadowNode(String className) {
        return this.mViewManagers.get(className).createShadowNodeInstance();
    }

    protected final ReactShadowNode resolveShadowNode(int reactTag) {
        return this.mShadowNodeRegistry.getNode(reactTag);
    }

    protected final ViewManager resolveViewManager(String className) {
        return this.mViewManagers.get(className);
    }

    UIViewOperationQueue getUIViewOperationQueue() {
        return this.mOperationsQueue;
    }

    public void registerRootView(SizeMonitoringFrameLayout rootView, int tag, int width, int height, ThemedReactContext context) {
        ReactShadowNode rootCSSNode = createRootShadowNode();
        rootCSSNode.setReactTag(tag);
        rootCSSNode.setThemedContext(context);
        rootCSSNode.setStyleWidth((float) width);
        rootCSSNode.setStyleHeight((float) height);
        this.mShadowNodeRegistry.addRootNode(rootCSSNode);
        this.mOperationsQueue.addRootView(tag, rootView, context);
    }

    public void removeRootView(int rootViewTag) {
        this.mShadowNodeRegistry.removeRootNode(rootViewTag);
        this.mOperationsQueue.enqueueRemoveRootView(rootViewTag);
    }

    public void updateNodeSize(int nodeViewTag, int newWidth, int newHeight) {
        ReactShadowNode cssNode = this.mShadowNodeRegistry.getNode(nodeViewTag);
        cssNode.setStyleWidth((float) newWidth);
        cssNode.setStyleHeight((float) newHeight);
        if (this.mOperationsQueue.isEmpty()) {
            dispatchViewUpdates(-1);
        }
    }

    public double getLayoutCount() {
        return this.mLayoutCount;
    }

    public double getLayoutTimer() {
        return this.mLayoutTimer;
    }

    public void createView(int tag, String className, int rootViewTag, ReadableMap props) {
        ReactShadowNode cssNode = createShadowNode(className);
        ReactShadowNode rootNode = this.mShadowNodeRegistry.getNode(rootViewTag);
        cssNode.setReactTag(tag);
        cssNode.setViewClassName(className);
        cssNode.setRootNode(rootNode);
        cssNode.setThemedContext(rootNode.getThemedContext());
        this.mShadowNodeRegistry.addNode(cssNode);
        ReactStylesDiffMap styles = null;
        if (props != null) {
            styles = new ReactStylesDiffMap(props);
            cssNode.updateProperties(styles);
        }
        handleCreateView(cssNode, rootViewTag, styles);
    }

    protected void handleCreateView(ReactShadowNode cssNode, int rootViewTag, @Nullable ReactStylesDiffMap styles) {
        if (!cssNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleCreateView(cssNode, cssNode.getThemedContext(), styles);
        }
    }

    public void updateView(int tag, String className, ReadableMap props) {
        if (this.mViewManagers.get(className) == null) {
            throw new IllegalViewOperationException("Got unknown view type: " + className);
        }
        ReactShadowNode cssNode = this.mShadowNodeRegistry.getNode(tag);
        if (cssNode == null) {
            throw new IllegalViewOperationException("Trying to update non-existent view with tag " + tag);
        } else if (props != null) {
            ReactStylesDiffMap styles = new ReactStylesDiffMap(props);
            cssNode.updateProperties(styles);
            handleUpdateView(cssNode, className, styles);
        }
    }

    public void synchronouslyUpdateViewOnUIThread(int tag, ReactStylesDiffMap props) {
        UiThreadUtil.assertOnUiThread();
        this.mOperationsQueue.getNativeViewHierarchyManager().updateProperties(tag, props);
    }

    protected void handleUpdateView(ReactShadowNode cssNode, String className, ReactStylesDiffMap styles) {
        if (!cssNode.isVirtual()) {
            this.mNativeViewHierarchyOptimizer.handleUpdateView(cssNode, className, styles);
        }
    }

    public void manageChildren(int viewTag, @Nullable ReadableArray moveFrom, @Nullable ReadableArray moveTo, @Nullable ReadableArray addChildTags, @Nullable ReadableArray addAtIndices, @Nullable ReadableArray removeFrom) {
        ReactShadowNode cssNodeToManage = this.mShadowNodeRegistry.getNode(viewTag);
        int numToMove = moveFrom == null ? 0 : moveFrom.size();
        int numToAdd = addChildTags == null ? 0 : addChildTags.size();
        int numToRemove = removeFrom == null ? 0 : removeFrom.size();
        if (numToMove != 0 && (moveTo == null || numToMove != moveTo.size())) {
            throw new IllegalViewOperationException("Size of moveFrom != size of moveTo!");
        } else if (numToAdd == 0 || (addAtIndices != null && numToAdd == addAtIndices.size())) {
            int i;
            ViewAtIndex[] viewsToAdd = new ViewAtIndex[(numToMove + numToAdd)];
            int[] indicesToRemove = new int[(numToMove + numToRemove)];
            int[] tagsToRemove = new int[indicesToRemove.length];
            int[] tagsToDelete = new int[numToRemove];
            if (numToMove > 0) {
                Assertions.assertNotNull(moveFrom);
                Assertions.assertNotNull(moveTo);
                for (i = 0; i < numToMove; i++) {
                    int moveFromIndex = moveFrom.getInt(i);
                    int tagToMove = cssNodeToManage.getChildAt(moveFromIndex).getReactTag();
                    viewsToAdd[i] = new ViewAtIndex(tagToMove, moveTo.getInt(i));
                    indicesToRemove[i] = moveFromIndex;
                    tagsToRemove[i] = tagToMove;
                }
            }
            if (numToAdd > 0) {
                Assertions.assertNotNull(addChildTags);
                Assertions.assertNotNull(addAtIndices);
                for (i = 0; i < numToAdd; i++) {
                    viewsToAdd[numToMove + i] = new ViewAtIndex(addChildTags.getInt(i), addAtIndices.getInt(i));
                }
            }
            if (numToRemove > 0) {
                Assertions.assertNotNull(removeFrom);
                for (i = 0; i < numToRemove; i++) {
                    int indexToRemove = removeFrom.getInt(i);
                    int tagToRemove = cssNodeToManage.getChildAt(indexToRemove).getReactTag();
                    indicesToRemove[numToMove + i] = indexToRemove;
                    tagsToRemove[numToMove + i] = tagToRemove;
                    tagsToDelete[i] = tagToRemove;
                }
            }
            Arrays.sort(viewsToAdd, ViewAtIndex.COMPARATOR);
            Arrays.sort(indicesToRemove);
            int lastIndexRemoved = -1;
            for (i = indicesToRemove.length - 1; i >= 0; i--) {
                if (indicesToRemove[i] == lastIndexRemoved) {
                    throw new IllegalViewOperationException("Repeated indices in Removal list for view tag: " + viewTag);
                }
                cssNodeToManage.removeChildAt(indicesToRemove[i]);
                lastIndexRemoved = indicesToRemove[i];
            }
            for (ViewAtIndex viewAtIndex : viewsToAdd) {
                ReactShadowNode cssNodeToAdd = this.mShadowNodeRegistry.getNode(viewAtIndex.mTag);
                if (cssNodeToAdd == null) {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag);
                }
                cssNodeToManage.addChildAt(cssNodeToAdd, viewAtIndex.mIndex);
            }
            if (!(cssNodeToManage.isVirtual() || cssNodeToManage.isVirtualAnchor())) {
                this.mNativeViewHierarchyOptimizer.handleManageChildren(cssNodeToManage, indicesToRemove, tagsToRemove, viewsToAdd, tagsToDelete);
            }
            for (int node : tagsToDelete) {
                removeShadowNode(this.mShadowNodeRegistry.getNode(node));
            }
        } else {
            throw new IllegalViewOperationException("Size of addChildTags != size of addAtIndices!");
        }
    }

    public void setChildren(int viewTag, ReadableArray childrenTags) {
        ReactShadowNode cssNodeToManage = this.mShadowNodeRegistry.getNode(viewTag);
        for (int i = 0; i < childrenTags.size(); i++) {
            ReactShadowNode cssNodeToAdd = this.mShadowNodeRegistry.getNode(childrenTags.getInt(i));
            if (cssNodeToAdd == null) {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + childrenTags.getInt(i));
            }
            cssNodeToManage.addChildAt(cssNodeToAdd, i);
        }
        if (!cssNodeToManage.isVirtual() && !cssNodeToManage.isVirtualAnchor()) {
            this.mNativeViewHierarchyOptimizer.handleSetChildren(cssNodeToManage, childrenTags);
        }
    }

    public void replaceExistingNonRootView(int oldTag, int newTag) {
        if (this.mShadowNodeRegistry.isRootNode(oldTag) || this.mShadowNodeRegistry.isRootNode(newTag)) {
            throw new IllegalViewOperationException("Trying to add or replace a root tag!");
        }
        ReactShadowNode oldNode = this.mShadowNodeRegistry.getNode(oldTag);
        if (oldNode == null) {
            throw new IllegalViewOperationException("Trying to replace unknown view tag: " + oldTag);
        }
        ReactShadowNode parent = oldNode.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("Node is not attached to a parent: " + oldTag);
        }
        int oldIndex = parent.indexOf(oldNode);
        if (oldIndex < 0) {
            throw new IllegalStateException("Didn't find child tag in parent");
        }
        WritableArray tagsToAdd = Arguments.createArray();
        tagsToAdd.pushInt(newTag);
        WritableArray addAtIndices = Arguments.createArray();
        addAtIndices.pushInt(oldIndex);
        WritableArray indicesToRemove = Arguments.createArray();
        indicesToRemove.pushInt(oldIndex);
        manageChildren(parent.getReactTag(), null, null, tagsToAdd, addAtIndices, indicesToRemove);
    }

    public void removeSubviewsFromContainerWithID(int containerTag) {
        ReactShadowNode containerNode = this.mShadowNodeRegistry.getNode(containerTag);
        if (containerNode == null) {
            throw new IllegalViewOperationException("Trying to remove subviews of an unknown view tag: " + containerTag);
        }
        WritableArray indicesToRemove = Arguments.createArray();
        for (int childIndex = 0; childIndex < containerNode.getChildCount(); childIndex++) {
            indicesToRemove.pushInt(childIndex);
        }
        manageChildren(containerTag, null, null, null, null, indicesToRemove);
    }

    public void findSubviewIn(int reactTag, float targetX, float targetY, Callback callback) {
        this.mOperationsQueue.enqueueFindTargetForTouch(reactTag, targetX, targetY, callback);
    }

    public void measure(int reactTag, Callback callback) {
        this.mOperationsQueue.enqueueMeasure(reactTag, callback);
    }

    public void measureInWindow(int reactTag, Callback callback) {
        this.mOperationsQueue.enqueueMeasureInWindow(reactTag, callback);
    }

    public void measureLayout(int tag, int ancestorTag, Callback errorCallback, Callback successCallback) {
        try {
            measureLayout(tag, ancestorTag, this.mMeasureBuffer);
            float relativeX = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[0]);
            float relativeY = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[1]);
            float width = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[2]);
            float height = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[3]);
            successCallback.invoke(Float.valueOf(relativeX), Float.valueOf(relativeY), Float.valueOf(width), Float.valueOf(height));
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    public void measureLayoutRelativeToParent(int tag, Callback errorCallback, Callback successCallback) {
        try {
            measureLayoutRelativeToParent(tag, this.mMeasureBuffer);
            float relativeX = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[0]);
            float relativeY = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[1]);
            float width = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[2]);
            float height = PixelUtil.toDIPFromPixel((float) this.mMeasureBuffer[3]);
            successCallback.invoke(Float.valueOf(relativeX), Float.valueOf(relativeY), Float.valueOf(width), Float.valueOf(height));
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    public void dispatchViewUpdates(int batchId) {
        updateViewHierarchy();
        this.mNativeViewHierarchyOptimizer.onBatchComplete();
        this.mOperationsQueue.dispatchViewUpdates(batchId);
    }

    protected void updateViewHierarchy() {
        for (int i = 0; i < this.mShadowNodeRegistry.getRootNodeCount(); i++) {
            ReactShadowNode cssRoot = this.mShadowNodeRegistry.getNode(this.mShadowNodeRegistry.getRootTag(i));
            notifyOnBeforeLayoutRecursive(cssRoot);
            calculateRootLayout(cssRoot);
            applyUpdatesRecursive(cssRoot, 0.0f, 0.0f);
        }
    }

    public void registerAnimation(Animation animation) {
        this.mOperationsQueue.enqueueRegisterAnimation(animation);
    }

    public void addAnimation(int reactTag, int animationID, Callback onSuccess) {
        assertViewExists(reactTag, "addAnimation");
        this.mOperationsQueue.enqueueAddAnimation(reactTag, animationID, onSuccess);
    }

    public void removeAnimation(int reactTag, int animationID) {
        assertViewExists(reactTag, "removeAnimation");
        this.mOperationsQueue.enqueueRemoveAnimation(animationID);
    }

    public void setLayoutAnimationEnabledExperimental(boolean enabled) {
        this.mOperationsQueue.enqueueSetLayoutAnimationEnabled(enabled);
    }

    public void configureNextLayoutAnimation(ReadableMap config, Callback success, Callback error) {
        this.mOperationsQueue.enqueueConfigureLayoutAnimation(config, success, error);
    }

    public void setJSResponder(int reactTag, boolean blockNativeResponder) {
        assertViewExists(reactTag, "setJSResponder");
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(reactTag);
        while (true) {
            if (node.isVirtual() || node.isLayoutOnly()) {
                node = node.getParent();
            } else {
                this.mOperationsQueue.enqueueSetJSResponder(node.getReactTag(), reactTag, blockNativeResponder);
                return;
            }
        }
    }

    public void clearJSResponder() {
        this.mOperationsQueue.enqueueClearJSResponder();
    }

    public void dispatchViewManagerCommand(int reactTag, int commandId, ReadableArray commandArgs) {
        assertViewExists(reactTag, "dispatchViewManagerCommand");
        this.mOperationsQueue.enqueueDispatchCommand(reactTag, commandId, commandArgs);
    }

    public void showPopupMenu(int reactTag, ReadableArray items, Callback error, Callback success) {
        assertViewExists(reactTag, "showPopupMenu");
        this.mOperationsQueue.enqueueShowPopupMenu(reactTag, items, error, success);
    }

    public void sendAccessibilityEvent(int tag, int eventType) {
        this.mOperationsQueue.enqueueSendAccessibilityEvent(tag, eventType);
    }

    public void onHostResume() {
        this.mOperationsQueue.resumeFrameCallback();
    }

    public void onHostPause() {
        this.mOperationsQueue.pauseFrameCallback();
    }

    public void onHostDestroy() {
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener listener) {
        this.mOperationsQueue.setViewHierarchyUpdateDebugListener(listener);
    }

    protected final void removeShadowNode(ReactShadowNode nodeToRemove) {
        removeShadowNodeRecursive(nodeToRemove);
        nodeToRemove.dispose();
    }

    private void removeShadowNodeRecursive(ReactShadowNode nodeToRemove) {
        NativeViewHierarchyOptimizer.handleRemoveNode(nodeToRemove);
        this.mShadowNodeRegistry.removeNode(nodeToRemove.getReactTag());
        for (int i = nodeToRemove.getChildCount() - 1; i >= 0; i--) {
            removeShadowNodeRecursive(nodeToRemove.getChildAt(i));
        }
        nodeToRemove.removeAndDisposeAllChildren();
    }

    private void measureLayout(int tag, int ancestorTag, int[] outputBuffer) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(tag);
        ReactShadowNode ancestor = this.mShadowNodeRegistry.getNode(ancestorTag);
        if (node == null || ancestor == null) {
            StringBuilder append = new StringBuilder().append("Tag ");
            if (node != null) {
                tag = ancestorTag;
            }
            throw new IllegalViewOperationException(append.append(tag).append(" does not exist").toString());
        }
        if (node != ancestor) {
            for (ReactShadowNode currentParent = node.getParent(); currentParent != ancestor; currentParent = currentParent.getParent()) {
                if (currentParent == null) {
                    throw new IllegalViewOperationException("Tag " + ancestorTag + " is not an ancestor of tag " + tag);
                }
            }
        }
        measureLayoutRelativeToVerifiedAncestor(node, ancestor, outputBuffer);
    }

    private void measureLayoutRelativeToParent(int tag, int[] outputBuffer) {
        ReactShadowNode node = this.mShadowNodeRegistry.getNode(tag);
        if (node == null) {
            throw new IllegalViewOperationException("No native view for tag " + tag + " exists!");
        }
        ReactShadowNode parent = node.getParent();
        if (parent == null) {
            throw new IllegalViewOperationException("View with tag " + tag + " doesn't have a parent!");
        }
        measureLayoutRelativeToVerifiedAncestor(node, parent, outputBuffer);
    }

    private void measureLayoutRelativeToVerifiedAncestor(ReactShadowNode node, ReactShadowNode ancestor, int[] outputBuffer) {
        int offsetX = 0;
        int offsetY = 0;
        if (node != ancestor) {
            offsetX = Math.round(node.getLayoutX());
            offsetY = Math.round(node.getLayoutY());
            for (ReactShadowNode current = node.getParent(); current != ancestor; current = current.getParent()) {
                Assertions.assertNotNull(current);
                assertNodeDoesNotNeedCustomLayoutForChildren(current);
                offsetX += Math.round(current.getLayoutX());
                offsetY += Math.round(current.getLayoutY());
            }
            assertNodeDoesNotNeedCustomLayoutForChildren(ancestor);
        }
        outputBuffer[0] = offsetX;
        outputBuffer[1] = offsetY;
        outputBuffer[2] = node.getScreenWidth();
        outputBuffer[3] = node.getScreenHeight();
    }

    private void assertViewExists(int reactTag, String operationNameForExceptionMessage) {
        if (this.mShadowNodeRegistry.getNode(reactTag) == null) {
            throw new IllegalViewOperationException("Unable to execute operation " + operationNameForExceptionMessage + " on view with " + "tag: " + reactTag + ", since the view does not exists");
        }
    }

    private void assertNodeDoesNotNeedCustomLayoutForChildren(ReactShadowNode node) {
        ViewManager viewManager = (ViewManager) Assertions.assertNotNull(this.mViewManagers.get(node.getViewClass()));
        if (viewManager instanceof ViewGroupManager) {
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            if (viewGroupManager != null && viewGroupManager.needsCustomLayoutForChildren()) {
                throw new IllegalViewOperationException("Trying to measure a view using measureLayout/measureLayoutRelativeToParent relative to an ancestor that requires custom layout for it's children (" + node.getViewClass() + "). Use measure instead.");
            }
            return;
        }
        throw new IllegalViewOperationException("Trying to use view " + node.getViewClass() + " as a parent, but its Manager doesn't extends ViewGroupManager");
    }

    private void notifyOnBeforeLayoutRecursive(ReactShadowNode cssNode) {
        if (cssNode.hasUpdates()) {
            for (int i = 0; i < cssNode.getChildCount(); i++) {
                notifyOnBeforeLayoutRecursive(cssNode.getChildAt(i));
            }
            cssNode.onBeforeLayout();
        }
    }

    protected void calculateRootLayout(ReactShadowNode cssRoot) {
        SystraceMessage.beginSection(0, "cssRoot.calculateLayout").arg("rootTag", cssRoot.getReactTag()).flush();
        double startTime = (double) System.nanoTime();
        try {
            cssRoot.calculateLayout();
        } finally {
            Systrace.endSection(0);
            this.mLayoutTimer += (((double) System.nanoTime()) - startTime) / 1.0E9d;
            this.mLayoutCount += 1.0d;
        }
    }

    protected void applyUpdatesRecursive(ReactShadowNode cssNode, float absoluteX, float absoluteY) {
        if (cssNode.hasUpdates()) {
            if (!cssNode.isVirtualAnchor()) {
                for (int i = 0; i < cssNode.getChildCount(); i++) {
                    applyUpdatesRecursive(cssNode.getChildAt(i), cssNode.getLayoutX() + absoluteX, cssNode.getLayoutY() + absoluteY);
                }
            }
            int tag = cssNode.getReactTag();
            if (!this.mShadowNodeRegistry.isRootNode(tag) && cssNode.dispatchUpdates(absoluteX, absoluteY, this.mOperationsQueue, this.mNativeViewHierarchyOptimizer) && cssNode.shouldNotifyOnLayout()) {
                this.mEventDispatcher.dispatchEvent(OnLayoutEvent.obtain(tag, cssNode.getScreenX(), cssNode.getScreenY(), cssNode.getScreenWidth(), cssNode.getScreenHeight()));
            }
            cssNode.markUpdateSeen();
        }
    }

    public void addUIBlock(UIBlock block) {
        this.mOperationsQueue.enqueueUIBlock(block);
    }

    public int resolveRootTagFromReactTag(int reactTag) {
        if (this.mShadowNodeRegistry.isRootNode(reactTag)) {
            return reactTag;
        }
        ReactShadowNode node = resolveShadowNode(reactTag);
        int rootTag = 0;
        if (node != null) {
            rootTag = node.getRootNode().getReactTag();
        } else {
            FLog.m151w(ReactConstants.TAG, "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + reactTag);
        }
        return rootTag;
    }
}
