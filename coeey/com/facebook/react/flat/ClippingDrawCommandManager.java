package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.animation.Animation;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import java.util.ArrayList;
import javax.annotation.Nullable;

abstract class ClippingDrawCommandManager extends DrawCommandManager {
    private final SparseArray<View> mClippedSubviews = new SparseArray();
    protected final Rect mClippingRect = new Rect();
    private final ArrayList<ReactClippingViewGroup> mClippingViewGroups = new ArrayList();
    protected float[] mCommandMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
    protected float[] mCommandMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
    private DrawCommand[] mDrawCommands = DrawCommand.EMPTY_ARRAY;
    private SparseIntArray mDrawViewIndexMap = StateBuilder.EMPTY_SPARSE_INT;
    private final FlatViewGroup mFlatViewGroup;
    private NodeRegion[] mNodeRegions = NodeRegion.EMPTY_ARRAY;
    protected float[] mRegionMaxBottom = StateBuilder.EMPTY_FLOAT_ARRAY;
    protected float[] mRegionMinTop = StateBuilder.EMPTY_FLOAT_ARRAY;
    private int mStart;
    private int mStop;
    private final ArrayList<View> mViewsToKeep = new ArrayList();
    private final SparseArray<View> mViewsToRemove = new SparseArray();

    abstract int commandStartIndex();

    abstract int commandStopIndex(int i);

    abstract boolean regionAboveTouch(int i, float f, float f2);

    abstract int regionStopIndex(float f, float f2);

    ClippingDrawCommandManager(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        this.mFlatViewGroup = flatViewGroup;
        initialSetup(drawCommands);
    }

    private void initialSetup(DrawCommand[] drawCommands) {
        mountDrawCommands(drawCommands, this.mDrawViewIndexMap, this.mCommandMaxBottom, this.mCommandMinTop, true);
        updateClippingRect();
    }

