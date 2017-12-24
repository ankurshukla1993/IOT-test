package com.facebook.react.flat;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.NoSuchNativeViewException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.UIViewOperationQueue$UIOperation;
import java.util.ArrayList;
import javax.annotation.Nullable;

final class FlatUIViewOperationQueue extends UIViewOperationQueue {
    private static final int[] MEASURE_BUFFER = new int[4];
    private final FlatNativeViewHierarchyManager mNativeViewHierarchyManager;
    private final ProcessLayoutRequests mProcessLayoutRequests = new ProcessLayoutRequests();

    private final class DropViews implements UIViewOperationQueue$UIOperation {
        private final SparseIntArray mViewsToDrop;

        private DropViews(ArrayList<Integer> viewsToDrop, ArrayList<Integer> parentsForViewsToDrop) {
            SparseIntArray sparseIntArray = new SparseIntArray();
            int count = viewsToDrop.size();
            for (int i = 0; i < count; i++) {
                sparseIntArray.put(((Integer) viewsToDrop.get(i)).intValue(), ((Integer) parentsForViewsToDrop.get(i)).intValue());
            }
            this.mViewsToDrop = sparseIntArray;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.dropViews(this.mViewsToDrop);
        }
    }

    private final class FindTargetForTouchOperation implements UIViewOperationQueue$UIOperation {
        private final int[] NATIVE_VIEW_BUFFER;
        private final Callback mCallback;
        private final int mReactTag;
        private final float mTargetX;
        private final float mTargetY;

        private FindTargetForTouchOperation(int reactTag, float targetX, float targetY, Callback callback) {
            this.NATIVE_VIEW_BUFFER = new int[1];
            this.mReactTag = reactTag;
            this.mTargetX = targetX;
            this.mTargetY = targetY;
            this.mCallback = callback;
        }

