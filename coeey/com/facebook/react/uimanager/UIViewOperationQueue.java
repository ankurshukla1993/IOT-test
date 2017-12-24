package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;
import com.facebook.react.animation.AnimationRegistry;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.systrace.Systrace;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class UIViewOperationQueue {
    private final AnimationRegistry mAnimationRegistry;
    private final Object mDispatchRunnablesLock = new Object();
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    @GuardedBy("mDispatchRunnablesLock")
    private final ArrayList<Runnable> mDispatchUIRunnables = new ArrayList();
    private boolean mIsDispatchUIFrameCallbackEnqueued = false;
    private final int[] mMeasureBuffer = new int[4];
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;
    @GuardedBy("mNonBatchedOperationsLock")
    private ArrayDeque<UIOperation> mNonBatchedOperations = new ArrayDeque();
    private final Object mNonBatchedOperationsLock = new Object();
    private ArrayList<UIOperation> mOperations = new ArrayList();
    private final ReactApplicationContext mReactApplicationContext;
    @Nullable
    private NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;

    private final class CreateViewOperation extends ViewOperation {
        private final String mClassName;
        @Nullable
        private final ReactStylesDiffMap mInitialProps;
        private final ThemedReactContext mThemedContext;

        public CreateViewOperation(ThemedReactContext themedContext, int tag, String className, @Nullable ReactStylesDiffMap initialProps) {
            super(UIViewOperationQueue.this, tag);
            this.mThemedContext = themedContext;
            this.mClassName = className;
            this.mInitialProps = initialProps;
            Systrace.startAsyncFlow(0, "createView", this.mTag);
        }

        public void execute() {
            Systrace.endAsyncFlow(0, "createView", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.createView(this.mThemedContext, this.mTag, this.mClassName, this.mInitialProps);
        }
    }

    private final class DispatchCommandOperation extends ViewOperation {
        @Nullable
        private final ReadableArray mArgs;
        private final int mCommand;

        public DispatchCommandOperation(int tag, int command, @Nullable ReadableArray args) {
            super(UIViewOperationQueue.this, tag);
            this.mCommand = command;
            this.mArgs = args;
        }

        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }
    }

    private final class ManageChildrenOperation extends ViewOperation {
        @Nullable
        private final int[] mIndicesToRemove;
        @Nullable
        private final int[] mTagsToDelete;
        @Nullable
        private final ViewAtIndex[] mViewsToAdd;

        public ManageChildrenOperation(int tag, @Nullable int[] indicesToRemove, @Nullable ViewAtIndex[] viewsToAdd, @Nullable int[] tagsToDelete) {
            super(UIViewOperationQueue.this, tag);
            this.mIndicesToRemove = indicesToRemove;
            this.mViewsToAdd = viewsToAdd;
            this.mTagsToDelete = tagsToDelete;
        }

        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.manageChildren(this.mTag, this.mIndicesToRemove, this.mViewsToAdd, this.mTagsToDelete);
        }
    }

    public UIViewOperationQueue(ReactApplicationContext reactContext, NativeViewHierarchyManager nativeViewHierarchyManager) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
        this.mAnimationRegistry = nativeViewHierarchyManager.getAnimationRegistry();
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(this, reactContext, null);
        this.mReactApplicationContext = reactContext;
    }

    NativeViewHierarchyManager getNativeViewHierarchyManager() {
        return this.mNativeViewHierarchyManager;
    }

    public void setViewHierarchyUpdateDebugListener(@Nullable NotThreadSafeViewHierarchyUpdateDebugListener listener) {
        this.mViewHierarchyUpdateDebugListener = listener;
    }

    public boolean isEmpty() {
        return this.mOperations.isEmpty();
    }

    public void addRootView(int tag, SizeMonitoringFrameLayout rootView, ThemedReactContext themedRootContext) {
        if (UiThreadUtil.isOnUiThread()) {
            this.mNativeViewHierarchyManager.addRootView(tag, rootView, themedRootContext);
            return;
        }
        Semaphore semaphore = new Semaphore(0);
        this.mReactApplicationContext.runOnUiQueueThread(new 1(this, tag, rootView, themedRootContext, semaphore));
        try {
            SoftAssertions.assertCondition(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS), "Timed out adding root view");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void enqueueUIOperation(UIOperation operation) {
        SoftAssertions.assertNotNull(operation);
        this.mOperations.add(operation);
    }

    public void enqueueRemoveRootView(int rootViewTag) {
        this.mOperations.add(new RemoveRootViewOperation(this, rootViewTag));
    }

    public void enqueueSetJSResponder(int tag, int initialTag, boolean blockNativeResponder) {
        this.mOperations.add(new ChangeJSResponderOperation(this, tag, initialTag, false, blockNativeResponder));
    }

    public void enqueueClearJSResponder() {
        this.mOperations.add(new ChangeJSResponderOperation(this, 0, 0, true, false));
    }

    public void enqueueDispatchCommand(int reactTag, int commandId, ReadableArray commandArgs) {
        this.mOperations.add(new DispatchCommandOperation(reactTag, commandId, commandArgs));
    }

    public void enqueueUpdateExtraData(int reactTag, Object extraData) {
        this.mOperations.add(new UpdateViewExtraData(this, reactTag, extraData));
    }

    public void enqueueShowPopupMenu(int reactTag, ReadableArray items, Callback error, Callback success) {
        this.mOperations.add(new ShowPopupMenuOperation(this, reactTag, items, success));
    }

    public void enqueueCreateView(ThemedReactContext themedContext, int viewReactTag, String viewClassName, @Nullable ReactStylesDiffMap initialProps) {
        synchronized (this.mNonBatchedOperationsLock) {
            this.mNonBatchedOperations.addLast(new CreateViewOperation(themedContext, viewReactTag, viewClassName, initialProps));
        }
    }

    public void enqueueUpdateProperties(int reactTag, String className, ReactStylesDiffMap props) {
        this.mOperations.add(new UpdatePropertiesOperation(this, reactTag, props, null));
    }

    public void enqueueUpdateLayout(int parentTag, int reactTag, int x, int y, int width, int height) {
        this.mOperations.add(new UpdateLayoutOperation(this, parentTag, reactTag, x, y, width, height));
    }

    public void enqueueManageChildren(int reactTag, @Nullable int[] indicesToRemove, @Nullable ViewAtIndex[] viewsToAdd, @Nullable int[] tagsToDelete) {
        this.mOperations.add(new ManageChildrenOperation(reactTag, indicesToRemove, viewsToAdd, tagsToDelete));
    }

    public void enqueueSetChildren(int reactTag, ReadableArray childrenTags) {
        this.mOperations.add(new SetChildrenOperation(this, reactTag, childrenTags));
    }

    public void enqueueRegisterAnimation(Animation animation) {
        this.mOperations.add(new RegisterAnimationOperation(this, animation, null));
    }

    public void enqueueAddAnimation(int reactTag, int animationID, Callback onSuccess) {
        this.mOperations.add(new AddAnimationOperation(this, reactTag, animationID, onSuccess, null));
    }

    public void enqueueRemoveAnimation(int animationID) {
        this.mOperations.add(new RemoveAnimationOperation(this, animationID, null));
    }

    public void enqueueSetLayoutAnimationEnabled(boolean enabled) {
        this.mOperations.add(new SetLayoutAnimationEnabledOperation(this, enabled, null));
    }

    public void enqueueConfigureLayoutAnimation(ReadableMap config, Callback onSuccess, Callback onError) {
        this.mOperations.add(new ConfigureLayoutAnimationOperation(this, config, null));
    }

    public void enqueueMeasure(int reactTag, Callback callback) {
        this.mOperations.add(new MeasureOperation(this, reactTag, callback, null));
    }

    public void enqueueMeasureInWindow(int reactTag, Callback callback) {
        this.mOperations.add(new MeasureInWindowOperation(this, reactTag, callback, null));
    }

    public void enqueueFindTargetForTouch(int reactTag, float targetX, float targetY, Callback callback) {
        this.mOperations.add(new FindTargetForTouchOperation(this, reactTag, targetX, targetY, callback, null));
    }

    public void enqueueSendAccessibilityEvent(int tag, int eventType) {
        this.mOperations.add(new SendAccessibilityEvent(this, tag, eventType, null));
    }

    public void enqueueUIBlock(UIBlock block) {
        this.mOperations.add(new UIBlockOperation(this, block));
    }

    void dispatchViewUpdates(int batchId) {
        UIOperation[] nonBatchedOperations;
        ArrayList<UIOperation> operations = this.mOperations.isEmpty() ? null : this.mOperations;
        if (operations != null) {
            this.mOperations = new ArrayList();
        }
        synchronized (this.mNonBatchedOperationsLock) {
            if (this.mNonBatchedOperations.isEmpty()) {
                nonBatchedOperations = null;
            } else {
                nonBatchedOperations = (UIOperation[]) this.mNonBatchedOperations.toArray(new UIOperation[this.mNonBatchedOperations.size()]);
                this.mNonBatchedOperations.clear();
            }
        }
        if (this.mViewHierarchyUpdateDebugListener != null) {
            this.mViewHierarchyUpdateDebugListener.onViewHierarchyUpdateEnqueued();
        }
        synchronized (this.mDispatchRunnablesLock) {
            this.mDispatchUIRunnables.add(new 2(this, batchId, nonBatchedOperations, operations));
        }
        if (!this.mIsDispatchUIFrameCallbackEnqueued) {
            UiThreadUtil.runOnUiThread(new 3(this));
        }
    }

    void resumeFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback(CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    void pauseFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = false;
        ReactChoreographer.getInstance().removeFrameCallback(CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
        flushPendingBatches();
    }

    private void flushPendingBatches() {
        synchronized (this.mDispatchRunnablesLock) {
            for (int i = 0; i < this.mDispatchUIRunnables.size(); i++) {
                ((Runnable) this.mDispatchUIRunnables.get(i)).run();
            }
            this.mDispatchUIRunnables.clear();
        }
    }
}
