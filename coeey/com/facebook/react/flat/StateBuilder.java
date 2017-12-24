package com.facebook.react.flat;

import android.util.SparseIntArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.ArrayList;
import javax.annotation.Nullable;

final class StateBuilder {
    static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    static final SparseIntArray EMPTY_SPARSE_INT = new SparseIntArray();
    private static final boolean SKIP_UP_TO_DATE_NODES = true;
    private final ElementsList<AttachDetachListener> mAttachDetachListeners = new ElementsList(AttachDetachListener.EMPTY_ARRAY);
    @Nullable
    private FlatUIViewOperationQueue$DetachAllChildrenFromViews mDetachAllChildrenFromViews;
    private final ElementsList<DrawCommand> mDrawCommands = new ElementsList(DrawCommand.EMPTY_ARRAY);
    private final ElementsList<FlatShadowNode> mNativeChildren = new ElementsList(FlatShadowNode.EMPTY_ARRAY);
    private final ElementsList<NodeRegion> mNodeRegions = new ElementsList(NodeRegion.EMPTY_ARRAY);
    private final ArrayList<OnLayoutEvent> mOnLayoutEvents = new ArrayList();
    private final FlatUIViewOperationQueue mOperationsQueue;
    private final ArrayList<Integer> mParentsForViewsToDrop = new ArrayList();
    private final ArrayList<UIOperation> mUpdateViewBoundsOperations = new ArrayList();
    private final ArrayList<UIOperation> mViewManagerCommands = new ArrayList();
    private final ArrayList<FlatShadowNode> mViewsToDetach = new ArrayList();
    private final ArrayList<FlatShadowNode> mViewsToDetachAllChildrenFrom = new ArrayList();
    private final ArrayList<Integer> mViewsToDrop = new ArrayList();

    StateBuilder(FlatUIViewOperationQueue operationsQueue) {
        this.mOperationsQueue = operationsQueue;
    }

    FlatUIViewOperationQueue getOperationsQueue() {
        return this.mOperationsQueue;
    }