        public void execute() {
            try {
                FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                float containerX = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[0];
                float containerY = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[1];
                int touchTargetReactTag = TouchTargetHelper.findTargetTagForTouch(this.mTargetX, this.mTargetY, (ViewGroup) FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.getView(this.mReactTag), this.NATIVE_VIEW_BUFFER);
                try {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.NATIVE_VIEW_BUFFER[0], FlatUIViewOperationQueue.MEASURE_BUFFER);
                    NodeRegion region = NodeRegion.EMPTY;
                    boolean isNativeView = this.NATIVE_VIEW_BUFFER[0] == touchTargetReactTag;
                    if (!isNativeView) {
                        View view = FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.getView(this.NATIVE_VIEW_BUFFER[0]);
                        if (view instanceof FlatViewGroup) {
                            region = ((FlatViewGroup) view).getNodeRegionForTag(this.mReactTag);
                        }
                    }
                    int resultTag = region == NodeRegion.EMPTY ? touchTargetReactTag : region.mTag;
                    float x = PixelUtil.toDIPFromPixel((region.getLeft() + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[0])) - containerX);
                    float y = PixelUtil.toDIPFromPixel((region.getTop() + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[1])) - containerY);
                    float width = PixelUtil.toDIPFromPixel(isNativeView ? (float) FlatUIViewOperationQueue.MEASURE_BUFFER[2] : region.getRight() - region.getLeft());
                    float height = PixelUtil.toDIPFromPixel(isNativeView ? (float) FlatUIViewOperationQueue.MEASURE_BUFFER[3] : region.getBottom() - region.getTop());
                    this.mCallback.invoke(Integer.valueOf(resultTag), Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
                } catch (IllegalViewOperationException e) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException e2) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    private final class MeasureVirtualView implements UIViewOperationQueue$UIOperation {
        private final Callback mCallback;
        private final int mReactTag;
        private final boolean mRelativeToWindow;
        private final float mScaledHeight;
        private final float mScaledWidth;
        private final float mScaledX;
        private final float mScaledY;

        private MeasureVirtualView(int reactTag, float scaledX, float scaledY, float scaledWidth, float scaledHeight, boolean relativeToWindow, Callback callback) {
            this.mReactTag = reactTag;
            this.mScaledX = scaledX;
            this.mScaledY = scaledY;
            this.mScaledWidth = scaledWidth;
            this.mScaledHeight = scaledHeight;
            this.mCallback = callback;
            this.mRelativeToWindow = relativeToWindow;
        }

        public void execute() {
            try {
                if (this.mRelativeToWindow) {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                } else {
                    FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, FlatUIViewOperationQueue.MEASURE_BUFFER);
                }
                float nativeViewY = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[1];
                float nativeViewWidth = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[2];
                float nativeViewHeight = (float) FlatUIViewOperationQueue.MEASURE_BUFFER[3];
                float x = PixelUtil.toDIPFromPixel((this.mScaledX * nativeViewWidth) + ((float) FlatUIViewOperationQueue.MEASURE_BUFFER[0]));
                float y = PixelUtil.toDIPFromPixel((this.mScaledY * nativeViewHeight) + nativeViewY);
                float width = PixelUtil.toDIPFromPixel(this.mScaledWidth * nativeViewWidth);
                float height = PixelUtil.toDIPFromPixel(this.mScaledHeight * nativeViewHeight);
                if (this.mRelativeToWindow) {
                    this.mCallback.invoke(Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
                    return;
                }
                this.mCallback.invoke(Integer.valueOf(0), Integer.valueOf(0), Float.valueOf(width), Float.valueOf(height), Float.valueOf(x), Float.valueOf(y));
            } catch (NoSuchNativeViewException e) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    private final class ProcessLayoutRequests implements UIViewOperationQueue$UIOperation {
        private ProcessLayoutRequests() {
        }

        public void execute() {
            FlatViewGroup.processLayoutRequests();
        }
    }

    private final class SetPadding implements UIViewOperationQueue$UIOperation {
        private final int mPaddingBottom;
        private final int mPaddingLeft;
        private final int mPaddingRight;
        private final int mPaddingTop;
        private final int mReactTag;

        private SetPadding(int reactTag, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
            this.mReactTag = reactTag;
            this.mPaddingLeft = paddingLeft;
            this.mPaddingTop = paddingTop;
            this.mPaddingRight = paddingRight;
            this.mPaddingBottom = paddingBottom;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.setPadding(this.mReactTag, this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
        }
    }

    public final class UpdateViewBounds implements UIViewOperationQueue$UIOperation {
        private final int mBottom;
        private final int mLeft;
        private final int mReactTag;
        private final int mRight;
        private final int mTop;

        private UpdateViewBounds(int reactTag, int left, int top, int right, int bottom) {
            this.mReactTag = reactTag;
            this.mLeft = left;
            this.mTop = top;
            this.mRight = right;
            this.mBottom = bottom;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewBounds(this.mReactTag, this.mLeft, this.mTop, this.mRight, this.mBottom);
        }
    }

    private final class UpdateViewGroup implements UIViewOperationQueue$UIOperation {
        private final int mReactTag;
        private final int[] mViewsToAdd;
        private final int[] mViewsToDetach;

        private UpdateViewGroup(int reactTag, int[] viewsToAdd, int[] viewsToDetach) {
            this.mReactTag = reactTag;
            this.mViewsToAdd = viewsToAdd;
            this.mViewsToDetach = viewsToDetach;
        }

        public void execute() {
            FlatUIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewGroup(this.mReactTag, this.mViewsToAdd, this.mViewsToDetach);
        }
    }

    public FlatUIViewOperationQueue(ReactApplicationContext reactContext, FlatNativeViewHierarchyManager nativeViewHierarchyManager) {
        super(reactContext, nativeViewHierarchyManager);
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
    }

    public void enqueueUpdateMountState(int reactTag, @Nullable DrawCommand[] drawCommands, @Nullable AttachDetachListener[] listeners, @Nullable NodeRegion[] nodeRegions) {
        enqueueUIOperation(new UpdateMountState(this, reactTag, drawCommands, listeners, nodeRegions, null));
    }

    public void enqueueUpdateClippingMountState(int reactTag, @Nullable DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] commandMaxBot, float[] commandMinTop, @Nullable AttachDetachListener[] listeners, @Nullable NodeRegion[] nodeRegions, float[] regionMaxBot, float[] regionMinTop, boolean willMountViews) {
        enqueueUIOperation(new UpdateClippingMountState(this, reactTag, drawCommands, drawViewIndexMap, commandMaxBot, commandMinTop, listeners, nodeRegions, regionMaxBot, regionMinTop, willMountViews, null));
    }

    public void enqueueUpdateViewGroup(int reactTag, int[] viewsToAdd, int[] viewsToDetach) {
        enqueueUIOperation(new UpdateViewGroup(reactTag, viewsToAdd, viewsToDetach));
    }

    public UpdateViewBounds createUpdateViewBounds(int reactTag, int left, int top, int right, int bottom) {
        return new UpdateViewBounds(reactTag, left, top, right, bottom);
    }

    public ViewManagerCommand createViewManagerCommand(int reactTag, int command, @Nullable ReadableArray args) {
        return new ViewManagerCommand(this, reactTag, command, args);
    }

    void enqueueFlatUIOperation(UIViewOperationQueue$UIOperation operation) {
        enqueueUIOperation(operation);
    }

    public void enqueueSetPadding(int reactTag, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        enqueueUIOperation(new SetPadding(reactTag, paddingLeft, paddingTop, paddingRight, paddingBottom));
    }

    public void enqueueDropViews(ArrayList<Integer> viewsToDrop, ArrayList<Integer> parentsOfViewsToDrop) {
        enqueueUIOperation(new DropViews(viewsToDrop, parentsOfViewsToDrop));
    }

    public void enqueueMeasureVirtualView(int reactTag, float scaledX, float scaledY, float scaledWidth, float scaledHeight, boolean relativeToWindow, Callback callback) {
        enqueueUIOperation(new MeasureVirtualView(reactTag, scaledX, scaledY, scaledWidth, scaledHeight, relativeToWindow, callback));
    }

    public void enqueueProcessLayoutRequests() {
        enqueueUIOperation(this.mProcessLayoutRequests);
    }

    public DetachAllChildrenFromViews enqueueDetachAllChildrenFromViews() {
        DetachAllChildrenFromViews op = new DetachAllChildrenFromViews(this);
        enqueueUIOperation(op);
        return op;
    }

    public void enqueueFindTargetForTouch(int reactTag, float targetX, float targetY, Callback callback) {
        enqueueUIOperation(new FindTargetForTouchOperation(reactTag, targetX, targetY, callback));
    }
}