    public void mountDrawCommands(DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] maxBottom, float[] minTop, boolean willMountViews) {
        this.mDrawCommands = drawCommands;
        this.mCommandMaxBottom = maxBottom;
        this.mCommandMinTop = minTop;
        this.mDrawViewIndexMap = drawViewIndexMap;
        if (this.mClippingRect.bottom != this.mClippingRect.top) {
            this.mStart = commandStartIndex();
            this.mStop = commandStopIndex(this.mStart);
            if (!willMountViews) {
                updateClippingToCurrentRect();
            }
        }
    }

    public void mountNodeRegions(NodeRegion[] nodeRegions, float[] maxBottom, float[] minTop) {
        this.mNodeRegions = nodeRegions;
        this.mRegionMaxBottom = maxBottom;
        this.mRegionMinTop = minTop;
    }

    @Nullable
    public NodeRegion virtualNodeRegionWithinBounds(float touchX, float touchY) {
        int i = regionStopIndex(touchX, touchY);
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            NodeRegion nodeRegion = this.mNodeRegions[i2];
            if (!nodeRegion.mIsVirtual) {
                i = i2;
            } else if (regionAboveTouch(i2, touchX, touchY)) {
                break;
            } else if (nodeRegion.withinBounds(touchX, touchY)) {
                return nodeRegion;
            } else {
                i = i2;
            }
        }
        return null;
    }

    @Nullable
    public NodeRegion anyNodeRegionWithinBounds(float touchX, float touchY) {
        int i = regionStopIndex(touchX, touchY);
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            NodeRegion nodeRegion = this.mNodeRegions[i2];
            if (regionAboveTouch(i2, touchX, touchY)) {
                break;
            } else if (nodeRegion.withinBounds(touchX, touchY)) {
                return nodeRegion;
            } else {
                i = i2;
            }
        }
        return null;
    }

    private void clip(int id, View view) {
        this.mClippedSubviews.put(id, view);
    }

    private void unclip(int id) {
        this.mClippedSubviews.remove(id);
    }

    private boolean isClipped(int id) {
        return this.mClippedSubviews.get(id) != null;
    }

    private boolean isNotClipped(int id) {
        return this.mClippedSubviews.get(id) == null;
    }

    void onClippedViewDropped(View view) {
        unclip(view.getId());
        this.mFlatViewGroup.removeDetachedView(view);
    }

    public void mountViews(ViewResolver viewResolver, int[] viewsToAdd, int[] viewsToDetach) {
        this.mClippingViewGroups.clear();
        for (int viewToAdd : viewsToAdd) {
            int viewToAdd2;
            boolean newView;
            if (viewToAdd2 > 0) {
                newView = true;
            } else {
                newView = false;
            }
            if (!newView) {
                viewToAdd2 = -viewToAdd2;
            }
            int commandArrayIndex = this.mDrawViewIndexMap.get(viewToAdd2);
            DrawView drawView = this.mDrawCommands[commandArrayIndex];
            View view = viewResolver.getView(drawView.reactTag);
            DrawCommandManager.ensureViewHasNoParent(view);
            if ((view instanceof ReactClippingViewGroup) && ((ReactClippingViewGroup) view).getRemoveClippedSubviews()) {
                this.mClippingViewGroups.add((ReactClippingViewGroup) view);
            }
            if (newView) {
                drawView.mWasMounted = true;
                if (animating(view) || withinBounds(commandArrayIndex)) {
                    this.mFlatViewGroup.addViewInLayout(view);
                } else {
                    clip(drawView.reactTag, view);
                }
            } else if (!drawView.mWasMounted) {
                drawView.mWasMounted = true;
                if (animating(view) || withinBounds(commandArrayIndex)) {
                    if (isClipped(drawView.reactTag)) {
                        this.mFlatViewGroup.addViewInLayout(view);
                        unclip(drawView.reactTag);
                    } else {
                        this.mFlatViewGroup.attachViewToParent(view);
                    }
                } else if (isNotClipped(drawView.reactTag)) {
                    this.mFlatViewGroup.removeDetachedView(view);
                    clip(drawView.reactTag, view);
                }
            } else if (isNotClipped(drawView.reactTag)) {
                this.mFlatViewGroup.attachViewToParent(view);
            }
        }
        for (int viewToDetach : viewsToDetach) {
            view = viewResolver.getView(viewToDetach);
            if (view.getParent() != null) {
                throw new RuntimeException("Trying to remove view not owned by FlatViewGroup");
            }
            this.mFlatViewGroup.removeDetachedView(view);
            unclip(viewToDetach);
        }
    }

    private static boolean animating(View view) {
        Animation animation = view.getAnimation();
        return (animation == null || animation.hasEnded()) ? false : true;
    }

    private boolean withinBounds(int i) {
        return this.mStart <= i && i < this.mStop;
    }

    public boolean updateClippingRect() {
        ReactClippingViewGroupHelper.calculateClippingRect(this.mFlatViewGroup, this.mClippingRect);
        if (this.mFlatViewGroup.getParent() == null || this.mClippingRect.top == this.mClippingRect.bottom) {
            return false;
        }
        int start = commandStartIndex();
        int stop = commandStopIndex(start);
        if (this.mStart > start || stop > this.mStop) {
            this.mStart = start;
            this.mStop = stop;
            updateClippingToCurrentRect();
            updateClippingRecursively();
            return true;
        }
        updateClippingRecursively();
        return false;
    }

    private void updateClippingRecursively() {
        int children = this.mClippingViewGroups.size();
        for (int i = 0; i < children; i++) {
            ReactClippingViewGroup view = (ReactClippingViewGroup) this.mClippingViewGroups.get(i);
            if (isNotClipped(((View) view).getId())) {
                view.updateClippingRect();
            }
        }
    }

    private void updateClippingToCurrentRect() {
        int i;
        int childIndex;
        int size = this.mFlatViewGroup.getChildCount();
        for (i = 0; i < size; i++) {
            View view = this.mFlatViewGroup.getChildAt(i);
            if (withinBounds(this.mDrawViewIndexMap.get(view.getId())) || animating(view)) {
                this.mViewsToKeep.add(view);
            } else {
                this.mViewsToRemove.append(i, view);
                clip(view.getId(), view);
            }
        }
        int removeSize = this.mViewsToRemove.size();
        boolean removeAll = removeSize > 2;
        if (!removeAll) {
            int removeSize2 = removeSize;
            while (true) {
                removeSize = removeSize2 - 1;
                if (removeSize2 <= 0) {
                    break;
                }
                this.mFlatViewGroup.removeViewsInLayout(this.mViewsToRemove.keyAt(removeSize), 1);
                removeSize2 = removeSize;
            }
        } else {
            this.mFlatViewGroup.detachAllViewsFromParent();
            for (i = 0; i < removeSize; i++) {
                this.mFlatViewGroup.removeDetachedView((View) this.mViewsToRemove.valueAt(i));
            }
        }
        this.mViewsToRemove.clear();
        int current = this.mStart;
        int childIndex2 = 0;
        size = this.mViewsToKeep.size();
        for (i = 0; i < size; i++) {
            view = (View) this.mViewsToKeep.get(i);
            int commandIndex = this.mDrawViewIndexMap.get(view.getId());
            if (current <= commandIndex) {
                childIndex = childIndex2;
                while (current != commandIndex) {
                    if (this.mDrawCommands[current] instanceof DrawView) {
                        DrawView drawView = this.mDrawCommands[current];
                        childIndex2 = childIndex + 1;
                        this.mFlatViewGroup.addViewInLayout((View) Assertions.assumeNotNull(this.mClippedSubviews.get(drawView.reactTag)), childIndex);
                        unclip(drawView.reactTag);
                    } else {
                        childIndex2 = childIndex;
                    }
                    current++;
                    childIndex = childIndex2;
                }
                current++;
                childIndex2 = childIndex;
            }
            if (removeAll) {
                this.mFlatViewGroup.attachViewToParent(view, childIndex2);
            }
            childIndex2++;
        }
        this.mViewsToKeep.clear();
        childIndex = childIndex2;
        while (current < this.mStop) {
            if (this.mDrawCommands[current] instanceof DrawView) {
                drawView = (DrawView) this.mDrawCommands[current];
                childIndex2 = childIndex + 1;
                this.mFlatViewGroup.addViewInLayout((View) Assertions.assumeNotNull(this.mClippedSubviews.get(drawView.reactTag)), childIndex);
                unclip(drawView.reactTag);
            } else {
                childIndex2 = childIndex;
            }
            current++;
            childIndex = childIndex2;
        }
    }

    public void getClippingRect(Rect outClippingRect) {
        outClippingRect.set(this.mClippingRect);
    }

    public SparseArray<View> getDetachedViews() {
        return this.mClippedSubviews;
    }

    public void draw(Canvas canvas) {
        int commandIndex = this.mStart;
        int size = this.mFlatViewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            int commandIndex2;
            int viewIndex = this.mDrawViewIndexMap.get(this.mFlatViewGroup.getChildAt(i).getId());
            if (this.mStop < viewIndex) {
                while (commandIndex < this.mStop) {
                    commandIndex2 = commandIndex + 1;
                    this.mDrawCommands[commandIndex].draw(this.mFlatViewGroup, canvas);
                    commandIndex = commandIndex2;
                }
            } else if (commandIndex <= viewIndex) {
                commandIndex2 = commandIndex;
                while (commandIndex2 < viewIndex) {
                    commandIndex = commandIndex2 + 1;
                    this.mDrawCommands[commandIndex2].draw(this.mFlatViewGroup, canvas);
                    commandIndex2 = commandIndex;
                }
                commandIndex = commandIndex2 + 1;
            }
            this.mDrawCommands[viewIndex].draw(this.mFlatViewGroup, canvas);
        }
        while (commandIndex < this.mStop) {
            commandIndex2 = commandIndex + 1;
            this.mDrawCommands[commandIndex].draw(this.mFlatViewGroup, canvas);
            commandIndex = commandIndex2;
        }
    }

    void debugDraw(Canvas canvas) {
        for (DrawCommand drawCommand : this.mDrawCommands) {
            if (!(drawCommand instanceof DrawView)) {
                drawCommand.debugDraw(this.mFlatViewGroup, canvas);
            } else if (isNotClipped(((DrawView) drawCommand).reactTag)) {
                drawCommand.debugDraw(this.mFlatViewGroup, canvas);
            }
        }
    }
}
