package com.facebook.react.views.modal;

import android.content.Context;
import android.view.MotionEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;

class ReactModalHostView$DialogRootViewGroup extends ReactViewGroup implements RootView {
    private final JSTouchDispatcher mJSTouchDispatcher = new JSTouchDispatcher(this);

    public ReactModalHostView$DialogRootViewGroup(Context context) {
        super(context);
    }

    protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount() > 0) {
            ((ReactContext) getContext()).runOnNativeModulesQueueThread(new Runnable() {
                public void run() {
                    ((UIManagerModule) ((ReactContext) ReactModalHostView$DialogRootViewGroup.this.getContext()).getNativeModule(UIManagerModule.class)).updateNodeSize(ReactModalHostView$DialogRootViewGroup.this.getChildAt(0).getId(), w, h);
                }
            });
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        this.mJSTouchDispatcher.handleTouchEvent(event, getEventDispatcher());
        return super.onInterceptTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.mJSTouchDispatcher.handleTouchEvent(event, getEventDispatcher());
        super.onTouchEvent(event);
        return true;
    }

    public void onChildStartedNativeGesture(MotionEvent androidEvent) {
        this.mJSTouchDispatcher.onChildStartedNativeGesture(androidEvent, getEventDispatcher());
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private EventDispatcher getEventDispatcher() {
        return ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }
}
