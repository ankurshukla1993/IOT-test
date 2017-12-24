package com.facebook.react.uimanager;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaValue;
import com.facebook.yoga.YogaWrap;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

@ReactPropertyHolder
public class ReactShadowNode {
    private float mAbsoluteBottom;
    private float mAbsoluteLeft;
    private float mAbsoluteRight;
    private float mAbsoluteTop;
    @Nullable
    private ArrayList<ReactShadowNode> mChildren;
    private final Spacing mDefaultPadding = new Spacing(0.0f);
    private boolean mIsLayoutOnly;
    @Nullable
    private ArrayList<ReactShadowNode> mNativeChildren;
    @Nullable
    private ReactShadowNode mNativeParent;
    private boolean mNodeUpdated = true;
    private final float[] mPadding = new float[9];
    private final boolean[] mPaddingIsPercent = new boolean[9];
    @Nullable
    private ReactShadowNode mParent;
    private int mReactTag;
    @Nullable
    private ReactShadowNode mRootNode;
    private boolean mShouldNotifyOnLayout;
    @Nullable
    private ThemedReactContext mThemedContext;
    private int mTotalNativeChildren = 0;
    @Nullable
    private String mViewClassName;
    private final YogaNode mYogaNode;

    public ReactShadowNode() {
        if (isVirtual()) {
            this.mYogaNode = null;
            return;
        }
        YogaNode node = (YogaNode) YogaNodePool.get().acquire();
        if (node == null) {
            node = new YogaNode();
        }
        this.mYogaNode = node;
        Arrays.fill(this.mPadding, YogaConstants.UNDEFINED);
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return false;
    }

    public final String getViewClass() {
        return (String) Assertions.assertNotNull(this.mViewClassName);
    }

    public final boolean hasUpdates() {
        return this.mNodeUpdated || hasNewLayout() || isDirty();
    }

    public final void markUpdateSeen() {
        this.mNodeUpdated = false;
        if (hasNewLayout()) {
            markLayoutSeen();
        }
    }

    public void markUpdated() {
        if (!this.mNodeUpdated) {
            this.mNodeUpdated = true;
            ReactShadowNode parent = getParent();
            if (parent != null) {
                parent.markUpdated();
            }
        }
    }

    public final boolean hasUnseenUpdates() {
        return this.mNodeUpdated;
    }

    public void dirty() {
        if (!isVirtual()) {
            this.mYogaNode.dirty();
        }
    }

    public final boolean isDirty() {
        return this.mYogaNode != null && this.mYogaNode.isDirty();
    }

