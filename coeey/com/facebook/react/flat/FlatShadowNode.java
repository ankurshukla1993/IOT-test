package com.facebook.react.flat;

import android.graphics.Rect;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

class FlatShadowNode extends LayoutShadowNode {
    static final FlatShadowNode[] EMPTY_ARRAY = new FlatShadowNode[0];
    private static final DrawView EMPTY_DRAW_VIEW = new DrawView(0);
    private static final Rect LOGICAL_OFFSET_EMPTY = new Rect();
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_DECOMPOSED_MATRIX = "decomposedMatrix";
    protected static final String PROP_HORIZONTAL = "horizontal";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    private static final String PROP_OPACITY = "opacity";
    protected static final String PROP_REMOVE_CLIPPED_SUBVIEWS = "removeClippedSubviews";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private AttachDetachListener[] mAttachDetachListeners = AttachDetachListener.EMPTY_ARRAY;
    private boolean mBackingViewIsCreated;
    private float mClipBottom;
    private float mClipLeft;
    float mClipRadius;
    private float mClipRight;
    boolean mClipToBounds = false;
    private float mClipTop;
    @Nullable
    private DrawBackgroundColor mDrawBackground;
    private DrawCommand[] mDrawCommands = DrawCommand.EMPTY_ARRAY;
    @Nullable
    private DrawView mDrawView;
    private boolean mForceMountChildrenToView;
    private boolean mIsUpdated = true;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private int mLayoutX;
    private int mLayoutY;
    private Rect mLogicalOffset = LOGICAL_OFFSET_EMPTY;
    private FlatShadowNode[] mNativeChildren = EMPTY_ARRAY;
    private int mNativeParentTag;
    private NodeRegion mNodeRegion = NodeRegion.EMPTY;
    private NodeRegion[] mNodeRegions = NodeRegion.EMPTY_ARRAY;
    private boolean mOverflowsContainer;
    private int mViewBottom;
    private int mViewLeft;
    private int mViewRight;
    private int mViewTop;

    FlatShadowNode() {
    }

    void handleUpdateProperties(ReactStylesDiffMap styles) {
        if (!mountsToView()) {
            if (styles.hasKey(PROP_DECOMPOSED_MATRIX) || styles.hasKey(PROP_OPACITY) || styles.hasKey(PROP_RENDER_TO_HARDWARE_TEXTURE) || styles.hasKey("testID") || styles.hasKey(PROP_ACCESSIBILITY_LABEL) || styles.hasKey(PROP_ACCESSIBILITY_COMPONENT_TYPE) || styles.hasKey(PROP_ACCESSIBILITY_LIVE_REGION) || styles.hasKey(PROP_TRANSFORM) || styles.hasKey(PROP_IMPORTANT_FOR_ACCESSIBILITY) || styles.hasKey("removeClippedSubviews")) {
                forceMountToView();
            }
        }
    }

    final void forceMountChildrenToView() {
        if (!this.mForceMountChildrenToView) {
            this.mForceMountChildrenToView = true;
            int childCount = getChildCount();
            for (int i = 0; i != childCount; i++) {
                ReactShadowNode child = getChildAt(i);
                if (child instanceof FlatShadowNode) {
                    ((FlatShadowNode) child).forceMountToView();
                }
            }
        }
    }

