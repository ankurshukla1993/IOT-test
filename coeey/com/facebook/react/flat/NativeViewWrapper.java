package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import javax.annotation.Nullable;

final class NativeViewWrapper extends FlatShadowNode implements AndroidView {
    private boolean mForceMountGrandChildrenToView;
    private final boolean mNeedsCustomLayoutForChildren;
    private boolean mPaddingChanged = false;
    @Nullable
    private final ReactShadowNode mReactShadowNode;

    NativeViewWrapper(ViewManager viewManager) {
        ReactShadowNode reactShadowNode = viewManager.createShadowNodeInstance();
        if (reactShadowNode instanceof YogaMeasureFunction) {
            this.mReactShadowNode = reactShadowNode;
            setMeasureFunction((YogaMeasureFunction) reactShadowNode);
        } else {
            this.mReactShadowNode = null;
        }
        if (viewManager instanceof ViewGroupManager) {
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            this.mNeedsCustomLayoutForChildren = viewGroupManager.needsCustomLayoutForChildren();
            this.mForceMountGrandChildrenToView = viewGroupManager.shouldPromoteGrandchildren();
        } else {
            this.mNeedsCustomLayoutForChildren = false;
        }
        forceMountToView();
        forceMountChildrenToView();
    }

    public boolean needsCustomLayoutForChildren() {
        return this.mNeedsCustomLayoutForChildren;
    }

    public boolean isPaddingChanged() {
        return this.mPaddingChanged;
    }

    public void resetPaddingChanged() {
        this.mPaddingChanged = false;
    }

    public void setBackgroundColor(int backgroundColor) {
    }

    public void setReactTag(int reactTag) {
        super.setReactTag(reactTag);
        if (this.mReactShadowNode != null) {
            this.mReactShadowNode.setReactTag(reactTag);
        }
    }

    public void setThemedContext(ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        if (this.mReactShadowNode != null) {
            this.mReactShadowNode.setThemedContext(themedContext);
        }
    }

    void handleUpdateProperties(ReactStylesDiffMap styles) {
        if (this.mReactShadowNode != null) {
            this.mReactShadowNode.updateProperties(styles);
        }
    }

    public void addChildAt(ReactShadowNode child, int i) {
        super.addChildAt(child, i);
        if (this.mForceMountGrandChildrenToView && (child instanceof FlatShadowNode)) {
            ((FlatShadowNode) child).forceMountChildrenToView();
        }
    }

    public void setPadding(int spacingType, float padding) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PIXEL || current.value != padding) {
            super.setPadding(spacingType, padding);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }

    public void setPaddingPercent(int spacingType, float percent) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.setPadding(spacingType, percent);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        if (this.mReactShadowNode != null && this.mReactShadowNode.hasUnseenUpdates()) {
            this.mReactShadowNode.onCollectExtraUpdates(uiViewOperationQueue);
            markUpdateSeen();
        }
    }
}