    public void addChildAt(ReactShadowNode child, int i) {
        if (child.mParent != null) {
            throw new IllegalViewOperationException("Tried to add child that already has a parent! Remove it from its parent first.");
        }
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(4);
        }
        this.mChildren.add(i, child);
        child.mParent = this;
        if (!(this.mYogaNode == null || this.mYogaNode.isMeasureDefined())) {
            YogaNode childYogaNode = child.mYogaNode;
            if (childYogaNode == null) {
                throw new RuntimeException("Cannot add a child that doesn't have a YogaNode to a parent without a measure function! (Trying to add a '" + child.getClass().getSimpleName() + "' to a '" + getClass().getSimpleName() + "')");
            }
            this.mYogaNode.addChildAt(childYogaNode, i);
        }
        markUpdated();
        int increase = child.mIsLayoutOnly ? child.mTotalNativeChildren : 1;
        this.mTotalNativeChildren += increase;
        updateNativeChildrenCountInParent(increase);
    }

    public ReactShadowNode removeChildAt(int i) {
        if (this.mChildren == null) {
            throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
        }
        ReactShadowNode removed = (ReactShadowNode) this.mChildren.remove(i);
        removed.mParent = null;
        if (!(this.mYogaNode == null || this.mYogaNode.isMeasureDefined())) {
            this.mYogaNode.removeChildAt(i);
        }
        markUpdated();
        int decrease = removed.mIsLayoutOnly ? removed.mTotalNativeChildren : 1;
        this.mTotalNativeChildren -= decrease;
        updateNativeChildrenCountInParent(-decrease);
        return removed;
    }

    public final int getChildCount() {
        return this.mChildren == null ? 0 : this.mChildren.size();
    }

    public final ReactShadowNode getChildAt(int i) {
        if (this.mChildren != null) {
            return (ReactShadowNode) this.mChildren.get(i);
        }
        throw new ArrayIndexOutOfBoundsException("Index " + i + " out of bounds: node has no children");
    }

    public final int indexOf(ReactShadowNode child) {
        return this.mChildren == null ? -1 : this.mChildren.indexOf(child);
    }

    public void removeAndDisposeAllChildren() {
        if (getChildCount() != 0) {
            int decrease = 0;
            for (int i = getChildCount() - 1; i >= 0; i--) {
                int i2;
                if (!(this.mYogaNode == null || this.mYogaNode.isMeasureDefined())) {
                    this.mYogaNode.removeChildAt(i);
                }
                ReactShadowNode toRemove = getChildAt(i);
                toRemove.mParent = null;
                toRemove.dispose();
                if (toRemove.mIsLayoutOnly) {
                    i2 = toRemove.mTotalNativeChildren;
                } else {
                    i2 = 1;
                }
                decrease += i2;
            }
            ((ArrayList) Assertions.assertNotNull(this.mChildren)).clear();
            markUpdated();
            this.mTotalNativeChildren -= decrease;
            updateNativeChildrenCountInParent(-decrease);
        }
    }

    private void updateNativeChildrenCountInParent(int delta) {
        if (this.mIsLayoutOnly) {
            ReactShadowNode parent = getParent();
            while (parent != null) {
                parent.mTotalNativeChildren += delta;
                if (parent.mIsLayoutOnly) {
                    parent = parent.getParent();
                } else {
                    return;
                }
            }
        }
    }

    public void onBeforeLayout() {
    }

    public final void updateProperties(ReactStylesDiffMap props) {
        ViewManagerPropertyUpdater.updateProps(this, props);
        onAfterUpdateTransaction();
    }

    public void onAfterUpdateTransaction() {
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
    }

    boolean dispatchUpdates(float absoluteX, float absoluteY, UIViewOperationQueue uiViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        if (this.mNodeUpdated) {
            onCollectExtraUpdates(uiViewOperationQueue);
        }
        if (!hasNewLayout()) {
            return false;
        }
        this.mAbsoluteLeft = (float) Math.round(getLayoutX() + absoluteX);
        this.mAbsoluteTop = (float) Math.round(getLayoutY() + absoluteY);
        this.mAbsoluteRight = (float) Math.round((getLayoutX() + absoluteX) + getLayoutWidth());
        this.mAbsoluteBottom = (float) Math.round((getLayoutY() + absoluteY) + getLayoutHeight());
        nativeViewHierarchyOptimizer.handleUpdateLayout(this);
        return true;
    }

    public final int getReactTag() {
        return this.mReactTag;
    }

    public void setReactTag(int reactTag) {
        this.mReactTag = reactTag;
    }

    public final ReactShadowNode getRootNode() {
        return (ReactShadowNode) Assertions.assertNotNull(this.mRootNode);
    }

    final void setRootNode(ReactShadowNode rootNode) {
        this.mRootNode = rootNode;
    }

    final void setViewClassName(String viewClassName) {
        this.mViewClassName = viewClassName;
    }

    @Nullable
    public final ReactShadowNode getParent() {
        return this.mParent;
    }

    public final ThemedReactContext getThemedContext() {
        return (ThemedReactContext) Assertions.assertNotNull(this.mThemedContext);
    }

    public void setThemedContext(ThemedReactContext themedContext) {
        this.mThemedContext = themedContext;
    }

    public final boolean shouldNotifyOnLayout() {
        return this.mShouldNotifyOnLayout;
    }

    public void calculateLayout() {
        this.mYogaNode.calculateLayout();
    }

    public final boolean hasNewLayout() {
        return this.mYogaNode == null ? false : this.mYogaNode.hasNewLayout();
    }

    public final void markLayoutSeen() {
        if (this.mYogaNode != null) {
            this.mYogaNode.markLayoutSeen();
        }
    }

    public final void addNativeChildAt(ReactShadowNode child, int nativeIndex) {
        boolean z = true;
        Assertions.assertCondition(!this.mIsLayoutOnly);
        if (child.mIsLayoutOnly) {
            z = false;
        }
        Assertions.assertCondition(z);
        if (this.mNativeChildren == null) {
            this.mNativeChildren = new ArrayList(4);
        }
        this.mNativeChildren.add(nativeIndex, child);
        child.mNativeParent = this;
    }

    public final ReactShadowNode removeNativeChildAt(int i) {
        Assertions.assertNotNull(this.mNativeChildren);
        ReactShadowNode removed = (ReactShadowNode) this.mNativeChildren.remove(i);
        removed.mNativeParent = null;
        return removed;
    }

    public final void removeAllNativeChildren() {
        if (this.mNativeChildren != null) {
            for (int i = this.mNativeChildren.size() - 1; i >= 0; i--) {
                ((ReactShadowNode) this.mNativeChildren.get(i)).mNativeParent = null;
            }
            this.mNativeChildren.clear();
        }
    }

    public final int getNativeChildCount() {
        return this.mNativeChildren == null ? 0 : this.mNativeChildren.size();
    }

    public final int indexOfNativeChild(ReactShadowNode nativeChild) {
        Assertions.assertNotNull(this.mNativeChildren);
        return this.mNativeChildren.indexOf(nativeChild);
    }

    @Nullable
    public final ReactShadowNode getNativeParent() {
        return this.mNativeParent;
    }

    public final void setIsLayoutOnly(boolean isLayoutOnly) {
        boolean z;
        boolean z2 = true;
        if (getParent() == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.assertCondition(z, "Must remove from no opt parent first");
        if (this.mNativeParent == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.assertCondition(z, "Must remove from native parent first");
        if (getNativeChildCount() != 0) {
            z2 = false;
        }
        Assertions.assertCondition(z2, "Must remove all native children first");
        this.mIsLayoutOnly = isLayoutOnly;
    }

    public final boolean isLayoutOnly() {
        return this.mIsLayoutOnly;
    }

    public final int getTotalNativeChildren() {
        return this.mTotalNativeChildren;
    }

    public final int getNativeOffsetForChild(ReactShadowNode child) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < getChildCount(); i++) {
            ReactShadowNode current = getChildAt(i);
            if (child == current) {
                found = true;
                break;
            }
            int totalNativeChildren;
            if (current.mIsLayoutOnly) {
                totalNativeChildren = current.getTotalNativeChildren();
            } else {
                totalNativeChildren = 1;
            }
            index += totalNativeChildren;
        }
        if (found) {
            return index;
        }
        throw new RuntimeException("Child " + child.mReactTag + " was not a child of " + this.mReactTag);
    }

    public final float getLayoutX() {
        return this.mYogaNode.getLayoutX();
    }

    public final float getLayoutY() {
        return this.mYogaNode.getLayoutY();
    }

    public final float getLayoutWidth() {
        return this.mYogaNode.getLayoutWidth();
    }

    public final float getLayoutHeight() {
        return this.mYogaNode.getLayoutHeight();
    }

    public int getScreenX() {
        return Math.round(getLayoutX());
    }

    public int getScreenY() {
        return Math.round(getLayoutY());
    }

    public int getScreenWidth() {
        return Math.round(this.mAbsoluteRight - this.mAbsoluteLeft);
    }

    public int getScreenHeight() {
        return Math.round(this.mAbsoluteBottom - this.mAbsoluteTop);
    }

    public final YogaDirection getLayoutDirection() {
        return this.mYogaNode.getLayoutDirection();
    }

    public void setLayoutDirection(YogaDirection direction) {
        this.mYogaNode.setDirection(direction);
    }

    public final YogaValue getStyleWidth() {
        return this.mYogaNode.getWidth();
    }

    public void setStyleWidth(float widthPx) {
        this.mYogaNode.setWidth(widthPx);
    }

    public void setStyleWidthPercent(float percent) {
        this.mYogaNode.setWidthPercent(percent);
    }

    public void setStyleMinWidth(float widthPx) {
        this.mYogaNode.setMinWidth(widthPx);
    }

    public void setStyleMinWidthPercent(float percent) {
        this.mYogaNode.setMinWidthPercent(percent);
    }

    public void setStyleMaxWidth(float widthPx) {
        this.mYogaNode.setMaxWidth(widthPx);
    }

    public void setStyleMaxWidthPercent(float percent) {
        this.mYogaNode.setMaxWidthPercent(percent);
    }

    public final YogaValue getStyleHeight() {
        return this.mYogaNode.getHeight();
    }

    public void setStyleHeight(float heightPx) {
        this.mYogaNode.setHeight(heightPx);
    }

    public void setStyleHeightPercent(float percent) {
        this.mYogaNode.setHeightPercent(percent);
    }

    public void setStyleMinHeight(float widthPx) {
        this.mYogaNode.setMinHeight(widthPx);
    }

    public void setStyleMinHeightPercent(float percent) {
        this.mYogaNode.setMinHeightPercent(percent);
    }

    public void setStyleMaxHeight(float widthPx) {
        this.mYogaNode.setMaxHeight(widthPx);
    }

    public void setStyleMaxHeightPercent(float percent) {
        this.mYogaNode.setMaxHeightPercent(percent);
    }

    public void setFlex(float flex) {
        this.mYogaNode.setFlex(flex);
    }

    public void setFlexGrow(float flexGrow) {
        this.mYogaNode.setFlexGrow(flexGrow);
    }

    public void setFlexShrink(float flexShrink) {
        this.mYogaNode.setFlexShrink(flexShrink);
    }

    public void setFlexBasis(float flexBasis) {
        this.mYogaNode.setFlexBasis(flexBasis);
    }

    public void setFlexBasisPercent(float percent) {
        this.mYogaNode.setFlexBasisPercent(percent);
    }

    public void setStyleAspectRatio(float aspectRatio) {
        this.mYogaNode.setAspectRatio(aspectRatio);
    }

    public void setFlexDirection(YogaFlexDirection flexDirection) {
        this.mYogaNode.setFlexDirection(flexDirection);
    }

    public void setFlexWrap(YogaWrap wrap) {
        this.mYogaNode.setWrap(wrap);
    }

    public void setAlignSelf(YogaAlign alignSelf) {
        this.mYogaNode.setAlignSelf(alignSelf);
    }

    public void setAlignItems(YogaAlign alignItems) {
        this.mYogaNode.setAlignItems(alignItems);
    }

    public void setJustifyContent(YogaJustify justifyContent) {
        this.mYogaNode.setJustifyContent(justifyContent);
    }

    public void setOverflow(YogaOverflow overflow) {
        this.mYogaNode.setOverflow(overflow);
    }

    public void setMargin(int spacingType, float margin) {
        this.mYogaNode.setMargin(YogaEdge.fromInt(spacingType), margin);
    }

    public void setMarginPercent(int spacingType, float percent) {
        this.mYogaNode.setMarginPercent(YogaEdge.fromInt(spacingType), percent);
    }

    public final float getPadding(int spacingType) {
        return this.mYogaNode.getLayoutPadding(YogaEdge.fromInt(spacingType));
    }

    public final YogaValue getStylePadding(int spacingType) {
        return this.mYogaNode.getPadding(YogaEdge.fromInt(spacingType));
    }

    public void setDefaultPadding(int spacingType, float padding) {
        this.mDefaultPadding.set(spacingType, padding);
        updatePadding();
    }

    public void setPadding(int spacingType, float padding) {
        this.mPadding[spacingType] = padding;
        this.mPaddingIsPercent[spacingType] = false;
        updatePadding();
    }

    public void setPaddingPercent(int spacingType, float percent) {
        this.mPadding[spacingType] = percent;
        this.mPaddingIsPercent[spacingType] = !YogaConstants.isUndefined(percent);
        updatePadding();
    }

    private void updatePadding() {
        int spacingType = 0;
        while (spacingType <= 8) {
            if (spacingType == 0 || spacingType == 2 || spacingType == 4 || spacingType == 5) {
                if (YogaConstants.isUndefined(this.mPadding[spacingType]) && YogaConstants.isUndefined(this.mPadding[6]) && YogaConstants.isUndefined(this.mPadding[8])) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
                }
                if (this.mPaddingIsPercent[spacingType]) {
                    this.mYogaNode.setPaddingPercent(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                } else {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                }
            } else if (spacingType == 1 || spacingType == 3) {
                if (YogaConstants.isUndefined(this.mPadding[spacingType]) && YogaConstants.isUndefined(this.mPadding[7]) && YogaConstants.isUndefined(this.mPadding[8])) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
                }
                if (this.mPaddingIsPercent[spacingType]) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                } else {
                    this.mYogaNode.setPaddingPercent(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                }
            } else {
                if (YogaConstants.isUndefined(this.mPadding[spacingType])) {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mDefaultPadding.getRaw(spacingType));
                }
                if (this.mPaddingIsPercent[spacingType]) {
                    this.mYogaNode.setPaddingPercent(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                } else {
                    this.mYogaNode.setPadding(YogaEdge.fromInt(spacingType), this.mPadding[spacingType]);
                }
            }
            spacingType++;
        }
    }

    public void setBorder(int spacingType, float borderWidth) {
        this.mYogaNode.setBorder(YogaEdge.fromInt(spacingType), borderWidth);
    }

    public void setPosition(int spacingType, float position) {
        this.mYogaNode.setPosition(YogaEdge.fromInt(spacingType), position);
    }

    public void setPositionPercent(int spacingType, float percent) {
        this.mYogaNode.setPositionPercent(YogaEdge.fromInt(spacingType), percent);
    }

    public void setPositionType(YogaPositionType positionType) {
        this.mYogaNode.setPositionType(positionType);
    }

    public void setShouldNotifyOnLayout(boolean shouldNotifyOnLayout) {
        this.mShouldNotifyOnLayout = shouldNotifyOnLayout;
    }

    public void setMeasureFunction(YogaMeasureFunction measureFunction) {
        if (((measureFunction == null ? 1 : 0) ^ this.mYogaNode.isMeasureDefined()) == 0 || getChildCount() == 0) {
            this.mYogaNode.setMeasureFunction(measureFunction);
            return;
        }
        throw new RuntimeException("Since a node with a measure function does not add any native yoga children, it's not safe to transition to/from having a measure function unless a node has no children");
    }

    public String toString() {
        return this.mYogaNode.toString();
    }

    public void dispose() {
        if (this.mYogaNode != null) {
            this.mYogaNode.reset();
            YogaNodePool.get().release(this.mYogaNode);
        }
    }
}
