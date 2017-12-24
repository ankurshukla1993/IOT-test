package com.facebook.drawee.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import javax.annotation.Nullable;

public class DraweeHolder<DH extends DraweeHierarchy> implements VisibilityCallback {
    private DraweeController mController = null;
    private final DraweeEventTracker mEventTracker = new DraweeEventTracker();
    private DH mHierarchy;
    private boolean mIsActivityStarted = true;
    private boolean mIsControllerAttached = false;
    private boolean mIsHolderAttached = false;
    private boolean mIsVisible = true;

    public static <DH extends DraweeHierarchy> DraweeHolder<DH> create(@Nullable DH hierarchy, Context context) {
        DraweeHolder<DH> holder = new DraweeHolder(hierarchy);
        holder.registerWithContext(context);
        return holder;
    }

    public void registerWithContext(Context context) {
    }

    public DraweeHolder(@Nullable DH hierarchy) {
        if (hierarchy != null) {
            setHierarchy(hierarchy);
        }
    }

    public void onAttach() {
        this.mEventTracker.recordEvent(Event.ON_HOLDER_ATTACH);
        this.mIsHolderAttached = true;
        attachOrDetachController();
    }

    public boolean isAttached() {
        return this.mIsHolderAttached;
    }

    public void onDetach() {
        this.mEventTracker.recordEvent(Event.ON_HOLDER_DETACH);
        this.mIsHolderAttached = false;
        attachOrDetachController();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mController == null) {
            return false;
        }
        return this.mController.onTouchEvent(event);
    }

    public void onVisibilityChange(boolean isVisible) {
        if (this.mIsVisible != isVisible) {
            this.mEventTracker.recordEvent(isVisible ? Event.ON_DRAWABLE_SHOW : Event.ON_DRAWABLE_HIDE);
            this.mIsVisible = isVisible;
            attachOrDetachController();
        }
    }

    public void onDraw() {
        if (!this.mIsControllerAttached) {
            FLog.wtf(DraweeEventTracker.class, "%x: Draw requested for a non-attached controller %x. %s", new Object[]{Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.mController)), toString()});
            this.mIsHolderAttached = true;
            this.mIsVisible = true;
            this.mIsActivityStarted = true;
            attachOrDetachController();
        }
    }

    private void setVisibilityCallback(@Nullable VisibilityCallback visibilityCallback) {
        Drawable drawable = getTopLevelDrawable();
        if (drawable instanceof VisibilityAwareDrawable) {
            ((VisibilityAwareDrawable) drawable).setVisibilityCallback(visibilityCallback);
        }
    }

    private void setActivityStarted(boolean isStarted) {
        this.mEventTracker.recordEvent(isStarted ? Event.ON_ACTIVITY_START : Event.ON_ACTIVITY_STOP);
        this.mIsActivityStarted = isStarted;
        attachOrDetachController();
    }

    public void setController(@Nullable DraweeController draweeController) {
        boolean wasAttached = this.mIsControllerAttached;
        if (wasAttached) {
            detachController();
        }
        if (this.mController != null) {
            this.mEventTracker.recordEvent(Event.ON_CLEAR_OLD_CONTROLLER);
            this.mController.setHierarchy(null);
        }
        this.mController = draweeController;
        if (this.mController != null) {
            this.mEventTracker.recordEvent(Event.ON_SET_CONTROLLER);
            this.mController.setHierarchy(this.mHierarchy);
        } else {
            this.mEventTracker.recordEvent(Event.ON_CLEAR_CONTROLLER);
        }
        if (wasAttached) {
            attachController();
        }
    }

    @Nullable
    public DraweeController getController() {
        return this.mController;
    }

    public void setHierarchy(DH hierarchy) {
        this.mEventTracker.recordEvent(Event.ON_SET_HIERARCHY);
        setVisibilityCallback(null);
        this.mHierarchy = (DraweeHierarchy) Preconditions.checkNotNull(hierarchy);
        Drawable drawable = this.mHierarchy.getTopLevelDrawable();
        boolean z = drawable == null || drawable.isVisible();
        onVisibilityChange(z);
        setVisibilityCallback(this);
        if (this.mController != null) {
            this.mController.setHierarchy(hierarchy);
        }
    }

    public DH getHierarchy() {
        return (DraweeHierarchy) Preconditions.checkNotNull(this.mHierarchy);
    }

    public boolean hasHierarchy() {
        return this.mHierarchy != null;
    }

    public Drawable getTopLevelDrawable() {
        return this.mHierarchy == null ? null : this.mHierarchy.getTopLevelDrawable();
    }

    protected DraweeEventTracker getDraweeEventTracker() {
        return this.mEventTracker;
    }

    private void attachController() {
        if (!this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(Event.ON_ATTACH_CONTROLLER);
            this.mIsControllerAttached = true;
            if (this.mController != null && this.mController.getHierarchy() != null) {
                this.mController.onAttach();
            }
        }
    }

    private void detachController() {
        if (this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
            this.mIsControllerAttached = false;
            if (this.mController != null) {
                this.mController.onDetach();
            }
        }
    }

    private void attachOrDetachController() {
        if (this.mIsHolderAttached && this.mIsVisible && this.mIsActivityStarted) {
            attachController();
        } else {
            detachController();
        }
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("controllerAttached", this.mIsControllerAttached).add("holderAttached", this.mIsHolderAttached).add("drawableVisible", this.mIsVisible).add("activityStarted", this.mIsActivityStarted).add("events", this.mEventTracker.toString()).toString();
    }
}