    void applyUpdates(FlatShadowNode node) {
        float width = node.getLayoutWidth();
        float height = node.getLayoutHeight();
        float left = node.getLayoutX();
        float top = node.getLayoutY();
        float right = left + width;
        float bottom = top + height;
        collectStateForMountableNode(node, left, top, right, bottom, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        updateViewBounds(node, left, top, right, bottom);
    }

    void afterUpdateViewHierarchy(EventDispatcher eventDispatcher) {
        int i;
        if (this.mDetachAllChildrenFromViews != null) {
            int[] viewsToDetachAllChildrenFrom = collectViewTags(this.mViewsToDetachAllChildrenFrom);
            this.mViewsToDetachAllChildrenFrom.clear();
            this.mDetachAllChildrenFromViews.setViewsToDetachAllChildrenFrom(viewsToDetachAllChildrenFrom);
            this.mDetachAllChildrenFromViews = null;
        }
        int size = this.mUpdateViewBoundsOperations.size();
        for (i = 0; i != size; i++) {
            this.mOperationsQueue.enqueueFlatUIOperation((UIOperation) this.mUpdateViewBoundsOperations.get(i));
        }
        this.mUpdateViewBoundsOperations.clear();
        size = this.mViewManagerCommands.size();
        for (i = 0; i != size; i++) {
            this.mOperationsQueue.enqueueFlatUIOperation((UIOperation) this.mViewManagerCommands.get(i));
        }
        this.mViewManagerCommands.clear();
        size = this.mOnLayoutEvents.size();
        for (i = 0; i != size; i++) {
            eventDispatcher.dispatchEvent((Event) this.mOnLayoutEvents.get(i));
        }
        this.mOnLayoutEvents.clear();
        if (this.mViewsToDrop.size() > 0) {
            this.mOperationsQueue.enqueueDropViews(this.mViewsToDrop, this.mParentsForViewsToDrop);
            this.mViewsToDrop.clear();
            this.mParentsForViewsToDrop.clear();
        }
        this.mOperationsQueue.enqueueProcessLayoutRequests();
    }

    void removeRootView(int rootViewTag) {
        this.mViewsToDrop.add(Integer.valueOf(-rootViewTag));
        this.mParentsForViewsToDrop.add(Integer.valueOf(-1));
    }

    void addDrawCommand(AbstractDrawCommand drawCommand) {
        this.mDrawCommands.add(drawCommand);
    }

    void addAttachDetachListener(AttachDetachListener listener) {
        this.mAttachDetachListeners.add(listener);
    }

    void enqueueViewManagerCommand(int reactTag, int commandId, ReadableArray commandArgs) {
        this.mViewManagerCommands.add(this.mOperationsQueue.createViewManagerCommand(reactTag, commandId, commandArgs));
    }

    void enqueueCreateOrUpdateView(FlatShadowNode node, @Nullable ReactStylesDiffMap styles) {
        if (node.isBackingViewCreated()) {
            this.mOperationsQueue.enqueueUpdateProperties(node.getReactTag(), node.getViewClass(), styles);
            return;
        }
        this.mOperationsQueue.enqueueCreateView(node.getThemedContext(), node.getReactTag(), node.getViewClass(), styles);
        node.signalBackingViewIsCreated();
    }

    void ensureBackingViewIsCreated(FlatShadowNode node) {
        if (!node.isBackingViewCreated()) {
            this.mOperationsQueue.enqueueCreateView(node.getThemedContext(), node.getReactTag(), node.getViewClass(), null);
            node.signalBackingViewIsCreated();
        }
    }

    void dropView(FlatShadowNode node, int parentReactTag) {
        this.mViewsToDrop.add(Integer.valueOf(node.getReactTag()));
        this.mParentsForViewsToDrop.add(Integer.valueOf(parentReactTag));
    }

    private void addNodeRegion(FlatShadowNode node, float left, float top, float right, float bottom, boolean isVirtual) {
        if (left != right && top != bottom) {
            node.updateNodeRegion(left, top, right, bottom, isVirtual);
            if (node.doesDraw()) {
                this.mNodeRegions.add(node.getNodeRegion());
            }
        }
    }

    private void addNativeChild(FlatShadowNode nativeChild) {
        this.mNativeChildren.add(nativeChild);
    }

    private void updateViewBounds(FlatShadowNode node, float left, float top, float right, float bottom) {
        int viewLeft = Math.round(left);
        int viewTop = Math.round(top);
        int viewRight = Math.round(right);
        int viewBottom = Math.round(bottom);
        if (node.getViewLeft() != viewLeft || node.getViewTop() != viewTop || node.getViewRight() != viewRight || node.getViewBottom() != viewBottom) {
            node.setViewBounds(viewLeft, viewTop, viewRight, viewBottom);
            this.mUpdateViewBoundsOperations.add(this.mOperationsQueue.createUpdateViewBounds(node.getReactTag(), viewLeft, viewTop, viewRight, viewBottom));
        }
    }

    private boolean collectStateForMountableNode(FlatShadowNode node, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        boolean expectingUpdate = node.hasNewLayout() || node.isUpdated() || node.hasUnseenUpdates() || node.clipBoundsChanged(clipLeft, clipTop, clipRight, clipBottom);
        if (!expectingUpdate) {
            return false;
        }
        node.setClipBounds(clipLeft, clipTop, clipRight, clipBottom);
        this.mDrawCommands.start(node.getDrawCommands());
        this.mAttachDetachListeners.start(node.getAttachDetachListeners());
        this.mNodeRegions.start(node.getNodeRegions());
        this.mNativeChildren.start(node.getNativeChildren());
        boolean isAndroidView = false;
        boolean needsCustomLayoutForChildren = false;
        if (node instanceof AndroidView) {
            AndroidView androidView = (AndroidView) node;
            updateViewPadding(androidView, node.getReactTag());
            isAndroidView = true;
            needsCustomLayoutForChildren = androidView.needsCustomLayoutForChildren();
            clipLeft = Float.NEGATIVE_INFINITY;
            clipTop = Float.NEGATIVE_INFINITY;
            clipRight = Float.POSITIVE_INFINITY;
            clipBottom = Float.POSITIVE_INFINITY;
        }
        if (!isAndroidView && node.isVirtualAnchor()) {
            addNodeRegion(node, left, top, right, bottom, true);
        }
        boolean descendantUpdated = collectStateRecursively(node, left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom, isAndroidView, needsCustomLayoutForChildren);
        boolean shouldUpdateMountState = false;
        DrawCommand[] drawCommands = (DrawCommand[]) this.mDrawCommands.finish();
        if (drawCommands != null) {
            shouldUpdateMountState = true;
            node.setDrawCommands(drawCommands);
        }
        AttachDetachListener[] listeners = (AttachDetachListener[]) this.mAttachDetachListeners.finish();
        if (listeners != null) {
            shouldUpdateMountState = true;
            node.setAttachDetachListeners(listeners);
        }
        NodeRegion[] nodeRegions = (NodeRegion[]) this.mNodeRegions.finish();
        if (nodeRegions != null) {
            shouldUpdateMountState = true;
            node.setNodeRegions(nodeRegions);
        } else if (descendantUpdated) {
            node.updateOverflowsContainer();
        }
        FlatShadowNode[] nativeChildren = (FlatShadowNode[]) this.mNativeChildren.finish();
        if (shouldUpdateMountState) {
            if (node.clipsSubviews()) {
                float[] commandMaxBottom = EMPTY_FLOAT_ARRAY;
                float[] commandMinTop = EMPTY_FLOAT_ARRAY;
                SparseIntArray drawViewIndexMap = EMPTY_SPARSE_INT;
                if (drawCommands != null) {
                    drawViewIndexMap = new SparseIntArray();
                    commandMaxBottom = new float[drawCommands.length];
                    commandMinTop = new float[drawCommands.length];
                    if (node.isHorizontal()) {
                        HorizontalDrawCommandManager.fillMaxMinArrays(drawCommands, commandMaxBottom, commandMinTop, drawViewIndexMap);
                    } else {
                        VerticalDrawCommandManager.fillMaxMinArrays(drawCommands, commandMaxBottom, commandMinTop, drawViewIndexMap);
                    }
                }
                float[] regionMaxBottom = EMPTY_FLOAT_ARRAY;
                float[] regionMinTop = EMPTY_FLOAT_ARRAY;
                if (nodeRegions != null) {
                    regionMaxBottom = new float[nodeRegions.length];
                    regionMinTop = new float[nodeRegions.length];
                    if (node.isHorizontal()) {
                        HorizontalDrawCommandManager.fillMaxMinArrays(nodeRegions, regionMaxBottom, regionMinTop);
                    } else {
                        VerticalDrawCommandManager.fillMaxMinArrays(nodeRegions, regionMaxBottom, regionMinTop);
                    }
                }
                this.mOperationsQueue.enqueueUpdateClippingMountState(node.getReactTag(), drawCommands, drawViewIndexMap, commandMaxBottom, commandMinTop, listeners, nodeRegions, regionMaxBottom, regionMinTop, nativeChildren != null);
            } else {
                this.mOperationsQueue.enqueueUpdateMountState(node.getReactTag(), drawCommands, listeners, nodeRegions);
            }
        }
        if (node.hasUnseenUpdates()) {
            node.onCollectExtraUpdates(this.mOperationsQueue);
            node.markUpdateSeen();
        }
        if (nativeChildren != null) {
            updateNativeChildren(node, node.getNativeChildren(), nativeChildren);
        }
        boolean updated = shouldUpdateMountState || nativeChildren != null || descendantUpdated;
        if (expectingUpdate || !updated) {
            return updated;
        }
        throw new RuntimeException("Node " + node.getReactTag() + " updated unexpectedly.");
    }

    private void updateNativeChildren(FlatShadowNode node, FlatShadowNode[] oldNativeChildren, FlatShadowNode[] newNativeChildren) {
        int[] viewsToAdd;
        int i = 0;
        node.setNativeChildren(newNativeChildren);
        if (this.mDetachAllChildrenFromViews == null) {
            this.mDetachAllChildrenFromViews = this.mOperationsQueue.enqueueDetachAllChildrenFromViews();
        }
        if (oldNativeChildren.length != 0) {
            this.mViewsToDetachAllChildrenFrom.add(node);
        }
        int tag = node.getReactTag();
        int numViewsToAdd = newNativeChildren.length;
        if (numViewsToAdd == 0) {
            viewsToAdd = EMPTY_INT_ARRAY;
        } else {
            viewsToAdd = new int[numViewsToAdd];
            int i2 = 0;
            for (FlatShadowNode child : newNativeChildren) {
                if (child.getNativeParentTag() == tag) {
                    viewsToAdd[i2] = -child.getReactTag();
                } else {
                    viewsToAdd[i2] = child.getReactTag();
                }
                child.setNativeParentTag(-1);
                i2++;
            }
        }
        for (FlatShadowNode child2 : oldNativeChildren) {
            if (child2.getNativeParentTag() == tag) {
                this.mViewsToDetach.add(child2);
                child2.setNativeParentTag(-1);
            }
        }
        int[] viewsToDetach = collectViewTags(this.mViewsToDetach);
        this.mViewsToDetach.clear();
        int length = newNativeChildren.length;
        while (i < length) {
            newNativeChildren[i].setNativeParentTag(tag);
            i++;
        }
        this.mOperationsQueue.enqueueUpdateViewGroup(tag, viewsToAdd, viewsToDetach);
    }

    private boolean collectStateRecursively(FlatShadowNode node, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom, boolean isAndroidView, boolean needsCustomLayoutForChildren) {
        if (node.hasNewLayout()) {
            node.markLayoutSeen();
        }
        float roundedLeft = roundToPixel(left);
        float roundedTop = roundToPixel(top);
        float roundedRight = roundToPixel(right);
        float roundedBottom = roundToPixel(bottom);
        if (node.shouldNotifyOnLayout()) {
            OnLayoutEvent layoutEvent = node.obtainLayoutEvent(Math.round(node.getLayoutX()), Math.round(node.getLayoutY()), (int) (roundedRight - roundedLeft), (int) (roundedBottom - roundedTop));
            if (layoutEvent != null) {
                this.mOnLayoutEvents.add(layoutEvent);
            }
        }
        if (node.clipToBounds()) {
            clipLeft = Math.max(left, clipLeft);
            clipTop = Math.max(top, clipTop);
            clipRight = Math.min(right, clipRight);
            clipBottom = Math.min(bottom, clipBottom);
        }
        node.collectState(this, roundedLeft, roundedTop, roundedRight, roundedBottom, roundToPixel(clipLeft), roundToPixel(clipTop), roundToPixel(clipRight), clipBottom);
        boolean updated = false;
        int childCount = node.getChildCount();
        for (int i = 0; i != childCount; i++) {
            ReactShadowNode child = node.getChildAt(i);
            if (!child.isVirtual()) {
                updated |= processNodeAndCollectState((FlatShadowNode) child, left, top, clipLeft, clipTop, clipRight, clipBottom, isAndroidView, needsCustomLayoutForChildren);
            }
        }
        node.resetUpdated();
        return updated;
    }

    private boolean processNodeAndCollectState(FlatShadowNode node, float parentLeft, float parentTop, float parentClipLeft, float parentClipTop, float parentClipRight, float parentClipBottom, boolean parentIsAndroidView, boolean needsCustomLayout) {
        float left = parentLeft + node.getLayoutX();
        float top = parentTop + node.getLayoutY();
        float right = left + node.getLayoutWidth();
        float bottom = top + node.getLayoutHeight();
        boolean mountsToView = node.mountsToView();
        if (!parentIsAndroidView) {
            addNodeRegion(node, left, top, right, bottom, !mountsToView);
        }
        if (!mountsToView) {
            return collectStateRecursively(node, left, top, right, bottom, parentClipLeft, parentClipTop, parentClipRight, parentClipBottom, false, false);
        }
        ensureBackingViewIsCreated(node);
        addNativeChild(node);
        boolean updated = collectStateForMountableNode(node, 0.0f, 0.0f, right - left, bottom - top, parentClipLeft - left, parentClipTop - top, parentClipRight - left, parentClipBottom - top);
        if (!parentIsAndroidView) {
            this.mDrawCommands.add(node.collectDrawView(left, top, right, bottom, parentClipLeft, parentClipTop, parentClipRight, parentClipBottom));
        }
        if (needsCustomLayout) {
            return updated;
        }
        updateViewBounds(node, left, top, right, bottom);
        return updated;
    }

    private void updateViewPadding(AndroidView androidView, int reactTag) {
        if (androidView.isPaddingChanged()) {
            this.mOperationsQueue.enqueueSetPadding(reactTag, Math.round(androidView.getPadding(0)), Math.round(androidView.getPadding(1)), Math.round(androidView.getPadding(2)), Math.round(androidView.getPadding(3)));
            androidView.resetPaddingChanged();
        }
    }

    private static int[] collectViewTags(ArrayList<FlatShadowNode> views) {
        int numViews = views.size();
        if (numViews == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] viewTags = new int[numViews];
        for (int i = 0; i < numViews; i++) {
            viewTags[i] = ((FlatShadowNode) views.get(i)).getReactTag();
        }
        return viewTags;
    }

    private static float roundToPixel(float pos) {
        return (float) Math.floor((double) (0.5f + pos));
    }
}
