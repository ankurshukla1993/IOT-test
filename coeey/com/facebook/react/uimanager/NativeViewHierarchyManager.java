package com.facebook.react.uimanager;

import android.content.res.Resources;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.animation.Animation;
import com.facebook.react.animation.AnimationListener;
import com.facebook.react.animation.AnimationRegistry;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.google.common.primitives.Ints;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NativeViewHierarchyManager {
    private static final String TAG = NativeViewHierarchyManager.class.getSimpleName();
    private final AnimationRegistry mAnimationRegistry;
    private final JSResponderHandler mJSResponderHandler;
    private boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;

    private static class PopupMenuCallbackHandler implements OnMenuItemClickListener, OnDismissListener {
        boolean mConsumed;
        final Callback mSuccess;

        private PopupMenuCallbackHandler(Callback success) {
            this.mConsumed = false;
            this.mSuccess = success;
        }

        public void onDismiss(PopupMenu menu) {
            if (!this.mConsumed) {
                this.mSuccess.invoke(UIManagerModuleConstants.ACTION_DISMISSED);
                this.mConsumed = true;
            }
        }

        public boolean onMenuItemClick(MenuItem item) {
            if (this.mConsumed) {
                return false;
            }
            this.mSuccess.invoke(UIManagerModuleConstants.ACTION_ITEM_SELECTED, Integer.valueOf(item.getOrder()));
            this.mConsumed = true;
            return true;
        }
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagers) {
        this(viewManagers, new RootViewManager());
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagers, RootViewManager manager) {
        this.mJSResponderHandler = new JSResponderHandler();
        this.mLayoutAnimator = new LayoutAnimationController();
        this.mAnimationRegistry = new AnimationRegistry();
        this.mViewManagers = viewManagers;
        this.mTagsToViews = new SparseArray();
        this.mTagsToViewManagers = new SparseArray();
        this.mRootTags = new SparseBooleanArray();
        this.mRootViewManager = manager;
    }

    public final View resolveView(int tag) {
        View view = (View) this.mTagsToViews.get(tag);
        if (view != null) {
            return view;
        }
        throw new IllegalViewOperationException("Trying to resolve view with tag " + tag + " which doesn't exist");
    }

    public final ViewManager resolveViewManager(int tag) {
        ViewManager viewManager = (ViewManager) this.mTagsToViewManagers.get(tag);
        if (viewManager != null) {
            return viewManager;
        }
        throw new IllegalViewOperationException("ViewManager for tag " + tag + " could not be found");
    }

    public AnimationRegistry getAnimationRegistry() {
        return this.mAnimationRegistry;
    }

    public void setLayoutAnimationEnabled(boolean enabled) {
        this.mLayoutAnimationEnabled = enabled;
    }

    public void updateProperties(int tag, ReactStylesDiffMap props) {
        UiThreadUtil.assertOnUiThread();
        try {
            resolveViewManager(tag).updateProperties(resolveView(tag), props);
        } catch (IllegalViewOperationException e) {
            Log.e(TAG, "Unable to update properties for view tag " + tag, e);
        }
    }

    public void updateViewExtraData(int tag, Object extraData) {
        UiThreadUtil.assertOnUiThread();
        resolveViewManager(tag).updateExtraData(resolveView(tag), extraData);
    }

    public void updateLayout(int parentTag, int tag, int x, int y, int width, int height) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0, "NativeViewHierarchyManager_updateLayout").arg("parentTag", parentTag).arg(JobStorage.COLUMN_TAG, tag).flush();
        try {
            View viewToUpdate = resolveView(tag);
            viewToUpdate.measure(MeasureSpec.makeMeasureSpec(width, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(height, Ints.MAX_POWER_OF_TWO));
            if (this.mRootTags.get(parentTag)) {
                updateLayout(viewToUpdate, x, y, width, height);
            } else {
                ViewManager parentViewManager = (ViewManager) this.mTagsToViewManagers.get(parentTag);
                if (parentViewManager instanceof ViewGroupManager) {
                    ViewGroupManager parentViewGroupManager = (ViewGroupManager) parentViewManager;
                    if (!(parentViewGroupManager == null || parentViewGroupManager.needsCustomLayoutForChildren())) {
                        updateLayout(viewToUpdate, x, y, width, height);
                    }
                } else {
                    throw new IllegalViewOperationException("Trying to use view with tag " + tag + " as a parent, but its Manager doesn't extends ViewGroupManager");
                }
            }
            Systrace.endSection(0);
        } catch (Throwable th) {
            Systrace.endSection(0);
        }
    }

    private void updateLayout(View viewToUpdate, int x, int y, int width, int height) {
        if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(viewToUpdate)) {
            this.mLayoutAnimator.applyLayoutUpdate(viewToUpdate, x, y, width, height);
        } else {
            viewToUpdate.layout(x, y, x + width, y + height);
        }
    }

    public void createView(ThemedReactContext themedContext, int tag, String className, @Nullable ReactStylesDiffMap initialProps) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0, "NativeViewHierarchyManager_createView").arg(JobStorage.COLUMN_TAG, tag).arg("className", (Object) className).flush();
        try {
            ViewManager viewManager = this.mViewManagers.get(className);
            View view = viewManager.createView(themedContext, this.mJSResponderHandler);
            this.mTagsToViews.put(tag, view);
            this.mTagsToViewManagers.put(tag, viewManager);
            view.setId(tag);
            if (initialProps != null) {
                viewManager.updateProperties(view, initialProps);
            }
            Systrace.endSection(0);
        } catch (Throwable th) {
            Systrace.endSection(0);
        }
    }

    private static String constructManageChildrenErrorMessage(ViewGroup viewToManage, ViewGroupManager viewManager, @Nullable int[] indicesToRemove, @Nullable ViewAtIndex[] viewsToAdd, @Nullable int[] tagsToDelete) {
        int index;
        int innerOffset;
        StringBuilder stringBuilder = new StringBuilder();
        if (viewToManage != null) {
            stringBuilder.append("View tag:" + viewToManage.getId() + "\n");
            stringBuilder.append("  children(" + viewManager.getChildCount(viewToManage) + "): [\n");
            for (index = 0; index < viewManager.getChildCount(viewToManage); index += 16) {
                innerOffset = 0;
                while (index + innerOffset < viewManager.getChildCount(viewToManage) && innerOffset < 16) {
                    stringBuilder.append(viewManager.getChildAt(viewToManage, index + innerOffset).getId() + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (indicesToRemove != null) {
            stringBuilder.append("  indicesToRemove(" + indicesToRemove.length + "): [\n");
            for (index = 0; index < indicesToRemove.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < indicesToRemove.length && innerOffset < 16) {
                    stringBuilder.append(indicesToRemove[index + innerOffset] + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (viewsToAdd != null) {
            stringBuilder.append("  viewsToAdd(" + viewsToAdd.length + "): [\n");
            for (index = 0; index < viewsToAdd.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < viewsToAdd.length && innerOffset < 16) {
                    stringBuilder.append("[" + viewsToAdd[index + innerOffset].mIndex + "," + viewsToAdd[index + innerOffset].mTag + "],");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ],\n");
        }
        if (tagsToDelete != null) {
            stringBuilder.append("  tagsToDelete(" + tagsToDelete.length + "): [\n");
            for (index = 0; index < tagsToDelete.length; index += 16) {
                innerOffset = 0;
                while (index + innerOffset < tagsToDelete.length && innerOffset < 16) {
                    stringBuilder.append(tagsToDelete[index + innerOffset] + ",");
                    innerOffset++;
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append(" ]\n");
        }
        return stringBuilder.toString();
    }

    public void manageChildren(int tag, @Nullable int[] indicesToRemove, @Nullable ViewAtIndex[] viewsToAdd, @Nullable int[] tagsToDelete) {
        final ViewGroup viewToManage = (ViewGroup) this.mTagsToViews.get(tag);
        final ViewGroupManager viewManager = (ViewGroupManager) resolveViewManager(tag);
        if (viewToManage == null) {
            throw new IllegalViewOperationException("Trying to manageChildren view with tag " + tag + " which doesn't exist\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
        }
        int i;
        int lastIndexToRemove = viewManager.getChildCount(viewToManage);
        if (indicesToRemove != null) {
            i = indicesToRemove.length - 1;
            while (i >= 0) {
                int indexToRemove = indicesToRemove[i];
                if (indexToRemove < 0) {
                    throw new IllegalViewOperationException("Trying to remove a negative view index:" + indexToRemove + " view tag: " + tag + "\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else if (indexToRemove >= viewManager.getChildCount(viewToManage)) {
                    throw new IllegalViewOperationException("Trying to remove a view index above child count " + indexToRemove + " view tag: " + tag + "\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else if (indexToRemove >= lastIndexToRemove) {
                    throw new IllegalViewOperationException("Trying to remove an out of order view index:" + indexToRemove + " view tag: " + tag + "\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                } else {
                    View viewToRemove = viewManager.getChildAt(viewToManage, indexToRemove);
                    if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(viewToRemove)) {
                        if (arrayContains(tagsToDelete, viewToRemove.getId())) {
                            lastIndexToRemove = indexToRemove;
                            i--;
                        }
                    }
                    viewManager.removeViewAt(viewToManage, indexToRemove);
                    lastIndexToRemove = indexToRemove;
                    i--;
                }
            }
        }
        if (viewsToAdd != null) {
            for (ViewAtIndex viewAtIndex : viewsToAdd) {
                View viewToAdd = (View) this.mTagsToViews.get(viewAtIndex.mTag);
                if (viewToAdd == null) {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + viewAtIndex.mTag + "\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                }
                viewManager.addView(viewToManage, viewToAdd, viewAtIndex.mIndex);
            }
        }
        if (tagsToDelete != null) {
            for (int tagToDelete : tagsToDelete) {
                final View viewToDestroy = (View) this.mTagsToViews.get(tagToDelete);
                if (viewToDestroy == null) {
                    throw new IllegalViewOperationException("Trying to destroy unknown view tag: " + tagToDelete + "\n detail: " + constructManageChildrenErrorMessage(viewToManage, viewManager, indicesToRemove, viewsToAdd, tagsToDelete));
                }
                if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(viewToDestroy)) {
                    this.mLayoutAnimator.deleteView(viewToDestroy, new LayoutAnimationListener() {
                        public void onAnimationEnd() {
                            viewManager.removeView(viewToManage, viewToDestroy);
                            NativeViewHierarchyManager.this.dropView(viewToDestroy);
                        }
                    });
                } else {
                    dropView(viewToDestroy);
                }
            }
        }
    }

    private boolean arrayContains(@Nullable int[] array, int ele) {
        if (array == null) {
            return false;
        }
        for (int curEle : array) {
            if (curEle == ele) {
                return true;
            }
        }
        return false;
    }

    private static String constructSetChildrenErrorMessage(ViewGroup viewToManage, ViewGroupManager viewManager, ReadableArray childrenTags) {
        ViewAtIndex[] viewsToAdd = new ViewAtIndex[childrenTags.size()];
        for (int i = 0; i < childrenTags.size(); i++) {
            viewsToAdd[i] = new ViewAtIndex(childrenTags.getInt(i), i);
        }
        return constructManageChildrenErrorMessage(viewToManage, viewManager, null, viewsToAdd, null);
    }

    public void setChildren(int tag, ReadableArray childrenTags) {
        ViewGroup viewToManage = (ViewGroup) this.mTagsToViews.get(tag);
        ViewGroupManager viewManager = (ViewGroupManager) resolveViewManager(tag);
        for (int i = 0; i < childrenTags.size(); i++) {
            View viewToAdd = (View) this.mTagsToViews.get(childrenTags.getInt(i));
            if (viewToAdd == null) {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + childrenTags.getInt(i) + "\n detail: " + constructSetChildrenErrorMessage(viewToManage, viewManager, childrenTags));
            }
            viewManager.addView(viewToManage, viewToAdd, i);
        }
    }

    public void addRootView(int tag, SizeMonitoringFrameLayout view, ThemedReactContext themedContext) {
        addRootViewGroup(tag, view, themedContext);
    }

    protected final void addRootViewGroup(int tag, ViewGroup view, ThemedReactContext themedContext) {
        UiThreadUtil.assertOnUiThread();
        if (view.getId() != -1) {
            throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addMeasuredRootView.");
        }
        this.mTagsToViews.put(tag, view);
        this.mTagsToViewManagers.put(tag, this.mRootViewManager);
        this.mRootTags.put(tag, true);
        view.setId(tag);
    }

    protected void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(view.getId())) {
            resolveViewManager(view.getId()).onDropViewInstance(view);
        }
        ViewManager viewManager = (ViewManager) this.mTagsToViewManagers.get(view.getId());
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            for (int i = viewGroupManager.getChildCount(viewGroup) - 1; i >= 0; i--) {
                View child = viewGroupManager.getChildAt(viewGroup, i);
                if (this.mTagsToViews.get(child.getId()) != null) {
                    dropView(child);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.mTagsToViews.remove(view.getId());
        this.mTagsToViewManagers.remove(view.getId());
    }

    public void removeRootView(int rootViewTag) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(rootViewTag)) {
            SoftAssertions.assertUnreachable("View with tag " + rootViewTag + " is not registered as a root view");
        }
        dropView((View) this.mTagsToViews.get(rootViewTag));
        this.mRootTags.delete(rootViewTag);
    }

    public void measure(int tag, int[] outputBuffer) {
        UiThreadUtil.assertOnUiThread();
        View v = (View) this.mTagsToViews.get(tag);
        if (v == null) {
            throw new NoSuchNativeViewException("No native view for " + tag + " currently exists");
        }
        View rootView = (View) RootViewUtil.getRootView(v);
        if (rootView == null) {
            throw new NoSuchNativeViewException("Native view " + tag + " is no longer on screen");
        }
        rootView.getLocationInWindow(outputBuffer);
        int rootX = outputBuffer[0];
        int rootY = outputBuffer[1];
        v.getLocationInWindow(outputBuffer);
        outputBuffer[0] = outputBuffer[0] - rootX;
        outputBuffer[1] = outputBuffer[1] - rootY;
        outputBuffer[2] = v.getWidth();
        outputBuffer[3] = v.getHeight();
    }

    public void measureInWindow(int tag, int[] outputBuffer) {
        UiThreadUtil.assertOnUiThread();
        View v = (View) this.mTagsToViews.get(tag);
        if (v == null) {
            throw new NoSuchNativeViewException("No native view for " + tag + " currently exists");
        }
        v.getLocationOnScreen(outputBuffer);
        Resources resources = v.getContext().getResources();
        int statusBarId = resources.getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (statusBarId > 0) {
            outputBuffer[1] = outputBuffer[1] - ((int) resources.getDimension(statusBarId));
        }
        outputBuffer[2] = v.getWidth();
        outputBuffer[3] = v.getHeight();
    }

    public int findTargetTagForTouch(int reactTag, float touchX, float touchY) {
        View view = (View) this.mTagsToViews.get(reactTag);
        if (view != null) {
            return TouchTargetHelper.findTargetTagForTouch(touchX, touchY, (ViewGroup) view);
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + reactTag);
    }

    public void setJSResponder(int reactTag, int initialReactTag, boolean blockNativeResponder) {
        if (blockNativeResponder) {
            View view = (View) this.mTagsToViews.get(reactTag);
            if (initialReactTag == reactTag || !(view instanceof ViewParent)) {
                if (this.mRootTags.get(reactTag)) {
                    SoftAssertions.assertUnreachable("Cannot block native responder on " + reactTag + " that is a root view");
                }
                this.mJSResponderHandler.setJSResponder(initialReactTag, view.getParent());
                return;
            }
            this.mJSResponderHandler.setJSResponder(initialReactTag, (ViewParent) view);
            return;
        }
        this.mJSResponderHandler.setJSResponder(initialReactTag, null);
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    void configureLayoutAnimation(ReadableMap config) {
        this.mLayoutAnimator.initializeFromConfig(config);
    }

    void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }

    void startAnimationForNativeView(int reactTag, Animation animation, @Nullable final Callback animationCallback) {
        UiThreadUtil.assertOnUiThread();
        View view = (View) this.mTagsToViews.get(reactTag);
        final int animationId = animation.getAnimationID();
        if (view != null) {
            animation.setAnimationListener(new AnimationListener() {
                public void onFinished() {
                    Assertions.assertNotNull(NativeViewHierarchyManager.this.mAnimationRegistry.removeAnimation(animationId), "Animation was already removed somehow!");
                    if (animationCallback != null) {
                        animationCallback.invoke(Boolean.valueOf(true));
                    }
                }

                public void onCancel() {
                    Assertions.assertNotNull(NativeViewHierarchyManager.this.mAnimationRegistry.removeAnimation(animationId), "Animation was already removed somehow!");
                    if (animationCallback != null) {
                        animationCallback.invoke(Boolean.valueOf(false));
                    }
                }
            });
            animation.start(view);
            return;
        }
        throw new IllegalViewOperationException("View with tag " + reactTag + " not found");
    }

    public void dispatchCommand(int reactTag, int commandId, @Nullable ReadableArray args) {
        UiThreadUtil.assertOnUiThread();
        View view = (View) this.mTagsToViews.get(reactTag);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to send command to a non-existing view with tag " + reactTag);
        }
        resolveViewManager(reactTag).receiveCommand(view, commandId, args);
    }

    public void showPopupMenu(int reactTag, ReadableArray items, Callback success) {
        UiThreadUtil.assertOnUiThread();
        View anchor = (View) this.mTagsToViews.get(reactTag);
        if (anchor == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + reactTag);
        }
        PopupMenu popupMenu = new PopupMenu(getReactContextForView(reactTag), anchor);
        Menu menu = popupMenu.getMenu();
        for (int i = 0; i < items.size(); i++) {
            menu.add(0, 0, i, items.getString(i));
        }
        PopupMenuCallbackHandler handler = new PopupMenuCallbackHandler(success);
        popupMenu.setOnMenuItemClickListener(handler);
        popupMenu.setOnDismissListener(handler);
        popupMenu.show();
    }

    private ThemedReactContext getReactContextForView(int reactTag) {
        View view = (View) this.mTagsToViews.get(reactTag);
        if (view != null) {
            return (ThemedReactContext) view.getContext();
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + reactTag);
    }

    public void sendAccessibilityEvent(int tag, int eventType) {
        View view = (View) this.mTagsToViews.get(tag);
        if (view == null) {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + tag);
        }
        AccessibilityHelper.sendAccessibilityEvent(view, eventType);
    }
}