    protected void collectState(StateBuilder stateBuilder, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        if (this.mDrawBackground != null) {
            this.mDrawBackground = (DrawBackgroundColor) this.mDrawBackground.updateBoundsAndFreeze(left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
            stateBuilder.addDrawCommand(this.mDrawBackground);
        }
    }

    boolean doesDraw() {
        return (this.mDrawView == null && this.mDrawBackground == null) ? false : true;
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(int backgroundColor) {
        this.mDrawBackground = backgroundColor == 0 ? null : new DrawBackgroundColor(backgroundColor);
        invalidate();
    }

    public void setOverflow(String overflow) {
        super.setOverflow(overflow);
        this.mClipToBounds = "hidden".equals(overflow);
        if (this.mClipToBounds) {
            this.mOverflowsContainer = false;
            if (this.mClipRadius > 0.5f) {
                forceMountToView();
            }
        } else {
            updateOverflowsContainer();
        }
        invalidate();
    }

    public final boolean clipToBounds() {
        return this.mClipToBounds;
    }

    public final int getScreenX() {
        return this.mViewLeft;
    }

    public final int getScreenY() {
        return this.mViewTop;
    }

    public final int getScreenWidth() {
        if (mountsToView()) {
            return this.mViewRight - this.mViewLeft;
        }
        return Math.round(this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
    }

    public final int getScreenHeight() {
        if (mountsToView()) {
            return this.mViewBottom - this.mViewTop;
        }
        return Math.round(this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
    }

    public void addChildAt(ReactShadowNode child, int i) {
        super.addChildAt(child, i);
        if (this.mForceMountChildrenToView && (child instanceof FlatShadowNode)) {
            ((FlatShadowNode) child).forceMountToView();
        }
    }

    protected final void invalidate() {
        FlatShadowNode node = this;
        while (true) {
            if (node.mountsToView()) {
                if (!node.mIsUpdated) {
                    node.mIsUpdated = true;
                } else {
                    return;
                }
            }
            ReactShadowNode parent = node.getParent();
            if (parent != null) {
                node = (FlatShadowNode) parent;
            } else {
                return;
            }
        }
    }

    public void markUpdated() {
        super.markUpdated();
        this.mIsUpdated = true;
        invalidate();
    }

    final boolean isUpdated() {
        return this.mIsUpdated;
    }

    final void resetUpdated() {
        this.mIsUpdated = false;
    }

    final boolean clipBoundsChanged(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        return (this.mClipLeft == clipLeft && this.mClipTop == clipTop && this.mClipRight == clipRight && this.mClipBottom == clipBottom) ? false : true;
    }

    final void setClipBounds(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        this.mClipLeft = clipLeft;
        this.mClipTop = clipTop;
        this.mClipRight = clipRight;
        this.mClipBottom = clipBottom;
    }

    final DrawCommand[] getDrawCommands() {
        return this.mDrawCommands;
    }

    final void setDrawCommands(DrawCommand[] drawCommands) {
        this.mDrawCommands = drawCommands;
    }

    final void setAttachDetachListeners(AttachDetachListener[] listeners) {
        this.mAttachDetachListeners = listeners;
    }

    final AttachDetachListener[] getAttachDetachListeners() {
        return this.mAttachDetachListeners;
    }

    final FlatShadowNode[] getNativeChildren() {
        return this.mNativeChildren;
    }

    final void setNativeChildren(FlatShadowNode[] nativeChildren) {
        this.mNativeChildren = nativeChildren;
    }

    final int getNativeParentTag() {
        return this.mNativeParentTag;
    }

    final void setNativeParentTag(int nativeParentTag) {
        this.mNativeParentTag = nativeParentTag;
    }

    final NodeRegion[] getNodeRegions() {
        return this.mNodeRegions;
    }

    final void setNodeRegions(NodeRegion[] nodeRegion) {
        this.mNodeRegions = nodeRegion;
        updateOverflowsContainer();
    }

    final void updateOverflowsContainer() {
        boolean overflowsContainer = false;
        int width = (int) (this.mNodeRegion.getRight() - this.mNodeRegion.getLeft());
        int height = (int) (this.mNodeRegion.getBottom() - this.mNodeRegion.getTop());
        float leftBound = 0.0f;
        float rightBound = (float) width;
        float topBound = 0.0f;
        float bottomBound = (float) height;
        Rect rect = null;
        if (!this.mClipToBounds && height > 0 && width > 0) {
            for (NodeRegion region : this.mNodeRegions) {
                if (region.getLeft() < leftBound) {
                    leftBound = region.getLeft();
                    overflowsContainer = true;
                }
                if (region.getRight() > rightBound) {
                    rightBound = region.getRight();
                    overflowsContainer = true;
                }
                if (region.getTop() < topBound) {
                    topBound = region.getTop();
                    overflowsContainer = true;
                }
                if (region.getBottom() > bottomBound) {
                    bottomBound = region.getBottom();
                    overflowsContainer = true;
                }
            }
            if (overflowsContainer) {
                rect = new Rect((int) leftBound, (int) topBound, (int) (rightBound - ((float) width)), (int) (bottomBound - ((float) height)));
            }
        }
        if (!(overflowsContainer || this.mNodeRegion == NodeRegion.EMPTY)) {
            int children = getChildCount();
            for (int i = 0; i < children; i++) {
                ReactShadowNode node = getChildAt(i);
                if ((node instanceof FlatShadowNode) && ((FlatShadowNode) node).mOverflowsContainer) {
                    Rect childLogicalOffset = ((FlatShadowNode) node).mLogicalOffset;
                    if (rect == null) {
                        rect = new Rect();
                    }
                    rect.union(childLogicalOffset);
                    overflowsContainer = true;
                }
            }
        }
        if (this.mOverflowsContainer != overflowsContainer) {
            this.mOverflowsContainer = overflowsContainer;
            if (rect == null) {
                rect = LOGICAL_OFFSET_EMPTY;
            }
            this.mLogicalOffset = rect;
        }
    }

    void updateNodeRegion(float left, float top, float right, float bottom, boolean isVirtual) {
        if (!this.mNodeRegion.matches(left, top, right, bottom, isVirtual)) {
            setNodeRegion(new NodeRegion(left, top, right, bottom, getReactTag(), isVirtual));
        }
    }

    protected final void setNodeRegion(NodeRegion nodeRegion) {
        this.mNodeRegion = nodeRegion;
        updateOverflowsContainer();
    }

    final NodeRegion getNodeRegion() {
        return this.mNodeRegion;
    }

    final void setViewBounds(int left, int top, int right, int bottom) {
        this.mViewLeft = left;
        this.mViewTop = top;
        this.mViewRight = right;
        this.mViewBottom = bottom;
    }

    final int getViewLeft() {
        return this.mViewLeft;
    }

    final int getViewTop() {
        return this.mViewTop;
    }

    final int getViewRight() {
        return this.mViewRight;
    }

    final int getViewBottom() {
        return this.mViewBottom;
    }

    final void forceMountToView() {
        if (!isVirtual() && this.mDrawView == null) {
            this.mDrawView = EMPTY_DRAW_VIEW;
            invalidate();
            this.mNodeRegion = NodeRegion.EMPTY;
        }
    }

    final DrawView collectDrawView(float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        Assertions.assumeNotNull(this.mDrawView);
        if (this.mDrawView == EMPTY_DRAW_VIEW) {
            this.mDrawView = new DrawView(getReactTag());
        }
        this.mDrawView = this.mDrawView.collectDrawView(left, top, right, bottom, left + ((float) this.mLogicalOffset.left), top + ((float) this.mLogicalOffset.top), right + ((float) this.mLogicalOffset.right), bottom + ((float) this.mLogicalOffset.bottom), clipLeft, clipTop, clipRight, clipBottom, this.mClipToBounds ? this.mClipRadius : 0.0f);
        return this.mDrawView;
    }

    @Nullable
    final OnLayoutEvent obtainLayoutEvent(int x, int y, int width, int height) {
        if (this.mLayoutX == x && this.mLayoutY == y && this.mLayoutWidth == width && this.mLayoutHeight == height) {
            return null;
        }
        this.mLayoutX = x;
        this.mLayoutY = y;
        this.mLayoutWidth = width;
        this.mLayoutHeight = height;
        return OnLayoutEvent.obtain(getReactTag(), x, y, width, height);
    }

    final boolean mountsToView() {
        return this.mDrawView != null;
    }

    final boolean isBackingViewCreated() {
        return this.mBackingViewIsCreated;
    }

    final void signalBackingViewIsCreated() {
        this.mBackingViewIsCreated = true;
    }

    public boolean clipsSubviews() {
        return false;
    }

    public boolean isHorizontal() {
        return false;
    }
}
